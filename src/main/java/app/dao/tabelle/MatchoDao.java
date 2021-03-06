package app.dao.tabelle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.EhOdds;
import app.dao.tabelle.entities.Matcho;
import app.dao.tabelle.entities.Team;
import app.dao.tabelle.entities.UoOdds;
import app.dao.tabelle.entities._1X2Odds;
import app.dao.tipologiche.BetHouseDao;
import app.dao.tipologiche.HomeVariationTypeDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;
import app.logic._1_matchesDownlaoder.model.BetHouseEnum;
import app.logic._1_matchesDownlaoder.model.EhTimeType;
import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.model.UoFull;
import app.logic._1_matchesDownlaoder.model.UoLeaf;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;
import app.logic._1_matchesDownlaoder.model.UoTimeType;
import app.logic._1_matchesDownlaoder.model._1x2Full;
import app.logic._1_matchesDownlaoder.model._1x2Leaf;
import app.utils.AppConstants;
import app.utils.ChampEnum;
import ma.glasnost.orika.MapperFacade;

@Service
@EnableCaching
public class MatchoDao {

	@Autowired
	private MapperFacade mapper;
	
	@Autowired
	private MatchoRepo matchRepo;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private ChampDao champDao;

	@Autowired
	private BetHouseDao betHouseDao;

	@Autowired
	private TimeTypeDao timeTypeDao;
	
	@Autowired
	private HomeVariationTypeDao homeVariationTypeDao;
	
	@Autowired
	private UoThresholdTypeDao uoThresholdTypeDao;
	
	@Autowired
	private EventOddsDao eventOddsDao;
	

	public void initTable() {
		
	}

	Map<Integer, Matcho> cache = new HashMap<Integer, Matcho>();
	
