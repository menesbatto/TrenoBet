package app.dao.tabelle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.EhOdds;
import app.dao.tabelle.entities.EventOdds;
import app.dao.tabelle.entities.Matcho;
import app.dao.tabelle.entities.ResultGoodness;
import app.dao.tabelle.entities.ResultGoodnessUo;
import app.dao.tabelle.entities.ResultGoodnessWdl;
import app.dao.tabelle.entities.UoOdds;
import app.dao.tabelle.entities._1X2Odds;
import app.dao.tipologiche.HomeVariationTypeDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;
import app.logic._1_matchesDownlaoder.model.EventOddsBean;
import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessBean;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessUoBean;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessWDLBean;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.model.UoLeaf;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;
import app.logic._1_matchesDownlaoder.model._1x2Leaf;
import app.utils.ChampEnum;

@Service
public class EventOddsDao {

	@Autowired
	private EventOddsRepo eventOddsRepo;

	@Autowired
	private UoThresholdTypeDao uoThresholdTypeDao;

	@Autowired
	private ChampDao champDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private MatchoDao matchDao;

	@Autowired
	private HomeVariationTypeDao homeVariationTypeDao;


	public void save(Map<TimeTypeEnum, ArrayList<EventOddsBean>> eventsOddsBeanMap, ChampEnum champEnum) {
		EventOdds ent;
		List<EventOdds> ents = new ArrayList<EventOdds>();
		List<Matcho> matches = new ArrayList<Matcho>();
		
		for (Entry<TimeTypeEnum, ArrayList<EventOddsBean>> entry : eventsOddsBeanMap.entrySet()) {
			TimeTypeEnum timeTypeBean = entry.getKey();
			ArrayList<EventOddsBean> eventsOddsBean = entry.getValue();
			for (EventOddsBean bean : eventsOddsBean) {
				ent = new EventOdds();
				
				ent.setHomeMotivation(bean.getHomeMotivation());
				ent.setAwayMotivation(bean.getAwayMotivation());
				ent.setHomeTrend(bean.getHomeTrend());
				ent.setAwayTrend(bean.getAwayTrend());
				
				TimeType timeType = timeTypeDao.findByValue(timeTypeBean.name());
				ent.setTimeType(timeType);
				
				Map<HomeVariationType, String> homeTrendEh = createTrendEhMapEnt(bean.getHomeTrendEh());
				ent.setHomeTrendEh(homeTrendEh);

				Map<HomeVariationType, String> awayTrendEh = createTrendEhMapEnt(bean.getAwayTrendEh());;
				ent.setAwayTrendEh(awayTrendEh);
				
				Map<UoThresholdType, String> homeTrendUo = createTrendUoMapEnt(bean.getHomeTrendUo());;
				ent.setHomeTrendUo(homeTrendUo);
				
				Map<UoThresholdType, String> awayTrendUo = createTrendUoMapEnt(bean.getAwayTrendUo());;;
				ent.setAwayTrendUo(awayTrendUo);
				
				ResultGoodness homeResultGoodness = createResultGoodnessEnt(bean.getHomeResultGoodness());
				ent.setHomeResultGoodness(homeResultGoodness);

				ResultGoodness awayResultGoodness = createResultGoodnessEnt(bean.getAwayResultGoodness());
				ent.setAwayResultGoodness(awayResultGoodness);
				
				ResultGoodness totalResultGoodness = createResultGoodnessEnt(bean.getTotalResultGoodness());
				ent.setTotalResultGoodness(totalResultGoodness);
				
				Matcho match =  findMatch(bean.getHomeTeam(), bean.getAwayTeam(), champEnum, matches);
				ent.setMatch(match);
				
//				match.getEventsOdds().add(ent); HHH
				ents.add(ent);
			
			}
		}
		
//		matchDao.saveAll(matches);
		eventOddsRepo.save(ents);
		
	}

	
	private Matcho findMatch( String homeTeam, String awayTeam, ChampEnum champEnum, List<Matcho> matches) {
		for (Matcho m : matches)
			if (m.getHomeTeam().getName().equals(homeTeam))
				if (m.getAwayTeam().getName().equals(awayTeam))
					return m;
			
		Matcho match = matchDao.findByTeamAndChamp(homeTeam, awayTeam, champEnum);
//		match.setEventsOdds(new ArrayList<EventOdds>()); HHH
		matches.add(match);
		return match;
	}
	

