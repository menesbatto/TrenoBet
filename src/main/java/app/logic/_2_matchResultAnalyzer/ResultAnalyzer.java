package app.logic._2_matchResultAnalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.GoalsStatsDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.TeamDao;
import app.dao.tabelle.WinRangeStatsDao;
import app.dao.tabelle.entities.WinRangeStats;
import app.dao.tipologiche.OddsRangeDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.dao.tipologiche.entities.OddsRange;
import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;
import app.logic._1_matchesDownlaoder.model._1x2Leaf;
import app.logic._2_matchResultAnalyzer.model.GoalsStatsBean;
import app.logic._2_matchResultAnalyzer.model.TeamResultEnum;
import app.logic._2_matchResultAnalyzer.model.UoThresholdStats;
import app.logic._2_matchResultAnalyzer.model.WinRangeStatsBean;
import app.utils.AppConstants;
import app.utils.ChampEnum;
import app.utils.IOUtils;
import app.utils.Utils;

@Service
public class ResultAnalyzer {
	@Autowired
	private WinRangeStatsDao winRangeStatsDao;

	@Autowired
	private GoalsStatsDao goalsStatsDao;

	@Autowired
	private MatchoDao matchDao;
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private TimeTypeDao timeTypeDao;
	
	@Autowired
	private OddsRangeDao oddsRangeDao;

	public ArrayList<MatchResult> execute(Integer seasonDay) {
		ChampEnum[] allChamps = ChampEnum.values();
		return execute(seasonDay, allChamps);
	}
	
	public ArrayList<MatchResult> execute(Integer seasonDay, ChampEnum[] champs) {
		
		Date dateOfBet = Utils.getDateOfBet(seasonDay);
		
	
		for (ChampEnum champ : champs){
			Boolean existStatsByChampInSeasonDay = winRangeStatsDao.existStatsByChampInSeasonDay(champ, seasonDay);
			if (existStatsByChampInSeasonDay) {
				continue;
			}
			System.out.println(champ);
			
			ArrayList<MatchResult> teamMatchesAway = matchDao.getDownloadedPastMatchByChampBeforeDateFull(champ, dateOfBet);
			Map<String, ArrayList<MatchResult>> homeMatchesMap = new HashMap<String, ArrayList<MatchResult>>();
			createMatchMap(homeMatchesMap, teamMatchesAway, "H");
			
			Map<String, ArrayList<MatchResult>> awayMatchesMap = new HashMap<String, ArrayList<MatchResult>>();
			createMatchMap(awayMatchesMap, teamMatchesAway, "A");
		
			Map<String, ArrayList<MatchResult>> matchesMapAll = new HashMap<String, ArrayList<MatchResult>>();
			createMatchMap(matchesMapAll, teamMatchesAway, "T");
			
			
			ArrayList<String> teams = teamDao.findTeamsNamesByChamp(champ);
			ArrayList<String> teamsCorrect = new ArrayList<String>();
			for (String team : teams) {
				team = Utils.cleanString(team);
				teamsCorrect.add(team);
			}
			long start = System.nanoTime();
			long duration;
			analyzeWinOdds				(champ, homeMatchesMap, awayMatchesMap, matchesMapAll, teamsCorrect, null, seasonDay);
			long end1 = System.nanoTime();
			duration = (end1 - start)/1000000;  //divide by 1000000 to get milliseconds
			System.out.println(duration);
			
			analyzeUnderOverOdds		(champ, homeMatchesMap, awayMatchesMap, matchesMapAll, teamsCorrect, seasonDay);
			long end2 = System.nanoTime();
			duration = (end2 - end1)/1000000;  //divide by 1000000 to get milliseconds.
			System.out.println(duration);
			
			
			analyzeEuropeanHandicapOdds	(champ, homeMatchesMap, awayMatchesMap, matchesMapAll, teamsCorrect, seasonDay);
			long end3 = System.nanoTime();
			duration = (end3 - end2)/1000000;  //divide by 1000000 to get milliseconds.
			System.out.println(duration);
		}
		
		return null;
	
	}
	
