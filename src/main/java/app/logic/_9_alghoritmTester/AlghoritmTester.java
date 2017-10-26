package app.logic._9_alghoritmTester;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import app.dao.tabelle.EventOddsDao;
import app.dao.tabelle.GoalsStatsDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.SingleBetDao;
import app.dao.tabelle.WinRangeStatsDao;
import app.logic._1_matchesDownlaoder.model.BetType;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.SingleBetBean;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._2_matchResultAnalyzer.ResultAnalyzer;
import app.logic._3_rankingCalculator.RankingCalculator;
import app.logic._5_goodnessCalculator.GoodnessCalculator;
import app.logic._6_betCreator.BetCreator;
import app.logic._7_betAnalyzer.BetAnalyzer;
import app.utils.ChampEnum;
import app.utils.Utils;

@Service
public class AlghoritmTester {
	
	@Autowired
	private ResultAnalyzer resultAnalyzer;
	
	@Autowired
	private RankingCalculator rankingCalculator;
	
	@Autowired
	private GoodnessCalculator goodnessCalculator;

	@Autowired
	private BetCreator betCreator;
	
	@Autowired
	private BetAnalyzer betAnalyzer;
	
	
	public void execute(){
		
		// Prende le partite settimana per settimana

		// Scorre settimana dopo settimana e calcola e salva le stats per fino a quel momento
		// e anche le classifiche
		// N.B. è una scrittura viene fatta una volta soltanto, poichè sono dati oggettivi
		// calcolati senza alcun algoritmo, (percentuali di risultati e classifiche)

		boolean calculateStats = true; //
		
	
		
		
		List<SingleBetBean> allChampsSingleBets = new ArrayList<SingleBetBean>();
		Integer actualSeasonDay = Utils.getActualTrenoSeasonDay();
		
		for (int seasonDay = 7; seasonDay < actualSeasonDay; seasonDay++) {
//			Date dateOfBet = getDateOfBet(seasonDay);
			
			if (calculateStats) {
				resultAnalyzer.execute(seasonDay);
//				rankingCalculator.execute(seasonDay);
				goodnessCalculator.execute(seasonDay);
			}
		
//			
			betCreator.execute(seasonDay);
			
			List<SingleBetBean> bets = betAnalyzer.execute(seasonDay);
			
			allChampsSingleBets.addAll(bets);
			System.out.println("#################################");
			System.out.print("############  " + seasonDay);
			if (seasonDay < 10) System.out.println("  ################");
			else				System.out.println("  ###############");
			System.out.println("#################################");
			printAllBetStats(bets);
		
		}
		
		
//		0 amount = 50.0;
//		System.out.println("Total");
//		System.out.println();
//		printAllBetStats(allChampsSingleBets);
		
		// Calcola la goodness fino a quel momento
		
		// Crea la bet
		
		// Verifica se è vinta
		
		
		
		
		
		
		
		
		
	}


