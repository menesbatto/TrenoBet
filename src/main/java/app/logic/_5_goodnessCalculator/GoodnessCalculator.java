package app.logic._5_goodnessCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.EventOddsDao;
import app.dao.tabelle.GoalsStatsDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.RankingRowDao;
import app.dao.tabelle.WinRangeStatsDao;
import app.dao.tipologiche.TimeTypeDao;
import app.logic._1_matchesDownlaoder.model.BetType;
import app.logic._1_matchesDownlaoder.model.EventOddsBean;
import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessBean;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessUoBean;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessWDLBean;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.model.UoFull;
import app.logic._1_matchesDownlaoder.model.UoLeaf;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;
import app.logic._1_matchesDownlaoder.model._1x2Full;
import app.logic._1_matchesDownlaoder.model._1x2Leaf;
import app.logic._2_matchResultAnalyzer.model.GoalsStatsBean;
import app.logic._2_matchResultAnalyzer.model.TeamResultEnum;
import app.logic._2_matchResultAnalyzer.model.UoThresholdStats;
import app.logic._2_matchResultAnalyzer.model.WinRangeStatsBean;
import app.logic._3_rankingCalculator.RankingCalculator;
import app.logic._3_rankingCalculator.model.RankingRow;
import app.utils.AppConstants;
import app.utils.ChampEnum;
import app.utils.IOUtils;
import app.utils.Utils;



@Service
public class GoodnessCalculator {
//	private static HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>> allAnalyzedChampsWinHome;
//	private static HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>> allAnalyzedChampsWinAway;
//	private static HashMap<ChampEnum, HashMap<String, ArrayList<WinRangeStatsBean>>> allAnalyzedChampsWinAll;
//
//	private static HashMap<ChampEnum, HashMap<String, GoalsStatsBean>> allAnalyzedChampsUoHome;
//	private static HashMap<ChampEnum, HashMap<String, GoalsStatsBean>> allAnalyzedChampsUoAway;
//	
//	private static HashMap<ChampEnum, ArrayList<String>> allTeams;
//	private static HashMap<ChampEnum, ArrayList<EventOdds>> allMatchesOdds;
//	
//	private static HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>> trends;
//	private static HashMap<ChampEnum, ArrayList<RankingRow>> rankings; 

	
	@Autowired
	private MatchoDao matchDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private RankingRowDao rankingRowDao;
	
	@Autowired
	private RankingCalculator rankingCalculator;

	@Autowired
	private WinRangeStatsDao winRangeStatsDao;
	
	@Autowired
	private GoalsStatsDao goalsStatsDao;
	
	@Autowired
	private EventOddsDao eventOddsDao;
	
	
	
	public void execute(){
//	public void execute(HashMap<ChampEnum, ArrayList<EventOdds>> allMatchesOddsInput){
//		allMatchesOdds = allMatchesOddsInput;
//		initStaticFields();
		
		
//		matchDao.removeAllEventOdds(); HHH
		
		List<RankingRow> ranking;
		for (ChampEnum champ : ChampEnum.values()){
			calculateMatchGoodnessOfChamp(champ);
			ranking = rankingRowDao.findByChamp(champ);
			rankingCalculator.printRanking(ranking, champ);
		}
//		System.out.println(allMatchesOdds);
//		IOUtils.write(AppConstants.MATCHES_ODDS_WITH_GOODNESS_PATH, allMatchesOdds);
	}
	
	
	