	public void createMatchMap(Map<String, ArrayList<MatchResult>> matchesMap, ArrayList<MatchResult> teamMatches, String playingField) {
		for (MatchResult match : teamMatches) {
			String team1 = null;
			String team2 = null;

			if (playingField.equals("H") || playingField.equals("T")) {
				team1 = match.getHomeTeam();
			}
			if (playingField.equals("A") || playingField.equals("T")) {
				team2 = match.getAwayTeam();
			}
			
			//team = team.replace(" ", " ").trim(); sono due caratteri simili ma diversi
			if (team1!= null) {
				team1 = Utils.cleanString(team1);
				if ( !matchesMap.keySet().contains( team1 ) )
					matchesMap.put(team1, new ArrayList<MatchResult>()) ;
				matchesMap.get(team1).add(match);
			}
			
			if (team2!= null) {
				team2 = Utils.cleanString(team2);
				if ( !matchesMap.keySet().contains( team2 ) )
					matchesMap.put(team2, new ArrayList<MatchResult>()) ;
				matchesMap.get(team2).add(match);
			}
				
						
		}
	}
	
	// ogni volta che l'atalanta giocando in casa aveva quotato il -2 handicap a 1,6 si è comportata cosi
	
	// Probabilita che l'atalanta giocando in casa ed avendo quotato l'handicap a 2 (m2) ad una cifra tra 1,5 e 1,5 vinca
	private void analyzeEuropeanHandicapOdds(ChampEnum champ, Map<String, ArrayList<MatchResult>> matchesMapHome, Map<String, ArrayList<MatchResult>> matchesMapAway, Map<String, ArrayList<MatchResult>> matchesMapAll, ArrayList<String> teams, Integer seasonDay) {
		
		for (HomeVariationEnum homeVariation : HomeVariationEnum.getSubSet())
			analyzeWinOdds(champ, matchesMapHome, matchesMapAway, matchesMapAll, teams, homeVariation, seasonDay);
	
	}

	

	private void analyzeWinOdds(ChampEnum champ, Map<String, ArrayList<MatchResult>> matchesMapHome, Map<String, ArrayList<MatchResult>> matchesMapAway, Map<String, ArrayList<MatchResult>> matchesMapAll, ArrayList<String> teams, HomeVariationEnum homeVariation, Integer seasonDay) {
		List<WinRangeStats> createdWinRangeToSave = new ArrayList<WinRangeStats>();
		
		for (String teamName : teams) {
			
			// HOME
			List<WinRangeStats> homeWinStats = analyzeTeamResultWin(teamName, matchesMapHome.get(teamName), champ, "H", homeVariation, seasonDay);
			createdWinRangeToSave.addAll(homeWinStats);
			
			// AWAY
			List<WinRangeStats> awayWinStats = analyzeTeamResultWin(teamName,  matchesMapAway.get(teamName), champ, "A", homeVariation, seasonDay);
			createdWinRangeToSave.addAll(awayWinStats);
			
			// TOTAL
			List<WinRangeStats> totalWinStats = analyzeTeamResultWin(teamName, matchesMapAll.get(teamName), champ, "T", homeVariation, seasonDay);
			createdWinRangeToSave.addAll(totalWinStats);
			
		}
		
		winRangeStatsDao.saveWinRangeStats(createdWinRangeToSave);
		
	}

