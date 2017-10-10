package app.logic._6_betCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.EventOddsDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.RankingRowDao;
import app.logic._1_matchesDownlaoder.model.BetType;
import app.logic._1_matchesDownlaoder.model.EventOddsBean;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessBean;
import app.logic._3_rankingCalculator.RankingCalculator;
import app.logic._3_rankingCalculator.model.RankingRow;
import app.logic._5_goodnessCalculator.GoodnessCalculator;
import app.utils.AppConstants;
import app.utils.ChampEnum;
import app.utils.IOUtils;
import app.utils.Utils;


@Service
public class BetCreator {

	private static HashMap<ChampEnum, ArrayList<EventOddsBean>> matchesOddWithGoodness;
	private static HashMap<ChampEnum, ArrayList<String>> allTeams;
	private static HashMap<ChampEnum, ArrayList<EventOddsBean>> mainBet = new HashMap<ChampEnum, ArrayList<EventOddsBean>>();
	private static HashMap<ChampEnum, ArrayList<RankingRow>> rankings; 
	
	@Autowired
	private RankingCalculator rankingCalculator;
	
	@Autowired
	private RankingRowDao rankingRowDao;
	
	@Autowired
	private MatchoDao matchDao;
	
	@Autowired
	private EventOddsDao eventOddsDao;
	
	public  void execute(){
//		initStaticFields();
//		System.out.println(matchesOddWithGoodness);
		List<RankingRow> ranking;

		for (ChampEnum champ : ChampEnum.values()){
			calculateGoodnessOfChamp(champ);
//			Collections.sort(mainBet.get(champ));
			ranking = rankingRowDao.findByChamp(champ);
			rankingCalculator.printRanking(ranking, champ);
		}
		
		
//		System.out.println("Main Bet");
//		System.out.println(mainBet);
//		int count = 0;
//		for (Entry<ChampEnum, ArrayList<EventOddsBean>> e :mainBet.entrySet())
//			count +=e.getValue().size();
//		System.out.println(count);
		
//		System.out.println("################################################################");
//		System.out.println();
//		System.out.println("################################################################");
//		System.out.println();
//		System.out.println("################################################################");
//		
		
		
		IOUtils.write(AppConstants.MAIN_BET_PATH, mainBet);
	}
	