	private void calculateMatchGoodnessOfChamp(ChampEnum champ) {
		
		// Recupero dei prossimi match
		ArrayList<MatchResult> nextMatches = matchDao.getDownloadedNextMatchByChampFull(champ);
//		ArrayList<EventOdds> nextMatchesOdds = createNextEventsOdds(nextMatches);
		
		// Inizializzazione degli EventsOdds a partire dai match recuperati con le odds e le info base recuperate dal match
		Map<TimeTypeEnum, ArrayList<EventOddsBean>> mapNextMatchOdds = createNextEventsOdds(nextMatches);
		
		// ##############################
		// #### RECUPERO STATISTICHE ####
		// ##############################
		
		// 1x2 e EH
		List<WinRangeStatsBean> teamWinRangeStatsAll = winRangeStatsDao.findByChamp(champ); //ordinate per team
		
		List<WinRangeStatsBean> teamWinRangeStats = new ArrayList<WinRangeStatsBean>();
		List<WinRangeStatsBean> teamWinEhRangeStats = new ArrayList<WinRangeStatsBean>();
		for (WinRangeStatsBean winBean: teamWinRangeStatsAll) {
			if (winBean.getHomeVariationBean() == null) 
				teamWinRangeStats.add(winBean);
			else 
				teamWinEhRangeStats.add(winBean);
		}
		
		// UO
		List<GoalsStatsBean> teamGoalsStats = goalsStatsDao.findByChamp(champ); //ordinate per team

		// ########################################
		// #### RIORGANIZZAZIONE STATS IN MAPS ####
		// ########################################
		
		//		homeVariation		team, 			field,			time,					range 
								Map<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>> 	mapTeamWinRangStats = createWinRangeStatsMap(teamWinRangeStats);
								Map<String, HashMap<String, HashMap<TimeTypeEnum, GoalsStatsBean>>> 				mapTeamGoalsStats = createGoalsStatsMap(teamGoalsStats);
		Map<HomeVariationEnum,	Map<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>>> 	mapTeamWinEhRangStats = createWinEhRangeStatsMap(teamWinEhRangeStats);
		
		
//		System.out.println(mapTeamWinEhRangStats.get(HomeVariationEnum.m1).get("Liverpool").get("H").get(TimeTypeEnum._final));
		// ##########################
		// #### CALCOLO GOODNESS ####
		// ##########################
		List<TimeTypeEnum> timeTypes = timeTypeDao.findAllTimeTypeEnum();	
		for (TimeTypeEnum timeType : timeTypes) {		
			for (EventOddsBean eo : mapNextMatchOdds.get(timeType)){
				
				ResultGoodnessBean resultGoodnessHome = createPlayingFieldStats(mapTeamWinRangStats, mapTeamGoalsStats, mapTeamWinEhRangStats, timeType, "H", eo);
				eo.setHomeResultGoodness(resultGoodnessHome);
				
				ResultGoodnessBean resultGoodnessAway = createPlayingFieldStats(mapTeamWinRangStats, mapTeamGoalsStats, mapTeamWinEhRangStats, timeType, "A", eo);
				eo.setAwayResultGoodness(resultGoodnessAway);
				
				
//				ResultGoodnessBean resultGoodnessAway = new ResultGoodnessBean();
//				
//				ArrayList<WinRangeStatsBean> rangesStatsByTeamFieldTimeAway = mapTeamWinRangStats.get(eo.getAwayTeam()).get("A").get(timeType);
//				ResultGoodnessWDLBean winCleanResultGoodnessA = calculateAllWinResultsGoodness(rangesStatsByTeamFieldTimeAway, eo.getOddsA());
//				resultGoodnessAway.setWinClean(winCleanResultGoodnessA);
//				
//				GoalsStatsBean goalsStatsByTeamFieldTimeAway = mapTeamGoalsStats.get(eo.getAwayTeam()).get("A").get(timeType);
//				calculateAllUoResultsGoodness(goalsStatsByTeamFieldTimeAway, resultGoodnessAway);
//				
//				for (HomeVariationEnum homeVariation : HomeVariationEnum.values()) {
//					rangesStatsByTeamFieldTimeAway = mapTeamWinEhRangStats.get(homeVariation).get(eo.getAwayTeam()).get("A").get(timeType);
//					ResultGoodnessWDLBean winCleanEhResultGoodnessA = calculateAllWinResultsGoodness(rangesStatsByTeamFieldTimeAway, eo.getOddsA());
//					resultGoodnessAway.getEhGoodness().put(homeVariation, winCleanEhResultGoodnessA);
//					
//				}
//				
//				eo.setAwayResultGoodness(resultGoodnessAway);
				
				
				
				
				eo.setTimeType(timeType);
				
			}
//			if (timeType == TimeTypeEnum._final) {
				System.out.println("###############################");
				System.out.println(timeType);
				System.out.println("###############################");
				System.out.println(mapNextMatchOdds.get(timeType));
//			}
		}
		
		
//		updateGoodnessWithMotivationalIndex(allMatchesOdds.get(champ), rankings.get(champ));
//
//		updateGoodnessWithTrend(allMatchesOdds.get(champ), trends.get(champ));
		
		eventOddsDao.save(mapNextMatchOdds, champ);
		//salva e stampa le odds
		System.out.println();
	}