	// Ogni volta che l'atalanta che gioca in casa � quotata a una quota che va da 1,5 a 1,7 allora finora si � comportata cosi. 
	// Ogni volta che l'atalanta che gioca fuori casa � quotata a una quota che va da 1,5 a 1,7 allora finora si � comportata cosi. 
	private List<WinRangeStats> analyzeTeamResultWin(String teamName, ArrayList<MatchResult> matches, ChampEnum champ, String playingField, HomeVariationEnum homeVariation, Integer seasonDay) {
		List<WinRangeStats> createdWinRangeToSave = new ArrayList<WinRangeStats>();
		
		List<TimeTypeEnum> timeTypes = timeTypeDao.findAllTimeTypeEnum();
		List<OddsRange> oddsRangeList = oddsRangeDao.findAll();
		
		ArrayList<WinRangeStatsBean> allRanges = new ArrayList<WinRangeStatsBean>();
		String trend;
		for (TimeTypeEnum timeType : timeTypes) {
			ArrayList<WinRangeStatsBean> ranges = createRanges(oddsRangeList, timeType, teamName, seasonDay);
			
			WinRangeStatsBean winRangeStatsBean = new WinRangeStatsBean();
			winRangeStatsBean.setTimeTypeBean(timeType);
			if (matches ==  null) {
				continue;
			}
			trend = "";
			for (MatchResult m : matches){
				
				if (m.getFTHG() == null){
					continue;
				}
				
				Integer homeGoals = 0;
				Integer awayGoals = 0;
				String resultString = "";
				String resultStringTest = null;			//da cancellare
				_1x2Leaf avg1x2Odds;
				if (homeVariation == null) {
					avg1x2Odds = m.get_1x2().get(timeType).getAvg1x2Odds();
				}
				else {
					avg1x2Odds = m.getEh().get(timeType).getMap().get(homeVariation).getAvg1x2Odds();
				}
				
				MatchResultEnum resultEnum = null;

				switch (timeType) {
					case _final:
						homeGoals = m.getFTHG();
						awayGoals = m.getFTAG();		
						resultStringTest = m.getFTR();		//da cancellare
//						result = MatchResultEnum.valueOf(resultString);
						break;
					case _1:
						homeGoals = m.getHTHG();
						awayGoals = m.getHTAG();
						resultStringTest = m.getHTR();		//da cancellare
//						result = MatchResultEnum.valueOf(resultString);
						break;
					case _2:
						homeGoals = m.getFTHG() - m.getHTHG();
						awayGoals = m.getFTAG() - m.getHTAG();
						
						break;
	
					default:
						break;
				}
				if (homeVariation != null) 
					homeGoals += homeVariation.getValueNum();
				
				
				if (homeGoals > awayGoals)				resultString = "H";
				else if (homeGoals == awayGoals)		resultString = "D";
				else									resultString = "A";
				
			
				String playingFieldDeducted = null;
				if (teamName.equals(m.getHomeTeam()))
					playingFieldDeducted = "H";
				else
					playingFieldDeducted = "A";
				
//				if (!playingFieldDeducted.equals(playingField))
//					System.out.println("ERRORE");
				
				
				// Calcolo TREND
				if (resultString.equals("D"))
					trend += TeamResultEnum.D.name();
				else if (playingFieldDeducted.equals(resultString)) {
					trend += TeamResultEnum.W.name();
				}
				else if (!playingFieldDeducted.equals(resultString)) {
					trend += TeamResultEnum.L.name();
				}
				else {
					System.out.println("Errore durante il calcolo del trend");
				}
				
				resultEnum = MatchResultEnum.valueOf(resultString);

				if (homeVariation == null)
					if (resultStringTest != null)
						if (!resultStringTest.equals(resultString))
							System.out.println("C'è un errore nel calcolo del risultato ");
				
				
				Double oddsOfTeamAnalyzed = null;
				
				// Se è quotato allora aggiorno ANCHE lo specifico range (mentre quello generale lo aggiorno sempre)
				if (avg1x2Odds != null) {
					Double homeOdds = avg1x2Odds.getOdd1();
					Double drawOdds = avg1x2Odds.getOddX();
					Double awayOdds = avg1x2Odds.getOdd2();
					
					// in caso non ci sono quote da cancellare
					if (homeOdds == null || drawOdds == null || awayOdds == null){	
						homeOdds = 0.0;
						drawOdds = 0.0;
						awayOdds = 0.0;
					}
					
					Double percHome = 1/homeOdds;
					Double percDraw = 1/drawOdds;
					Double percAway = 1/awayOdds;
					
					Double percTotal = percHome + percDraw + percAway;
		
					Double percHomeAdjusted = percHome  / percTotal;
					Double percDrawAdjusted = percDraw  / percTotal;
					Double percAwayAdjusted = percAway  / percTotal;
					
					Double homeOddsAdjusted = 1 / percHomeAdjusted;
					Double drawOddsAdjusted = 1 / percDrawAdjusted;
					Double awayOddsAdjusted = 1 / percAwayAdjusted;
					
					
					// capisce se la quota su cui andare a inserire la statistica  e' della squadra in casa o fuoricasa
					if (teamName.equals(m.getHomeTeam()))
						if (AppConstants.PERCENTIFY_ODDS_ON)
							oddsOfTeamAnalyzed = homeOddsAdjusted;
						else
							oddsOfTeamAnalyzed = homeOdds;
					else 
						if (AppConstants.PERCENTIFY_ODDS_ON)
							oddsOfTeamAnalyzed = awayOddsAdjusted;
						else
							oddsOfTeamAnalyzed = awayOdds;
				}
				updateRangeStats(ranges, resultEnum, oddsOfTeamAnalyzed, playingFieldDeducted);
				
			}
			
			if (!matches.isEmpty())
				enrichTeamResult(ranges, playingField);
			
			ranges.get(ranges.size()-1).setTrend(trend);
			
			allRanges.addAll(ranges);
		}
		
		//System.out.println(teamName + " " + this.i++ + " " + homeVariation);
		List<WinRangeStats> createWinEhRangeToSave = winRangeStatsDao.createWinRangesToSave(allRanges, teamName, champ, playingField, homeVariation);
		createdWinRangeToSave.addAll(createWinEhRangeToSave);
		
		return createdWinRangeToSave;
	}