	private void printAllBetStats(List<SingleBetBean> allChampsSingleBets) {
		
//		Map<TimeTypeEnum, ArrayList<SingleBetBean>> mapByTimeType = new HashMap<TimeTypeEnum, ArrayList<SingleBetBean>>();
//		for (SingleBetBean singleBet : allChampsSingleBets) {
//			ArrayList<SingleBetBean> arrayList = mapByTimeType.get(singleBet.getTimeTypeEnum());
//			if (arrayList== null) {
//				arrayList = new ArrayList<>();
//				mapByTimeType.put(singleBet.getTimeTypeEnum(), arrayList);
//			}
//			arrayList.add(singleBet);
//		}
//		
//		
//		Map<ChampEnum, ArrayList<SingleBetBean>> mapByChamp = new HashMap<ChampEnum, ArrayList<SingleBetBean>>();
//		for (SingleBetBean singleBet : allChampsSingleBets) {
//			ArrayList<SingleBetBean> arrayList = mapByChamp.get(singleBet.getChamp());
//			if (arrayList== null) {
//				arrayList = new ArrayList<>();
//				mapByChamp.put(singleBet.getChamp(), arrayList);
//			}
//			arrayList.add(singleBet);
//		}
//		for (Entry<TimeTypeEnum, ArrayList<SingleBetBean>> entry: mapByTimeType.entrySet()) {
//			System.out.println(entry.getKey());
//			printBetStats(entry.getValue());
//		}
		
		
		// Organizza le bet sulla base del Champ e TimeType
		Map<TimeTypeEnum, HashMap<ChampEnum, ArrayList<SingleBetBean>>> mapByChampAndTime = new HashMap<TimeTypeEnum, HashMap<ChampEnum, ArrayList<SingleBetBean>>>();
		for (SingleBetBean singleBet : allChampsSingleBets) {
			HashMap<ChampEnum, ArrayList<SingleBetBean>> mapByTime = mapByChampAndTime.get(singleBet.getTimeTypeEnum());
			if (mapByTime == null) {
				mapByTime = new HashMap<ChampEnum, ArrayList<SingleBetBean>>();
				mapByChampAndTime.put(singleBet.getTimeTypeEnum(), mapByTime);
			}
			ArrayList<SingleBetBean> arrayList = mapByTime.get(singleBet.getChamp());
			if (arrayList == null) {
				arrayList = new ArrayList<SingleBetBean>();
				mapByTime.put(singleBet.getChamp(), arrayList);
			}
			arrayList.add(singleBet);
		}
		
		
		// Crea delle statistiche per ogni sotto lista ottenuta dalla organizzazione precedente
		HashMap<ChampEnum, SeasonDayBetResultInfo> seasonDayBetInfoAlTimeTogheter = new HashMap<ChampEnum, SeasonDayBetResultInfo>();
		
		Map<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo >> mapBetResultInfoGeneral = new HashMap<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo>>();
		
 		for ( Entry<TimeTypeEnum, HashMap<ChampEnum, ArrayList<SingleBetBean>>> entry: mapByChampAndTime.entrySet()) {
 			TimeTypeEnum key = entry.getKey();
 			HashMap<ChampEnum, SeasonDayBetResultInfo> mapBetResultInfoByChamp = mapBetResultInfoGeneral.get(key);
			if (mapBetResultInfoByChamp == null) {
				mapBetResultInfoByChamp = new HashMap<ChampEnum, SeasonDayBetResultInfo>();
				mapBetResultInfoGeneral.put(key, mapBetResultInfoByChamp);
			}
			
			SeasonDayBetResultInfo seasonDayBetInfoAllChampTogheter = new SeasonDayBetResultInfo();
			for (Entry<ChampEnum, ArrayList<SingleBetBean>> entry2: entry.getValue().entrySet()) {
				ChampEnum timeType = entry2.getKey();
				ArrayList<SingleBetBean> betList = entry2.getValue();
				SeasonDayBetResultInfo seasonDayBetInfo = calculateBetsResultsInfoPerTimeType(betList);
				mapBetResultInfoByChamp.put(timeType, seasonDayBetInfo);
				
				addPartialToTotalChamp(seasonDayBetInfo, seasonDayBetInfoAllChampTogheter);
			}
			mapBetResultInfoByChamp.put(null, seasonDayBetInfoAllChampTogheter);
			
			addPartialToTotalTime(mapBetResultInfoByChamp, seasonDayBetInfoAlTimeTogheter);
			
		}
 		
 		
		mapBetResultInfoGeneral.put(null, seasonDayBetInfoAlTimeTogheter);
 		
 		
 		// Stampa le statistiche
 		for (Entry<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo>> entry : mapBetResultInfoGeneral.entrySet()) {
 			TimeTypeEnum timeType = entry.getKey();
 			System.out.println();
 			System.out.println(timeType);
 			HashMap<ChampEnum, SeasonDayBetResultInfo> value = entry.getValue();
 			String lineChamp = "";
 			String lineHeader = "";
 			String line12 = "";
 			String lineX = "";
 			String lineUo = "";
 			String lineEh = "";
 			String lineTotal = "";
 			
 			for (ChampEnum key : value.keySet()) {
 				SeasonDayBetResultInfo info = value.get(key);
 				String keyName = key == null ? Utils.redimString("ALL CHAMPS",16) : Utils.redimString(key.name(),16);
 				lineChamp += keyName + "\t\t\t\t\t\t";
 				lineHeader += "\tTOT\tWIN\tLOST\t€" + "\t\t\t\t";
 				line12 += "12"+ 			"\t" + info.get_12BetNum() + 			"\t" + info.get_12WinBetNum() + 	"\t" + ( info.get_12BetNum()-info.get_12WinBetNum())+ 		"\t" + Utils.redimString(info.get_12WinAmount()) + "\t\t\t\t";
 				lineX += "12"+ 				"\t" + info.getxBetNum() + 				"\t" + info.getxWinBetNum() + 		"\t" + ( info.getxBetNum()-info.getxWinBetNum())+ 			"\t" + Utils.redimString(info.getxWinAmount()) + "\t\t\t\t";
 				lineUo += "12"+ 			"\t" + info.getUoBetNum() + 			"\t" + info.getUoWinBetNum() + 		"\t" + ( info.getUoBetNum()-info.getUoWinBetNum())+ 		"\t" + Utils.redimString(info.getUoWinAmount()) + "\t\t\t\t";
 				lineEh += "12"+ 			"\t" + info.getEhBetNum() + 			"\t" + info.getEhWinBetNum() + 		"\t" + ( info.getEhBetNum()-info.getEhWinBetNum())+ 		"\t" + Utils.redimString(info.getEhWinAmount()) + "\t\t\t\t";
 				lineTotal += "12"+ 			"\t" + info.getTotalBetNum() + 			"\t" + info.getTotalWinBetNum() + 	"\t" + ( info.getTotalBetNum()-info.getTotalWinBetNum())+ 	"\t" + Utils.redimString(info.getTotalWinAmount()) + "\t\t\t\t";
 			}
 			
 			System.out.println(lineChamp);
 			System.out.println(lineHeader);
 			System.out.println(line12);
 			System.out.println(lineX);
 			System.out.println(lineUo);
 			System.out.println(lineEh);
 			System.out.println(lineTotal);
 			
 		}
 		
 		
 		
 		
 		
 		
 		
 		
	}
	
	
	
