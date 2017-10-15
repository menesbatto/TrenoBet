package app.logic._7_betAnalyzer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.EventOddsDao;
import app.dao.tabelle.SingleBetDao;
import app.logic._1_matchesDownlaoder.model.BetType;
import app.logic._1_matchesDownlaoder.model.EventOddsBean;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.SingleBetBean;
import app.logic._2_matchResultAnalyzer.ResultAnalyzer;
import app.logic._3_rankingCalculator.RankingCalculator;
import app.logic._4_trendCalculator.TrendCalculator;
import app.logic._5_goodnessCalculator.GoodnessCalculator;
import app.logic._6_betCreator.BetCreator;
import app.logic._7_betAnalyzer.model.BetAnalysis;
import app.utils.AppConstants;
import app.utils.ChampEnum;

@Service
public class BetAnalyzer {
	
	@Autowired
	private SingleBetDao singleBetDao;
	
	private static HashMap<ChampEnum, ArrayList<MatchResult>> allMatchesResults;
	private static HashMap<Integer, BetAnalysis> allBetsAnalysis;
	private static HashMap<ChampEnum, HashMap<Integer, BetAnalysis>> betsAnalysisChamps;
	
	private static Double initialInvestment;
	private static Double initialInvestmentSure;
	private static Double initialInvestmentWithSpent;
	private static Double spent = 0.0;
	private static Double quotaToBet;
	private static int i;
	
	public static void main(String args[]) {
//		initStaticFields();
//		for (int i = 30 ; i< 36 ; i ++){
////			execute(i);
//			System.out.print(".");
//		}
//		
//		for (Entry<ChampEnum, HashMap<Integer, BetAnalysis>> entry : betsAnalysisChamps.entrySet()){
//			HashMap<Integer, BetAnalysis> bas = entry.getValue();
////			System.out.println(entry.getKey());
////			BetAnalysis basChamp = calculateTotalGain(bas);
////			System.out.println(basChamp);
////			System.out.println("#########################################################");
//		}		
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
////		BetAnalysis baTot = calculateTotalGain(allBetsAnalysis);
////		System.out.println(baTot);
	}