	private ArrayList<WinRangeStatsBean> createRanges(List<OddsRange> oddsRangeList, TimeTypeEnum timeType, String teamName, Integer seasonDay) {
		ArrayList<WinRangeStatsBean> ranges = new ArrayList<WinRangeStatsBean>();
		WinRangeStatsBean elem;
		for (OddsRange elemRange : oddsRangeList) {
			elem = new WinRangeStatsBean();
			elem.setTeamName(teamName);
			elem.setTimeTypeBean(timeType);
			elem.setEdgeUp(elemRange.getValueUp());
			elem.setEdgeDown(elemRange.getValueDown());
			elem.setRange(elemRange.getValueDown() + "-" + elemRange.getValueUp());
			elem.setSeasonDay(seasonDay);
			ranges.add(elem);
		}
		elem = new WinRangeStatsBean();
		elem.setTeamName(teamName);
		elem.setTimeTypeBean(timeType);
		elem.setSeasonDay(seasonDay);
		ranges.add(elem);
		
		return ranges;
	}

	private static void updateRangeStats(List<WinRangeStatsBean> ranges, MatchResultEnum result, Double hitOdds, String playingFieldDeducted) {
		WinRangeStatsBean total = ranges.get(ranges.size()-1);
		
		if (result.equals(MatchResultEnum.H)){
			if (playingFieldDeducted.equals("H")) 
				total.setWinTot(total.getWinTot() + 1);
			else 
				total.setLoseTot(total.getLoseTot() + 1);
			
//			total.setHomeHits(total.getHomeHits() + 1);
//			total.setDrawMisses(total.getDrawMisses() + 1);
//			total.setAwayMisses(total.getAwayMisses() + 1);
		}
		
		else if (result.equals(MatchResultEnum.D)){
			total.setDrawTot(total.getDrawTot() + 1);
			
//			total.setHomeMisses(total.getHomeMisses() + 1);
//			total.setDrawHits(total.getDrawHits() + 1);
//			total.setAwayMisses(total.getAwayMisses() + 1);
		}
		
		
		
		
		else {//if (result.equals(Result.A)){
			if (playingFieldDeducted.equals("H")) 
				total.setLoseTot(total.getLoseTot() + 1);
			else 
				total.setWinTot(total.getWinTot() + 1);
			
//			total.setHomeMisses(total.getHomeMisses() + 1);
//			total.setDrawMisses(total.getDrawMisses() + 1);
//			total.setAwayHits(total.getAwayHits() + 1);
		}
		
		total.setTotal(total.getTotal() + 1);
		
		if (hitOdds != null) {
		
			for (WinRangeStatsBean range : ranges) {
				if (hitOdds < range.getEdgeUp()){
					if (result.equals(MatchResultEnum.H)){
						if (playingFieldDeducted.equals("H")) 
							range.setWinTot(range.getWinTot() + 1);
						else 
							range.setLoseTot(range.getLoseTot() + 1);
						
//						range.setHomeHits(range.getHomeHits() + 1);
//						range.setDrawMisses(range.getDrawMisses() + 1);
//						range.setAwayMisses(range.getAwayMisses() + 1);
					}
					else if (result.equals(MatchResultEnum.D)){
						range.setDrawTot(range.getDrawTot() + 1);
						
//						range.setHomeMisses(range.getHomeMisses() + 1);
//						range.setDrawHits(range.getDrawHits() + 1);
//						range.setAwayMisses(range.getAwayMisses() + 1);
					}
					else {//if (result.equals(Result.A)){
						if (playingFieldDeducted.equals("H")) 
							range.setLoseTot(range.getLoseTot() + 1);
						else 
							range.setWinTot(range.getWinTot() + 1);
						
//						range.setHomeMisses(range.getHomeMisses() + 1);
//						range.setDrawMisses(range.getDrawMisses() + 1);
//						range.setAwayHits(range.getAwayHits() + 1);
					}
					range.setTotal(range.getTotal() + 1);
					break;
				}
			}
		}
	}

