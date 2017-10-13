package app.logic._6_betCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.EventOddsDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.RankingRowDao;
import app.logic._1_matchesDownlaoder.model.BetType;
import app.logic._1_matchesDownlaoder.model.EventOddsBean;
import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessBean;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessUoBean;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessWDLBean;
import app.logic._1_matchesDownlaoder.model.UoLeaf;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;
import app.logic._1_matchesDownlaoder.model._1x2Leaf;
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
		
		
//		IOUtils.write(AppConstants.MAIN_BET_PATH, mainBet);
	}
	
	private void calculateGoodnessOfChamp(ChampEnum champ) {
		List<EventOddsBean> eventsOdds = eventOddsDao.getNextEventsOdds(champ);
		
//		for (MatchResult m : matches){
		for (EventOddsBean eo : eventsOdds){
			
			ResultGoodnessBean homeResultGoodness = eo.getHomeResultGoodness();
			ResultGoodnessBean awayResultGoodness = eo.getAwayResultGoodness();
			
			
			Double goodnessHD = homeResultGoodness.getWinClean().getGoodnessD() != null ? homeResultGoodness.getWinClean().getGoodnessD() : 0;
			Double goodnessAD = awayResultGoodness.getWinClean().getGoodnessD() != null ? awayResultGoodness.getWinClean().getGoodnessD() : 0;
			
			// Clean
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
//			
			//############################# 1/2 ############################# 
//			add12matches(champ, eo, goodnessHW, goodnessHL, goodnessAW, goodnessAL);
//			
			//#############################  X  #############################	
//			addXmatches(champ, eo, goodnessHD, goodnessAD);	
//				
//			//############################# UO ############################# 
//			addUOmatches(champ, eo, homeResultGoodness, awayResultGoodness);
//		
//			//############################# EH ############################# 
			addEhmatches(champ, eo, homeResultGoodness, awayResultGoodness);
			
			
		}
	}

	private EventOddsBean add12matches(ChampEnum champ, EventOddsBean eo, Double goodnessHW, Double goodnessHL, Double goodnessAW, Double goodnessAL) {
		
		Double limit = 0.5;
//		Double limit = 0.1;
//		Double limitSum = 1.5;
		
		Boolean condition1 = goodnessHW >= limit && goodnessAL >= limit;
//		Boolean condition1 = goodnessHW + goodnessAL >= limitSum; // && if (eo.getOddsH() <= 1.4){
		Boolean condition2 = goodnessHL >= limit && goodnessAW >= limit;
//		Boolean condition2 = goodnessHL + goodnessAW >= limitSum; // && if (eo.getOddsA() <= 1.4){ 

		if (Utils.isMatchInTemporalRange(eo.getDate(), AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
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
				System.out.println(eo.toStringInner(true, null, null, false));
//				mainBet.get(champ).add(SerializationUtils.clone(eo));
			}
		}
		return eo;
	}

	private void addEhmatches(ChampEnum champ, EventOddsBean eo, ResultGoodnessBean homeResultGoodness, ResultGoodnessBean awayResultGoodness) {
		
		Double limit = 0.1;
		Double limitMix = 1.0;
		
		Map<HomeVariationEnum, ResultGoodnessWDLBean> ehGoodnessMapH = homeResultGoodness.getEhGoodness();
		Map<HomeVariationEnum, ResultGoodnessWDLBean> ehGoodnessMapA = awayResultGoodness.getEhGoodness();
		Map<HomeVariationEnum, _1x2Leaf> ehOddsMap = eo.getEhOddsMap();
		
		
		
		for (HomeVariationEnum homeVar: HomeVariationEnum.getSubSet()) {

			ResultGoodnessWDLBean ehGoodnessH = ehGoodnessMapH.get(homeVar);
			ResultGoodnessWDLBean ehGoodnessA = ehGoodnessMapA.get(homeVar);
			
			if (ehGoodnessH.getGoodnessW() == null || ehGoodnessA.getGoodnessL() == null)
				continue;
			
			_1x2Leaf _1x2leaf = ehOddsMap.get(homeVar);
			if (_1x2leaf == null)	// la scommessa non è quotata
				continue;
			
			
//			Boolean condition1 = ehGoodnessH.getGoodnessW() >= limit && ehGoodnessA.getGoodnessL() >= limit;
			//Boolean condition1 = ehGoodnessH.getGoodnessW() + ehGoodnessA.getGoodnessL() >= 1.6 && if (eo.getOddsH() <= 1.4){
			Boolean condition1 = ehGoodnessH.getGoodnessW() * (_1x2leaf.getOdd1() - 1.0)>= limitMix 	&& ehGoodnessA.getGoodnessL() * (_1x2leaf.getOdd1() - 1.0)  >= limitMix;
			
			
//			Boolean conditionX = ehGoodnessH.getGoodnessD() >= limit && ehGoodnessA.getGoodnessD() >= limit;
			//Boolean condition2 = ehGoodnessH.getGoodnessD() + ehGoodnessA.getGoodnessD() >= 1.6 && if (eo.getOddsA() <= 1.4){ 
			Boolean conditionX = ehGoodnessH.getGoodnessD() * (_1x2leaf.getOddX() - 1.0)>= limitMix 	&& ehGoodnessA.getGoodnessD() * (_1x2leaf.getOddX() - 1.0)  >= limitMix;
			
			
//			Boolean condition2 = ehGoodnessH.getGoodnessL() >= limit && ehGoodnessA.getGoodnessW() >= limit;
			//Boolean condition2 = ehGoodnessH.getGoodnessL() + ehGoodnessA.getGoodnessW() >= 1.6 && if (eo.getOddsA() <= 1.4){ 
			Boolean condition2 = ehGoodnessH.getGoodnessL() * (_1x2leaf.getOdd2() - 1.0)>= limitMix 	&& ehGoodnessA.getGoodnessW() * (_1x2leaf.getOdd2() - 1.0)  >= limitMix;
			
			
			
			if (Utils.isMatchInTemporalRange(eo.getDate(), AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
				// Esito finale vittoria/sconfitta di entrambe alto
				BetType betType = BetType.valueOf(homeVar.name());
				
				if ( condition1	) {
					eo.setBetType(betType);
					eo.setMatchResult(MatchResultEnum.H);
					eo.setWinOdds(_1x2leaf.getOdd1());
					System.out.println(eo.toStringInner(null, null, homeVar, false));
//					mainBet.get(champ).add(SerializationUtils.clone(eo));
				}
				else if ( conditionX ) {
					eo.setBetType(betType);
					eo.setMatchResult(MatchResultEnum.D);
					eo.setWinOdds(_1x2leaf.getOddX());
					System.out.println(eo.toStringInner(null, null, homeVar, false));
//					mainBet.get(champ).add(SerializationUtils.clone(eo));
				}
				else if ( condition2 ) {
					eo.setBetType(betType);
					eo.setMatchResult(MatchResultEnum.A);
					eo.setWinOdds(_1x2leaf.getOdd2());
					System.out.println(eo.toStringInner(null, null, homeVar, false));
//					mainBet.get(champ).add(SerializationUtils.clone(eo));
				}
			}
		}
		
	}

	private static void addUOmatches(ChampEnum champ, EventOddsBean eo, ResultGoodnessBean homeResultGoodness, ResultGoodnessBean awayResultGoodness) {
//		Double limitGoodness = 0.75;
		Double limitGoodness = 1.0;
//		Double limitOdds = 1.7;
		Double limitOdds = 1.1;
		Double limitMix = 1.0;
		
		Map<UoThresholdEnum, ResultGoodnessUoBean> uoGoodnessMapHome = homeResultGoodness.getUoGoodness();
		Map<UoThresholdEnum, ResultGoodnessUoBean> uoGoodnessMapAway = awayResultGoodness.getUoGoodness();
		Map<UoThresholdEnum, UoLeaf> uoOddsMap = eo.getUoOddsMap();
		
		for (UoThresholdEnum uoThr : UoThresholdEnum.values()) {
			
			ResultGoodnessUoBean uoGoodnessH = uoGoodnessMapHome.get(uoThr);
			ResultGoodnessUoBean uoGoodnessA = uoGoodnessMapAway.get(uoThr);
			UoLeaf uoLeaf = uoOddsMap.get(uoThr);
			if (uoLeaf == null)	// la scommessa non è quotata
				continue;
			
//			Boolean conditionO = uoGoodnessH.getGoodnessO() >= limitGoodness 	|| uoGoodnessA.getGoodnessO() >= limitGoodness;
//			Boolean conditionO = uoGoodnessH.getGoodnessO() >= limitGoodness 	&& uoGoodnessA.getGoodnessO() >= limitGoodness;
//			Boolean conditionO = uoGoodnessH.getGoodnessO() 					+ uoGoodnessA.getGoodnessO() >= 1.3;
			Boolean conditionO = uoGoodnessH.getGoodnessO() * (uoLeaf.getO() - 1.0) >= limitMix 	&& uoGoodnessA.getGoodnessO() * (uoLeaf.getO() - 1.0) >= limitMix;
			
			
			
//			Boolean conditionU = uoGoodnessH.getGoodnessU() >= limitGoodness 	|| uoGoodnessA.getGoodnessU() >= limitGoodness;
//			Boolean conditionU = uoGoodnessH.getGoodnessU() >= limitGoodness	&& uoGoodnessA.getGoodnessU() >= limitGoodness;
//			Boolean conditionU = uoGoodnessH.getGoodnessU() 					+ uoGoodnessA.getGoodnessU() >= 1.3:
			Boolean conditionU = uoGoodnessH.getGoodnessU() * (uoLeaf.getU() - 1.0)>= limitMix 	&& uoGoodnessA.getGoodnessU() * (uoLeaf.getU() - 1.0)  >= limitMix;
			
			
			if (Utils.isMatchInTemporalRange(eo.getDate(), AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {		
				// UO di entrambe alto
				
				BetType betType = BetType.valueOf(uoThr.name());
				
				if ( conditionO ){
					if (uoLeaf.getO() != null && uoLeaf.getO() >= limitOdds){
						eo.setBetType(betType);
						eo.setMatchResult(MatchResultEnum.O);
						eo.setWinOdds(uoLeaf.getO());
						//mainBet.get(champ).add(SerializationUtils.clone(eo));
						System.out.println(eo.toStringInner(null, uoThr, null, false));
					}
				}
				else if ( conditionU ){
					if (uoLeaf.getU() != null && uoLeaf.getU() >= limitOdds){
						eo.setBetType(betType);
						eo.setMatchResult(MatchResultEnum.U);
						
						eo.setWinOdds(uoLeaf.getU());
//						mainBet.get(champ).add(SerializationUtils.clone(eo));
						System.out.println(eo.toStringInner(null, uoThr, null, false));
					}
				}
			}
		}
	}



	private EventOddsBean addXmatches(ChampEnum champ, EventOddsBean eo, Double goodnessHD, Double goodnessAD) {
//		Double limit = 0.4;
		Double limit = 0.4;
		
		Boolean conditionX = goodnessHD >= limit && goodnessAD >= limit;
		
		if (Utils.isMatchInTemporalRange(eo.getDate(), AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
			if ( conditionX ){
				eo.setBetType(BetType.WIN);
				eo.setMatchResult(MatchResultEnum.D);
				eo.setWinOdds(eo.getOddsD());
//				mainBet.get(champ).add(SerializationUtils.clone(eo));
				System.out.println(eo.toStringInner(true, null, null, false));
			}
		}
		return eo;
	}



	



	private EventOddsBean addSurpriseMatches(ChampEnum champ, EventOddsBean eo, Double homeMot, Double awayMot) {
		
		Boolean conditionMot1 = homeMot - awayMot > 0.8;
//		Boolean conditionMot1 = homeMot - awayMot > 1.0;
		Boolean conditionMot2 = awayMot - homeMot > 0.8;
//		Boolean conditionMot2 = awayMot - homeMot > 1.0;
		
		
		if (Utils.isMatchInTemporalRange(eo.getDate(), AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
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
		}
		
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