	@Cacheable("match")
	public Matcho findById(Integer id) {
//		Matcho m = cache.get(id);
//		if (m == null) {
//			m = matchRepo.findById(id);
//			cache.put(id, m);
//		}
		Matcho m = matchRepo.findById(id);
		return m;
	}

	
	public Matcho findByHomeTeamNameAndAwayTeamName(String homeTeam, String awayTeam) {
		Matcho m = matchRepo.findByHomeTeamNameAndAwayTeamName(homeTeam, awayTeam);
		List<Matcho> matches = new ArrayList<Matcho>();
		matches.add(m);
		return m;
	}
	
	
	public MatchResult save(MatchResult bean) {
		Matcho ent = null;

		if (bean.getId() != 0) 
			ent = matchRepo.findById(bean.getId());				// In aggiornamento
		else 
			ent =  new Matcho();								// Nuovo
		
		Champ champEnt = champDao.findByChampEnum(bean.getChamp());
		ent.setChamp(champEnt);
		
		Team teamHome = teamDao.findByNameAndChamp(bean.getHomeTeam(), champEnt);
		ent.setHomeTeam(teamHome);

		Team awayHome = teamDao.findByNameAndChamp(bean.getAwayTeam(), champEnt);
		ent.setAwayTeam(awayHome);
		
		ent.setFullTimeResult(bean.getFTR());

		ent.setHalfTimeResult(bean.getHTR());
		
		ent.setFullTimeHomeGoals(bean.getFTHG());
		ent.setFullTimeAwayGoals(bean.getFTAG());
		ent.setHalfTimeHomeGoals(bean.getHTHG());
		ent.setHalfTimeAwayGoals(bean.getHTAG());
		ent.setMatchDate(bean.getMatchDate());
		
		
		boolean needToSaveOdds = bean.get_1x2().get(TimeTypeEnum._final).getAvg1x2Odds() != null;
		
		if (needToSaveOdds) {
			
		// _1X2 Odds
			List<_1X2Odds> _1x2oddsEnts = new ArrayList<_1X2Odds>();
			
			HashMap<TimeTypeEnum, _1x2Full> _1X2 = bean.get_1x2();
			for (Entry<TimeTypeEnum, _1x2Full> entry : _1X2.entrySet()) {
				TimeTypeEnum timeType = entry.getKey();
				_1x2Full value = entry.getValue();
				
				_1x2Leaf leaf = value.getAvg1x2Odds();
				_1X2Odds oddsEnt = create1X2OddsEnt(timeType, null, leaf);
				_1x2oddsEnts.add(oddsEnt);
				
				for (Entry<BetHouseEnum, _1x2Leaf> entry3 : value.getBetHouseTo1x2Odds().entrySet()) {
					BetHouseEnum betHouse = entry3.getKey();
					leaf = entry3.getValue();
					oddsEnt = create1X2OddsEnt(timeType, betHouse, leaf);
	
					_1x2oddsEnts.add(oddsEnt);
					
				}
			}
			
			ent.set_1X2(_1x2oddsEnts);
			
			
			
			// Eh Odds
			List<EhOdds> ehOddsEnts = new ArrayList<EhOdds>();
			
			HashMap<TimeTypeEnum, EhTimeType> eh = bean.getEh();
			for (Entry<TimeTypeEnum, EhTimeType> entry : eh.entrySet()) {
				
				TimeTypeEnum timeType = entry.getKey();
				EhTimeType ehTimeType = entry.getValue();
				
				for (Entry<HomeVariationEnum, _1x2Full> entry2 : ehTimeType.getMap().entrySet()) {
					
					HomeVariationEnum homeVariation = entry2.getKey();
					_1x2Full value = entry2.getValue();
					_1x2Leaf leaf = value.getAvg1x2Odds();
	
					if (leaf != null) {//se è null allora tale homeVariation non è bancata
						EhOdds oddsEnt = createEhOddsEnt(timeType, homeVariation, null, leaf);
						ehOddsEnts.add(oddsEnt);
						
						for (Entry<BetHouseEnum, _1x2Leaf> entry3 : value.getBetHouseTo1x2Odds().entrySet()) {
							BetHouseEnum betHouse = entry3.getKey();
							leaf = entry3.getValue();
							oddsEnt = createEhOddsEnt(timeType, homeVariation, betHouse, leaf);
		
							ehOddsEnts.add(oddsEnt);
						}
					}
				}
			}
			ent.setEh(ehOddsEnts);
	
	
			
			// Uo Odds
			List<UoOdds> uoOddsEnts = new ArrayList<UoOdds>();
			
			HashMap<TimeTypeEnum, UoTimeType> uo = bean.getUo();
			for (Entry<TimeTypeEnum, UoTimeType> entry : uo.entrySet()) {
				
				TimeTypeEnum timeType = entry.getKey();
				UoTimeType uoTimeType = entry.getValue();
				
				for (Entry<UoThresholdEnum, UoFull> entry2 : uoTimeType.getMap().entrySet()) {
					UoThresholdEnum uoThreshold = entry2.getKey();
					UoFull value = entry2.getValue();
					
					UoLeaf leaf = value.getAvgUoOdds();
					if (leaf != null) {//se è null allora tale UoThreshold non è bancata
						UoOdds oddsEnt = createUoOddsEnt(timeType, uoThreshold, null, leaf);
						uoOddsEnts.add(oddsEnt);
						
						for (Entry<BetHouseEnum, UoLeaf> entry3 : value.getBetHouseToUoOdds().entrySet()) {
							BetHouseEnum betHouse = entry3.getKey();
							leaf = entry3.getValue();
							oddsEnt = createUoOddsEnt(timeType, uoThreshold, betHouse, leaf);
		
							uoOddsEnts.add(oddsEnt);
						}
					}
				}
			}
			ent.setUo(uoOddsEnts);
		}
		
		matchRepo.save(ent);
		
		
		return bean;
		
	}

	private UoOdds createUoOddsEnt(TimeTypeEnum timeType, UoThresholdEnum uoThreshold, BetHouseEnum betHouse, UoLeaf odds) {
		UoOdds ent = new UoOdds();
		TimeType timeTypeEnt = timeTypeDao.findByValue(timeType.name());
		ent.setTimeType(timeTypeEnt);
		
		if (betHouse != null) {
			BetHouse betHouseEnt = betHouseDao.findByValue(betHouse.name());
			ent.setBetHouse(betHouseEnt);
		}
		
		UoThresholdType thresholdEnt = uoThresholdTypeDao.findByValue(uoThreshold.name());
		ent.setThresholdType(thresholdEnt);
		
		ent.setU(odds.getU());
		ent.setO(odds.getO());
		
		return ent;
	}