	private ResultGoodness createResultGoodnessEnt(ResultGoodnessBean bean) {
		ResultGoodness ent = new ResultGoodness();
		if (bean != null) {
			List<ResultGoodnessWdl> eh = createResultGoodnessEhEnt(bean.getEhGoodness());
			ent.setEh(eh);
			
			List<ResultGoodnessUo> uo = createResultGoodnessUoEnt(bean.getUoGoodness());
			ent.setUo(uo);
			
	//		EventOdds eventOdds = 
	//		ent.setEventOdds(eventOdds);
			
			ResultGoodnessWdl winClean = createResultGoodnessWdlEnt(bean.getWinClean());
			ent.setWinClean(winClean);
	
			ResultGoodnessWdl winFinal = createResultGoodnessWdlEnt(bean.getWinFinal());
			ent.setWinFinal(winFinal);
			
			ResultGoodnessWdl winTrend = createResultGoodnessWdlEnt(bean.getWinTrend());
			ent.setWinTrend(winTrend);
			
			ResultGoodnessWdl winMotivation = createResultGoodnessWdlEnt(bean.getWinMotivation());
			ent.setWinMotivation(winMotivation);
		}
		return ent;
	}

	private List<ResultGoodnessWdl> createResultGoodnessEhEnt(Map<HomeVariationEnum, ResultGoodnessWDLBean> mapBean) {
		List<ResultGoodnessWdl> listEnt = new ArrayList<ResultGoodnessWdl>();
		if (mapBean!= null) {
			ResultGoodnessWdl ent;
			for (Entry<HomeVariationEnum, ResultGoodnessWDLBean> entry : mapBean.entrySet()) {
				ResultGoodnessWDLBean bean = entry.getValue();
				
				HomeVariationEnum key = entry.getKey();

				bean.setHomeVariationType(key);
				ent = createResultGoodnessWdlEnt(bean);

				listEnt.add(ent);
			}
		}
			
		return listEnt;
	}

	private List<ResultGoodnessUo> createResultGoodnessUoEnt(Map<UoThresholdEnum, ResultGoodnessUoBean> mapBean) {
		List<ResultGoodnessUo> listEnt = new ArrayList<ResultGoodnessUo>();
		if (mapBean!= null) {
			ResultGoodnessUo ent;
			for (Entry<UoThresholdEnum, ResultGoodnessUoBean> entry : mapBean.entrySet()) {
				ResultGoodnessUoBean bean = entry.getValue();
				
				ent = new ResultGoodnessUo();
				ent.setGoodnessU(bean.getGoodnessU());
				ent.setGoodnessO(bean.getGoodnessO());
				UoThresholdEnum key = entry.getKey();
				UoThresholdType thresholdEnt = uoThresholdTypeDao.findByValue(key.name());
				ent.setThreshold(thresholdEnt);
				
				listEnt.add(ent);
			}
		}
			
		return listEnt;
	}

	private ResultGoodnessWdl createResultGoodnessWdlEnt(ResultGoodnessWDLBean bean) {
		ResultGoodnessWdl ent = new ResultGoodnessWdl();
		if (bean != null) {
			ent.setGoodnessW(bean.getGoodnessW());
			ent.setGoodnessD(bean.getGoodnessD());
			ent.setGoodnessL(bean.getGoodnessL());
			
			if (bean.getHomeVariationType() != null) {
				HomeVariationEnum homeVariationEnum = bean.getHomeVariationType();
				HomeVariationType homeVariationType = homeVariationTypeDao.findByValue(homeVariationEnum.name());
				ent.setHomeVariationType(homeVariationType);
		
			}
		}
		
		return ent;
	}

