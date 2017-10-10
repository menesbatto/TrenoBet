//package app.logic._1_matchesDownlaoder;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import app.logic._1_matchesDownlaoder.model.BetType;
//import app.logic._1_matchesDownlaoder.model.EventOdds;
//import app.utils.AppConstants;
//import app.utils.ChampEnum;
//import app.utils.HttpUtils;
//import app.utils.IOUtils;
//import app.utils.Utils;
//
//public class  EventsOddsDownloaderOLD {
////	static HashMap<ChampEnum, ArrayList<EventOdds>> allMatchesOdds = new HashMap<ChampEnum, ArrayList<EventOdds>>(); 
//	
//	
//	public static  void execute(){
//			
//		ArrayList<EventOdds> eventOdds = null;
//		int i = 1;
//		for (ChampEnum champ : ChampEnum.values()){
//			System.out.print("Downloading odds: " + i++ + " di "  + ChampEnum.values().length + " -> ");
//			eventOdds = new ArrayList<EventOdds>();
////			getResults(eventOdds, BetType.WIN, 			champ.getOddsWinUrl());
////			getResults(eventOdds, BetType.UNDER_OVER, 	champ.getOddsUoUrl());
////			correctNames(eventOdds);
////			allMatchesOdds.put(champ, eventOdds);
//			System.out.println(" DONE");
//		}
//		
////		getResults(eventOdds, BetType.HALF_TIME);
////		getResults(eventOdds, BetType.DOUBLE_CHANCE);
//		
//		
////		System.out.println(allMatchesOdds);
////		IOUtils.write(AppConstants.EVENTS_ODDS_PATH, allMatchesOdds);
//	}
//	
//	
//
//	
//	
//
//	private static void getResults(ArrayList<EventOdds> eventOdds, BetType betType, String urlInput) {
//		
//		Elements trs = getOddsElements(urlInput);
//
//		String dateString = null;
//		EventOdds eo = null;
//		Date date = null;
//		for (Element tr : trs){
//			if (tr.className().equals("sub1")){
//				dateString = tr.text();
//				date = Utils.getDateFromStringDate(dateString);
//				continue;
//			}
//			// Sono le quote di una partita
//			if (tr.getElementsByTag("td").size()==14){
//				if (Utils.isMatchNotTooFar(date, AppConstants.DAYS_FAR)){
//					eo = getEventOdd(eventOdds, tr, date);
//					eo.setDate(date);
//					switch (betType) {
//					case WIN:
//						populateEventOddsWin(tr, eo);
//						break;
//					case UNDER_OVER:
//						populateEventOddsUnderOver(tr, eo);
//						break;
//					case HALF_TIME:
//						populateEventOddsHalfTime(tr, eo);
//						break;
//					case DOUBLE_CHANCE:
//						populateEventOddsDoubleChance(tr, eo);
//						break;
//	
//					default:
//						break;
//					}
//				}
//			}
//		}
//	}
//
//
//	private static Elements getOddsElements(String urlInput) {
//		String url = urlInput; 
//		Document doc = null;
//		int i = 0;
////		System.setProperty("http.proxyHost", AppConstants.PROXY_HOST);
////		System.setProperty("http.proxyPort", AppConstants.PROXY_PORT);
//		doc = HttpUtils.getHtmlPage(url);
//		Element oddsTable = doc.getElementsByClass("odds-generic").get(0);
//		
//		Elements trs = oddsTable.getElementsByTag("tr");
//		return trs;
//	}
//
//
//
//	private static EventOdds getEventOdd(ArrayList<EventOdds> eventOdds, Element tr, Date date) {
//		Elements elements = tr.getElementsByTag("td");
//		String homeTeam = elements.get(4).text();
//		String awayTeam = elements.get(8).text();
//		for (EventOdds eo : eventOdds){ 
//			if (eo.getHomeTeam().equals(homeTeam) &&
//				eo.getAwayTeam().equals(awayTeam)	){
//				return eo;
//			}
//		}
//		// Non ancora inserito
//		EventOdds newEo = new EventOdds(); 
//		newEo.setHomeTeam(homeTeam);
//		newEo.setAwayTeam(awayTeam);
//		eventOdds.add(newEo);
//		return newEo;
//	}
//
//	private static EventOdds populateEventOddsWin(Element tr, EventOdds eo) {
//		Elements elements = tr.getElementsByTag("td");
//		Double oddsH = Double.valueOf(elements.get(2).text());
//		Double oddsD = Double.valueOf(elements.get(6).text());
//		Double oddsA = Double.valueOf(elements.get(10).text());
//		
//		Double percHome = 1/oddsH;
//		Double percDraw = 1/oddsD;
//		Double percAway = 1/oddsA;
//		
//		Double percTotal = percHome + percDraw + percAway;
//
//		Double percHomeAdjusted = percHome  / percTotal;
//		Double percDrawAdjusted = percDraw  / percTotal;
//		Double percAwayAdjusted = percAway  / percTotal;
//		
//		Double homeOddsAdjusted = 1 / percHomeAdjusted;
//		Double drawOddsAdjusted = 1 / percDrawAdjusted;
//		Double awayOddsAdjusted = 1 / percAwayAdjusted;
//		
//		
//		homeOddsAdjusted = homeOddsAdjusted != null && homeOddsAdjusted.toString().length() > 4 ? new Double(homeOddsAdjusted.toString().substring(0,4)) : homeOddsAdjusted;
//		drawOddsAdjusted = drawOddsAdjusted != null && drawOddsAdjusted.toString().length() > 4 ? new Double(drawOddsAdjusted.toString().substring(0,4)) : drawOddsAdjusted;
//		awayOddsAdjusted = awayOddsAdjusted != null && awayOddsAdjusted.toString().length() > 4 ? new Double(awayOddsAdjusted.toString().substring(0,4)) : awayOddsAdjusted;
//		
//		
//		eo.setOddsH(homeOddsAdjusted);
//		eo.setOddsD(drawOddsAdjusted);
//		eo.setOddsA(awayOddsAdjusted);
//		return eo;
//	}
//	
//	private static void populateEventOddsUnderOver(Element tr, EventOdds eo) {
//		Elements elements = tr.getElementsByTag("td");
//		Double oddsO = Double.valueOf(elements.get(2).text());
//		Double oddsU = Double.valueOf(elements.get(10).text());
//		eo.setOddsO(oddsO);
//		eo.setOddsU(oddsU);
//	}
//	
//	private static EventOdds populateEventOddsDoubleChance(Element tr, EventOdds eo) {
//		Elements elements = tr.getElementsByTag("td");
//		Double oddsHD = Double.valueOf(elements.get(2).text());
//		Double oddsDA = Double.valueOf(elements.get(6).text());
//		Double oddsHA = Double.valueOf(elements.get(10).text());
//		eo.setOddsHD(oddsHD);
//		eo.setOddsDA(oddsDA);
//		eo.setOddsHA(oddsHA);
//		return eo;
//	}
//
//	private static EventOdds populateEventOddsHalfTime(Element tr, EventOdds eo) {
//		Elements elements = tr.getElementsByTag("td");
//		Double oddsHalfTimeH = Double.valueOf(elements.get(2).text());
//		Double oddsHalfTimeD = Double.valueOf(elements.get(6).text());
//		Double oddsHalfTimeA = Double.valueOf(elements.get(10).text());
//		eo.setOddsHalfTimeH(oddsHalfTimeH);
//		eo.setOddsHalfTimeD(oddsHalfTimeD);
//		eo.setOddsHalfTimeA(oddsHalfTimeA);
//		return eo;
//	}
//
//	private static void correctNames(ArrayList<EventOdds> eventOdds) {
//		Map<String, String> nameToName = new HashMap<String, String>();
//		
//		//primo internet statto, secondo db excel
//		// ITA SERIE A
//		nameToName.put("Inter Milan", "Inter");
//		nameToName.put("AC Milan", "Milan");
//		
//		nameToName.put("Espanyol", "Espanol");
//		nameToName.put("Real Betis", "Betis");
//		nameToName.put("Atletico Madrid", "Ath Madrid");
//		nameToName.put("Real Sociedad", "Sociedad");
//		nameToName.put("Sporting Gijon", "Sp Gijon");
//		nameToName.put("Deportivo La Coruna", "La Coruna");
//		nameToName.put("Seville", "Sevilla");
//		nameToName.put("Celta Vigo", "Celta");
//		nameToName.put("Athletic Bilbao", "Ath Bilbao");
//		
//		// ENG PREM
//		nameToName.put("Tottenham Hotspur", "Tottenham");
//		nameToName.put("Hull City", "Hull");
//		nameToName.put("Leicester City", "Leicester");
//		nameToName.put("Stoke City", "Stoke");
//		nameToName.put("Manchester United", "Man United");
//		nameToName.put("West Bromwich Albion", "West Brom");
//		nameToName.put("Manchester City", "Man City");
//		nameToName.put("Swansea City", "Swansea");
//		nameToName.put("West Ham United", "West Ham");
//		
//		// ENG CHAM
//		nameToName.put("Sheffield Wednesday", "Sheffield Weds");
//		nameToName.put("Derby County", "Derby");
//		nameToName.put("Queens Park Rangers", "QPR");
//		nameToName.put("Norwich City", "Norwich");
//		nameToName.put("Brighton and Hove A", "Brighton");
//		nameToName.put("Huddersfield Town", "Huddersfield");
//		nameToName.put("Newcastle United", "Newcastle");
//		nameToName.put("Preston North End", "Preston");
//		nameToName.put("Rotherham United", "Rotherham");
//		nameToName.put("Wolverhampton Wndrs", "Wolves");
//		nameToName.put("Cardiff City", "Cardiff");
//		nameToName.put("Leeds United", "Leeds");
//		nameToName.put("Nottingham Forest", "Nott'm Forest");
//		nameToName.put("Wigan Athletic", "Wigan");
//		nameToName.put("Birmingham City", "Birmingham");
//		nameToName.put("Burton Albion", "Burton");
//		nameToName.put("Blackburn Rovers", "Blackburn");
//		nameToName.put("Ipswich Town", "Ipswich");
//		
//		//ENG one
//		nameToName.put("Bradford City", "Bradford"); 
//		nameToName.put("Oxford United","Oxford"); 
//		nameToName.put("Southend United", "Southend"); 
//		nameToName.put("Coventry City", "Coventry"); 
//		nameToName.put("Charlton Athletic", "Charlton"); 
//		nameToName.put("Bristol Rovers", "Bristol Rvs"); 
//		nameToName.put("Northampton Town", "Northampton"); 
//		nameToName.put("Scunthorpe United", "Scunthorpe"); 
//		nameToName.put("Peterborough United", "Peterboro"); 
//		nameToName.put("Shrewsbury Town", "Shrewsbury"); 
//		nameToName.put("Swindon Town", "Swindon"); 
//		nameToName.put("Oldham Athletic", "Oldham"); 
//		nameToName.put("Bolton Wanderers", "Bolton"); 
//		
//		
//		//ENF TWO
//		nameToName.put("Doncaster Rovers", "Doncaster"); 
//		nameToName.put("Plymouth Argyle", "Plymouth"); 
//		nameToName.put("Luton Town", "Luton"); 
//		nameToName.put("Carlisle United", "Carlisle"); 
//		nameToName.put("Cambridge United", "Cambridge"); 
//		nameToName.put("Colchester United", "Colchester"); 
//		nameToName.put("Exeter City", "Exeter"); 
//		nameToName.put("Wycombe Wanderers", "Wycombe"); 
//		nameToName.put("Mansfield Town", "Mansfield"); 
//		nameToName.put("Accrington Stanley", "Accrington"); 
//		nameToName.put("Grimsby Town", "Grimsby"); 
//		nameToName.put("Barnet 41", "Barnet"); 
//		nameToName.put("Crewe Alexandra", "Crewe"); 
//		nameToName.put("Cheltenham Town", "Cheltenham"); 
//		nameToName.put("Hartlepool United", "Hartlepool"); 
//		nameToName.put("Yeovil Town", "Yeovil"); 
//		nameToName.put("Newport County AFC", "Newport County"); 
//		
//		// GER BUND
//		nameToName.put("Hertha BSC Berlin", "Hertha");
//		nameToName.put("TSG Hoffenheim", "Hoffenheim");
//		nameToName.put("Borussia Monch'bach", "M'gladbach");
//		nameToName.put("Eintracht Frankfurt", "Ein Frankfurt");
//		nameToName.put("Darmstadt 98", "Darmstadt");
//		nameToName.put("Borussia Dortmund", "Dortmund");
//		nameToName.put("FC Cologne", "FC Koln");
//		nameToName.put("SV Hamburg", "Hamburg");
//		nameToName.put("Bayer Leverkusen", "Leverkusen");
//		nameToName.put("VfL Wolfsburg", "Wolfsburg");
//		
//		// FRA LIG 1
//		nameToName.put("Paris Saint-Germain", "Paris SG");
//		nameToName.put("Saint-Etienne", "St Etienne");
//		
//		// OLA EDER
//		nameToName.put("FC Groningen", "Groningen");
//		nameToName.put("FC Utrecht", "Utrecht");
//		nameToName.put("Heracles Almelo", "Heracles");
//		nameToName.put("ADO Den Haag", "Den Haag");
//		nameToName.put("Roda JC", "Roda");
//		nameToName.put("FC Twente Enschede", "Twente");
//		nameToName.put("Go Ahead Eagles D", "Go Ahead Eagles");
//		nameToName.put("Vitesse Arnhem", "Vitesse");
//		nameToName.put("NEC Nijmegen", "Nijmegen"); 
//		
//		
//		// SCO_PRE
//		nameToName.put("Heart of Midlothian", "Hearts"); 
//		nameToName.put("Partick Thistle", "Partick"); 
//		nameToName.put("Hamilton Academical", "Hamilton"); 
//		nameToName.put("Inverness CT", "Inverness C");
//		
//		// SCO CHAM
//		nameToName.put("Dunfermline Athletic", "Dunfermline"); 
//		nameToName.put("Queen of the South", "Queen of Sth"); 
//		nameToName.put("Raith Rovers", "Raith Rvs"); 
//		nameToName.put("Ayr United", "Ayr"); 
//				
//		
//		// SCO TWO
//		nameToName.put("Forfar Athletic", "Forfar"); 
//		nameToName.put("Elgin City", "Elgin"); 
//		nameToName.put("Stirling Albion", "Stirling"); 
//		nameToName.put("Berwick Rangers", "Berwick"); 
//		
//		//SCO ONE
//		nameToName.put("Alloa Athletic", "Alloa"); 
//		nameToName.put("Airdrieonians", "Airdrie Utd"); 
//		nameToName.put("Queen's Park", "Queens Park"); 
//		nameToName.put("Brechin City", "Brechin"); 
//		nameToName.put("Albion Rovers", "Albion Rvs"); 
//
//		// POR PRI
//		nameToName.put("Vitoria Setubal", "Setubal"); 
//		nameToName.put("Sporting Lisbon", "Sp Lisbon"); 
//		nameToName.put("FC Porto", "Porto"); 
//		nameToName.put("Vitoria Guimaraes", "Guimaraes"); 
//		nameToName.put("Sporting Braga", "Sp Braga"); 
//		nameToName.put("Nacional Madeira", "Nacional"); 
//		
//		//Grecia
//		nameToName.put("PAOK Salonika", "PAOK"); 
//		nameToName.put("AEK Athens", "AEK"); 
//		nameToName.put("Atromitos Athens", "Atromitos"); 
//		nameToName.put("Panaitolikos", "Panetolikos"); 
//		nameToName.put("Larissa", "Larisa"); 
//		nameToName.put("Levadiakos", "Levadeiakos"); 
//		nameToName.put("Iraklis Salonika", "Iraklis");
//		
//		//BEL
//		nameToName.put("Zulte-Waregem", "Waregem"); 
//		nameToName.put("Standard Liege", "Standard"); 
//		nameToName.put("Sint Truiden", "St Truiden"); 
//		nameToName.put("Mouscron-Peruwelz", "Mouscron"); 
//		
//		// TUR
//		nameToName.put("Istanbul Buyuksehir", "Buyuksehyr"); 
//		nameToName.put("Caykur Rizespor" , "Rizespor"); 
//
//		
//		
//		for (EventOdds e : eventOdds){
//			for (Map.Entry<String, String> entry : nameToName.entrySet()) {
//				if (e.getHomeTeam().equals(entry.getKey()))
//					e.setHomeTeam(entry.getValue());
//				if (e.getAwayTeam().equals(entry.getKey()))
//					e.setAwayTeam(entry.getValue());
//			}
//		}
//		
//	}
//	
//	public static HashMap<ChampEnum, ArrayList<EventOdds>>  retrieveEventsOdds() {
//		HashMap<ChampEnum, ArrayList<EventOdds>> allMatchesOdds = IOUtils.read(AppConstants.EVENTS_ODDS_PATH, HashMap.class);
//		return allMatchesOdds;
//	}
//	
//	
//}
