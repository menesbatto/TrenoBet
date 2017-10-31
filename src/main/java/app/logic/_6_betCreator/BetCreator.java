package app.logic._6_betCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.tabelle.EventOddsDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.RankingRowDao;
import app.dao.tabelle.SingleBetDao;
import app.logic._1_matchesDownlaoder.model.SingleBetBean;
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

//	private static HashMap<ChampEnum, ArrayList<EventOddsBean>> matchesOddWithGoodness;
//	private static HashMap<ChampEnum, ArrayList<String>> allTeams;
//	private static HashMap<ChampEnum, ArrayList<EventOddsBean>> mainBet = new HashMap<ChampEnum, ArrayList<EventOddsBean>>();
//	private static HashMap<ChampEnum, ArrayList<RankingRow>> rankings; 
//	
	@Autowired
	private RankingCalculator rankingCalculator;
	
	@Autowired
	private RankingRowDao rankingRowDao;
	
//	@Autowired
//	private MatchoDao matchDao;
	
	@Autowired
	private EventOddsDao eventOddsDao;
	
	
	@Autowired
	private SingleBetDao singleBetDao;

	private boolean printBet = false;
	
	

	public void execute(int seasonDay) {
		ChampEnum[] allChamps = ChampEnum.values();
		execute(seasonDay, allChamps);
	}
	
	
	@Transactional
	public  void execute(Integer seasonDay, ChampEnum[] champs){
//		initStaticFields();
//		System.out.println(matchesOddWithGoodness);
		List<RankingRow> ranking;

		for (ChampEnum champ : champs){
			System.out.println("\tBC t1 deleteSingleBets");
			singleBetDao.deleteBetResultByChampAndSeasonDay(champ, seasonDay);
			System.out.println("\tBC t2 createBetsOfChamp");
			createBetsOfChamp(champ, seasonDay);
			System.out.println("\tBC t3 createBetsOfChampComplete");
//			Collections.sort(mainBet.get(champ));
//			ranking = rankingRowDao.findByChamp(champ);
//			rankingCalculator.printRanking(ranking, champ);
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
	
	private void createBetsOfChamp(ChampEnum champ, Integer seasonDay) {
		System.out.println("\t\tBC 1 retrieveEventsOdds");
		List<EventOddsBean> eventsOdds = eventOddsDao.getNextEventsOdds(champ, seasonDay);
		System.out.println("\t\tBC 2 saveBetsInit");
//		for (MatchResult m : matches){
		List<SingleBetBean> singleBetList = new ArrayList<SingleBetBean>();

		for (EventOddsBean eo : eventsOdds){
			
			ResultGoodnessBean homeResultGoodness = eo.getHomeResultGoodness();
			ResultGoodnessBean awayResultGoodness = eo.getAwayResultGoodness();
			
			
//			Double goodnessHD = homeResultGoodness.getWinClean().getGoodnessD() != null ? homeResultGoodness.getWinClean().getGoodnessD() : 0;
//			Double goodnessAD = awayResultGoodness.getWinClean().getGoodnessD() != null ? awayResultGoodness.getWinClean().getGoodnessD() : 0;
			
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
//			Double goodnessHW = homeResultGoodness.getWinFinal().getGoodnessW() != null ? homeResultGoodness.getWinFinal().getGoodnessW() : 0;
//			Double goodnessHL = homeResultGoodness.getWinFinal().getGoodnessL() != null ? homeResultGoodness.getWinFinal().getGoodnessL() : 0;
//			Double goodnessAW = awayResultGoodness.getWinFinal().getGoodnessW() != null ? awayResultGoodness.getWinFinal().getGoodnessW() : 0;
//			Double goodnessAL = awayResultGoodness.getWinFinal().getGoodnessL() != null ? awayResultGoodness.getWinFinal().getGoodnessL() : 0;
			
			
			
			Double homeMot = eo.getHomeMotivation();
			Double awayMot = eo.getAwayMotivation();

			
 			//############################# SURPRISE ############################# 
//			addSurpriseMatches(champ, eo, homeMot, awayMot, singleBetList, seasonDay);
			
			//############################# 1/2 ############################# 
			add12matches(champ, eo, singleBetList, seasonDay);
			
			//#############################  X  #############################	
			addXmatches(champ, eo, singleBetList, seasonDay);	
				
			//############################# UO ############################# 
			addUOmatches(champ, eo, homeResultGoodness, awayResultGoodness, singleBetList, seasonDay);
		
			//############################# EH ############################# 
			addEhmatches(champ, eo, homeResultGoodness, awayResultGoodness, singleBetList, seasonDay);

			
//			for (SingleBetBean bet : singleBetList)
//				bet.setSeasonDay(seasonDay);
			
//			System.out.println(singleBetList);
		}
		singleBetDao.saveBetResult(singleBetList, champ);
		
		System.out.println("\t\tBC 3 saveBetsComplete");
		//eventOddsDao.saveBetResult(eventsOdds, champ);
	}

	private void add12matches(ChampEnum champ, EventOddsBean eo, List<SingleBetBean> singleBetList, Integer seasonDay) {
		
		ResultGoodnessWDLBean winFinalH = eo.getHomeResultGoodness().getWinFinal();
		ResultGoodnessWDLBean winFinalA = eo.getAwayResultGoodness().getWinFinal();

		Double goodnessHW = winFinalH.getGoodnessW() != null ? winFinalH.getGoodnessW() : 0;
		Double goodnessHL = winFinalH.getGoodnessL() != null ? winFinalH.getGoodnessL() : 0;
		Double goodnessAW = winFinalA.getGoodnessW() != null ? winFinalA.getGoodnessW() : 0;
		Double goodnessAL = winFinalA.getGoodnessL() != null ? winFinalA.getGoodnessL() : 0;
		
		
		Double limit = 0.65;
//		Double limit = 0.1;
		Double limitSum = 1.5;
		Double limitMix = 0.3;
		Double singleLimit = 0.9;
		
//		Boolean condition1 = goodnessHW >= limit && goodnessAL >= limit;
//		Boolean condition1 = goodnessHW + goodnessAL >= limitSum; // && if (eo.getOddsH() <= 1.4);
		Boolean condition1 = goodnessHW >= singleLimit || goodnessAL >= singleLimit; // && if (eo.getOddsH() <= 1.4);
//		Boolean condition1 = goodnessHW * (eo.getOdds1() - 1.0)>= limitMix 	&& goodnessAL * (eo.getOdds1() - 1.0)  >= limitMix;
		condition1 = condition1 && winFinalH.getTotalEventsW() != 0 && winFinalA.getTotalEventsL() != 0;
		
		
//		Boolean condition2 = goodnessHL >= limit && goodnessAW >= limit;
//		Boolean condition2 = goodnessHL + goodnessAW >= limitSum; // && if (eo.getOddsA() <= 1.4){ 
		Boolean condition2 = goodnessHL >= singleLimit || goodnessAW >= singleLimit; // && if (eo.getOddsA() <= 1.4){ 
//		Boolean condition2 = goodnessHL * (eo.getOdds2() - 1.0)>= limitMix 	&& goodnessAW * (eo.getOdds2() - 1.0)  >= limitMix;
		condition2 = condition2 && winFinalH.getTotalEventsL() != 0 && winFinalA.getTotalEventsW() != 0;
		
		
		
		Date betDate = Utils.getDateOfBet(seasonDay);
		if (Utils.isMatchInTemporalRange(eo.getDate(), betDate, AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
			// Esito finale vittoria/sconfitta di entrambe alto
			SingleBetBean br = null;
			
			if ( condition1	) {
				br = new SingleBetBean(BetType._1x2, MatchResultEnum.H, eo.getOdds1(), eo.getTimeType(), champ);
				br.setMatchId(eo.getMatchId());
				br.setSeasonDay(seasonDay);
				singleBetList.add(br);

//				System.out.println(br);
			}
			else if ( condition2 ) {
				br = new SingleBetBean(BetType._1x2, MatchResultEnum.A, eo.getOdds2(), eo.getTimeType(), champ);
				br.setMatchId(eo.getMatchId());
				br.setSeasonDay(seasonDay);
				singleBetList.add(br);
				
//				System.out.println(br);
			}
			if (printBet)
				if (br != null) {
					print1x2SingleBet(eo, br);
//					System.out.println(eo);
				}

		}
	}

	private void addEhmatches(ChampEnum champ, EventOddsBean eo, ResultGoodnessBean homeResultGoodness, ResultGoodnessBean awayResultGoodness, List<SingleBetBean> singleBetList, Integer seasonDay) {
		
		Double limit = 0.1;
		Double limitMix = 4.0;
		
		Map<HomeVariationEnum, ResultGoodnessWDLBean> ehGoodnessMapH = homeResultGoodness.getEhGoodness();
		Map<HomeVariationEnum, ResultGoodnessWDLBean> ehGoodnessMapA = awayResultGoodness.getEhGoodness();
		Map<HomeVariationEnum, _1x2Leaf> ehOddsMap = eo.getEhOddsMap();
		
		
		
		for (HomeVariationEnum homeVar: HomeVariationEnum.getSubSet()) {

			ResultGoodnessWDLBean ehGoodnessH = ehGoodnessMapH.get(homeVar);
			ResultGoodnessWDLBean ehGoodnessA = ehGoodnessMapA.get(homeVar);
			
			
			
			_1x2Leaf _1x2leaf = ehOddsMap.get(homeVar);
			if (_1x2leaf == null)	// la scommessa non è quotata
				continue;
			
			Boolean condition1 = false;
			if (ehGoodnessH.getGoodnessW() != null && ehGoodnessA.getGoodnessL() != null) {
//				condition1 = ehGoodnessH.getGoodnessW() >= limit && ehGoodnessA.getGoodnessL() >= limit;
//				condition1 = ehGoodnessH.getGoodnessW() + ehGoodnessA.getGoodnessL() >= 1.6 && if (eo.getOddsH() <= 1.4){
				condition1 = ehGoodnessH.getGoodnessW() * (_1x2leaf.getOdd1() - 1.0)>= limitMix 	&& ehGoodnessA.getGoodnessL() * (_1x2leaf.getOdd1() - 1.0)  >= limitMix;
				condition1 = condition1 && ehGoodnessH.getTotalEventsW() != 0 && ehGoodnessA.getTotalEventsL() != 0;
			}
			
			Boolean conditionX = false;
			if (ehGoodnessH.getGoodnessD() != null && ehGoodnessA.getGoodnessD() != null) {
//				conditionX = ehGoodnessH.getGoodnessD() >= limit && ehGoodnessA.getGoodnessD() >= limit;
//				condition2 = ehGoodnessH.getGoodnessD() + ehGoodnessA.getGoodnessD() >= 1.6 && if (eo.getOddsA() <= 1.4){ 
				conditionX = ehGoodnessH.getGoodnessD() * (_1x2leaf.getOddX() - 1.0)>= limitMix 	&& ehGoodnessA.getGoodnessD() * (_1x2leaf.getOddX() - 1.0)  >= limitMix;
				conditionX = conditionX && ehGoodnessH.getTotalEventsD() != 0 && ehGoodnessA.getTotalEventsD() != 0;
			}
			
			Boolean condition2 = false;
			if (ehGoodnessH.getGoodnessL() != null && ehGoodnessA.getGoodnessW() != null) {
//				condition2 = ehGoodnessH.getGoodnessL() >= limit && ehGoodnessA.getGoodnessW() >= limit;
//				condition2 = ehGoodnessH.getGoodnessL() + ehGoodnessA.getGoodnessW() >= 1.6 && if (eo.getOddsA() <= 1.4){ 
				condition2 = ehGoodnessH.getGoodnessL() * (_1x2leaf.getOdd2() - 1.0)>= limitMix 	&& ehGoodnessA.getGoodnessW() * (_1x2leaf.getOdd2() - 1.0)  >= limitMix;
				condition2 = condition2 && ehGoodnessH.getTotalEventsL() != 0 && ehGoodnessA.getTotalEventsW() != 0;
			}
			
			
			Date betDate = Utils.getDateOfBet(seasonDay);
			if (Utils.isMatchInTemporalRange(eo.getDate(), betDate, AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
				// Esito finale vittoria/sconfitta di entrambe alto
				BetType betType = BetType.valueOf(homeVar.name());
				SingleBetBean br = null;
				if ( condition1	) {
					br = new SingleBetBean(betType, MatchResultEnum.H, _1x2leaf.getOdd1(), eo.getTimeType(), champ);
					br.setMatchId(eo.getMatchId());
					br.setSeasonDay(seasonDay);
					singleBetList.add(br);

//					System.out.println(br);
//					System.out.println(eo.toStringInner(null, null, homeVar, false));
				}
				else if ( conditionX ) {
					br = new SingleBetBean(betType, MatchResultEnum.D, _1x2leaf.getOddX(), eo.getTimeType(), champ);
					br.setMatchId(eo.getMatchId());
					br.setSeasonDay(seasonDay);
					singleBetList.add(br);
					
//					System.out.println(br);
//					System.out.println(eo.toStringInner(null, null, homeVar, false));
				}
				else if ( condition2 ) {
					br = new SingleBetBean(betType, MatchResultEnum.A, _1x2leaf.getOdd2(), eo.getTimeType(), champ);
					br.setMatchId(eo.getMatchId());
					br.setSeasonDay(seasonDay);
					singleBetList.add(br);
					
//					System.out.println(br);
//					System.out.println(eo.toStringInner(null, null, homeVar, false));
				}
				if (printBet) {
					if (br != null) {
						printEhSingleBet(eo, homeVar, br);
//						System.out.println(eo);
					}
				}
			}
		}
		
	}

	private void printEhSingleBet(EventOddsBean eo, HomeVariationEnum homeVar, SingleBetBean br) {
		System.out.println();
		ResultGoodnessWDLBean uoBeanH = eo.getHomeResultGoodness().getEhGoodness().get(homeVar);
		ResultGoodnessWDLBean uoBeanA = eo.getAwayResultGoodness().getEhGoodness().get(homeVar);
		System.out.println(Utils.redimString(eo.getHomeTeam(), 16) + " " + uoBeanH);
		System.out.println(Utils.redimString(eo.getAwayTeam(), 16) + " " + uoBeanA.toStringAway());
		
		System.out.print(br);
		System.out.println("----------------");
		
	}


	private void addUOmatches(ChampEnum champ, EventOddsBean eo, ResultGoodnessBean homeResultGoodness, ResultGoodnessBean awayResultGoodness, List<SingleBetBean> singleBetList, Integer seasonDay) {
//		Double limitGoodness = 0.75;
//		Double limitGoodness = 1.0;
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
			
			Date betDate = Utils.getDateOfBet(seasonDay);
			if (Utils.isMatchInTemporalRange(eo.getDate(), betDate, AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {		
				// UO di entrambe alto
				
				BetType betType = BetType.valueOf(uoThr.name());
				SingleBetBean br = null;

				if ( conditionO ){
					br = new SingleBetBean(betType, MatchResultEnum.O, uoLeaf.getO(), eo.getTimeType(), champ);
					br.setMatchId(eo.getMatchId());
					br.setSeasonDay(seasonDay);
					singleBetList.add(br);
//					System.out.println(br);
//					System.out.println(eo.toStringInner(null, uoThr, null, false));
				}
				else if ( conditionU ){
					br = new SingleBetBean(betType, MatchResultEnum.U, uoLeaf.getU(), eo.getTimeType(), champ);
					br.setMatchId(eo.getMatchId());
					br.setSeasonDay(seasonDay);
					singleBetList.add(br);
//					System.out.println(br);
//					System.out.println(eo.toStringInner(null, uoThr, null, false));
				}

				if (printBet) {
					if (br != null) {
						printUoSingleBet(eo, uoThr, br);
					}
				}
			}
		}
	}


	private void printUoSingleBet(EventOddsBean eo, UoThresholdEnum uoThr, SingleBetBean br) {
		System.out.println();
		ResultGoodnessUoBean uoBeanH = eo.getHomeResultGoodness().getUoGoodness().get(uoThr);
		ResultGoodnessUoBean uoBeanA = eo.getAwayResultGoodness().getUoGoodness().get(uoThr);
		System.out.println(Utils.redimString(eo.getHomeTeam(), 16) + " " + uoBeanH);
		System.out.println(Utils.redimString(eo.getAwayTeam(), 16) + " " + uoBeanA);
		
		System.out.print(br);
		System.out.println("----------------");
	}



	private void addXmatches(ChampEnum champ, EventOddsBean eo, List<SingleBetBean> singleBetList, Integer seasonDay) {
//		Double limit = 0.4;
		Double limit = 0.65;
		
		ResultGoodnessWDLBean winCleanH = eo.getHomeResultGoodness().getWinClean();
		ResultGoodnessWDLBean winCleanA = eo.getAwayResultGoodness().getWinClean();
		Double goodnessHD = winCleanH.getGoodnessD() != null ? winCleanH.getGoodnessD() : 0;
		Double goodnessAD = winCleanA.getGoodnessD() != null ? winCleanA.getGoodnessD() : 0;
		
		Boolean conditionX = goodnessHD >= limit && goodnessAD >= limit;
		conditionX = conditionX && winCleanH.getTotalEventsD() != 0 && winCleanA.getTotalEventsD() != 0;
		
		
		SingleBetBean br = null;
		Date betDate = Utils.getDateOfBet(seasonDay);
		if (Utils.isMatchInTemporalRange(eo.getDate(), betDate, AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
			if ( conditionX ){
				br = new SingleBetBean(BetType._1x2, MatchResultEnum.D, eo.getOddsX(), eo.getTimeType(), champ);
				br.setMatchId(eo.getMatchId());
				br.setSeasonDay(seasonDay);
				singleBetList.add(br);
//				System.out.println(br);
				
//				eo.setBetType(BetType.WIN);
//				eo.setMatchResult(MatchResultEnum.D);
//				eo.setWinOdds(eo.getOddsD());
//				mainBet.get(champ).add(SerializationUtils.clone(eo));
//				System.out.println(eo.toStringInner(true, null, null, false));
			}
		}
		if (printBet) {
			if (br != null) {
				print1x2SingleBet(eo, br);
//				System.out.println(eo);
			}
		}
	}



	



	private void print1x2SingleBet(EventOddsBean eo, SingleBetBean br) {
		System.out.println();
		ResultGoodnessWDLBean _1x2BeanH = eo.getHomeResultGoodness().getWinFinal();
		ResultGoodnessWDLBean _1x2BeanA = eo.getAwayResultGoodness().getWinFinal();
		System.out.println(Utils.redimString(eo.getHomeTeam(), 16) + " " + _1x2BeanH);
		System.out.println(Utils.redimString(eo.getAwayTeam(), 16) + " " + _1x2BeanA);
		
		System.out.print(br);
		System.out.println("----------------");
		
	}


	private EventOddsBean addSurpriseMatches(ChampEnum champ, EventOddsBean eo, Double homeMot, Double awayMot, List<SingleBetBean> singleBetList, Integer seasonDay) {
		
		Boolean conditionMot1 = homeMot - awayMot > 0.8;
//		Boolean conditionMot1 = homeMot - awayMot > 1.0;
		Boolean conditionMot2 = awayMot - homeMot > 0.8;
//		Boolean conditionMot2 = awayMot - homeMot > 1.0;
		
		
		SingleBetBean br;
		Date betDate = Utils.getDateOfBet(seasonDay);
		if (Utils.isMatchInTemporalRange(eo.getDate(), betDate, AppConstants.DAYS_FAR_BET_FROM, AppConstants.DAYS_FAR_BET_TO)) {
			if ( conditionMot1 ){
				br = new SingleBetBean(BetType._1x2, MatchResultEnum.H, eo.getOdds1(), eo.getTimeType(), champ);
				br.setMatchId(eo.getMatchId());
				br.setSeasonDay(seasonDay);
				singleBetList.add(br);
//				System.out.println(br);
				
				
//				eo.setBetType(BetType.WIN);
//				eo.setMatchResult(MatchResultEnum.H);
//				eo.setWinOdds(eo.getOddsH());
//				System.out.println(eo);
				
				
//				mainBet.get(champ).add(SerializationUtils.clone(eo));
				if (eo.getOdds1() >= 6){
					br = new SingleBetBean(BetType._1x2, MatchResultEnum.D, eo.getOddsX(), eo.getTimeType(), champ);
					br.setMatchId(eo.getMatchId());
					br.setSeasonDay(seasonDay);
					singleBetList.add(br);
//					System.out.println(br);
					
//					eo.setBetType(BetType.WIN);
//					eo.setMatchResult(MatchResultEnum.D);
//					mainBet.get(champ).add(SerializationUtils.clone(eo));
				}
			}
			else if ( conditionMot2 ){
				br = new SingleBetBean(BetType._1x2, MatchResultEnum.A, eo.getOdds2(), eo.getTimeType(), champ);
				br.setMatchId(eo.getMatchId());
				br.setSeasonDay(seasonDay);
				singleBetList.add(br);
//				System.out.println(br);
				
				
//				eo.setBetType(BetType.WIN);
//				eo.setMatchResult(MatchResultEnum.A);
//				eo.setWinOdds(eo.getOddsA());
//				System.out.println(eo);
//				mainBet.get(champ).add(SerializationUtils.clone(eo));
				if (eo.getOdds2() >= 6){
					br = new SingleBetBean(BetType._1x2, MatchResultEnum.D, eo.getOddsX(), eo.getTimeType(), champ);
					br.setMatchId(eo.getMatchId());
					br.setSeasonDay(seasonDay);
					singleBetList.add(br);
//					System.out.println(br);
					
//					eo.setBetType(BetType.WIN);
//					eo.setMatchResult(MatchResultEnum.D);
//					mainBet.get(champ).add(SerializationUtils.clone(eo));
				}
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