	private ResultGoodnessBean createPlayingFieldStats (
			Map<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>> mapTeamWinRangStats,
			Map<String, HashMap<String, HashMap<TimeTypeEnum, GoalsStatsBean>>> mapTeamGoalsStats,
			Map<HomeVariationEnum, Map<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>>> mapTeamWinEhRangStats,
			TimeTypeEnum timeType, 
			String playingField, EventOddsBean eo ) {
		
		Double oddsToWin;
		String teamName;
		if (playingField.equals("H")) {
			teamName = eo.getHomeTeam();
			oddsToWin =  eo.getOddsH(); 
			
		}
		else {
			teamName = eo.getAwayTeam(); 
			oddsToWin = eo.getOddsA();
		} 
		
		ResultGoodnessBean resultGoodnessHome = new ResultGoodnessBean();
		
		// 1x2
		ArrayList<WinRangeStatsBean> rangesStatsByTeamFieldTimeHome = mapTeamWinRangStats.get(teamName).get(playingField).get(timeType);
		ResultGoodnessWDLBean winCleanResultGoodnessH = calculateAllWinResultsGoodness(rangesStatsByTeamFieldTimeHome, oddsToWin);
		resultGoodnessHome.setWinClean(winCleanResultGoodnessH);
		
		// UO
		GoalsStatsBean goalsStatsByTeamFieldTimeHome = mapTeamGoalsStats.get(teamName).get(playingField).get(timeType);
		calculateAllUoResultsGoodness(goalsStatsByTeamFieldTimeHome, resultGoodnessHome);
		
		// EH
		// Quando l'atalanta è quotata in m2 a 1.5, nell'EH m2 allora ci azzecca questo numero di volte
		for (HomeVariationEnum homeVariation : mapTeamWinEhRangStats.keySet()) {
			rangesStatsByTeamFieldTimeHome = mapTeamWinEhRangStats.get(homeVariation).get(teamName).get(playingField).get(timeType);
			
			Double oddsToWinWithHomeVariation = null;
			if (eo.getEhOddsMap().get(homeVariation) != null) {
				if (playingField.equals("H")) {
					oddsToWinWithHomeVariation = eo.getEhOddsMap().get(homeVariation).getOdd1();
				}
				else {
					oddsToWinWithHomeVariation = eo.getEhOddsMap().get(homeVariation).getOdd2();
				}
			}
			
			ResultGoodnessWDLBean winCleanEhResultGoodnessH = calculateAllWinResultsGoodness(rangesStatsByTeamFieldTimeHome, oddsToWinWithHomeVariation);
			resultGoodnessHome.getEhGoodness().put(homeVariation, winCleanEhResultGoodnessH);
			
		}
		
		return resultGoodnessHome;
	}



	private Map<HomeVariationEnum, Map<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>>> createWinEhRangeStatsMap(	List<WinRangeStatsBean> teamWinRangeStats) {
		Map<HomeVariationEnum, Map<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>>> ehMap = new HashMap<HomeVariationEnum, Map<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>>>();
		Map<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>> singleEhMap;
		Map<HomeVariationEnum, List<WinRangeStatsBean>> mapInput = createMapInput(teamWinRangeStats);
		
		//for (HomeVariationEnum homeVariation : HomeVariationEnum.values()) {
		for (HomeVariationEnum homeVariation : mapInput.keySet()) {
			singleEhMap = createWinRangeStatsMap(mapInput.get(homeVariation));
			ehMap.put(homeVariation, singleEhMap);
		}
		
		
		
		return ehMap;
	}



	private HashMap<HomeVariationEnum, List<WinRangeStatsBean>> createMapInput(List<WinRangeStatsBean> teamWinRangeStats) {
		HashMap<HomeVariationEnum, List<WinRangeStatsBean>> mapInput = new HashMap<HomeVariationEnum, List<WinRangeStatsBean>>();
		
		HomeVariationEnum homeVariationBean;
		for (WinRangeStatsBean winStats : teamWinRangeStats) {
			homeVariationBean = winStats.getHomeVariationBean();
			if (mapInput.get(homeVariationBean) == null) {
				mapInput.put(homeVariationBean, new ArrayList<WinRangeStatsBean>());
			}
			mapInput.get(homeVariationBean).add(winStats);
		}
		
		
		return mapInput;
	}

	private Map<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>> createWinRangeStatsMap(List<WinRangeStatsBean> teamWinRangeStats) {
		Map<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>> map = new HashMap<String, HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>>();
		
		for (WinRangeStatsBean rangeStats : teamWinRangeStats) {
			String teamNameClean = Utils.cleanString(rangeStats.getTeamName());
			if (!map.keySet().contains(rangeStats.getTeamName())) {
				HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>> teamMap = new HashMap<String, HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>>();
				teamMap.put("H", new HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>());
				teamMap.get("H").put(TimeTypeEnum._final, new ArrayList<WinRangeStatsBean>());
				teamMap.get("H").put(TimeTypeEnum._1, new ArrayList<WinRangeStatsBean>());
				teamMap.get("H").put(TimeTypeEnum._2, new ArrayList<WinRangeStatsBean>());
				teamMap.put("A", new HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>());
				teamMap.get("A").put(TimeTypeEnum._final, new ArrayList<WinRangeStatsBean>());
				teamMap.get("A").put(TimeTypeEnum._1, new ArrayList<WinRangeStatsBean>());
				teamMap.get("A").put(TimeTypeEnum._2, new ArrayList<WinRangeStatsBean>());
				teamMap.put("T", new HashMap<TimeTypeEnum, ArrayList<WinRangeStatsBean>>());
				teamMap.get("T").put(TimeTypeEnum._final, new ArrayList<WinRangeStatsBean>());
				teamMap.get("T").put(TimeTypeEnum._1, new ArrayList<WinRangeStatsBean>());
				teamMap.get("T").put(TimeTypeEnum._2, new ArrayList<WinRangeStatsBean>());
				map.put(teamNameClean, teamMap);
				
			}
			map.get(teamNameClean).get(rangeStats.getPlayingField()).get(rangeStats.getTimeTypeBean()).add(rangeStats);
		}
		
		return map;
	}