	private EhOdds createEhOddsEnt(TimeTypeEnum timeType, HomeVariationEnum homeVariation, BetHouseEnum betHouse, _1x2Leaf odds) {
		EhOdds ent = new EhOdds();
		TimeType timeTypeEnt = timeTypeDao.findByValue(timeType.name());
		ent.setTimeType(timeTypeEnt);
		
		if (betHouse != null) {
			BetHouse betHouseEnt = betHouseDao.findByValue(betHouse.name());
			ent.setBetHouse(betHouseEnt);
		}
		
		HomeVariationType homeVariationEnt = homeVariationTypeDao.findByValue(homeVariation.name());
		ent.setHomeVariationType(homeVariationEnt);
		
		ent.set_1(odds.getOdd1());
		ent.set_X(odds.getOddX());
		ent.set_2(odds.getOdd2());
		
		return ent;
	}

	private _1X2Odds create1X2OddsEnt(TimeTypeEnum timeType, BetHouseEnum betHouse, _1x2Leaf odds) {
		_1X2Odds ent = new _1X2Odds();
		TimeType timeTypeEnt = timeTypeDao.findByValue(timeType.name());
		ent.setTimeType(timeTypeEnt);
		
		if (betHouse != null) {
			BetHouse betHouseEnt = betHouseDao.findByValue(betHouse.name());
			ent.setBetHouse(betHouseEnt);
		}
		ent.set_1(odds.getOdd1());
		ent.set_X(odds.getOddX());
		ent.set_2(odds.getOdd2());
		
		return ent;
	}
	

	
	// NEXT
	public ArrayList<MatchResult> getDownloadedNextMatchByChampBetweenFull(ChampEnum champEnum, Date dateOfBet, Date limitDate) {
		return getDownloadedNextMatchByChamp(champEnum, dateOfBet, limitDate, false);
	}

