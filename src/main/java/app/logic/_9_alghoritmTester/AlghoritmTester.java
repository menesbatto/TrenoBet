package app.logic._9_alghoritmTester;

import java.text.SimpleDateFormat;
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
	
	Map<Integer, HashMap<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo >>> seasonDayInfoMap = null;
	
	public void execute(){
		
		// Prende le partite settimana per settimana

		// Scorre settimana dopo settimana e calcola e salva le stats per fino a quel momento
		// e anche le classifiche
		// N.B. è una scrittura viene fatta una volta soltanto, poichè sono dati oggettivi
		// calcolati senza alcun algoritmo, (percentuali di risultati e classifiche)

		boolean calculateStats = true; //
		
	
		
		
		List<SingleBetBean> allChampsSingleBets = new ArrayList<SingleBetBean>();
		Integer actualSeasonDay = Utils.getActualTrenoSeasonDay();
		
		//ChampEnum[] champs = new ChampEnum[]{ChampEnum.ENG_PREMIER, ChampEnum.ENG_CHAMPIONSHIP_2017, ChampEnum.ITA_SERIE_A_2017};
//		ChampEnum[] champs = new ChampEnum[]{ChampEnum.GER_BUNDESLIGA_2017, ChampEnum.GER_2_BUNDESLIGA_2017, ChampEnum.ITA_SERIE_B_2017};
//		ChampEnum[] champs = new ChampEnum[]{ChampEnum.ITA_SERIE_B_2017};
//		ChampEnum[] champs = new ChampEnum[]{ChampEnum.SCO_CHAMPIONSHIP_2017, ChampEnum.SCO_PREMIERSHIP_2017, ChampEnum.SPA_LA_LIGA_2017, ChampEnum.SPA_LA_LIGA_2_2017, ChampEnum.FRA_LIGUE_1_2017, ChampEnum.FRA_LIGUE_2_2017};
		ChampEnum[] champs = ChampEnum.values();
		
		if (seasonDayInfoMap == null) {
			seasonDayInfoMap = new HashMap<Integer, HashMap<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo>>>();
	
			
			for (int seasonDay = 7; seasonDay < actualSeasonDay + 1; seasonDay++) {
	//			Date dateOfBet = getDateOfBet(seasonDay);
				System.out.println(seasonDay + " - 1");
				if (calculateStats) {
					resultAnalyzer.execute(seasonDay, champs);
	//				rankingCalculator.execute(seasonDay, champs);
					System.out.println(seasonDay + " - 2");
					goodnessCalculator.execute(seasonDay, champs);
				}
				System.out.println(seasonDay + " - 3");
				
				betCreator.execute(seasonDay, champs);
				
				System.out.println(seasonDay + " - 4");
				
				List<SingleBetBean> bets = betAnalyzer.execute(seasonDay, champs);
				
				System.out.println(seasonDay + " - 5");
				
				allChampsSingleBets.addAll(bets);
				printSeasonDayTitle(seasonDay+"");
				
				
				HashMap<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo>> seasonDayInfoTotal = printAllBetStats(bets);;
				seasonDayInfoMap.put(seasonDay, seasonDayInfoTotal);
				System.out.println(seasonDay + " - 6");
			
			}
			
			printSeasonDayTitle("Total");
			HashMap<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo>> seasonDayInfoTotal = printAllBetStats(allChampsSingleBets);
			seasonDayInfoMap.put(null, seasonDayInfoTotal);
			
		}
		Map<ChampEnum, HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>> calculateMapBySeasonDay = calculateMapBySeasonDay(seasonDayInfoMap);
		
		Map<ChampEnum, HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>> progressBalance = calcolateProgressBalance(calculateMapBySeasonDay);
		
		
		printProgressBalance(progressBalance);
		
		
		
	}


	private void printProgressBalance( Map<ChampEnum, HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>> progressBalance) {
		for (ChampEnum champ : progressBalance.keySet()) {
			System.out.println(champ);
			HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>> seasonDayMap = progressBalance.get(champ);
			
			String _12 = champ + " _12\t";
			String x = champ + "x\t";
			String uo = champ + "uo\t";
			String eh = champ + " eh\t";
			String total = champ + " total\t";
			for (Integer seasonDay : seasonDayMap.keySet()) {
				HashMap<TimeTypeEnum, SeasonDayBetResultInfo> timeMap = seasonDayMap.get(seasonDay);
				for (TimeTypeEnum timeType: timeMap.keySet()) {
					SeasonDayBetResultInfo seasonDayBetResultInfo = timeMap.get(timeType);
					if (TimeTypeEnum._final.equals(timeType)) {
						_12 += Utils.redimString(seasonDayBetResultInfo.get_12WinAmount()) + "\t";
						x += Utils.redimString(seasonDayBetResultInfo.getxWinAmount()) + "\t";
						uo += Utils.redimString(seasonDayBetResultInfo.getUoWinAmount()) + "\t";
						eh += Utils.redimString(seasonDayBetResultInfo.getEhWinAmount()) + "\t";
						total += Utils.redimString(seasonDayBetResultInfo.getTotalWinAmount()) + "\t";
					}
				}
			}
			System.out.println(_12);
			System.out.println(x);
			System.out.println(uo);
			System.out.println(eh);
			System.out.println(total);
			System.out.println("\n\n\n\n\n\n\n\n\n");
		}
	}

	
	

	private	Map<ChampEnum, HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>> calcolateProgressBalance(Map<ChampEnum, HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>> calculateMapBySeasonDay) {
		Map<ChampEnum, HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>> progressMap = new HashMap<ChampEnum, HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>>();
		for (ChampEnum champ : calculateMapBySeasonDay.keySet()) {
			 
			HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>> champMap = calculateMapBySeasonDay.get(champ);

			HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>> progressChampMap = new HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>();
			progressMap.put(champ, progressChampMap);
			
			 
			for (Integer seasonDay: champMap.keySet()) {
				if (seasonDay != null){ // elimina i totali
					HashMap<TimeTypeEnum, SeasonDayBetResultInfo> timeTypeMap = champMap.get(seasonDay);
					
					if (progressChampMap.keySet().isEmpty()) { // prima seasonDay
						HashMap<TimeTypeEnum, SeasonDayBetResultInfo> progressTimeTypeMap = new HashMap<TimeTypeEnum, SeasonDayBetResultInfo>();
						progressTimeTypeMap = createCopy(timeTypeMap);
						HashMap<TimeTypeEnum, SeasonDayBetResultInfo> startSituazione = createStartSituation();
						progressChampMap.put(seasonDay-1, startSituazione);
						
						progressChampMap.put(seasonDay, progressTimeTypeMap);
						continue;
					}	
					
					 
					//Nel caso ci siano pause di campionato //METODO CHE é UNA CACATA
					HashMap<TimeTypeEnum, SeasonDayBetResultInfo> progressLastSeasonInfo = progressChampMap.get(seasonDay-1);
					if (progressLastSeasonInfo == null)
						 progressLastSeasonInfo = progressChampMap.get(seasonDay-2);
					if (progressLastSeasonInfo == null)
						 progressLastSeasonInfo = progressChampMap.get(seasonDay-3);
					
					
					
					HashMap<TimeTypeEnum, SeasonDayBetResultInfo> progressTimeTypeMap = updateProgressTimeTypeMap(progressLastSeasonInfo, timeTypeMap);
					progressChampMap.put(seasonDay, progressTimeTypeMap);
				}
			}
		}
		return progressMap;
	}


	private HashMap<TimeTypeEnum, SeasonDayBetResultInfo> createStartSituation() {
		
		HashMap<TimeTypeEnum, SeasonDayBetResultInfo> startMap = new HashMap<TimeTypeEnum, SeasonDayBetResultInfo>();
		for (TimeTypeEnum timeType : TimeTypeEnum.values()) {
			startMap.put(timeType, new SeasonDayBetResultInfo());
		}
		startMap.put(null, new SeasonDayBetResultInfo());
		
		return startMap;
	}


	private HashMap<TimeTypeEnum, SeasonDayBetResultInfo> createCopy(HashMap<TimeTypeEnum, SeasonDayBetResultInfo> orig) {
		
		HashMap<TimeTypeEnum, SeasonDayBetResultInfo> copy = new HashMap<TimeTypeEnum, SeasonDayBetResultInfo>();
		for (TimeTypeEnum key : orig.keySet()) {
			
			SeasonDayBetResultInfo origInfo = orig.get(key);
			SeasonDayBetResultInfo progressInfo = new SeasonDayBetResultInfo(origInfo);
			
			copy.put(key, progressInfo);
		}
		
		return copy;
	}


	private HashMap<TimeTypeEnum, SeasonDayBetResultInfo> updateProgressTimeTypeMap(HashMap<TimeTypeEnum, SeasonDayBetResultInfo> lastProgress, HashMap<TimeTypeEnum, SeasonDayBetResultInfo> singleSeasonDayMap) {
		HashMap<TimeTypeEnum, SeasonDayBetResultInfo> progressMapToAdd = new HashMap<TimeTypeEnum, SeasonDayBetResultInfo>(); 
	
		
		for (TimeTypeEnum timeType : singleSeasonDayMap.keySet()) {
			
			SeasonDayBetResultInfo singleSeasonDayInfo = singleSeasonDayMap.get(timeType);
			
			SeasonDayBetResultInfo progressLastSeasonDayInfo = lastProgress.get(timeType);
			
			if (progressLastSeasonDayInfo == null)
				progressLastSeasonDayInfo = new SeasonDayBetResultInfo();
			
			SeasonDayBetResultInfo progressSeasonDayInfoToAdd  = new SeasonDayBetResultInfo();
			
			progressSeasonDayInfoToAdd.set_12BetNum(progressLastSeasonDayInfo.get_12BetNum() + singleSeasonDayInfo.get_12BetNum());
			progressSeasonDayInfoToAdd.set_12WinBetNum(progressLastSeasonDayInfo.get_12WinBetNum() + singleSeasonDayInfo.get_12WinBetNum());
			progressSeasonDayInfoToAdd.set_12WinAmount(progressLastSeasonDayInfo.get_12WinAmount() + singleSeasonDayInfo.get_12WinAmount());
			
			progressSeasonDayInfoToAdd.setxBetNum(progressLastSeasonDayInfo.getxBetNum() + singleSeasonDayInfo.getxBetNum());
			progressSeasonDayInfoToAdd.setxWinBetNum(progressLastSeasonDayInfo.getxWinBetNum() + singleSeasonDayInfo.getxWinBetNum());
			progressSeasonDayInfoToAdd.setxWinAmount(progressLastSeasonDayInfo.getxWinAmount() + singleSeasonDayInfo.getxWinAmount());
			
			progressSeasonDayInfoToAdd.setUoBetNum(progressLastSeasonDayInfo.getUoBetNum() + singleSeasonDayInfo.getUoBetNum());
			progressSeasonDayInfoToAdd.setUoWinBetNum(progressLastSeasonDayInfo.getUoWinBetNum() + singleSeasonDayInfo.getUoWinBetNum());
			progressSeasonDayInfoToAdd.setUoWinAmount(progressLastSeasonDayInfo.getUoWinAmount() + singleSeasonDayInfo.getUoWinAmount());
			
			progressSeasonDayInfoToAdd.setEhBetNum(progressLastSeasonDayInfo.getEhBetNum() + singleSeasonDayInfo.getEhBetNum());
			progressSeasonDayInfoToAdd.setEhWinBetNum(progressLastSeasonDayInfo.getEhWinBetNum() + singleSeasonDayInfo.getEhWinBetNum());
			progressSeasonDayInfoToAdd.setEhWinAmount(progressLastSeasonDayInfo.getEhWinAmount() + singleSeasonDayInfo.getEhWinAmount());
			
			progressSeasonDayInfoToAdd.setTotalBetNum(progressLastSeasonDayInfo.getTotalBetNum() + singleSeasonDayInfo.getTotalBetNum());
			progressSeasonDayInfoToAdd.setTotalWinBetNum(progressLastSeasonDayInfo.getTotalWinBetNum() + singleSeasonDayInfo.getTotalWinBetNum());
			progressSeasonDayInfoToAdd.setTotalWinAmount(progressLastSeasonDayInfo.getTotalWinAmount() + singleSeasonDayInfo.getTotalWinAmount());
			
			progressMapToAdd.put(timeType, progressSeasonDayInfoToAdd);
			
			
		}
		
		return progressMapToAdd;
		
	}


	private Map<ChampEnum, HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>> calculateMapBySeasonDay( Map<Integer, HashMap<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo>>> seasonMap) {
		Map<ChampEnum, HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>> champsMap = new HashMap<ChampEnum, HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>>();
		
		for (Integer seasonDay : seasonMap.keySet()) {
			HashMap<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo>> seasonDayInfoMap = seasonMap.get(seasonDay);
			for (TimeTypeEnum timeType : seasonDayInfoMap.keySet()) {
				HashMap<ChampEnum, SeasonDayBetResultInfo> timeMap = seasonDayInfoMap.get(timeType);
				for(ChampEnum champ : timeMap.keySet()){
					SeasonDayBetResultInfo champInfo = timeMap.get(champ);
					HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>> champMap = champsMap.get(champ);
					if (champMap == null) {
						champMap = new HashMap<Integer, HashMap<TimeTypeEnum, SeasonDayBetResultInfo>>();
						champsMap.put(champ, champMap);
					}
					
					HashMap<TimeTypeEnum, SeasonDayBetResultInfo> seasonDayMap = champMap.get(seasonDay);
					if (seasonDayMap== null) {
						seasonDayMap = new HashMap<TimeTypeEnum, SeasonDayBetResultInfo>();
						champMap.put(seasonDay, seasonDayMap);
					}
					seasonDayMap.put(timeType, champInfo);
					
				}
			}
		}
		return champsMap;
		
		
	}




	private void printSeasonDayTitle(String seasonDay) {
		
		System.out.println("#################################################################");
		Integer seasonDayInt = 0;
		Date endDate;
		if (seasonDay!= "Total") {
			seasonDayInt = Integer.valueOf(seasonDay);
			endDate = Utils.getDateOfBet(seasonDayInt+1);
		}
		else {
			int actualTrenoSeasonDay = Utils.getActualTrenoSeasonDay();
			endDate = Utils.getDateOfBet(actualTrenoSeasonDay);
		}
		
		Date startDate = Utils.getDateOfBet(seasonDayInt);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println();
		System.out.print("############  " + seasonDay + " - da " + simpleDateFormat.format(startDate) + " a " + simpleDateFormat.format(endDate));
		
		if (seasonDay.length() == 1) 		System.out.println("  ###################");
		else if (seasonDay.length() == 5)	System.out.println("  ##############");
		else								System.out.println("  ###############");
		System.out.println("#################################################################");
	}


	public HashMap<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo>> printAllBetStats(List<SingleBetBean> allChampsSingleBets) {
		
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
		
		HashMap<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo >> mapBetResultInfoGeneral = new HashMap<TimeTypeEnum, HashMap<ChampEnum, SeasonDayBetResultInfo>>();
		
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
 			String timeTypeName = timeType == null ? Utils.redimString("ALL TIME TYPES",16) : Utils.redimString(timeType.name(),16);
 			System.out.println();
 			System.out.println(timeTypeName);
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
 				lineX += "X"+ 				"\t" + info.getxBetNum() + 				"\t" + info.getxWinBetNum() + 		"\t" + ( info.getxBetNum()-info.getxWinBetNum())+ 			"\t" + Utils.redimString(info.getxWinAmount()) + "\t\t\t\t";
 				lineUo += "Uo"+ 			"\t" + info.getUoBetNum() + 			"\t" + info.getUoWinBetNum() + 		"\t" + ( info.getUoBetNum()-info.getUoWinBetNum())+ 		"\t" + Utils.redimString(info.getUoWinAmount()) + "\t\t\t\t";
 				lineEh += "Eh"+ 			"\t" + info.getEhBetNum() + 			"\t" + info.getEhWinBetNum() + 		"\t" + ( info.getEhBetNum()-info.getEhWinBetNum())+ 		"\t" + Utils.redimString(info.getEhWinAmount()) + "\t\t\t\t";
 				lineTotal += "Total"+ 		"\t" + info.getTotalBetNum() + 			"\t" + info.getTotalWinBetNum() + 	"\t" + ( info.getTotalBetNum()-info.getTotalWinBetNum())+ 	"\t" + Utils.redimString(info.getTotalWinAmount()) + "\t\t\t\t";
 			}
 			
 			System.out.println(lineChamp);
 			System.out.println(lineHeader);
 			System.out.println(line12);
 			System.out.println(lineX);
 			System.out.println(lineUo);
 			System.out.println(lineEh);
 			System.out.println(lineTotal);
 			
 		}
 		
 		
 		
 		
 		return mapBetResultInfoGeneral;
 		
 		
 		
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
			if (bet.getWin()== null)
				continue;
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