	private Map<String, HashMap<String, HashMap<TimeTypeEnum, GoalsStatsBean>>> createGoalsStatsMap( List<GoalsStatsBean> teamGoalsStats) {
		Map<String, HashMap<String, HashMap<TimeTypeEnum, GoalsStatsBean>>> map = new HashMap<String, HashMap<String, HashMap<TimeTypeEnum, GoalsStatsBean>>>();
		
		for (GoalsStatsBean goalsStats : teamGoalsStats) {
			String teamNameClean = Utils.cleanString(goalsStats.getTeamName());
			if (!map.keySet().contains(goalsStats.getTeamName())) {
				HashMap<String, HashMap<TimeTypeEnum, GoalsStatsBean>> teamMap = new HashMap<String, HashMap<TimeTypeEnum, GoalsStatsBean>>();
				
				teamMap.put("H", new HashMap<TimeTypeEnum, GoalsStatsBean>());
				teamMap.put("A", new HashMap<TimeTypeEnum, GoalsStatsBean>());
				teamMap.put("T", new HashMap<TimeTypeEnum, GoalsStatsBean>());
				map.put(teamNameClean, teamMap);
			}
			map.get(teamNameClean).get(goalsStats.getPlayingField()).put(goalsStats.getTimeTypeBean(), goalsStats);
		}
		
		return map;
	}



	


	private Map<TimeTypeEnum, ArrayList<EventOddsBean>> createNextEventsOdds(ArrayList<MatchResult> nextMatches) {
		
		Map<TimeTypeEnum, ArrayList<EventOddsBean>> mapOdds = new HashMap<TimeTypeEnum, ArrayList<EventOddsBean>>();
		mapOdds.put(TimeTypeEnum._1, new ArrayList<EventOddsBean>());
		mapOdds.put(TimeTypeEnum._2, new ArrayList<EventOddsBean>());
		mapOdds.put(TimeTypeEnum._final, new ArrayList<EventOddsBean>());
		for (MatchResult m : nextMatches) {
			for (TimeTypeEnum timeType : TimeTypeEnum.values()) {
				
				// Copia delle quote di 1x2
				_1x2Leaf avg1x2Odds = m.get_1x2().get(timeType).getAvg1x2Odds();
				EventOddsBean eo = new EventOddsBean();
				String homeClean = Utils.cleanString(m.getHomeTeam());
				eo.setHomeTeam(homeClean);
				
				String awayClean = Utils.cleanString(m.getAwayTeam());
				eo.setAwayTeam(awayClean);
				eo.setDate(m.getMatchDate());
				eo.setBetType(BetType.WIN);
				eo.setOddsH(avg1x2Odds.getOdd1());
				eo.setOddsD(avg1x2Odds.getOddX());
				eo.setOddsA(avg1x2Odds.getOdd2());
				
				mapOdds.get(timeType).add(eo);
				
				
				// Copia delle quote di Under Over
				Map<UoThresholdEnum, UoLeaf> uoOdds = new HashMap<UoThresholdEnum, UoLeaf>();
				eo.setUoOddsMap(uoOdds);

				for (Entry<UoThresholdEnum, UoFull> entry : m.getUo().get(timeType).getMap().entrySet()) {
					UoThresholdEnum key = entry.getKey();
					UoFull value = entry.getValue();
					UoLeaf uoLeaf = value.getAvgUoOdds();
					uoOdds.put(key, uoLeaf);
				}
				
				
				// Copia delle quote di European Handicap
				Map<HomeVariationEnum, _1x2Leaf> ehOdds = new HashMap<HomeVariationEnum, _1x2Leaf>();
				eo.setEhOddsMap(ehOdds);

				for (Entry<HomeVariationEnum, _1x2Full> entry : m.getEh().get(timeType).getMap().entrySet()) {
					HomeVariationEnum key = entry.getKey();
					_1x2Full value = entry.getValue();
					_1x2Leaf _1x2Leaf = value.getAvg1x2Odds();
					ehOdds.put(key, _1x2Leaf);
				}
				
						
			}
		}
		return mapOdds;
	}



