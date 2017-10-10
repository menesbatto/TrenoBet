package app.logic._2_matchResultAnalyzer;

import java.util.ArrayList;
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

//	@Autowired
//	private WinEhRangeStatsDao winEhRangeStatsDao;

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

	public ArrayList<MatchResult> execute(){
		
		for (ChampEnum champ : ChampEnum.values()){
			System.out.println(champ);
			
			ArrayList<MatchResult> teamMatchesAway = matchDao.getDownloadedPastMatchByChampFull(champ);
			Map<String, ArrayList<MatchResult>> homeMatchesMap = new HashMap<String, ArrayList<MatchResult>>();
			createMatchMap(homeMatchesMap, teamMatchesAway, "H");
			Map<String, ArrayList<MatchResult>> awayMatchesMap = new HashMap<String, ArrayList<MatchResult>>();
			createMatchMap(awayMatchesMap, teamMatchesAway, "A");
			
			ArrayList<String> teams = teamDao.findByChamp(champ);
			ArrayList<String> teamsCorrect = new ArrayList<String>();
			for (String team : teams) {
				team = Utils.cleanString(team);
				teamsCorrect.add(team);
			}
			long start = System.nanoTime();
			long duration;
			analyzeWinOdds				(champ, homeMatchesMap, awayMatchesMap, teamsCorrect, null);
			long end1 = System.nanoTime();
			duration = (end1 - start)/1000000;  //divide by 1000000 to get milliseconds
			System.out.println(duration);
			
			analyzeUnderOverOdds		(champ, homeMatchesMap, awayMatchesMap, teamsCorrect);
			long end2 = System.nanoTime();
			duration = (end2 - end1)/1000000;  //divide by 1000000 to get milliseconds.
			System.out.println(duration);
			
			
			analyzeEuropeanHandicapOdds	(champ, homeMatchesMap, awayMatchesMap, teamsCorrect);
			long end3 = System.nanoTime();
			duration = (end3 - end2)/1000000;  //divide by 1000000 to get milliseconds.
			System.out.println(duration);
		}
		
		return null;
	
	}
	
	public void createMatchMap(Map<String, ArrayList<MatchResult>> matchesMap, ArrayList<MatchResult> teamHomeMatches, String playingField) {
		for (MatchResult match : teamHomeMatches) {
			String team = "";
			if (playingField.equals("H")) {
				team = match.getHomeTeam();
			}
			else { //if (playingField.equals("A")
				team = match.getAwayTeam();
			}
			
			//team = team.replace(" ", " ").trim(); sono due caratteri simili ma diversi
			team = Utils.cleanString(team);

			if ( !matchesMap.keySet().contains( team ) )
				matchesMap.put(team, new ArrayList<MatchResult>()) ;

			matchesMap.get(team).add(match);

						
		}
	}
	
	// ogni volta che l'atalanta giocando in casa aveva quotato il -2 handicap a 1,6 si è comportata cosi
	
	// Probabilita che l'atalanta giocando in casa ed avendo quotato l'handicap a 2 (m2) ad una cifra tra 1,5 e 1,5 vinca
	private void analyzeEuropeanHandicapOdds(ChampEnum champ, Map<String, ArrayList<MatchResult>> matchesMapHome, Map<String, ArrayList<MatchResult>> matchesMapAway, ArrayList<String> teams) {
		
		for (HomeVariationEnum homeVariation : HomeVariationEnum.getSubSet())
			analyzeWinOdds(champ, matchesMapHome, matchesMapAway, teams, homeVariation);
	
	}

	

	

	

	private void analyzeWinOdds(ChampEnum champ, Map<String, ArrayList<MatchResult>> matchesMapHome, Map<String, ArrayList<MatchResult>> matchesMapAway, ArrayList<String> teams, HomeVariationEnum homeVariation) {
		List<WinRangeStats> createdWinRangeToSave = new ArrayList<WinRangeStats>();
		
		for (String teamName : teams) {
			
			// HOME
			List<WinRangeStats> homeWinStats = analyzeTeamResultWin(teamName, matchesMapHome.get(teamName), champ, "H", homeVariation);
			createdWinRangeToSave.addAll(homeWinStats);
			
			// AWAY
			List<WinRangeStats> awayWinStats = analyzeTeamResultWin(teamName,  matchesMapAway.get(teamName), champ, "A", homeVariation);
			createdWinRangeToSave.addAll(awayWinStats);
			
			// TOTAL
//			winRangeStatsDao.calculateWinStatsNoPlayingField(teamName, champ);
			
		}
		
			winRangeStatsDao.saveWinRangeStats(createdWinRangeToSave);
		
	}
	
	// Ogni volta che l'atalanta che gioca in casa � quotata a una quota che va da 1,5 a 1,7 allora finora si � comportata cosi. 
	// Ogni volta che l'atalanta che gioca fuori casa � quotata a una quota che va da 1,5 a 1,7 allora finora si � comportata cosi. 
	private List<WinRangeStats> analyzeTeamResultWin(String teamName, ArrayList<MatchResult> matches, ChampEnum champ, String playingField, HomeVariationEnum homeVariation) {
		List<WinRangeStats> createdWinRangeToSave = new ArrayList<WinRangeStats>();
		
		List<TimeTypeEnum> timeTypes = timeTypeDao.findAllTimeTypeEnum();
		List<OddsRange> oddsRangeList = oddsRangeDao.findAll();
		
		ArrayList<WinRangeStatsBean> allRanges = new ArrayList<WinRangeStatsBean>();
		for (TimeTypeEnum timeType : timeTypes) {
			ArrayList<WinRangeStatsBean> ranges = createRanges(oddsRangeList, timeType, teamName);
			
			WinRangeStatsBean winRangeStatsBean = new WinRangeStatsBean();
			winRangeStatsBean.setTimeTypeBean(timeType);
			if (matches ==  null) {
				continue;
			}
			
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
				if (avg1x2Odds == null) {
					continue;
				}
				Double homeOdds = avg1x2Odds.getOdd1();
				Double drawOdds = avg1x2Odds.getOddX();
				Double awayOdds = avg1x2Odds.getOdd2();
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
				
				resultEnum = MatchResultEnum.valueOf(resultString);

				if (homeVariation == null)
					if (resultStringTest != null)
						if (!resultStringTest.equals(resultString))
							System.out.println("C'è un errore nel calcolo del risultato ");
				
				
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
				
				Double oddsOfTeamAnalyzed = null;
				
				// capisce se la quota su cui andare a inserire la statistica � della squadra in casa o fuoricasa
				if (teamName.equals(m.getHomeTeam()))
					if (AppConstants.PERCENTIFY_ODDS_ON)
						oddsOfTeamAnalyzed = homeOddsAdjusted;
					else
						oddsOfTeamAnalyzed = percHome;
				else 
					if (AppConstants.PERCENTIFY_ODDS_ON)
						oddsOfTeamAnalyzed = awayOddsAdjusted;
					else
						oddsOfTeamAnalyzed = percAway;
				
				updateRangeStats(ranges, resultEnum, oddsOfTeamAnalyzed);
				
			}
			
			if (!matches.isEmpty())
				enrichTeamResult(ranges, playingField);
		
			allRanges.addAll(ranges);
		}
		
		//System.out.println(teamName + " " + this.i++ + " " + homeVariation);
		List<WinRangeStats> createWinEhRangeToSave = winRangeStatsDao.createWinRangesToSave(allRanges, teamName, champ, playingField, homeVariation);
		createdWinRangeToSave.addAll(createWinEhRangeToSave);
		
		return createdWinRangeToSave;
	}

	private ArrayList<WinRangeStatsBean> createRanges(List<OddsRange> oddsRangeList, TimeTypeEnum timeType, String teamName) {
		ArrayList<WinRangeStatsBean> ranges = new ArrayList<WinRangeStatsBean>();
		for (OddsRange elemRange : oddsRangeList) {
			WinRangeStatsBean elem = new WinRangeStatsBean();
			elem.setTeamName(teamName);
			elem.setTimeTypeBean(timeType);
			elem.setEdgeUp(elemRange.getValueUp());
			elem.setEdgeDown(elemRange.getValueDown());
			elem.setRange(elemRange.getValueDown() + "-" + elemRange.getValueUp());
			ranges.add(elem);
		}
		return ranges;
	}

	private static void updateRangeStats(List<WinRangeStatsBean> ranges, MatchResultEnum result, Double hitOdds) {
		for (WinRangeStatsBean range : ranges) {
			if (hitOdds < range.getEdgeUp()){
				if (result.equals(MatchResultEnum.H)){
					range.setHomeHits(range.getHomeHits() + 1);
					range.setDrawMisses(range.getDrawMisses() + 1);
					range.setAwayMisses(range.getAwayMisses() + 1);
				}
				else if (result.equals(MatchResultEnum.D)){
					range.setHomeMisses(range.getHomeMisses() + 1);
					range.setDrawHits(range.getDrawHits() + 1);
					range.setAwayMisses(range.getAwayMisses() + 1);
				}
				else {//if (result.equals(Result.A)){
					range.setHomeMisses(range.getHomeMisses() + 1);
					range.setDrawMisses(range.getDrawMisses() + 1);
					range.setAwayHits(range.getAwayHits() + 1);
				}
				range.setTotal(range.getTotal() + 1);
				break;
			}
		}
		
	}

	private static void enrichTeamResult(ArrayList<WinRangeStatsBean> rangeList, String where) {
		for (WinRangeStatsBean range : rangeList){
			Double winPerc = null;
			Double drawPerc = null;
			Double losePerc = null;
			if (range.getTotal() != null && range.getTotal() != 0){
				if (where.equals("H")){
					winPerc = new Double(range.getHomeHits()) / new Double (range.getTotal());
					drawPerc = new Double(range.getDrawHits()) / new Double (range.getTotal());
					losePerc = new Double(range.getAwayHits()) / new Double (range.getTotal());
				}
				else {//if (where.equals("A")){
					winPerc = new Double(range.getAwayHits()) / new Double (range.getTotal());
					drawPerc = new Double(range.getDrawHits()) / new Double (range.getTotal());
					losePerc = new Double(range.getHomeHits()) / new Double (range.getTotal());
				}
			}
			range.setWinPerc(winPerc);
			range.setDrawPerc(drawPerc);
			range.setLosePerc(losePerc);
		}
	}

	
	
	private void analyzeUnderOverOdds(ChampEnum champ, Map<String, ArrayList<MatchResult>> matchesMapHome, Map<String, ArrayList<MatchResult>> matchesMapAway, ArrayList<String> teams ) {
		List<GoalsStatsBean> analyzeTeamResultUoAll = new ArrayList<GoalsStatsBean>();
		List<GoalsStatsBean> analyzeTeamResultUoH;
		List<GoalsStatsBean> analyzeTeamResultUoA;

		for (String teamName : teams) {

			// HOME
			analyzeTeamResultUoH = analyzeTeamResultUo(teamName, matchesMapHome.get(teamName), champ, "H");
			analyzeTeamResultUoAll.addAll(analyzeTeamResultUoH);
			// AWAY
			analyzeTeamResultUoA = analyzeTeamResultUo(teamName, matchesMapAway.get(teamName), champ, "A");
			analyzeTeamResultUoAll.addAll(analyzeTeamResultUoA);
			
			
			// TOTAL
			//goalsStatsDao.calculateGoalsStatsNoPlayingField(teamName, champ, analyzeTeamResultUoH, analyzeTeamResultUoA);
		}
		
		goalsStatsDao.saveGoalsStats(analyzeTeamResultUoAll, champ);
		
	}
	
	private List<GoalsStatsBean> analyzeTeamResultUo(String teamName, ArrayList<MatchResult> matches, ChampEnum champ, String playingField) {
		List<TimeTypeEnum> timeTypes = timeTypeDao.findAllTimeTypeEnum();
		
		ArrayList<GoalsStatsBean> goalsStatsBeans = new ArrayList<GoalsStatsBean>();

		for (TimeTypeEnum timeType : timeTypes) {
			
			GoalsStatsBean goalsStatsBean = new GoalsStatsBean();//goalsStatsDao.findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champ, timeType, playingField);
			goalsStatsBean.setTimeTypeBean(timeType);
			Integer strikedGoals = 0;
			Integer takenGoals = 0;
			if (matches==  null) {
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
		
		for (Entry<UoThresholdEnum, UoThresholdStats> entry : goalsStatsBean.getThresholdMap().entrySet()){
			UoThresholdEnum key = entry.getKey();
			UoThresholdStats value = entry.getValue();
			
			if (key.getValueNum() > allGoals)
				value.setUnderHit( value.getUnderHit() + 1 );
			else //if (elem.getThreshold() < allGoals)
				value.setOverHit( value.getOverHit() + 1 );

		}
		
	}
	
}
