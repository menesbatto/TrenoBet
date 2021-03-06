package app.dao.tabelle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Team;
import app.dao.tabelle.entities.WinRangeStats;
import app.dao.tipologiche.HomeVariationTypeDao;
import app.dao.tipologiche.OddsRangeDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.OddsRange;
import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._2_matchResultAnalyzer.model.WinRangeStatsBean;
import app.utils.ChampEnum;
import ma.glasnost.orika.MapperFacade;

@Service
@EnableCaching
public class WinRangeStatsDao {

	@Autowired
	private WinRangeStatsRepo winRangeStatsRepo;

	@Autowired
	private OddsRangeDao oddsRangeDao;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private ChampDao champDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private MapperFacade mapper;
	
	@Autowired
	private HomeVariationTypeDao homeVariationTypeDao;
	
	
	@Cacheable("winRangeStats")
	public List<WinRangeStatsBean> findByChampInSeasonDay(ChampEnum champEnum, Integer seasonDay) {
		Champ champ = champDao.findByChampEnum(champEnum);
		List<WinRangeStats> ents = winRangeStatsRepo.findByTeamChampAndSeasonDayOrderByTeam(champ, seasonDay);
		List<WinRangeStatsBean> beans = new ArrayList<WinRangeStatsBean>();
		WinRangeStatsBean bean;
		HomeVariationType homeVariation;
		TimeTypeEnum timeTypeBean;
		for (WinRangeStats ent : ents) {
			bean = new WinRangeStatsBean();
			mapper.map(ent, bean);
			
			homeVariation = ent.getHomeVariation();
			if (homeVariation!= null) {
				HomeVariationEnum homeVariationBean = homeVariationTypeDao.findBeanByEnt(homeVariation);
				bean.setHomeVariationBean(homeVariationBean);
			}
			if (ent.getRange() != null) {
				bean.setRange(ent.getRange().getValueDown() + "-" + ent.getRange().getValueUp());
				bean.setEdgeDown(ent.getRange().getValueDown());
				bean.setEdgeUp(ent.getRange().getValueUp());
			}
			bean.setTeamName(ent.getTeam().getName());
			timeTypeBean = timeTypeDao.findBeanByEnt(ent.getTimeType());
			bean.setTimeTypeBean(timeTypeBean);
			bean.setPlayingField(ent.getPlayingField());
			beans.add(bean);
		}
		
		return beans;
		
	}

	
	public Boolean existStatsByChampInSeasonDay(ChampEnum champEnum, Integer seasonDay) {
		Champ champ = champDao.findByChampEnum(champEnum);
		WinRangeStats firstStats = winRangeStatsRepo.findFirstByTeamChampAndSeasonDay(champ , seasonDay);
		boolean seasonDayStatsAlreadyCalculated = firstStats != null;
		return seasonDayStatsAlreadyCalculated;
	}
	

	public List<WinRangeStats> initWinRangeStatsForTeam(Team team, TimeType timeType, String playingField) {
		List<WinRangeStats> winRangeStatsList = new ArrayList<WinRangeStats>();
		List<OddsRange> oddsRangeList = oddsRangeDao.findAll();
		initSingleWinRangeStatsForTeam(team, winRangeStatsList, oddsRangeList, playingField,  timeType);
		//winRangeStatsRepo.save(winRangeStatsList);
		return winRangeStatsList;
	}



	private void initSingleWinRangeStatsForTeam(Team team, List<WinRangeStats> winRangeStatsList, List<OddsRange> oddsRangeList, String playingField, TimeType timeType) {
		WinRangeStats winRange;
		for(OddsRange range: oddsRangeList) {
			winRange = new WinRangeStats(range, team);
			winRange.setTimeType(timeType);
			winRange.setPlayingField(playingField);
			
			winRangeStatsList.add(winRange);			
		}
		winRange = new WinRangeStats();
		winRange.setTeam(team);
		winRange.setTimeType(timeType);
		winRange.setPlayingField(playingField);
		winRangeStatsList.add(winRange);		
	}

	