	private static void updateGoodnessWithMotivationalIndex(ArrayList<EventOddsBean> eventsOdds, ArrayList<RankingRow> rankings) {
		Double homeMotInd = null;
		Double awayMotInd = null;
		for (EventOddsBean eo :eventsOdds){
			for (RankingRow rr : rankings){
				if (eo.getHomeTeam().equals(rr.getTeamName()))
					homeMotInd = rr.getMotivationalIndex();
				else if (eo.getAwayTeam().equals(rr.getTeamName()))
					awayMotInd = rr.getMotivationalIndex();
			}
			Double actualGoodnessHomeWin = eo.getHomeResultGoodness().getWinClean().getGoodnessW();
			Double actualGoodnessHomeLose = eo.getHomeResultGoodness().getWinClean().getGoodnessL();
			Double actualGoodnessAwayWin =  eo.getAwayResultGoodness().getWinClean().getGoodnessL();
			Double actualGoodnessAwayLose = eo.getAwayResultGoodness().getWinClean().getGoodnessW();
			eo.setHomeMotivation(homeMotInd);
			eo.setAwayMotivation(awayMotInd);
			
			//Squadra fuori casa senza motivazione
			if (awayMotInd < 0.1 && homeMotInd > 0.4){
				if (actualGoodnessHomeWin != null){
					ResultGoodnessWDLBean homeWinMotivation = new ResultGoodnessWDLBean();
					homeWinMotivation.setGoodnessW(actualGoodnessHomeWin * 1.2);
					homeWinMotivation.setGoodnessL(actualGoodnessHomeLose * 1.2);
					eo.getHomeResultGoodness().setWinMotivation(homeWinMotivation);
//					eo.getHomeResultGoodness().setGoodnessWwithMotivation(actualGoodnessHomeWin * 1.2);
//					eo.getHomeResultGoodness().setGoodnessLwithMotivation(actualGoodnessHomeLose / 1.2);
				}
				if (actualGoodnessAwayLose != null){
					ResultGoodnessWDLBean awayWinMotivation = new ResultGoodnessWDLBean();
					awayWinMotivation.setGoodnessW(actualGoodnessAwayLose / 1.2);
					awayWinMotivation.setGoodnessL(actualGoodnessAwayWin * 1.2);
					eo.getAwayResultGoodness().setWinMotivation(awayWinMotivation);
//					eo.getAwayResultGoodness().setGoodnessWwithMotivation(actualGoodnessAwayLose / 1.2);
//					eo.getAwayResultGoodness().setGoodnessLwithMotivation(actualGoodnessAwayWin * 1.2);
				}
				
			}
			// squadra in casa senza motivazione
			else if (homeMotInd < 0.1 && awayMotInd > 0.4){
				if (actualGoodnessHomeLose != null){
					ResultGoodnessWDLBean homeWinMotivation = new ResultGoodnessWDLBean();
					homeWinMotivation.setGoodnessW(actualGoodnessHomeWin / 1.2);
					homeWinMotivation.setGoodnessL(actualGoodnessHomeLose * 1.2);
					eo.getHomeResultGoodness().setWinMotivation(homeWinMotivation);
//					eo.getHomeResultGoodness().setGoodnessWwithMotivation(actualGoodnessHomeWin / 1.2);
//					eo.getHomeResultGoodness().setGoodnessLwithMotivation(actualGoodnessHomeLose * 1.2);
				}
				if (actualGoodnessAwayWin != null){
					ResultGoodnessWDLBean awayWinMotivation = new ResultGoodnessWDLBean();
					awayWinMotivation.setGoodnessW(actualGoodnessAwayLose * 1.2);
					awayWinMotivation.setGoodnessL(actualGoodnessAwayWin / 1.2);
					eo.getAwayResultGoodness().setWinMotivation(awayWinMotivation);
//					eo.getAwayResultGoodness().setGoodnessWwithMotivation(actualGoodnessAwayLose * 1.2);
//					eo.getAwayResultGoodness().setGoodnessLwithMotivation(actualGoodnessAwayWin / 1.2);
				}
			}
			else {
				
		
				
				eo.getHomeResultGoodness().getWinMotivation().setGoodnessW(eo.getHomeResultGoodness().getWinClean().getGoodnessW());
				eo.getHomeResultGoodness().getWinMotivation().setGoodnessL(eo.getHomeResultGoodness().getWinClean().getGoodnessL());
				eo.getAwayResultGoodness().getWinMotivation().setGoodnessW(eo.getAwayResultGoodness().getWinClean().getGoodnessW());
				eo.getAwayResultGoodness().getWinMotivation().setGoodnessL(eo.getAwayResultGoodness().getWinClean().getGoodnessL());
				
//				eo.getHomeResultGoodness().setGoodnessWwithMotivation(eo.getHomeResultGoodness().getGoodnessW());
//				eo.getHomeResultGoodness().setGoodnessLwithMotivation(eo.getHomeResultGoodness().getGoodnessL());
//				eo.getAwayResultGoodness().setGoodnessWwithMotivation(eo.getAwayResultGoodness().getGoodnessW());
//				eo.getAwayResultGoodness().setGoodnessLwithMotivation(eo.getAwayResultGoodness().getGoodnessL());
			}
			
		}
		
		
		
	}