	private Map<UoThresholdType, String> createTrendUoMapEnt(Map<UoThresholdEnum, String> beanMap) {
		Map<UoThresholdType, String> entMap = new HashMap<UoThresholdType, String>();
		if (beanMap != null) {
			for (Entry<UoThresholdEnum, String> entry : beanMap.entrySet()) {
				UoThresholdEnum key = entry.getKey();
				UoThresholdType homVarEnt = uoThresholdTypeDao.findByValue(key.name());
				String value = entry.getValue();
				entMap.put(homVarEnt,value);
			}
		}
		
		
		
		return entMap;
	}

	private Map<HomeVariationType, String> createTrendEhMapEnt(Map<HomeVariationEnum, String> beanMap) {
		Map<HomeVariationType, String> entMap = new HashMap<HomeVariationType, String>();
		if (beanMap!= null) {
			for (Entry<HomeVariationEnum, String> entry : beanMap.entrySet()) {
				HomeVariationEnum key = entry.getKey();
				HomeVariationType homVarEnt = homeVariationTypeDao.findByValue(key.name());
				String value = entry.getValue();
				entMap.put(homVarEnt,value);
			}
		}
		
		return entMap;
	}

	public EventOddsBean createEventOddsBean(EventOdds ent) {
		EventOddsBean bean = new EventOddsBean();
		
		bean.setDate(ent.getMatch().getMatchDate());
		TimeTypeEnum timeTypeBean = timeTypeDao.findBeanByEnt(ent.getTimeType());
		bean.setTimeType(timeTypeBean);
		
		
		
		ResultGoodnessBean homeResultGoodness = createResultGoodnessBean(ent.getHomeResultGoodness());
		bean.setHomeResultGoodness(homeResultGoodness);
		ResultGoodnessBean awayResultGoodness = createResultGoodnessBean(ent.getAwayResultGoodness());
		bean.setAwayResultGoodness(awayResultGoodness);
		ResultGoodnessBean totalResultGoodness =  createResultGoodnessBean(ent.getTotalResultGoodness());
		bean.setTotalResultGoodness(totalResultGoodness);
		
		
		//Quote
		
		//Quote 1X2
		_1X2Odds odds = getOddsByTime(ent.getMatch().get_1X2(), ent.getTimeType());
		
		bean.setOddsH(odds.get_1());
		bean.setOddsD(odds.get_X());
		bean.setOddsA(odds.get_2());
		
		//Quote Eh
		Map<HomeVariationEnum, _1x2Leaf> ehOddsMap = createEhOddsMapBean(ent.getMatch().getEh(), ent.getTimeType());
		bean.setEhOddsMap(ehOddsMap);
		
		//Quote Uo
		Map<UoThresholdEnum, UoLeaf> uoOddsMap = createUoOddsMapBean(ent.getMatch().getUo(), ent.getTimeType()); ;
		bean.setUoOddsMap(uoOddsMap );
		
		
		
		
		// Trend
		
		//Trend 1x2
		bean.setHomeTrend(ent.getHomeTrend());
		bean.setAwayTrend(ent.getAwayTrend());
		
		
		//Trend Eh
		Map<HomeVariationEnum, String> homeTrendEh = createTrendEhMapBean(ent.getHomeTrendEh());
		bean.setHomeTrendEh(homeTrendEh);
		
		Map<HomeVariationEnum, String> awayTrendEh = createTrendEhMapBean(ent.getAwayTrendEh());
		bean.setAwayTrendEh(awayTrendEh);
		
		
		//Trend Uo
		Map<UoThresholdEnum, String> homeTrendUo = createTrendUoMapBean(ent.getHomeTrendUo());
		bean.setHomeTrendUo(homeTrendUo);

		Map<UoThresholdEnum, String> awayTrendUo = createTrendUoMapBean(ent.getAwayTrendUo());
		bean.setAwayTrendUo(awayTrendUo);
		
		
		bean.setHomeMotivation(ent.getHomeMotivation());
		bean.setHomeTeam(ent.getMatch().getHomeTeam().getName());
		
		
		bean.setAwayMotivation(ent.getAwayMotivation());
		bean.setAwayTeam(ent.getMatch().getAwayTeam().getName());
	
		
//		bean.setWinOdds(winOdds);
//		bean.setBetType(betType);
//		bean.setMatchResult(matchResult);
		
		return bean;
	}