	public void saveWinRangeStats(List<WinRangeStats> allWinRangeStats) {
		winRangeStatsRepo.save(allWinRangeStats);
	}

	public List<WinRangeStats> createWinRangesToSave(List<WinRangeStatsBean> listBean, String teamName, ChampEnum champEnum,
			String playingField, HomeVariationEnum homeVariationEnum) {
		List<TimeType> timeTypes = timeTypeDao.findAll();
		Champ champ = champDao.findByChampEnum(champEnum);
		Team teamEnt = teamDao.findByNameAndChamp(teamName, champ);

		List<WinRangeStats> allWinRangeStats = new ArrayList<WinRangeStats>();
		for (TimeType timeType : timeTypes) {
			TimeTypeEnum timeTypeBean = timeTypeDao.findBeanByEnt(timeType);
//			
//			List<WinRangeStats> existingWinRangeStats = winRangeStatsRepo.findByTeamAndTimeTypeAndPlayingField(teamEnt,timeType, playingField);
			
//			if (existingWinRangeStats.isEmpty()){ // ci entra soltanto quando viene creata la statTotal (indipendente dal tempo) 
			List<WinRangeStats> winRangeStatsByTime = initWinRangeStatsForTeam(teamEnt, timeType, playingField);
//			}
			
			for (WinRangeStatsBean bean : listBean) {
				for (WinRangeStats ent : winRangeStatsByTime) {
					if (bean.getTimeTypeBean().equals(timeTypeBean)) {
						if ( bean.getEdgeUp() == null && ent.getRange() == null 		|| 			
							bean.getEdgeUp() != null && ent.getRange() != null && bean.getEdgeUp().equals(ent.getRange().getValueUp())) {
							mapper.map(bean, ent);
							ent.setPlayingField(playingField);
							if (homeVariationEnum != null) {
								HomeVariationType homeVariationEnt = homeVariationTypeDao.findByValue(homeVariationEnum.name());
								ent.setHomeVariation(homeVariationEnt);
							}
						}
						
					}
				}
			}
			allWinRangeStats.addAll(winRangeStatsByTime);
			
		}
		
		return allWinRangeStats;
	}


	
	
	
	
	
	
	//#######################################################################################################################
	
	@Deprecated
	public List<WinRangeStats> saveWinRangeStats(List<WinRangeStatsBean> listBean, String teamName, ChampEnum champEnum, String playingField, HomeVariationEnum homeVariationEnum) {
		List<WinRangeStats> allWinRangeStats = createWinRangesToSave(listBean, teamName, champEnum, playingField, homeVariationEnum);
		
		winRangeStatsRepo.save(allWinRangeStats);
		return allWinRangeStats;
	}
	