	private static void updateGoodnessWithTrend(ArrayList<EventOddsBean> eventsOdds, HashMap<String, ArrayList<MatchResult>> trends) {
		Double homeTrendsIndex;
		Double awayTrendsIndex;
		Integer trendDimension = trends.size();
		for (EventOddsBean eo :eventsOdds){
			
			ArrayList<MatchResult> lastMatches = trends.get(eo.getHomeTeam());
			homeTrendsIndex = calculateTrendIndex(eo.getHomeTeam(), lastMatches, trendDimension, eo);
			lastMatches = trends.get(eo.getAwayTeam());
			awayTrendsIndex = calculateTrendIndex(eo.getAwayTeam(), lastMatches, trendDimension, eo);

			ResultGoodnessBean homeResultGoodness = eo.getHomeResultGoodness();
			ResultGoodnessBean awayResultGoodness = eo.getAwayResultGoodness();
			
			Double maxVariation = 1.3;
			Double homeTrendIndexMolt = 1.00  - (maxVariation - 1 ) + ((maxVariation - 1) * 2 / 15 * homeTrendsIndex);
			Double awayTrendIndexMolt = 1.00  - (maxVariation - 1 ) + ((maxVariation - 1) * 2 / 15 * awayTrendsIndex);
			// 0 -> /1.3
			
			if (homeResultGoodness.getWinClean().getGoodnessW() != null)
				homeResultGoodness.getWinTrend().setGoodnessW(homeResultGoodness.getWinClean().getGoodnessW() * homeTrendIndexMolt);
			if (homeResultGoodness.getWinClean().getGoodnessL() != null)
				homeResultGoodness.getWinTrend().setGoodnessL(homeResultGoodness.getWinClean().getGoodnessL() * awayTrendIndexMolt);
			
			if (awayResultGoodness.getWinClean().getGoodnessW() != null)
				awayResultGoodness.getWinTrend().setGoodnessW(awayResultGoodness.getWinClean().getGoodnessW() * awayTrendIndexMolt);
			if (awayResultGoodness.getWinClean().getGoodnessL() != null)
				awayResultGoodness.getWinTrend().setGoodnessL(awayResultGoodness.getWinClean().getGoodnessL() * homeTrendIndexMolt);
			

			if (homeResultGoodness.getWinClean().getGoodnessW() != null)
				homeResultGoodness.getWinFinal().setGoodnessW(homeResultGoodness.getWinMotivation().getGoodnessW() * homeTrendIndexMolt);
			if (homeResultGoodness.getWinClean().getGoodnessL() != null)
				homeResultGoodness.getWinFinal().setGoodnessL(homeResultGoodness.getWinMotivation().getGoodnessL() * awayTrendIndexMolt);
			
			if (awayResultGoodness.getWinClean().getGoodnessW() != null)
				awayResultGoodness.getWinFinal().setGoodnessW(awayResultGoodness.getWinMotivation().getGoodnessW() * awayTrendIndexMolt);
			if (awayResultGoodness.getWinClean().getGoodnessL() != null)
				awayResultGoodness.getWinFinal().setGoodnessL(awayResultGoodness.getWinMotivation().getGoodnessL() * homeTrendIndexMolt);
			
			
			
			
			
			
		}
		
		
	}



	private static Double calculateTrendIndex(String teamName, ArrayList<MatchResult> matches, Integer trendsDimension, EventOddsBean eo) {
		Double trendIndex = 0.0;
		String trendString ="";
		String trendUoString ="";
		trendsDimension = matches.size();
		for (int i = 0; i < matches.size(); i++) {
			
			MatchResult m = matches.get(i);
			if (i < AppConstants.TREND_SIZE_WIN){
				TeamResultEnum result = getResult(m, teamName);
				// VVVVV	15		5   + 4 +  3  + 2 + 1
				// DDDDD	7.5		2.5 + 2 + 1.5 + 1 + 0.5
				// LLLLL	0		0   + 0 + 0   + 0 + 0
				// VVPLL	10.5	2.5 + 2 + 1.5 + 0 + 0
				// LLPVV	4.5		0   + 0 + 1.5 + 1 + 0.5
				// VLVVV	11	
				// PPPVV	3
				// VPPPP	4
				switch (result) {
					case W:	trendIndex +=	(trendsDimension - AppConstants.TREND_SIZE_WIN - i) * 1.0;		break;
					case D:	trendIndex +=	(trendsDimension - AppConstants.TREND_SIZE_WIN - i) * 0.5;		break;
					case L:	trendIndex +=	(trendsDimension - AppConstants.TREND_SIZE_WIN - i) * 0.0;		break;
					default: 									break;
				}
				trendString+=result + " ";
			}
			
			
			MatchResultEnum resultUo = getResultUo(m);
			trendUoString += resultUo + " ";
			
		}
		if (eo.getHomeTeam().equals(teamName)){
			eo.setHomeTrend(trendIndex + " " + trendString);
//			eo.setHomeTrendUo(trendUoString);
			//Da sistemare con la mappa
		}
		
		else {
			eo.setAwayTrend(trendIndex + " " + trendString);
//			eo.setAwayTrendUo(trendUoString);
			//Da sistemare con la mappa
		}
		
		return trendIndex;
	}



	private static MatchResultEnum getResultUo(MatchResult m) {
		if (m.getFTHG() +  m.getFTAG() > 2){
			return MatchResultEnum.O;
		}
		return MatchResultEnum.U;
	}