	private void addPartialToTotalTime(HashMap<ChampEnum, SeasonDayBetResultInfo> p,	HashMap<ChampEnum, SeasonDayBetResultInfo> t) {
		
		for (ChampEnum key : p.keySet()) {
			SeasonDayBetResultInfo seasonDayBetResultInfoTotal = t.get(key);
			if (seasonDayBetResultInfoTotal == null) {
				seasonDayBetResultInfoTotal = new SeasonDayBetResultInfo();
				t.put(key, seasonDayBetResultInfoTotal);
			}
			addPartialToTotalChamp(p.get(key), t.get(key));
			
		}
		
		
	}


	private void addPartialToTotalChamp(SeasonDayBetResultInfo p, SeasonDayBetResultInfo t) {
		t.set_12BetNum(t.get_12BetNum() + p.get_12BetNum());
		t.set_12WinBetNum(t.get_12WinBetNum() + p.get_12WinBetNum());
		t.set_12WinAmount(t.get_12WinAmount() + p.get_12WinAmount());

		t.setUoBetNum(t.getUoBetNum() + p.getUoBetNum());
		t.setUoWinBetNum(t.getUoWinBetNum() + p.getUoWinBetNum());
		t.setUoWinAmount(t.getUoWinAmount() + p.getUoWinAmount());
		
		t.setEhBetNum(t.getEhBetNum() + p.getEhBetNum());
		t.setEhWinBetNum(t.getEhWinBetNum() + p.getEhWinBetNum());
		t.setEhWinAmount(t.getEhWinAmount() + p.getEhWinAmount());
		
		t.setxBetNum(t.getxBetNum() + p.getxBetNum());
		t.setxWinBetNum(t.getxWinBetNum() + p.getxWinBetNum());
		t.setxWinAmount(t.getxWinAmount() + p.getxWinAmount());
		
		t.setTotalBetNum(t.getTotalBetNum() + p.getTotalBetNum());
		t.setTotalWinBetNum(t.getTotalWinBetNum() + p.getTotalWinBetNum());
		t.setTotalWinAmount(t.getTotalWinAmount() + p.getTotalWinAmount());
	}