	private void calculateGoodnessOfChamp(ChampEnum champ) {
		List<EventOddsBean> eventsOdds = eventOddsDao.getNextEventsOdds(champ);
		
//		for (MatchResult m : matches){
		for (EventOddsBean eo : eventsOdds){
			
			ResultGoodnessBean homeResultGoodness = eo.getHomeResultGoodness();
			ResultGoodnessBean awayResultGoodness = eo.getAwayResultGoodness();
			
			
			Double goodnessHD = homeResultGoodness.getWinClean().getGoodnessD() != null ? homeResultGoodness.getWinClean().getGoodnessD() : 0;
			Double goodnessAD = awayResultGoodness.getWinClean().getGoodnessD() != null ? awayResultGoodness.getWinClean().getGoodnessD() : 0;
			
			// Niente
//			Double goodnessHW = homeResultGoodness.getGoodnessW() != null ? homeResultGoodness.getGoodnessW() : 0;
//			Double goodnessHL = homeResultGoodness.getGoodnessL() != null ? homeResultGoodness.getGoodnessL() : 0;
//			Double goodnessAW = awayResultGoodness.getGoodnessW() != null ? awayResultGoodness.getGoodnessW() : 0;
//			Double goodnessAL = awayResultGoodness.getGoodnessL() != null ? awayResultGoodness.getGoodnessL() : 0;
			
			// Motivation
//			Double goodnessHW = homeResultGoodness.getGoodnessWwithMotivation() != null ? homeResultGoodness.getGoodnessWwithMotivation() : 0;
//			Double goodnessHL = homeResultGoodness.getGoodnessLwithMotivation() != null ? homeResultGoodness.getGoodnessLwithMotivation() : 0;
//			Double goodnessAW = awayResultGoodness.getGoodnessWwithMotivation() != null ? awayResultGoodness.getGoodnessWwithMotivation() : 0;
//			Double goodnessAL = awayResultGoodness.getGoodnessLwithMotivation() != null ? awayResultGoodness.getGoodnessLwithMotivation() : 0;

			// Trend
//			Double goodnessHW = homeResultGoodness.getGoodnessWwithTrends() != null ? homeResultGoodness.getGoodnessWwithTrends() : 0;
//			Double goodnessHL = homeResultGoodness.getGoodnessLwithTrends() != null ? homeResultGoodness.getGoodnessLwithTrends() : 0;
//			Double goodnessAW = awayResultGoodness.getGoodnessWwithTrends() != null ? awayResultGoodness.getGoodnessWwithTrends() : 0;
//			Double goodnessAL = awayResultGoodness.getGoodnessLwithTrends() != null ? awayResultGoodness.getGoodnessLwithTrends() : 0;

			// Final
			Double goodnessHW = homeResultGoodness.getWinFinal().getGoodnessW() != null ? homeResultGoodness.getWinFinal().getGoodnessW() : 0;
			Double goodnessHL = homeResultGoodness.getWinFinal().getGoodnessL() != null ? homeResultGoodness.getWinFinal().getGoodnessL() : 0;
			Double goodnessAW = awayResultGoodness.getWinFinal().getGoodnessW() != null ? awayResultGoodness.getWinFinal().getGoodnessW() : 0;
			Double goodnessAL = awayResultGoodness.getWinFinal().getGoodnessL() != null ? awayResultGoodness.getWinFinal().getGoodnessL() : 0;
			
			
			
			Double homeMot = eo.getHomeMotivation();
			Double awayMot = eo.getAwayMotivation();

			
// 			//############################# SURPRISE ############################# 
//			addSurpriseMatches(champ, eo, homeMot, awayMot);
			
			//############################# 1/2 ############################# 
			add12matches(champ, eo, goodnessHW, goodnessHL, goodnessAW, goodnessAL);
			
			//#############################  X  #############################	
			addXmatches(champ, eo, goodnessHD, goodnessAD);	
				
			//############################# U/O ############################# 
//			addUOmatches(champ, eo, homeResultGoodness, awayResultGoodness);
		
			
			
		}
	}



//	private static void addUOmatches(ChampEnum champ, EventOddsBean eo, ResultGoodnessBean homeResultGoodness, ResultGoodnessBean awayResultGoodness) {
//		Boolean conditionO = homeResultGoodness.getGoodnessO() >= 0.75 || awayResultGoodness.getGoodnessO() >= 0.75;
//		//Boolean conditionO = homeResultGoodness.getGoodnessO() >= 0.75 && awayResultGoodness.getGoodnessO() >= 0.75 || homeResultGoodness.getGoodnessO() + awayResultGoodness.getGoodnessO() >= 1.3
//		Boolean conditionU = homeResultGoodness.getGoodnessU() >= 0.75 || awayResultGoodness.getGoodnessU() >= 0.75;
//		//Boolean conditionU = homeResultGoodness.getGoodnessU() >= 0.75 && awayResultGoodness.getGoodnessU() >= 0.75 || homeResultGoodness.getGoodnessU() + awayResultGoodness.getGoodnessU() >= 1.3
//		
//		if (Utils.isMatchInTemporalRange(eo.getDate(), AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {		
//			// UO di entrambe alto
//			if ( conditionO ){
//				if (eo.getOddsO() != null && eo.getOddsO()>= 1.7){
//					eo.setBetType(BetType.UNDER_OVER);
//					eo.setMatchResult(MatchResultEnum.O);
//					eo.setWinOdds(eo.getOddsO());
//					mainBet.get(champ).add(SerializationUtils.clone(eo));
//				}
//			}
//			else if ( conditionU ){
//				if (eo.getOddsU() != null && eo.getOddsU()>= 1.7){
//					eo.setBetType(BetType.UNDER_OVER);
//					eo.setMatchResult(MatchResultEnum.U);
//					
//					eo.setWinOdds(eo.getOddsU());
//					mainBet.get(champ).add(SerializationUtils.clone(eo));
//				}
//			}
//		}
//	}