	private static TeamResultEnum getResult(MatchResult m, String teamName) {
		 if (m.getFTHG() == null)
			 return TeamResultEnum.U;
		 Integer result = m.getFTHG() - m.getFTAG();
		if (result == 0)
			return TeamResultEnum.D;
		
		if (teamName.equals(m.getHomeTeam())){
			if (result < 0)		return TeamResultEnum.L;
			else 				return TeamResultEnum.W;
		}
		else { //if (teamName.equals(m.getAwayTeam())){{
			if (result < 0)		return TeamResultEnum.W;
			else 				return TeamResultEnum.L;
		}
	}



	private static void calculateAllUoResultsGoodness(GoalsStatsBean goalsStatsBean, ResultGoodnessBean resultGoodness) {
		
		UoThresholdEnum key;
		for (Entry<UoThresholdEnum, UoThresholdStats> entry : goalsStatsBean.getThresholdMap().entrySet()) {
			key = entry.getKey();
			UoThresholdStats value = entry.getValue();
			ResultGoodnessUoBean rguo = new ResultGoodnessUoBean();
			Double total = value.getOverHit() + value.getUnderHit();
			Double overPerc = value.getOverPerc();
			Double underPerc = value.getUnderPerc();
//			if (total <= 3) {
//				underPerc = overPerc / (4 - total);
//				overPerc = overPerc / (4 - total);
//			}
			rguo.setGoodnessO(overPerc);
			rguo.setGoodnessU(underPerc);
				
			resultGoodness.getUoGoodness().put(key, rguo);
		}

		
	}



	private ResultGoodnessWDLBean calculateAllWinResultsGoodness(ArrayList<WinRangeStatsBean> rangesStats, Double oddWin) {
		//Probabilita che la Roma, giocando in casa ad una certa quota 1,2 ad esempio ho da vincere perdere o pareggiare
		Double goodnessW = getSingleFinalGoodness(rangesStats, oddWin, TeamResultEnum.W);
		Double goodnessD = getSingleFinalGoodness(rangesStats, oddWin, TeamResultEnum.D);
		Double goodnessL = getSingleFinalGoodness(rangesStats, oddWin, TeamResultEnum.L);
//		capisci perche rangeStats ce ne stanno 20
		goodnessW = goodnessW != null && goodnessW.toString().length() > 4 ? new Double(goodnessW.toString().substring(0,4)) : goodnessW;
		goodnessD = goodnessD != null && goodnessD.toString().length() > 4 ? new Double(goodnessD.toString().substring(0,4)) : goodnessD;
		goodnessL = goodnessL != null && goodnessL.toString().length() > 4 ? new Double(goodnessL.toString().substring(0,4)) : goodnessL;
		
		ResultGoodnessWDLBean winClean = new ResultGoodnessWDLBean();
		winClean.setGoodnessW(goodnessW);
		winClean.setGoodnessD(goodnessD);
		winClean.setGoodnessL(goodnessL);
		
		return winClean;
		
	}

	private static Double getSingleFinalGoodness(ArrayList<WinRangeStatsBean> rangesStats, Double odds, TeamResultEnum teamResulOfInterest) {
			
		for (int i = 0; i < rangesStats.size(); i++) {
			WinRangeStatsBean sameRange = rangesStats.get(i);
			if (odds== null) return null;
			if (odds < sameRange.getEdgeUp()){
				
				if (needToCheckSameRange(rangesStats, i)){
				
					Double sameRangeHitPercentage = 0.0;
					switch (teamResulOfInterest) {	case W:		sameRangeHitPercentage = new Double(sameRange.getWinPerc()!=null ? sameRange.getWinPerc() : 0.0);		break;
													case D:		sameRangeHitPercentage = new Double(sameRange.getDrawPerc()!=null ? sameRange.getDrawPerc() : 0.0);		break;
													case L:		sameRangeHitPercentage = new Double(sameRange.getLosePerc()!=null ? sameRange.getLosePerc() : 0.0);		break;
													default:	break;	}
					
					if (AppConstants.ENABLE_ODD_IMPROVEMENTS_ALGHORITM) {
										
						
						WinRangeStatsBean nearRange = null;
						if (needToCheckNextRange(rangesStats, i, teamResulOfInterest, odds)){
							nearRange = rangesStats.get(i + 1);
						}
						else if (needToCheckPrevRange(rangesStats, i, teamResulOfInterest, odds)){
							nearRange = rangesStats.get(i - 1);
						}
						
	
						if (nearRange != null){
	
							Double nearRangeHitPercentage = null;
							switch (teamResulOfInterest) {	case W:		nearRangeHitPercentage = new Double(nearRange.getWinPerc());		break;
															case D:		nearRangeHitPercentage = new Double(nearRange.getDrawPerc());		break;
															case L:		nearRangeHitPercentage = new Double(nearRange.getLosePerc());		break;
															default:	break;	}
							
							Double bothRangeHitPercentage = null;
							Integer sameRangeTotal = sameRange.getTotal();
							Integer nearRangeTotal = nearRange.getTotal();
							
							
							if (sameRangeHitPercentage > nearRangeHitPercentage)
								bothRangeHitPercentage = (sameRangeHitPercentage * sameRangeTotal * 4 + nearRangeHitPercentage * nearRangeTotal * 1) / ( sameRangeTotal * 4 + nearRangeTotal * 1);
							else
								bothRangeHitPercentage = (sameRangeHitPercentage * sameRangeTotal * 1 + nearRangeHitPercentage * nearRangeTotal * 4) / ( sameRangeTotal * 1 + nearRangeTotal * 4);
	//														1					*	3			* 	4	 + 0,5				*	3		* 1 / ( 3 * 4 + 3*1)
							
							Double goodness = improveGoodness(sameRangeTotal + nearRangeTotal , bothRangeHitPercentage);
							
							return goodness;
						}
					}
					
					Double goodness = improveGoodness(sameRange.getTotal() , sameRangeHitPercentage);

					return goodness;
				}
				break;
			}
		}
		
		return null;
	}

	
	private static Boolean needToCheckSameRange(ArrayList<WinRangeStatsBean> rangesStats, Integer i) {
		
		if (i == rangesStats.size() - 1){
			if (rangesStats.get(i).getTotal() < 2 )
				return false;
		} 
		else {
			if (rangesStats.get(i).getTotal() + rangesStats.get(i+1).getTotal() < 2 )
				return false;
			//sistema
		}
		
		
		return true;
	}
	