	private static void enrichTeamResult(ArrayList<WinRangeStatsBean> rangeList, String where) {
		for (WinRangeStatsBean range : rangeList){
			Double winPerc = null;
			Double drawPerc = null;
			Double losePerc = null;
			if (range.getTotal() != null && range.getTotal() != 0){
				winPerc = new Double(range.getWinTot()) / new Double (range.getTotal());
				drawPerc = new Double(range.getDrawTot()) / new Double (range.getTotal());
				losePerc = new Double(range.getLoseTot()) / new Double (range.getTotal());
//				if (where.equals("H")){
//					winPerc = new Double(range.getHomeHits()) / new Double (range.getTotal());
//					drawPerc = new Double(range.getDrawHits()) / new Double (range.getTotal());
//					losePerc = new Double(range.getAwayHits()) / new Double (range.getTotal());
//				}
//				else {//if (where.equals("A")){
//					winPerc = new Double(range.getAwayHits()) / new Double (range.getTotal());
//					drawPerc = new Double(range.getDrawHits()) / new Double (range.getTotal());
//					losePerc = new Double(range.getHomeHits()) / new Double (range.getTotal());
//				}
			}
			range.setWinPerc(winPerc);
			range.setDrawPerc(drawPerc);
			range.setLosePerc(losePerc);
		}
	}

	
	
	private void analyzeUnderOverOdds(ChampEnum champ, Map<String, ArrayList<MatchResult>> matchesMapHome, Map<String, ArrayList<MatchResult>> matchesMapAway, Map<String, ArrayList<MatchResult>> matchesMapAll, ArrayList<String> teams, Integer seasonDay ) {
		List<GoalsStatsBean> analyzeTeamResultUoAll = new ArrayList<GoalsStatsBean>();
		List<GoalsStatsBean> analyzeTeamResultUoH;
		List<GoalsStatsBean> analyzeTeamResultUoA;
		List<GoalsStatsBean> analyzeTeamResultUoT;

		for (String teamName : teams) {

			// HOME
			analyzeTeamResultUoH = analyzeTeamResultUo(teamName, matchesMapHome.get(teamName), champ, "H", seasonDay);
			analyzeTeamResultUoAll.addAll(analyzeTeamResultUoH);
			// AWAY
			analyzeTeamResultUoA = analyzeTeamResultUo(teamName, matchesMapAway.get(teamName), champ, "A", seasonDay);
			analyzeTeamResultUoAll.addAll(analyzeTeamResultUoA);
			// TOTAL
			analyzeTeamResultUoT = analyzeTeamResultUo(teamName, matchesMapAll.get(teamName), champ, "T", seasonDay);
			analyzeTeamResultUoAll.addAll(analyzeTeamResultUoT);
			
		}
		
		goalsStatsDao.saveGoalsStats(analyzeTeamResultUoAll, champ);
		
	}
	