	@Deprecated
	public ArrayList<WinRangeStatsBean> findByTeamNameAndChampAndTimeTypeAndPlayingField(String teamName, ChampEnum champEnum, TimeTypeEnum timeTypeEnum, String playingField) {
		Champ champ = champDao.findByChampEnum(champEnum);
		Team team = teamDao.findByNameAndChamp(teamName, champ);
		TimeType timeType = timeTypeDao.findByValue(timeTypeEnum.name());
		List<WinRangeStats> listEnt = winRangeStatsRepo.findByTeamAndTimeTypeAndPlayingField(team, timeType, playingField);
		if (listEnt.isEmpty()){
			listEnt = initWinRangeStatsForTeam(team, timeType, playingField);
		}
		
		
		ArrayList<WinRangeStatsBean> listBean = new ArrayList<WinRangeStatsBean>();
		for (WinRangeStats ent : listEnt) {
			WinRangeStatsBean bean = new WinRangeStatsBean();
			if (ent.getTotal() != null)	// Per non sovrascrivere gli 0 con i null provenienti da DB, in caso metti diretto gli 0 a DB
				mapper.map(ent, bean);
			
			if (ent.getRange()!= null) {
				bean.setRange(ent.getRange().getValueDown() + "-" + ent.getRange().getValueUp());
				bean.setEdgeDown(ent.getRange().getValueDown());
				bean.setEdgeUp(ent.getRange().getValueUp());
			}
			bean.setTeamName(ent.getTeam().getName());
			
			listBean.add(bean);
		}

		return listBean;
	}
	
	
	@Deprecated
	public void calculateWinStatsNoPlayingField(String teamName, ChampEnum champEnum) {
		ArrayList<WinRangeStatsBean> totalStats = new ArrayList<WinRangeStatsBean>(); 
		
		for (TimeTypeEnum timeTypeEnum : timeTypeDao.findAllTimeTypeEnum()) {
			ArrayList<WinRangeStatsBean> homeStats = findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champEnum, timeTypeEnum, "A");
			ArrayList<WinRangeStatsBean> awayStats = findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champEnum, timeTypeEnum, "H");
			for (WinRangeStatsBean h : homeStats) {
				for (WinRangeStatsBean a : awayStats) {
					if (h.getRange() == null && h.getRange() == null ||  
							h.getRange().equals(a.getRange())) {
					
						WinRangeStatsBean t = new WinRangeStatsBean();

						t.setEdgeDown(h.getEdgeDown());
						t.setEdgeUp(h.getEdgeUp());
						t.setRange(h.getRange());
						t.setTeamName(h.getTeamName());
						t.setTotal(h.getTotal() + a.getTotal());

						//Informazioni che non hanno senso
//						t.setAwayHits(h.getAwayHits() + a.getAwayHits());
//						t.setHomeHits(h.getHomeHits() + a.getHomeHits());
						
//						t.setAwayMisses(h.getAwayMisses() + a.getAwayMisses());
//						t.setHomeMisses(h.getHomeMisses() + a.getHomeMisses());
						
//						t.setDrawHits(h.getDrawHits() + a.getDrawHits());
//						t.setDrawMisses(h.getDrawMisses() + a.getDrawMisses());
						
						double total = h.getTotal().doubleValue() + a.getTotal().doubleValue();
						if (total!=0) {
							t.setWinPerc((h.getHomeHits() + a.getAwayHits()) / total);
							t.setDrawPerc((h.getDrawHits() + a.getDrawHits()) / total);
							t.setLosePerc((h.getAwayHits() + a.getHomeHits()) / total);
						}
						
						t.setTimeTypeBean(timeTypeEnum);
						totalStats.add(t);
						
					}
				}
			}
		}
		saveWinRangeStats(totalStats, teamName, champEnum, "T", null);
	}

	@Deprecated
	public List<WinRangeStats> calculateWinStatsNoPlayingField(List<WinRangeStats> homeWinStats, List<WinRangeStats> awayWinStats) {
		WinRangeStats h;
		WinRangeStats a;
		WinRangeStats t;

		List<WinRangeStats> totalWinStats = new ArrayList<WinRangeStats>();
		
		for (int i = 0; i < homeWinStats.size(); i++) {
			h = homeWinStats.get(i);
			a = awayWinStats.get(i);
			
			t = new WinRangeStats();

			t.setHomeVariation(h.getHomeVariation());
			t.setPlayingField("T");
			t.setRange(h.getRange());
			t.setTimeType(h.getTimeType());
			t.setTeam(h.getTeam());
			
		
			t.setTotal(h.getTotal() + a.getTotal());
			
			double total = h.getTotal().doubleValue() + a.getTotal().doubleValue();
			if (total!=0) {
				t.setWinPerc((h.getHomeHits() + a.getAwayHits()) / total);
				t.setDrawPerc((h.getDrawHits() + a.getDrawHits()) / total);
				t.setLosePerc((h.getAwayHits() + a.getHomeHits()) / total);
			}
			
			
			totalWinStats.add(t);
			
		}
		
		
		return totalWinStats;
	}


	public void deleteWinRangeStatsByChampId(Integer champId) {
		ChampEnum champEnum = champDao.findChampEnumById(champId);
		Champ champ = champDao.findByChampEnum(champEnum );
		winRangeStatsRepo.deleteByTeamChamp(champ);
	}


}