	private static Boolean needToCheckPrevRange(ArrayList<WinRangeStatsBean> rangesStats, Integer i, TeamResultEnum resultOfInterest, Double odds) {
		
		if (resultOfInterest.equals(TeamResultEnum.D))
			return false;
		
		if (i == 0)
			return false;
		
		if (rangesStats.get(i).getTotal() + rangesStats.get(i-1).getTotal() < 2 )
			return false;
		
		if (rangesStats.get(i-1).getTotal() == 0 )
			return false;
		
		if (resultOfInterest.equals(TeamResultEnum.W))
			return false;
		
		
		return true;
	}

	private static Boolean needToCheckNextRange(ArrayList<WinRangeStatsBean> rangesStats, Integer i, TeamResultEnum resultOfInterest, Double odds) {
		
		if (resultOfInterest.equals(TeamResultEnum.D))
			return false;
		
		if (i == rangesStats.size() - 1)
			return false;
		
		if (rangesStats.get(i).getTotal() + rangesStats.get(i+1).getTotal() < 2 )
			return false;
		
		if (rangesStats.get(i+1).getTotal() == 0 )
			return false;
		
		if (resultOfInterest.equals(TeamResultEnum.L))
			return false;
		
		return true;
	}
	
	private static Double improveGoodness(Integer total, Double hitPercentage) {
		
		Double goodness;
		if (AppConstants.ENABLE_ODD_IMPROVEMENTS_ALGHORITM) {
			Double index = 4.0 - total;
			if (index < 0){
				index = 0.0;
			} 
			
			if (hitPercentage == 0) {
				goodness =  1 - Math.pow(0.9, index);  
			}
			else
				goodness = hitPercentage * Math.pow(0.9, index);
		}
		else {
			goodness = hitPercentage;
		}	
		
		if (goodness.toString().length() > 5)
			goodness =  new Double(goodness.toString().substring(0,5));

		return goodness;
	}
	
	
	
	private static void initStaticFields() {
//		if (allMatchesOdds == null){
//			allMatchesOdds =  EventsOddsDownloader.retrieveEventsOdds();
//		}
//		allTeams =  ResultParserOld.retrieveTeams();
//		
//		allAnalyzedChampsWinHome =  ResultAnalyzer.retrieveTeamsToRangeStatsWinHome();
//		allAnalyzedChampsWinAway =  ResultAnalyzer.retrieveTeamsToRangeStatsWinAway();
//		allAnalyzedChampsWinAll =  ResultAnalyzer.retrieveTeamsToRangeStatsWinAll();
//		
//		allAnalyzedChampsUoHome =  ResultAnalyzer.retrieveTeamsToRangeStatsUoHome();
//		allAnalyzedChampsUoAway =  ResultAnalyzer.retrieveTeamsToRangeStatsUoAway();
//		
//		trends = TrendCalculator.retrieveTrends();
//		rankings = RankingCalculator.retrieveRankings();
//		
//		
	}
	
	public static  HashMap<ChampEnum, ArrayList<EventOddsBean>>  retrieveMatchesOddsWithGoodness() {
		HashMap<ChampEnum, ArrayList<EventOddsBean>> matchesOddWithGoodness = IOUtils.read(AppConstants.MATCHES_ODDS_WITH_GOODNESS_PATH,  HashMap.class);
		return matchesOddWithGoodness;
	}

	

}