	private EventOddsBean addXmatches(ChampEnum champ, EventOddsBean eo, Double goodnessHD, Double goodnessAD) {
		Boolean conditionX = goodnessHD >= 0.4 && goodnessAD >= 0.4;
		
		if (Utils.isMatchInTemporalRange(eo.getDate(), AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
				if ( conditionX ){
					eo.setBetType(BetType.WIN);
					eo.setMatchResult(MatchResultEnum.D);
					eo.setWinOdds(eo.getOddsD());
//					mainBet.get(champ).add(SerializationUtils.clone(eo));
				}
		}
		return eo;
	}



	private EventOddsBean add12matches(ChampEnum champ, EventOddsBean eo, Double goodnessHW, Double goodnessHL, Double goodnessAW, Double goodnessAL) {
		Boolean condition1 = goodnessHW >= 0.8 && goodnessAL >= 0.8;
		//Boolean condition1 = goodnessHW + goodnessAL >= 1.6 && if (eo.getOddsH() <= 1.4){
		Boolean condition2 = goodnessHL >= 0.8 && goodnessAW >= 0.8;
		//Boolean condition2 = goodnessHL + goodnessAW >= 1.6 && if (eo.getOddsA() <= 1.4){ 
		
//rimetti		if (Utils.isMatchInTemporalRange(eo.getDate(), AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
			// Esito finale vittoria/sconfitta di entrambe alto
			if ( condition1	) {
				eo.setBetType(BetType.WIN);
				eo.setMatchResult(MatchResultEnum.H);
				eo.setWinOdds(eo.getOddsH());
				System.out.println(eo);
//				mainBet.get(champ).add(SerializationUtils.clone(eo));
			}
			else if ( condition2 ) {
				eo.setBetType(BetType.WIN);
				eo.setMatchResult(MatchResultEnum.A);
				eo.setWinOdds(eo.getOddsA());
				System.out.println(eo);
//				mainBet.get(champ).add(SerializationUtils.clone(eo));
			}
//		}
			return eo;
	}



	private EventOddsBean addSurpriseMatches(ChampEnum champ, EventOddsBean eo, Double homeMot, Double awayMot) {
		
		Boolean conditionMot1 = homeMot - awayMot > 0.8;
//		Boolean conditionMot1 = homeMot - awayMot > 1.0;
		Boolean conditionMot2 = awayMot - homeMot > 0.8;
//		Boolean conditionMot2 = awayMot - homeMot > 1.0;
		
		
//rimetti		if (Utils.isMatchInTemporalRange(eo.getDate(), AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
			if ( conditionMot1 ){
				eo.setBetType(BetType.WIN);
				eo.setMatchResult(MatchResultEnum.H);
				eo.setWinOdds(eo.getOddsH());
				System.out.println(eo);
//				mainBet.get(champ).add(SerializationUtils.clone(eo));
//				if (eo.getWinOdds() >= 6){
//					eo.setBetType(BetType.WIN);
//					eo.setMatchResult(MatchResultEnum.D);
//					mainBet.get(champ).add(SerializationUtils.clone(eo));
//				}
			}
			else if ( conditionMot2 ){
				eo.setBetType(BetType.WIN);
				eo.setMatchResult(MatchResultEnum.A);
				eo.setWinOdds(eo.getOddsA());
				System.out.println(eo);
//				mainBet.get(champ).add(SerializationUtils.clone(eo));
//				if (eo.getWinOdds() >= 6){
//					eo.setBetType(BetType.WIN);
//					eo.setMatchResult(MatchResultEnum.D);
//					mainBet.get(champ).add(SerializationUtils.clone(eo));
//				}
			}
//		}
		
		return eo;
	}



	private static void initStaticFields() {
//
//		allTeams =  ResultParserOld.retrieveTeams();
//		matchesOddWithGoodness =  GoodnessCalculator.retrieveMatchesOddsWithGoodness();
//		for (ChampEnum champ : ChampEnum.values()){
//			mainBet.put(champ, new ArrayList<EventOddsBean>());
//		}
//		rankings = RankingCalculator.retrieveRankings();
	}
	
	public static  HashMap<ChampEnum, ArrayList<EventOddsBean>>  retrieveMainBet() {
		HashMap<ChampEnum, ArrayList<EventOddsBean>> mainBet = IOUtils.read(AppConstants.MAIN_BET_PATH,  HashMap.class);
		return mainBet;
	}
	
	
}