	private _1X2Odds getOddsByTime(List<_1X2Odds> list, TimeType timeType) {
		for (_1X2Odds odds : list)
			if (odds.getTimeType().equals(timeType))
					return odds;
		return null;
	}

	private Map<UoThresholdEnum, UoLeaf> createUoOddsMapBean(List<UoOdds> uoOddsList, TimeType timeType) {
		Map<UoThresholdEnum, UoLeaf>  beanMap = new HashMap<UoThresholdEnum, UoLeaf> ();
		
		if (uoOddsList != null) {
			for (UoOdds uoOdds : uoOddsList) {
				if (uoOdds.getTimeType().equals(timeType)) {
					UoThresholdType thresholdEnt = uoOdds.getThresholdType();
					UoThresholdEnum thresholdBean = uoThresholdTypeDao.findBeanByEnt(thresholdEnt);
					UoLeaf uoLeaf = new UoLeaf(uoOdds.getU(), uoOdds.getO());
					beanMap.put(thresholdBean, uoLeaf);
				}
			}
		}
		
		
		
		return beanMap;
	}

	private Map<HomeVariationEnum, _1x2Leaf> createEhOddsMapBean(List<EhOdds> ehList, TimeType timeType) {
		Map<HomeVariationEnum, _1x2Leaf>  beanMap = new HashMap<HomeVariationEnum, _1x2Leaf> ();
		
		if (ehList != null) {
			for (EhOdds ehOdds : ehList) {
				if (ehOdds.getTimeType().equals(timeType)) {
					HomeVariationType homeVariationEnt = ehOdds.getHomeVariationType();
					HomeVariationEnum homeVariationBean = homeVariationTypeDao.findBeanByEnt(homeVariationEnt);
					_1x2Leaf _1x2Leaf = new _1x2Leaf(ehOdds.get_1(), ehOdds.get_X(), ehOdds.get_2());
					beanMap.put(homeVariationBean, _1x2Leaf);
				}
			}
		}
		
		return beanMap;
	}

	private ResultGoodnessBean createResultGoodnessBean(ResultGoodness ent) {
		ResultGoodnessBean bean = new ResultGoodnessBean();
		
		Map<HomeVariationEnum, ResultGoodnessWDLBean> eh = createResultGoodnessEhBean(ent.getEh());
		bean.setEhGoodness(eh);
		
		Map<UoThresholdEnum, ResultGoodnessUoBean> uo = createResultGoodnessUoBean(ent.getUo());
		bean.setUoGoodness(uo);
		
//		EventOdds eventOdds = 
//		ent.setEventOdds(eventOdds);
		
		ResultGoodnessWDLBean winClean = createResultGoodnessWdlBean(ent.getWinClean());
		bean.setWinClean(winClean);

		ResultGoodnessWDLBean winFinal = createResultGoodnessWdlBean(ent.getWinFinal());
		bean.setWinFinal(winFinal);
		
		ResultGoodnessWDLBean winTrend = createResultGoodnessWdlBean(ent.getWinTrend());
		bean.setWinTrend(winTrend);
		
		ResultGoodnessWDLBean winMotivation = createResultGoodnessWdlBean(ent.getWinMotivation());
		bean.setWinMotivation(winMotivation);
		
		return bean;
	}

	private ResultGoodnessWDLBean createResultGoodnessWdlBean(ResultGoodnessWdl ent) {
		ResultGoodnessWDLBean bean = new ResultGoodnessWDLBean();
		if (ent != null) {
			bean.setGoodnessW(ent.getGoodnessW());
			bean.setGoodnessD(ent.getGoodnessD());
			bean.setGoodnessL(ent.getGoodnessL());
			
			if (ent.getHomeVariationType() != null) {
				HomeVariationType homeVariationType = ent.getHomeVariationType();
				HomeVariationEnum homeVariationEnum = homeVariationTypeDao.findBeanByEnt(homeVariationType);
				bean.setHomeVariationType(homeVariationEnum);
		
			}
		}
		
		return bean;
	}