	private BetAnalysis calculateTotalGain(HashMap<Integer, BetAnalysis> betAnalysisMap) {
		BetAnalysis baTot = new BetAnalysis();
		for (Entry<Integer, BetAnalysis> entry : betAnalysisMap.entrySet()){
			BetAnalysis ba = entry.getValue();
			baTot.upUoGrossWinAmount(ba.getUoGrossWinAmount());
			baTot.upWinGrossWinAmount(ba.getWinGrossWinAmount());
			baTot.setWinSpentAmount(baTot.getWinSpentAmount() + ba.getWinSpentAmount());
			baTot.setUoSpentAmount(baTot.getUoSpentAmount() + ba.getUoSpentAmount());
			baTot.setUoHit(baTot.getUoHit()+ba.getUoHit());
			baTot.setUoTot(baTot.getUoTot()+ba.getUoTot());
			baTot.setWinHit(baTot.getWinHit()+ba.getWinHit());
			baTot.setWinTot(baTot.getWinTot()+ba.getWinTot());
			
			
		}
		return baTot;
	}
	
//	public void execute(int week){
	public void execute(){
//		i++;
//		HashMap<ChampEnum, ArrayList<MatchResult>> partialHistory = getPartialHistoryForSpecificWeek(week);
//		HashMap<ChampEnum, ArrayList<MatchResult>> bettableMatches = getBettableMatchesForSpecificWeek(week);
//		HashMap<ChampEnum, ArrayList<EventOddsBean>> allMatchesOdds = convertMatchResultInEventsOdds(bettableMatches);//trasformazione dei MatchResult negli EventOddsBean 
		
		for (ChampEnum champ : ChampEnum.values()){
		
			List<SingleBetBean> retrieveSingleBets = singleBetDao.retrieveSingleBetsToCheck(champ);

			System.out.println(retrieveSingleBets);
		
		}
	}
		
		
		
		
//		ResultAnalyzer.execute(partialHistory);
//		RankingCalculator.execute();
//		TrendCalculator.execute();
//		GoodnessCalculator.execute(allMatchesOdds);
//		BetCreator.execute();
//		System.out.println("\n\n\n ### " + week + " #################################### \n\n\n");
//		BetAnalysis ba = analyzeBet(bettableMatches, week);
//		System.out.println(ba);
//		if (initialInvestmentWithSpent < 100){
//			spent += initialInvestmentWithSpent - 100;
//			initialInvestmentWithSpent = 100.0;
//		}
//		
//		if (ba.getAllTot() >= 7) {
//			initialInvestmentWithSpent = initialInvestmentWithSpent * (ba.getPercentageVariation() + 100) / 100;
//			initialInvestmentSure = initialInvestmentSure * (1-quotaToBet) + initialInvestmentSure * quotaToBet * (ba.getPercentageVariation() + 100) / 100;
//			initialInvestment = initialInvestment * (ba.getPercentageVariation() + 100) / 100;
//			System.out.println("Settimana: " + i + "\tAndamento:\t" + initialInvestment);
//			System.out.println("Settimana: " + i + "\tAndamentoSure:\t " + initialInvestmentSure);
//			System.out.println("Settimana: " + i + "\tAndamentoSpent:\t " + initialInvestmentWithSpent);
//			System.out.println("Spesi: " + i + "\t" + spent);
//		}
//		
//		allBetsAnalysis.put(week, ba);
//		
//	}
//	
//
//	private static BetAnalysis analyzeBet(HashMap<ChampEnum, ArrayList<MatchResult>> bettableMatches, int week) {
//		HashMap<ChampEnum, ArrayList<EventOddsBean>> retrieveMainBet = BetCreator.retrieveMainBet();
////		HashMap<ChampEnum, ArrayList<EventOddsBean>> retrieveSurpriseBet = BetCreator.retrieveSurpriseBet();
////		HashMap<ChampEnum, ArrayList<EventOddsBean>> retrieveMainBet = BetCreator.retrieveSurpriseBet();
////		System.out.println(retrieveMainBet);
//		BetAnalysis ba = new BetAnalysis();
//		BetAnalysis baSingleWeekChamp = new BetAnalysis();
//		
//		for(ChampEnum champ : ChampEnum.values()){
//			baSingleWeekChamp = new BetAnalysis();
//			ArrayList<MatchResult> results = bettableMatches.get(champ);
//			ArrayList<EventOddsBean> bets = retrieveMainBet.get(champ);
//			for (EventOddsBean bet : bets){
//				for (MatchResult result : results){
//					if (bet.getHomeTeam().equals(result.getHomeTeam()) && bet.getAwayTeam().equals(result.getAwayTeam())){
//						if (bet.getBetType().equals(BetType.WIN)){
//							if (bet.getMatchResult().toString().equals(result.getFTR())){
//								
//								baSingleWeekChamp.upWinHit();
//								baSingleWeekChamp.upWinGrossWinAmount(bet.getWinOdds());
//								ba.upWinHit();
//								ba.upWinGrossWinAmount(bet.getWinOdds());
//							}
//							baSingleWeekChamp.upWinTot();
//							ba.upWinTot();
//						}
//						else if (bet.getBetType().equals(BetType.UNDER_OVER)){
//							//capisci
//							MatchResultEnum uo = result.getFTHG() + result.getFTAG() > 2 ? MatchResultEnum.O : MatchResultEnum.U;
//							if (bet.getMatchResult().equals(uo)){
//								baSingleWeekChamp.upUoHit();
//								baSingleWeekChamp.upUoGrossWinAmount(bet.getWinOdds());
//								ba.upUoHit();
//								ba.upUoGrossWinAmount(bet.getWinOdds());
//							}
//							baSingleWeekChamp.upUoTot();
//							ba.upUoTot();
//						}
//					}
//				}
//			}
//			
//			baSingleWeekChamp.setWinSpentAmount(baSingleWeekChamp.getWinTot() * 1.0);
//			baSingleWeekChamp.setUoSpentAmount(baSingleWeekChamp.getUoTot() * 1.0);
//			betsAnalysisChamps.get(champ).put(week, baSingleWeekChamp);
//			
//		}
//		ba.setWinSpentAmount(ba.getWinTot() * 1.0);
//		ba.setUoSpentAmount(ba.getUoTot() * 1.0);
////		System.out.println(ba);
//		
//		return ba;
//		
//		
//	}
//
//	private static HashMap<ChampEnum, ArrayList<EventOddsBean>> convertMatchResultInEventsOdds( HashMap<ChampEnum, ArrayList<MatchResult>> bettableMatches) {
//		HashMap<ChampEnum, ArrayList<EventOddsBean>> allMatchesOddsCurrent = new HashMap<ChampEnum, ArrayList<EventOddsBean>>();
//		for (Entry<ChampEnum, ArrayList<MatchResult>> entry : bettableMatches.entrySet()){
//			allMatchesOddsCurrent.put(entry.getKey(), new ArrayList<EventOddsBean>());
//			for (MatchResult matchResult : entry.getValue()){
//				EventOddsBean eo = convertSingleMatchResultInEventsOdds(matchResult);
//				if (eo!=null){
//					allMatchesOddsCurrent.get(entry.getKey()).add(eo);
//				}
//			}
//		}
//		return allMatchesOddsCurrent;
//	}
//
//	private static EventOddsBean convertSingleMatchResultInEventsOdds(MatchResult matchResult) {
//		EventOddsBean eo = new EventOddsBean();
//		eo.setHomeTeam(matchResult.getHomeTeam());
//		eo.setAwayTeam(matchResult.getAwayTeam());
//		eo.setOdds1(matchResult.getB365H());
//		eo.setOddsX(matchResult.getB365D());
//		eo.setOdds2(matchResult.getB365A());
//		if (eo.getOdds1() == null || eo.getOdds2() == null || eo.getOddsX() == null){
//			eo.setOdds1(matchResult.getBWH());
//			eo.setOddsX(matchResult.getBWD());
//			eo.setOdds2(matchResult.getBWA());
//		}	
//		if (eo.getOdds1() == null || eo.getOdds2() == null || eo.getOddsX() == null){
//			eo.setOdds1(matchResult.getPSCH());
//			eo.setOddsX(matchResult.getPSCD());
//			eo.setOdds2(matchResult.getPSCA());
//		}	
//		if (eo.getOdds1() == null || eo.getOdds2() == null || eo.getOddsX() == null){
//			return null;  //PER DATI MANCANTI
//		}
//		eo.setOddsU(matchResult.getBbAvU2_5());
//		eo.setOddsO(matchResult.getBbAvO2_5());
//		eo.setDate(matchResult.getMatchDate());
//		return eo;
//	}
//
//	private static HashMap<ChampEnum, ArrayList<MatchResult>> getBettableMatchesForSpecificWeek(int i) {
//		Date dateOfBet = getDateOfBet(i);		//scommessa su partite da questa settimana...
//
//		Date betEndDate = getBetEndDate(i);		//...fino a 6 giorni dopo
//		
//		HashMap<ChampEnum, ArrayList<MatchResult>> bettableMatches = SerializationUtils.clone(allMatchesResults);
//		
//		ArrayList<MatchResult> toRemove;
//		for (Entry<ChampEnum, ArrayList<MatchResult>> entry : bettableMatches.entrySet()) {
//			toRemove = new ArrayList<MatchResult>();
//			for (MatchResult matchResult : entry.getValue()) {
//				if (matchResult.getMatchDate().before(dateOfBet) || matchResult.getMatchDate().after(betEndDate)) {
//					toRemove.add(matchResult);
//				}
//			}
//			entry.getValue().removeAll(toRemove);
//		}
//
//		return bettableMatches;
//	}
//
//	private static HashMap<ChampEnum, ArrayList<MatchResult>> getPartialHistoryForSpecificWeek(int i) {
//		Date dateOfBet = getDateOfBet(i); 	//statistiche prese prima di questa data
//		
//		HashMap<ChampEnum, ArrayList<MatchResult>> partialHistory = SerializationUtils.clone(allMatchesResults);
//		
//		ArrayList<MatchResult> toRemove;
//		for (Entry<ChampEnum, ArrayList<MatchResult>> entry : partialHistory.entrySet()){
//			toRemove = new ArrayList<MatchResult>();
//			for (MatchResult matchResult : entry.getValue()){
//				if (matchResult.getMatchDate().after(dateOfBet)){
//					toRemove.add(matchResult);
//				}
//			}
//			entry.getValue().removeAll(toRemove);
//		}
//		
//		
//		return partialHistory;
//	}
//
//	private static Date getBetEndDate(int weekToAdd) {
//		Calendar c = getCalendarStartDate();
//		c.add(Calendar.WEEK_OF_YEAR, weekToAdd);
//		c.add(Calendar.DATE, AppConstants.DAYS_FAR);
//		Date betEndDate = c.getTime();
//		return betEndDate;
//	}
//
//	private static Date getDateOfBet(int weekToAdd) {
//		Calendar c = getCalendarStartDate();
////		Date initialDate = c.getTime();
//		c.add(Calendar.WEEK_OF_YEAR, weekToAdd);
//		Date dateOfWeek = c.getTime();
//		return dateOfWeek;
//	}
//
//	private static Calendar getCalendarStartDate() {
//		Calendar c = Calendar.getInstance();
//		
//		c.set(Calendar.DATE, 11);			//11 giovedi
//		c.set(Calendar.MONTH, 7);			//agosto
//		c.set(Calendar.YEAR, 2016);			//2017
//		return c;
//	}
//
//	private static void initStaticFields() {
//		allMatchesResults =  ResultParserOld.retrieveAllMatchResults();
//		allBetsAnalysis = new HashMap<Integer, BetAnalysis>();
//		quotaToBet = 2.0/3.0;
//		initialInvestment = 100.0;
//		initialInvestmentSure = 100.0;
//		initialInvestmentWithSpent = 100.0;
//		i = 0;
//		
//		betsAnalysisChamps = new HashMap<ChampEnum, HashMap<Integer,BetAnalysis>>();
//		for (ChampEnum champ : ChampEnum.values()){
//			betsAnalysisChamps.put(champ, new HashMap<Integer, BetAnalysis>());
//		}
//		
//	}
	
}