	public ArrayList<MatchResult> getDownloadedNextMatchByChampLight(ChampEnum champEnum) {
		Date limitDate;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +7);
		if (AppConstants.ENABLE_DOWNLOAD_ONLY_NEAR_MATCHES)
			limitDate = cal.getTime();
		Date now = new Date();
		return getDownloadedNextMatchByChamp(champEnum, now, limitDate, true);
	}
	
	private ArrayList<MatchResult> getDownloadedNextMatchByChamp(ChampEnum champEnum, Date dateOfBet, Date limitDate, Boolean light) { 
		Champ champ = champDao.findByChampEnum(champEnum);
		List<Matcho> listEnt = matchRepo.findByChampAndAndMatchDateBetween(champ, dateOfBet, limitDate);
		//List<Matcho> listEnt = matchRepo.findByChampAndFullTimeResultIsNullAndMatchDateBetween(champ, dateOfBet, limitDate);
		ArrayList<MatchResult> listBean = mapMatchosToMatchesResults(champEnum, listEnt, light);
		return listBean;
	}
	
	
	
	// PAST
	public ArrayList<MatchResult> getDownloadedPastMatchByChampBeforeDateFull(ChampEnum champEnum, Date limitDate) {
		return getDownloadedPastMatchByChampBeforeDate(champEnum, limitDate, false);
	}

	public ArrayList<MatchResult> getDownloadedPastMatchByChampLight(ChampEnum champEnum) {
		Date limitDate = new Date();
		return getDownloadedPastMatchByChampBeforeDate(champEnum, limitDate, true);
	}
	
	
	@Transactional
	public ArrayList<MatchResult> getDownloadedPastMatchByChampBeforeDate(ChampEnum champEnum, Date date, boolean light) {//light = true non scarica le quote
		Champ champ = champDao.findByChampEnum(champEnum);
		List<Matcho> listEnt = matchRepo.findByChampAndMatchDateBeforeOrderByMatchDateDesc(champ, date);
//		List<Matcho> listEnt = matchRepo.findByChampAndFullTimeResultIsNotNullOrderByMatchDateDesc(champ);
		ArrayList<MatchResult> listBean = mapMatchosToMatchesResults(champEnum, listEnt, light);
		return listBean;
	}




	private ArrayList<MatchResult> mapMatchosToMatchesResults(ChampEnum champEnum, List<Matcho> listEnt, boolean light) {
		ArrayList<MatchResult> listBean = new ArrayList<MatchResult>();
		for (Matcho ent : listEnt) {
			MatchResult bean = mapMatchoToMatchResult(ent, light);
			listBean.add(bean);
		}
		return listBean;
	}


	public MatchResult mapMatchoToMatchResult(Matcho ent, boolean light) {
		MatchResult bean = new MatchResult();
		
		ChampEnum champEnum = champDao.findChampEnumByChamp(ent.getChamp());
		bean.setId(ent.getId());
		bean.setChamp(champEnum );
		bean.setMatchDate(ent.getMatchDate());
		
		bean.setHomeTeam(ent.getHomeTeam().getName());
		bean.setAwayTeam(ent.getAwayTeam().getName());
		
		bean.setFTHG(ent.getFullTimeHomeGoals());
		bean.setFTAG(ent.getFullTimeAwayGoals());
		bean.setHTHG(ent.getHalfTimeHomeGoals());
		bean.setHTAG(ent.getHalfTimeAwayGoals());
		
		bean.setFTR(ent.getFullTimeResult());
		bean.setHTR(ent.getHalfTimeResult());
		
		if (!light) {
			
			// Mapping 1x2
			for (_1X2Odds oddsEnt : ent.get_1X2()) {
				TimeTypeEnum timeTypeEnum = timeTypeDao.findBeanByEnt(oddsEnt.getTimeType());
				
				Double _1 = oddsEnt.get_1();
				Double _2 = oddsEnt.get_2();
				Double x = oddsEnt.get_X();
				_1x2Leaf oddsBean = new _1x2Leaf(_1, x, _2);
				
				_1x2Full _1x2Full = bean.get_1x2().get(timeTypeEnum);

				BetHouse betHouseEnt = oddsEnt.getBetHouse();
				if ( betHouseEnt != null) {
					BetHouseEnum betHouseEnum = betHouseDao.findBeanByEnt(betHouseEnt);
					_1x2Full.getBetHouseTo1x2Odds().put(betHouseEnum, oddsBean);
				}
				else {
					_1x2Full.setAvg1x2Odds(oddsBean);
				}
			
			}
		
			
			
			// Mapping Under Over
			for (UoOdds uoOddsEnt : ent.getUo()) {
				
				TimeTypeEnum timeTypeEnum = timeTypeDao.findBeanByEnt(uoOddsEnt.getTimeType());
				Double u = uoOddsEnt.getU();
				Double o = uoOddsEnt.getO();
				UoLeaf oddsBean = new UoLeaf(u, o);
				
				UoTimeType uoTimeType = bean.getUo().get(timeTypeEnum);
				UoThresholdType uoThresTypeEnt = uoOddsEnt.getThresholdType();
				UoThresholdEnum uoThresTypeBean = uoThresholdTypeDao.findBeanByEnt(uoThresTypeEnt);
				
				UoFull uoFull = uoTimeType.getMap().get(uoThresTypeBean);
				
				BetHouse betHouseEnt = uoOddsEnt.getBetHouse();
				if ( betHouseEnt != null) {
					BetHouseEnum betHouseEnum = betHouseDao.findBeanByEnt(betHouseEnt);
					uoFull.getBetHouseToUoOdds().put(betHouseEnum, oddsBean);
				}
				else {
					uoFull.setAvgUoOdds(oddsBean);
				}
				
			}
			
			
			// Mapping European Handicap
			for (EhOdds ehOddsEnt : ent.getEh()) {
				
				TimeTypeEnum timeTypeEnum = timeTypeDao.findBeanByEnt(ehOddsEnt.getTimeType());
				Double _1 = ehOddsEnt.get_1();
				Double x = ehOddsEnt.get_X();
				Double _2 = ehOddsEnt.get_2();
				_1x2Leaf oddsBean = new _1x2Leaf(_1, x, _2);
				
				EhTimeType ehTimeType = bean.getEh().get(timeTypeEnum);
				HomeVariationType homeVarTypeEnt = ehOddsEnt.getHomeVariationType();
				HomeVariationEnum homeVarTypeBean = homeVariationTypeDao.findBeanByEnt(homeVarTypeEnt);
				
				 _1x2Full ehFull = ehTimeType.getMap().get(homeVarTypeBean);
				
				BetHouse betHouseEnt = ehOddsEnt.getBetHouse();
				if ( betHouseEnt != null) {
					BetHouseEnum betHouseEnum = betHouseDao.findBeanByEnt(betHouseEnt);
					ehFull.getBetHouseTo1x2Odds().put(betHouseEnum, oddsBean);
				}
				else {
					ehFull.setAvg1x2Odds(oddsBean);
				}
				
			}
		
		}
		return bean;
	}
	
	public Matcho findByTeamAndChamp(String homeTeam, String awayTeam, ChampEnum champEnum) {
		Champ champ = champDao.findByChampEnum(champEnum);
		List<Matcho> matches = matchRepo.findByHomeTeamNameAndAwayTeamNameAndChamp(homeTeam, awayTeam, champ);
		Matcho m = matches.get(0);
		return m;
	}


    @Transactional
	public void deleteMatch(Integer idMatch) {
		matchRepo.deleteById(idMatch);
		
	}
    
    @Transactional
   	public void deleteByChampId(Integer champId) {
   		matchRepo.deleteByChampId(champId);
   	}
    
    
    // ##################################################################################################################
   	
    @Deprecated
    public void updateEhOdds() {
   		List<Matcho> all = matchRepo.findAll();
   		
   		for (Matcho m : all) {
			for (EhOdds odds : m.getEh()) {
				if (odds.getBetHouse()== null) {
					Double get_1 = odds.get_1();
					Double get_2 = odds.get_2();
					odds.set_1(get_2);
					odds.set_2(get_1);
					
				}
			}
   		}
   		matchRepo.save(all);
   	}

    @Transactional
	public void removeAllNextMatchesByNoChamp() {
		eventOddsDao.deleteByMatchChampIsNull();
		matchRepo.deleteByChampIsNull();
	}
   	
	@Transactional
	public void removeAllNextMatchesByChamp(ChampEnum champEnum, Integer seasonDay) {
		Champ champ = champDao.findByChampEnum(champEnum);
		eventOddsDao.deleteByMatchChampAndSeasonDay(champ, seasonDay);
		matchRepo.deleteByChampAndFullTimeResultIsNull(champ);
	}

	public void saveAll(List<Matcho> matches) {
		matchRepo.save(matches);
		
	}
	
	@Deprecated
	public ArrayList<MatchResult> getDownloadedPastMatchByChampAndAwayTeamAfterSeasonDay(ChampEnum champEnum, String homeTeam, int lastSeasonDayOdds) {
		Champ champ = champDao.findByChampEnum(champEnum);
		Team team = teamDao.findByNameAndChamp(homeTeam, champ);
		List<Matcho> listEnt = matchRepo.findByChampAndAwayTeamAndFullTimeResultIsNotNullOrderByMatchDate(champ, team);
		List<Matcho> missingMatchesEnt = listEnt.subList(lastSeasonDayOdds, listEnt.size());
		ArrayList<MatchResult> listBean = mapMatchosToMatchesResults(champEnum, missingMatchesEnt, true);
		return listBean;
	}

	@Deprecated
	public ArrayList<MatchResult> getDownloadedPastMatchByChampAndHomeTeamAfterSeasonDay(ChampEnum champEnum, String homeTeam, int lastSeasonDayOdds) {
		Champ champ = champDao.findByChampEnum(champEnum);
		Team team = teamDao.findByNameAndChamp(homeTeam, champ);
		List<Matcho> listEnt = matchRepo.findByChampAndHomeTeamAndFullTimeResultIsNotNullOrderByMatchDate(champ, team);
		List<Matcho> missingMatchesEnt = listEnt.subList(lastSeasonDayOdds, listEnt.size());
		ArrayList<MatchResult> listBean = mapMatchosToMatchesResults(champEnum, missingMatchesEnt, true);
		
		return listBean;
	}
	
	
}