	private Map<UoThresholdEnum, ResultGoodnessUoBean> createResultGoodnessUoBean(List<ResultGoodnessUo> uoList) {
		Map<UoThresholdEnum, ResultGoodnessUoBean> uoMap = new HashMap<UoThresholdEnum, ResultGoodnessUoBean>();
		
		if (uoList!= null) {
			ResultGoodnessUoBean bean;
			for (ResultGoodnessUo ent : uoList) {
				bean = new ResultGoodnessUoBean();
				bean.setGoodnessU(ent.getGoodnessU());
				bean.setGoodnessO(ent.getGoodnessO());
				
				UoThresholdType key = ent.getThreshold();
				UoThresholdEnum thresholdBean = uoThresholdTypeDao.findBeanByEnt(key);
				
				uoMap.put(thresholdBean, bean);
				
			}
		}
			
		return uoMap;
	}

	private Map<HomeVariationEnum, ResultGoodnessWDLBean> createResultGoodnessEhBean(List<ResultGoodnessWdl> ehList) {
		Map<HomeVariationEnum, ResultGoodnessWDLBean> mapBean = new HashMap<HomeVariationEnum, ResultGoodnessWDLBean>();
		if (ehList != null) {
			ResultGoodnessWDLBean bean;
			for (ResultGoodnessWdl ent : ehList) {
				bean = createResultGoodnessWdlBean(ent);
				HomeVariationType homeVariationEnt = ent.getHomeVariationType();
				HomeVariationEnum homeVariationBean = homeVariationTypeDao.findBeanByEnt(homeVariationEnt);
				mapBean.put(homeVariationBean, bean);
			}
		}
			
		return mapBean;
	}

	private Map<UoThresholdEnum, String> createTrendUoMapBean(Map<UoThresholdType, String> trendUoMap) {
		Map<UoThresholdEnum, String> beanMap = new HashMap<UoThresholdEnum, String>();
		if (trendUoMap != null) {
			for (Entry<UoThresholdType, String> entry : trendUoMap.entrySet()) {
				UoThresholdType key = entry.getKey();
				UoThresholdEnum homVarEnt = uoThresholdTypeDao.findBeanByEnt(key);
				String value = entry.getValue();
				beanMap.put(homVarEnt,value);
			}
		}
		
		return beanMap;
	}

	private Map<HomeVariationEnum, String> createTrendEhMapBean(Map<HomeVariationType, String> trendEhMap) {
		Map<HomeVariationEnum, String> beanMap = new HashMap<HomeVariationEnum, String>();
		if (trendEhMap != null) {
			for (Entry<HomeVariationType, String> entry : trendEhMap.entrySet()) {
				HomeVariationType key = entry.getKey();
				HomeVariationEnum homVarEnt = homeVariationTypeDao.findBeanByEnt(key);
				String value = entry.getValue();
				beanMap.put(homVarEnt,value);
			}
		}
		
		return beanMap;
	}

	public List<EventOddsBean> getNextEventsOdds(ChampEnum champEnum) {
		List<EventOddsBean> beans = new ArrayList<EventOddsBean>();
		Champ champ = champDao.findByChampEnum(champEnum);
		List<EventOdds> eventsOddsEnts = eventOddsRepo.findByMatchChamp(champ);
		EventOddsBean bean;
		for (EventOdds ent : eventsOddsEnts) { // ce ne sono 3 una per ogni tempo _1, _2, final qui mi arrivano gli eh tutti a null
			bean = createEventOddsBean(ent);
			beans.add(bean);
		}
		
		return beans;
	}

	

	public void removeByChamp(Champ champ) {
		eventOddsRepo.deleteByMatchChamp(champ);
		
	}

    @Transactional

	public void removeByMatchId(Integer idMatch) {
		eventOddsRepo.deleteByMatchId(idMatch);
		
	}

}
