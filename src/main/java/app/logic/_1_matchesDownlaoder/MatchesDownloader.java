package app.logic._1_matchesDownlaoder;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.transaction.Transactional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import app.ApplicationContextProvider;
import app.dao.tabelle.MatchoDao;
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
import app.utils.HttpUtils;
import app.utils.IOUtils;
import app.utils.Utils;

public class MatchesDownloader implements Runnable {

	@Autowired
	private MatchoDao matchDao;
	
	
	
	private ChampEnum champ;
	private String type;

	public void setChamp(ChampEnum champ) {
		this.champ = champ;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void run() {
		downloadChampionshipResults(champ, type);
		HttpUtils.shutdown(champ);
	}

//	public void execute(String type){
//		ChampEnum[] allChamps = ChampEnum.values();
//		execute(type, allChamps);
//		
//	}
	
	
//	public void execute(String type, ChampEnum[] champs){
//		
//		//Ciclo su tutti i campionati per i risultati dei match giocati e le quote
//		for (ChampEnum champ : champs){
//			int savedMatchesNum = downloadChampionshipResults(champ, type);
//		}
//		
//	}
	
	
	
	@Transactional
	private int downloadChampionshipResults(ChampEnum champ, String type) {
		matchDao = ApplicationContextProvider.getApplicationContext().getBean(MatchoDao.class);
		String champSuffixUrl; 
		ArrayList<MatchResult> downloadedMatches;
		if (type == "Next") {
			downloadedMatches = matchDao.getDownloadedNextMatchByChampLight(champ);
			champSuffixUrl = champ.getNextMatchesUrl();
		}
		else {//if (type == "Past"){
			downloadedMatches = matchDao.getDownloadedPastMatchByChampLight(champ);
			champSuffixUrl = champ.getResultsUrl();
		}
		Document doc = null;
		
		String champSubsetUrl = champSuffixUrl; 
		doc = HttpUtils.getHtmlPage(champSubsetUrl);

		int size = 0;

		Element matchesTable;

		matchesTable = doc.getElementById("tournamentTable");
		int savedMatchesNum = createMatches(matchesTable, champSubsetUrl, champ, downloadedMatches);
		size = 50;

		
		// Calcolo numero di pagine in cui sono divisi i risultati
		Element paginationElement = doc.getElementById("pagination");
		if (paginationElement != null) {	// se è null stiamo alle prime giornate
			String resultsPagesString = paginationElement.getElementsByTag("a").last().attr("x-page");
			Integer resultsPages = Integer.valueOf(resultsPagesString);
			int i =  ( size / 50 ) + 1; 
			for (; i <= resultsPages; i++){
				champSubsetUrl = champSuffixUrl + "/#/page/" + i + "/";
				doc = HttpUtils.getHtmlPage(champSubsetUrl);
				matchesTable = doc.getElementById("tournamentTable");
				savedMatchesNum = createMatches(matchesTable, champSubsetUrl, champ, downloadedMatches);
				size += 50; 
				
			}
		}
		
		return size;
	}
	
	
	
	private int createMatches(Element matchesTable, String champSubsetUrl, ChampEnum champ, ArrayList<MatchResult> downloadedMatches) {
		Elements tableRows = matchesTable.getElementsByTag("tr");
		String[] split = champSubsetUrl.split("/");
		String pageNumString = split[split.length-1];
		Integer pageNum = 1;
		
//		if (!pageNumString.equals("results") && !pageNumString.equals("#") )
		if (pageNumString.chars().allMatch( Character::isDigit ))
			pageNum = Integer.valueOf(pageNumString);

		Date matchDate = null;
		MatchResult matchResult = null;
		int matchNum = 1;
		
		// VIA
//		Element obj = tableRows.get(0); // remember first item
//		Element obj2 = tableRows.get(1); // remember first item
//		Element obj3 = tableRows.get(2); // remember first item
//		Element obj4 = tableRows.get(3); // remember first item
//		Element obj5 = tableRows.get(4); // remember first item
//		Element obj6 = tableRows.get(5); // remember first item
//		Element obj7 = tableRows.get(6); // remember first item
//		Element obj8 = tableRows.get(7); // remember first item
//		tableRows.clear(); // clear complete list
//		tableRows.add(obj); // add first item
//		tableRows.add(obj2); // add first item
//		tableRows.add(obj3); // add first item
//		tableRows.add(obj4); // add first item
//		tableRows.add(obj5); // add first item
//		tableRows.add(obj6); // add first item
//		tableRows.add(obj7); // add first item
//		tableRows.add(obj8); // add first item
		// VIA 
		
		
//		<tr class="odd" xeid="EVzOVbsd">
//		 <td class="table-time datet t1503756000-1-1-0-0 ">14:00</td>
//		 <td class="name table-participant" colspan="2"><a href="/soccer/england/premier-league/watford-brighton-EVzOVbsd/">Watford - Brighton</a></td>
//		 <td class="odds-nowrp" xodd="1.9" xoid="E-2odiqxv464x0x6d3k2"><a href="" onclick="globals.ch.togle(this , 'E-2odiqxv464x0x6d3k2');return false;" xparam="odds_text">1.90</a></td>
//		 <td class="odds-nowrp" xodd="3.36" xoid="E-2odiqxv498x0x0"><a href="" onclick="globals.ch.togle(this , 'E-2odiqxv498x0x0');return false;" xparam="odds_text">3.36</a></td>
//		 <td class="odds-nowrp" xodd="4.19" xoid="E-2odiqxv464x0x6d3k3"><a href="" onclick="globals.ch.togle(this , 'E-2odiqxv464x0x6d3k3');return false;" xparam="odds_text">4.19</a></td>
//		 <td class="center info-value">6</td>
//		</tr>
//		int matchSkipped = 0;
		int savedMatches = 0;
		for (int i = 0; i < tableRows.size(); i++) {
			Element row = tableRows.get(i);
//			if (row.hasClass("dark")){
//				//ricerca della nazione, ma gia ce l'ho.
//			}
			if (row.hasClass("nob-border")){
				String dateString = row.getElementsByClass("datet").text();
				if (dateString.contains("Today"))
					dateString = dateString.substring(7) + " 2017";
				else if (dateString.contains("Yesterday"))
					dateString = dateString.substring(11) + " 2017";
				else if (dateString.contains("Tomorrow"))
					dateString = dateString.substring(10) + " 2017";
				
				matchDate = Utils.convertDateString(dateString); 
				
				//Scarico solo i matches che stanno a meno di 7 giorni di distanza da oggi
				if (AppConstants.ENABLE_DOWNLOAD_ONLY_NEAR_MATCHES) {
					Date expiringDate = Utils.getLimitDayOfCurrentSeasonDay();
					if (matchDate.after(expiringDate)) {
						return savedMatches;
					}
				}
			}
//			else if (row.hasClass("deactivate")){ 
//			else if (row.hasClass("odd") || row.hasAttr("heid")){ //next xxx
			else if (row.hasClass("deactivate") || row.hasClass("odd") || row.hasAttr("xeid")){ //results
					long startTime = System.nanoTime();
					System.out.println(champ + " Match " + ( (pageNum-1) * 50 + matchNum++ ) );
					
//				if (matchSkipped >= alreadySavedMatcheOnThisPage) {
					matchResult = createMatchResult(row, matchDate, champSubsetUrl, downloadedMatches); 
					if (matchResult != null) {
						matchResult.setChamp(champ);
					 	matchDao.save(matchResult);
					}
					savedMatches++;
//				}
//				else {
//					matchSkipped++;
//				}
				long currentTime = System.nanoTime();
				long duration = (currentTime - startTime);  //divide by 1000000 to get milliseconds.
				System.out.print("DONE\t" + duration / 1000000);
				System.out.println();				
				
				
			}
		}
		
		
		return savedMatches;
	}


	private MatchResult createMatchResult(Element row, Date matchDate, String champSubsetUrl, ArrayList<MatchResult> downloadedMatches) {
		try {
			MatchResult m = new MatchResult();
			
			// GENERAL INFO
			String time = row.getElementsByClass("datet").get(0).text();
			Integer hours = Integer.valueOf(time.split(":")[0]);
			Integer minutes = Integer.valueOf(time.split(":")[1]);
			matchDate.setHours(hours);
			matchDate.setMinutes(minutes);
			m.setMatchDate(matchDate);
			
			DateFormat df = new SimpleDateFormat("dd MM yyyy");
			String dateString = df.format(matchDate);
			
			m.setDate(dateString);
			
			Element teamsElement = row.getElementsByClass("table-participant").get(0);
			
			String teams = teamsElement.text();
			String homeTeam = teams.split(" - ")[0];
			String cleanHome = Utils.cleanString(homeTeam);
			m.setHomeTeam(cleanHome);
			String awayTeam = teams.split(" - ")[1];
			String cleanAway = Utils.cleanString(awayTeam);
			m.setAwayTeam(cleanAway);
			
			// Il match che si sta generando viene popolato dell'id del match a db
			MatchResult dbMatch = checkAlreadySavedMatch(m, downloadedMatches);
			if (dbMatch != null)
				m = dbMatch;
			
			boolean isAlreadySaved = dbMatch != null;
			
			Elements tableScoreElems = row.getElementsByClass("table-score");
			boolean isMatchPlayed = tableScoreElems != null && !tableScoreElems.isEmpty();
			
			boolean hasSavedMatchResult = m.getFTR() != null;

			boolean firstNext =  !isAlreadySaved && !isMatchPlayed;							//Salva		Quote
			
			boolean firstPast = !isAlreadySaved  && isMatchPlayed;							//Salva		Risultato e Quote
			
			boolean nextToPast = isAlreadySaved && isMatchPlayed && !hasSavedMatchResult;	//Aggiorna	Risultato
			
			boolean nextToNext =  isAlreadySaved && !isMatchPlayed;							//Aggiorna	Quote
			
			boolean pastToPast =  isAlreadySaved && isMatchPlayed && hasSavedMatchResult;	//Niente
			
			if (pastToPast)
				return null;
			
			
			
			if (firstPast || nextToPast) {
				
				if (AppConstants.DO_NOT_UPDATE_NEXT_MATCH && nextToNext) {
					return m;
				}
			
			//	RISULTATO
				
				String result = tableScoreElems.get(0).text();
				if (result.equals("abn.") || result.equals("award.") || result.equals("postp.")) {
					return null;
				}
				Integer homeScoreScoredGoals = Integer.valueOf(result.split(":")[0]);
				m.setFTHG(homeScoreScoredGoals);
				Integer awayScoredGoals = Integer.valueOf(result.split(":")[1]);
				m.setFTAG(awayScoredGoals);
				
				String finalResult;
				if (homeScoreScoredGoals > awayScoredGoals)
					finalResult = "H";
				else if (homeScoreScoredGoals == awayScoredGoals)
					finalResult = "D";
				else
					finalResult = "A";
					
				m.setFTR(finalResult);
				
				
				
				// Caricamento pagina per scaricare le informazioni relative al primo tempo
				Elements elementsByTag = teamsElement.getElementsByTag("a");
				String matchSuffixUrl = elementsByTag.last().attr("href");
				String matchUrl = AppConstants.SITE_URL + matchSuffixUrl;
				
				String infoUrl = matchUrl + TimeTypeEnum._final.get_1x2urlSuffix();
				Document infoPage = HttpUtils.getHtmlPage(infoUrl);
				
				popupateHalfTimeResultInfo(m, infoPage);
				
				
			}

			
			//	QUOTE
			if (firstNext || firstPast || nextToNext) {	

				if (AppConstants.DO_NOT_UPDATE_NEXT_MATCH && nextToNext) {
					return m;
				}
				
				Double H = Double.valueOf(row.getElementsByClass("odds-nowrp").get(0).text());
				Double D = Double.valueOf(row.getElementsByClass("odds-nowrp").get(1).text());
				Double A = Double.valueOf(row.getElementsByClass("odds-nowrp").get(2).text());
				m.setPSCH(H);
				m.setPSCD(D);
				m.setPSCA(A);
				_1x2Leaf avg1x2Odds = new _1x2Leaf(H, D, A);
				m.get_1x2().get(TimeTypeEnum._final).setAvg1x2Odds(avg1x2Odds);

				
				// ADDITIONAL MATCH INFO
				Elements elementsByTag = teamsElement.getElementsByTag("a");
				String matchSuffixUrl = elementsByTag.last().attr("href");
				
				// 		1x2
				String matchUrl = AppConstants.SITE_URL + matchSuffixUrl;
				populateMatch1X2(m, matchUrl);
				
		//		//		ASIAN HANDICAP
		//		populateMatchAH(m, matchUrl);
		//
		//		// 		UNDER OVER
				populateMatchUO(m, matchUrl);
		//		
		//		// 		EUROPEAN HANDICAP
				populateMatchEH(m, matchUrl);
		//		
		//		// 		DOUBLE CHANCE
		//		populateMatchDC(m, matchUrl);
		//		
		//		// 		CORRECT SCORE
		//		populateMatchCS(m, matchUrl);
		//		
		//		// 		DRAW NO BET
		//		populateMatchDNB(m, matchUrl);
				
		//		System.out.println(m);
				System.out.print(".");
		
			}
			return m;
		}
		catch (Exception e) {
			System.out.println("Problema nella creazione del match. ");
			e.printStackTrace();
		}
		return null;
	}

	


	

	private MatchResult checkAlreadySavedMatch(MatchResult m, ArrayList<MatchResult> downloadedMatches) {
		for (MatchResult dbMatch : downloadedMatches) {
			if  (	dbMatch.getHomeTeam().equals(m.getHomeTeam()) && 
					dbMatch.getAwayTeam().equals(m.getAwayTeam()) &&
					dbMatch.getMatchDate().getYear() == m.getMatchDate().getYear() &&
					dbMatch.getMatchDate().getMonth() == m.getMatchDate().getMonth() &&
					dbMatch.getMatchDate().getDay() == m.getMatchDate().getDay()
				) {
				return dbMatch;
				//return true;
			}
		}
		return null;
	}


	private static void populateMatchAH(MatchResult m, String matchUrl) {
		String asianHandicapSuffix = "/#ah;2";
		
	}

	private static void populateMatchUO(MatchResult m, String matchUrl) {
		// U O - FINAL
		for (TimeTypeEnum timeType : TimeTypeEnum.values()){
			populateMatchUOSpecificType(m, matchUrl, timeType);
		}
		
	}

	private static void populateMatchUOSpecificType(MatchResult m, String matchUrl, TimeTypeEnum timeType) {
		String infoUrl = matchUrl + timeType.getUoUrlSuffix();
		Document matchPage = HttpUtils.getHtmlPageUO(infoUrl);
		Elements thresholds = matchPage.getElementById("odds-data-table").getElementsByClass("table-container");

		
		
		for (Element treshold : thresholds){
			
			// Recupera l'elemento corretto skippando quelli inutili
			Elements uoAvgElems = treshold.getElementsByClass("table-header-light");
			if (uoAvgElems.size() == 0 ){
				continue;
			}
			Element uoAvgElem = uoAvgElems.get(0);
			
			String underAvgString = uoAvgElem.getElementsByTag("span").get(1).text();
			if( underAvgString.equals("") ){
				//qui becca il 0,75 che è roba nascosta...skippalo col try catch oppure in modo diverso
				continue;
			}
			
			
			// Recupera l'enum della soglia
			String uoThresholdRaw = treshold.getElementsByTag("strong").get(0).text();
			String uoThresholdString = uoThresholdRaw.substring(uoThresholdRaw.indexOf("+")).replace(".", "_").replace("+", "_");
			UoThresholdEnum uoThresholdEnum = UoThresholdEnum.valueOf(uoThresholdString);

			// Setta l'avg
			UoTimeType uoTimeType = m.getUo().get(timeType);
			UoFull uoThreshold = getUoThreshold(uoThresholdEnum, uoTimeType);
			Double underAvg = Double.valueOf(underAvgString);												//Invertiti
			Double overAvg = Double.valueOf(uoAvgElem.getElementsByTag("span").get(2).text());				//Invertiti
			UoLeaf uoAvgMatch = new UoLeaf(underAvg , overAvg);
			uoThreshold.setAvgUoOdds(uoAvgMatch);
			
			// Setta per i valori di UO per tutte le betHouse
			Elements allTrs = treshold.getElementsByTag("tbody").get(0).getElementsByTag("tr");
			for (Element tr : allTrs){
				Elements elementsMatchingText = tr.getElementsByAttributeValueStarting("title", "Go to");
				if (elementsMatchingText.size() > 0){
					String betHouseName = getBetHouseName(tr);
					String underString = tr.getElementsByTag("div").get(2).text();								//Corretti
					Double underBetRoom = !underString.equals("") ? Double.valueOf(underString) : 1.0;			//Corretti
					
					String overSring = tr.getElementsByTag("div").get(1).text();
					Double overBetRoom = !overSring.equals("") ? Double.valueOf(overSring) : 1.0;

					BetHouseEnum betHouse = BetHouseEnum.valueOf(betHouseName);
					
					UoLeaf uoMatch =  new UoLeaf(underBetRoom, overBetRoom);
					uoThreshold.getBetHouseToUoOdds().put(betHouse, uoMatch);
				}
			}
			
		}
	}

	private static UoFull getUoThreshold(UoThresholdEnum uoThresholdEnum, UoTimeType uoType) {
		UoFull uoThreshold = null;
		uoThreshold = uoType.getMap().get(uoThresholdEnum);
		return uoThreshold;
	}

	private static String getBetHouseName(Element tr) {
		return tr.getElementsByClass("name").text().split("\\.")[0];
	}
	
	private static void populateMatchDNB(MatchResult m, String matchUrl) {
		String drawNoBetSuffix = "/#dnb;2";
		
	}
	
	private static void populateMatchEH(MatchResult m, String matchUrl) {
		// European Handicap - FINAL
		for (TimeTypeEnum timeType : TimeTypeEnum.values()){
			populateMatchEHSpecificType(m, matchUrl, timeType);
		}
		
	}
	
	private static void populateMatchEHSpecificType(MatchResult m, String matchUrl, TimeTypeEnum timeType) {

		String infoUrl = matchUrl + timeType.getEhUrlSuffix();
		Document matchPage = HttpUtils.getHtmlPageEH(infoUrl);
		Elements handicaps = matchPage.getElementById("odds-data-table").getElementsByClass("table-container");

		
		for (Element handicap : handicaps){
			
			// Recupera l'elemento corretto skippando quelli inutili
			Elements uoAvgElems = handicap.getElementsByClass("table-header-light");
			if (uoAvgElems.size() == 0 ){
				continue;
			}
			Element uoAvgElem = uoAvgElems.get(0);
			
			String odds1AvgString = uoAvgElem.getElementsByTag("span").get(3).text();
			if( odds1AvgString.equals("") ){
				//qui becca il 0,75 che è roba nascosta...skippalo col try catch oppure in modo diverso
				continue;
			}
			
			
			// Recupera l'enum della soglia
			String ehThresholdRaw = handicap.getElementsByTag("strong").get(0).text();				//Sono messi da destra a sinistra!!!!
			String ehThresholdString = ehThresholdRaw.substring(ehThresholdRaw.lastIndexOf(" ")+1).replace("-", "m").replace("+", "p");
			HomeVariationEnum ehThresholdEnum = HomeVariationEnum.valueOf(ehThresholdString);

			// Setta l'avg
			EhTimeType ehTimeType = m.getEh().get(timeType);
			_1x2Full ehThreshold = getEhThreshold(ehThresholdEnum, ehTimeType);
			
			Double odds1Avg = !odds1AvgString.equals("") ? Double.valueOf(odds1AvgString) : 1.0;
			
			String oddsXavgString = uoAvgElem.getElementsByTag("span").get(2).text();				//Sono messi da destra a sinistra!!!!
			Double oddsXAvg = !oddsXavgString.equals("") ? Double.valueOf(oddsXavgString) : 1.0;
			
			String odds2avgString = uoAvgElem.getElementsByTag("span").get(1).text();				//Sono messi da destra a sinistra!!!!
			Double odds2Avg = !odds2avgString.equals("") ? Double.valueOf(odds2avgString) : 1.0;
			
			_1x2Leaf _1x2AvgMatch = new _1x2Leaf(odds1Avg , oddsXAvg, odds2Avg);
			
			ehThreshold.setAvg1x2Odds(_1x2AvgMatch);

			// Setta per i valori di UO per tutte le betHouse
			Elements allTrs = handicap.getElementsByTag("tbody").get(0).getElementsByTag("tr");
			for (Element tr : allTrs){
				Elements elementsMatchingText = tr.getElementsByAttributeValueStarting("title", "Go to");
				if (elementsMatchingText.size() > 0){
					String betHouseName = getBetHouseName(tr);
					String odds1String = tr.getElementsByTag("div").get(1).text();					//Sono messi da sinistra a destra!!!! NORMALI
					Double odd1 = !odds1String.equals("") ? Double.valueOf(odds1String) : 1.0;
					
					String oddsXString = tr.getElementsByTag("div").get(2).text();					//Sono messi da sinistra a destra!!!! NORMALI
					Double oddX =  !oddsXString.equals("") ? Double.valueOf(oddsXString) : 1.0;
										
					String odds2String = tr.getElementsByTag("div").get(3).text();					//Sono messi da sinistra a destra!!!! NORMALI
					Double odd2 =  !odds2String.equals("") ? Double.valueOf(odds2String) : 1.0;
					

					BetHouseEnum betHouse = BetHouseEnum.valueOf(betHouseName);
					
					_1x2Leaf _1x2Match = new _1x2Leaf(odd1 , oddX, odd2);
					ehThreshold.getBetHouseTo1x2Odds().put(betHouse, _1x2Match);
				}
			}
			
		}
		
	}


	private static _1x2Full getEhThreshold(HomeVariationEnum ehThresholdEnum, EhTimeType ehTimeType) {
		_1x2Full handicap = null;
		handicap = ehTimeType.getMap().get(ehThresholdEnum);
		return handicap;
	}

	private static void populateMatchDC(MatchResult m, String matchUrl) {
		String doubleChanceSuffix = "/#double;2";
		
	}
	
	private static void populateMatchCS(MatchResult m, String matchUrl) {
		String correctScoreSuffix = "/#cs;2";
		
	}
	
	private static void populateMatch1X2(MatchResult m, String matchUrl) {
		
		for (TimeTypeEnum timeType : TimeTypeEnum.values()){
			populateMatch1X2SpecificType(m, matchUrl, timeType);
		}
		
		
		
	}

	private static void populateMatch1X2SpecificType(MatchResult m, String matchUrl, TimeTypeEnum timeType) {
		
		// 1 X 2 - FINAL
		String infoUrl = matchUrl + timeType.get_1x2urlSuffix();
		Document infoPage = HttpUtils.getHtmlPage(infoUrl);

//		viapopupateHalfTimeResultInfo(m, infoPage);
		
		Elements betHouses = infoPage.getElementById("col-content").getElementsByClass("detail-odds").get(0).getElementsByClass("lo");
		
		Elements averageTds = infoPage.getElementById("col-content").getElementsByClass("detail-odds").get(0).getElementsByClass("aver").first().getElementsByTag("td");
		Double oddsH;
		Double oddsD;
		Double oddsA;
		
		for (Element house : betHouses){
			String betHouseName = getBetHouseName(house);
			if (betHouseName.equals("")) {
				continue;
			}
			BetHouseEnum betHouse = BetHouseEnum.valueOf(betHouseName);
			try {
				oddsH = Double.valueOf(house.getElementsByClass("right").get(0).text());
				oddsD = Double.valueOf(house.getElementsByClass("right").get(1).text());
				oddsA = Double.valueOf(house.getElementsByClass("right").get(2).text());
				
				
				_1x2Leaf _1x2Match = new _1x2Leaf(oddsH, oddsD, oddsA);
				
				m.get_1x2().get(timeType).getBetHouseTo1x2Odds().put(betHouse, _1x2Match);
			}
			catch (NumberFormatException e) {
				System.out.println("Problemi legati alla mancanza di quote, durante la creazione del match " + infoUrl);
			}
		}
		
		_1x2Leaf _1x2avgMatch;
		try {
			oddsH = Double.valueOf(averageTds.get(1).text());
			oddsD = Double.valueOf(averageTds.get(2).text());
			oddsA = Double.valueOf(averageTds.get(3).text());

			_1x2avgMatch = new _1x2Leaf(oddsH, oddsD, oddsA);
		}
		catch (Exception e) { // caso in cui ci sono dei dati malformati e manca la quota avg allora se la calcola
			HashMap<BetHouseEnum, _1x2Leaf> betHouseMap = m.get_1x2().get(timeType).getBetHouseTo1x2Odds();
			_1x2Leaf sum = new _1x2Leaf(0.0,0.0,0.0);
			for (BetHouseEnum betHouse: betHouseMap.keySet()) {
				_1x2Leaf single = betHouseMap.get(betHouse);
				
				sum.setOdd1(sum.getOdd1() + single.getOdd1());
				sum.setOddX(sum.getOddX() + single.getOddX());
				sum.setOdd2(sum.getOdd2() + single.getOdd2());
			}
			
			Double betHouseNum = new Double(betHouseMap.size());
			
			double odd1 = Math.round(sum.getOdd1()/betHouseNum * 100.0)/100.0;
			double oddX = Math.round(sum.getOddX()/betHouseNum * 100.0)/100.0;
			double odd2 = Math.round(sum.getOdd2()/betHouseNum * 100.0)/100.0;
			
			_1x2avgMatch = new _1x2Leaf(odd1, oddX, odd2);
			
		}
		
		m.get_1x2().get(timeType).setAvg1x2Odds(_1x2avgMatch);
		
		
		
	}

	private static void popupateHalfTimeResultInfo(MatchResult m, Document infoPage) {
		Elements halfTimeResultElems = infoPage.getElementsByClass("result");		//
		if (halfTimeResultElems != null && !halfTimeResultElems.isEmpty()) {
		
			String halfTimeResultString = halfTimeResultElems.get(0).text().split("\\(")[1].split(",")[0];
			Integer hthg = Integer.valueOf(halfTimeResultString.split(":")[0]);
			Integer htag = Integer.valueOf(halfTimeResultString.split(":")[1]);
			m.setHTHG(hthg);
			m.setHTAG(htag);
			
			String halfTimeResult;
			if (hthg > htag)
				halfTimeResult = "H";
			else if (hthg == htag)
				halfTimeResult = "D";
			else
				halfTimeResult = "A";
				
			m.setHTR(halfTimeResult);
			
		}
	}

	
	
	
	
}