	private List<GoalsStatsBean> analyzeTeamResultUo(String teamName, ArrayList<MatchResult> matches, ChampEnum champ, String playingField, Integer seasonDay) {
		List<TimeTypeEnum> timeTypes = timeTypeDao.findAllTimeTypeEnum();
		
		ArrayList<GoalsStatsBean> goalsStatsBeans = new ArrayList<GoalsStatsBean>();
		for (TimeTypeEnum timeType : timeTypes) {
			
			GoalsStatsBean goalsStatsBean = new GoalsStatsBean();//goalsStatsDao.findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champ, timeType, playingField);
			goalsStatsBean.setTimeTypeBean(timeType);
			Integer strikedGoals = 0;
			Integer takenGoals = 0;
			if (matches ==  null) {
				continue;
			}
				
			for (MatchResult m : matches){
				if (m.getFTHG() == null){
					continue;
				}
		
				switch (timeType) {
					case _final:
						if (teamName.equals(m.getHomeTeam())){
							strikedGoals = m.getFTHG();
							takenGoals = m.getFTAG();
						}
						else if (teamName.equals(m.getAwayTeam())){
							takenGoals = m.getFTAG();
							strikedGoals = m.getFTHG();
						}
						break;
						
					case _1:
						if (teamName.equals(m.getHomeTeam())){
							strikedGoals = m.getHTHG();
							takenGoals = m.getHTAG();
						}
						else if (teamName.equals(m.getAwayTeam())){
							takenGoals = m.getHTAG();
							strikedGoals = m.getHTHG();
						}
						break;
					
					case _2:
						if (teamName.equals(m.getHomeTeam())){
							strikedGoals = m.getFTHG() - m.getHTHG();
							takenGoals = m.getFTAG() - m.getHTAG();
						}
						else if (teamName.equals(m.getAwayTeam())){
							takenGoals = m.getFTAG() - m.getHTAG();
							strikedGoals = m.getFTHG() - m.getHTHG();
						}
						break;
	
					default:
						break;
				}
			

				updateGoalsStats(goalsStatsBean, takenGoals, strikedGoals, teamName);
				goalsStatsBean.setPlayingField(playingField);
				goalsStatsBean.setSeasonDay(seasonDay);
				
			}
			
			//CALCOLA LE PERCENTUALI
			for (Entry<UoThresholdEnum, UoThresholdStats> entry : goalsStatsBean.getThresholdMap().entrySet()){
				UoThresholdStats value = entry.getValue();
				double totalMatches = goalsStatsBean.getTotalMatches().doubleValue();
				if (totalMatches != 0) {
					value.setUnderPerc( value.getUnderHit().doubleValue() / totalMatches );
					value.setOverPerc( value.getOverHit().doubleValue() / totalMatches );
				}
			}
	
			goalsStatsBeans.add(goalsStatsBean);
		}
		
		return goalsStatsBeans;
	}

	private void updateGoalsStats(GoalsStatsBean goalsStatsBean, Integer takenGoals, Integer strikedGoals, String teamName) {
		
		Integer allGoals = takenGoals + strikedGoals;
		goalsStatsBean.setTotalMatches(goalsStatsBean.getTotalMatches() + 1);
		goalsStatsBean.setStrikedGoalsTotal(goalsStatsBean.getStrikedGoalsTotal() 	+ strikedGoals);
		goalsStatsBean.setTakenGoalsTotal(goalsStatsBean.getTakenGoalsTotal() 		+ takenGoals);
		goalsStatsBean.setTotalGoals(goalsStatsBean.getTotalGoals() 				+ strikedGoals + takenGoals);
		goalsStatsBean.setTeamName(teamName);
		
		String trend;
		UoThresholdEnum key;
		UoThresholdStats value;
		for (Entry<UoThresholdEnum, UoThresholdStats> entry : goalsStatsBean.getThresholdMap().entrySet()){
			key = entry.getKey();
			value = entry.getValue();
			
			trend = value.getTrend();
			if (key.getValueNum() > allGoals) {
				value.setUnderHit( value.getUnderHit() + 1 );
				trend += MatchResultEnum.U;
			}
			else  {//if (elem.getThreshold() < allGoals)
				value.setOverHit( value.getOverHit() + 1 );
				trend += MatchResultEnum.O;
			}
			 value.setTrend(trend);
		}
		
	}
}