	private void printBetResultInfo(TimeTypeEnum timeType, SeasonDayBetResultInfo info) {
//		System.out.println("12"+ 			"\t" + _12BetNum + 			"\t" + _12WinBetNum + 	"\t" + (_12BetNum-_12WinBetNum)+ 	"\t" + Utils.redimString(_12WinAmount) + "");
//		
//		System.out.println("X"+ 			"\t"+ xBetNum + 			"\t" + xWinBetNum + 	"\t" + (xBetNum-xWinBetNum)+ 	"\t" + Utils.redimString(xWinAmount) + "");
//		
//		System.out.println("UO"+ 			"\t" + uoBetNum + 			"\t" + uoWinBetNum + 	"\t" + (uoBetNum-uoWinBetNum)+ 	"\t" + Utils.redimString(uoWinAmount) + "");
//			
//		System.out.println("EH"+ 			"\t"+ehBetNum + 			"\t" + ehWinBetNum + 	"\t" + (ehBetNum-ehWinBetNum)+ 	"\t" +  Utils.redimString(ehWinAmount) + "");
//
//		System.out.println("TOTAL" + 		"\t"+ totalBetNum + 		"\t" + totalWinBetNum + "\t" + (totalBetNum-totalWinBetNum) + "\t" + Utils.redimString(totalWinAmount));
//		System.out.println("\n#################################\n");
	}


//	private Map<TimeTypeEnum, SeasonDayBetResultInfo> calculateBetResultsInfoPerChamp(HashMap<TimeTypeEnum, ArrayList<SingleBetBean>> timeMap) {
//		Map<TimeTypeEnum, SeasonDayBetResultInfo> mapInfo = new HashMap<TimeTypeEnum, SeasonDayBetResultInfo>();
//		for (Entry<TimeTypeEnum, ArrayList<SingleBetBean>> entry : timeMap.entrySet()) {
//			ArrayList<SingleBetBean> singleBets = entry.getValue();
//			SeasonDayBetResultInfo info = calculateBetsResultsInfoPerTimeType(singleBets);
//			mapInfo.put(entry.getKey(), info);
//				
//		}
//		return mapInfo;
////		System.out.println(entry.getKey());
//		
//	}


	private SeasonDayBetResultInfo calculateBetsResultsInfoPerTimeType(List<SingleBetBean> allChampsSingleBets) {
		Double totalWinAmount = 0.0;
		Integer totalBetNum = 0;
		Integer totalWinBetNum = 0;

		
		
		Double xWinAmount = 0.0;
		Integer xBetNum = 0;
		Integer xWinBetNum = 0;
		
		
		Double _12WinAmount = 0.0;
		Integer _12BetNum = 0;
		Integer _12WinBetNum = 0;
		
		Double uoWinAmount = 0.0;
		Integer uoBetNum = 0;
		Integer uoWinBetNum = 0;
		
		Double ehWinAmount = 0.0;
		Integer ehBetNum = 0;
		Integer ehWinBetNum = 0;
		
		
		
		for (SingleBetBean bet : allChampsSingleBets) {
			
			Double winOdds = bet.getWinOdds();
			if (bet.getBetType().equals(BetType._1x2)) {
				if ( bet.getMatchResultForecast().name().equals(MatchResultEnum.D.name())) {
					if (bet.getWin()) {
						xWinBetNum++;
						xWinAmount += winOdds;
					}
					xBetNum++;
					xWinAmount--;
						
				}
				else {
					if (bet.getWin()) {
						_12WinBetNum++;
						_12WinAmount += winOdds;
					}
					_12BetNum++;
					_12WinAmount--;
				}
			}
			else if (bet.getBetType().name().contains("_5")) {
				if (bet.getWin()) {
					uoWinBetNum++;
					uoWinAmount += winOdds;
				}
				uoBetNum++;
				uoWinAmount--;
			}
			else if (bet.getBetType().name().contains("m")||bet.getBetType().name().contains("p")) {
				if (bet.getWin()) {
					ehWinBetNum++;
					ehWinAmount += winOdds;
				}
				ehBetNum++;
				ehWinAmount--;
			}
			
			if (bet.getWin()) {
				totalWinBetNum++;
				totalWinAmount += winOdds;
			}
			totalBetNum++;
			totalWinAmount--;
			
		}
		
		
		SeasonDayBetResultInfo info = new SeasonDayBetResultInfo();
		info.set_12BetNum(_12BetNum);
		info.set_12WinAmount(_12WinAmount);
		info.set_12WinBetNum(_12WinBetNum);
		
		info.setEhBetNum(ehBetNum);
		info.setEhWinAmount(ehWinAmount);
		info.setEhWinBetNum(ehWinBetNum);

		info.setTotalBetNum(totalBetNum);
		info.setTotalWinAmount(totalWinAmount);
		info.setTotalWinBetNum(totalWinBetNum);
		
		info.setUoBetNum(uoBetNum);
		info.setUoWinAmount(uoWinAmount);
		info.setUoWinBetNum(uoWinBetNum);
		
		info.setxBetNum(xBetNum);
		info.setxWinAmount(xWinAmount);
		info.setxWinBetNum(xWinBetNum);
		
		
		return info;
		

	}
	
	

	
	
}
