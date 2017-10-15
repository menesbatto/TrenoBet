package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.utils.Utils;

public class EventOddsBean implements Serializable, Comparable<EventOddsBean>{
	private static final long serialVersionUID = 7778211632531238253L;
	
	private int matchId;
	
	private TimeTypeEnum timeType;
	
	// Info sulla bonta dei due team relativamente a tutto.
	private ResultGoodnessBean homeResultGoodness;
	private ResultGoodnessBean awayResultGoodness;
	private ResultGoodnessBean totalResultGoodness;
	
	private String homeTeam;
	private String awayTeam;
	
	// Informazioni sulla motivazione
	private Double homeMotivation;
	private Double awayMotivation;

	
	// Quote del 1x2, uo e eh
	private Double odds1;
	private Double oddsX;
	private Double odds2;
	
	private Map<HomeVariationEnum, _1x2Leaf> ehOddsMap;
	
	private Map<UoThresholdEnum, UoLeaf> uoOddsMap;
	
	private Date date;

	// Informazioni sui trend di 1x2, uo e eh
	private String homeTrend;
	private String awayTrend;

	private Map<UoThresholdEnum, String> homeTrendUo;
	private Map<UoThresholdEnum, String> awayTrendUo;

	private Map<HomeVariationEnum, String> homeTrendEh;
	private Map<HomeVariationEnum, String> awayTrendEh;


	//Campi per simulare la scommessa su questo evento
//	private List<SingleBetBean> betResults;
	

	
	
	public EventOddsBean() {
		this.homeTrendUo = new HashMap<UoThresholdEnum, String>();
		this.awayTrendUo = new HashMap<UoThresholdEnum, String>();
		this.homeTrendEh = new HashMap<HomeVariationEnum, String>();
		this.awayTrendEh = new HashMap<HomeVariationEnum, String>();
//		this.betResults = new ArrayList<SingleBetBean>();
	}

	@Override
	public String toString() {
		return toStringInner(null, null, null, true);
	}
	
	public String toStringInner(Boolean show1x2, UoThresholdEnum uoThr, HomeVariationEnum homeVar, Boolean full) {
		if (homeResultGoodness == null){
			return homeTeam + " - " + awayTeam + 
				
				"\n\tWIN\t" + odds1 + "\t" + oddsX + "\t" + odds2 + 
				
				"\n\tUO\t" + "via" + "\t" + "via" + "\n\n";
		}
		else {
			String _trend = "";
			String bets = createBets();
			
			String _1x2odds = "";
//			String _1x2trend = "";
			String _1x2header = "";
			String _1x2H = "";
			String _1x2A = "";
			String _1x2 = "";
			if (show1x2 != null || full) {
				_1x2odds = get1x2odds();
//				_1x2trend = "\tTREND\t | " + Utils.redimString(homeTrend,16) + "\t-\t" + Utils.redimString(awayTrend,16) + " - " + "\n";
				_trend =  Utils.redimString(homeTrend,16) + " " + Utils.redimString(awayTrend,16);
				_1x2header = get1x2Header();
				_1x2H = get1x2StringH();
				_1x2A = get1x2StringA();
				
				_1x2 = 	"\n\t        1x2          \n" + 
//						_1x2trend +
						_1x2odds  +
						_1x2header  +
						_1x2H + 
						_1x2A +
						"\t################### \n\n";
			}
					
			
			
			
			String uoOdds = "";
//			String uoTrend = "";
			String uoHeader  = "";
			String uoH = "";
			String uoA = "";
			String uo = "";
			if (uoThr!= null || full) {
				uoOdds = getUoOdds(uoOddsMap, uoThr);
//				uoTrend = getUoTrend(uoThr);
				_trend =  getUoTrend(uoThr);
				uoHeader = getUoHeader(uoThr);
				uoH = getUoString(homeResultGoodness.getUoGoodness(), uoThr);
				uoA = getUoString(awayResultGoodness.getUoGoodness(), uoThr);
				uo = 	"\t        UO          \n\n" + 
//						"\tTREND\t | " + uoTrend + "\n" + 
						"\tQUOTE\t | " + uoOdds + "\n" + 
						"\t\t | " + uoHeader + "\n" + 
						"\n\tH GOOD\t |" +
						" " +  uoH  +  
						"\n\tA GOOD\t |" +
						" " +  uoA + "\n\n" +
						"\t################### \n\n";
			}
			
			
			
			
			String ehOdds = "";
//			String ehTrend = "";
			String ehHeader = "";
			String ehH = "";
			String ehA = "";
			String eh = "";
			if (homeVar != null || full) {
				ehOdds = getEhOdds(ehOddsMap, homeVar);
//				ehTrend = getEhTrend(homeVar);
				_trend =  getEhTrend(homeVar);
				ehHeader = getEhHeader(homeVar);
				ehH = getEhString(homeResultGoodness.getEhGoodness(), "H", homeVar);
				ehA = getEhString(awayResultGoodness.getEhGoodness(), "A", homeVar);
				eh = "\t        EH          \n\n" + 
//						"\tTREND\t | " + ehTrend + "\n" + 
						"\tQUOTE\t | " + ehOdds + "\n" + 
						"\t\t | " + ehHeader + "\n" + 
						"\n\tH GOOD\t |" +
						" " +  ehH  +  
						"\n\tA GOOD\t |" +
						" " +  ehA + "\n\n" +
						"\t################### \n\n";
			}
			
			
			
		
			
			String s =  
	
				Utils.redimString(homeTeam,16) + " " + Utils.redimString(awayTeam,16) + "\t\t" +  timeType + "\t" +  getFormattedDate() + "\n" + 
				
				bets +
			
				  _trend + "\n" +
				  
				//1x2
				
				_1x2 +
				

				//UO	
				uo +
				
				//EH
				eh;
				
				
				
				
//				"\n\tA GOOD\t | " + awayResultGoodness.getGoodnessL() + "\t" + Utils.redimString(awayMotivation) + "\t | " + awayResultGoodness.getGoodnessD() + "\t | " + awayResultGoodness.getGoodnessW() + "\t" + Utils.redimString(awayMotivation) + "\t\t | " + awayResultGoodness.getGoodnessU() + "\t" + awayResultGoodness.getGoodnessO() + "\n\n"; 
	
			return s;
		}
	}



	//##################       1x2      ##################

	private String createBets() {
		String result = "";
//		for (SingleBetBean br : betResults) {
//			result += br.getBetType() + "\t" + br.getMatchResult() + "\t" + br.getWinOdds() + "\n";
//		}
		return result + "\n";
	}

	private String get1x2StringH() {
		String result =
				"\n\tH GOOD\t | " + Utils.redimString(homeResultGoodness.getWinClean().getGoodnessW()) + 
				"\t" + "null" + //Utils.redimString(homeMotivation) + 
				"\t" + "null" + //Utils.redimString(homeResultGoodness.getWinMotivation().getGoodnessW()) + 
				"\t" + "null" + //Utils.redimString(homeResultGoodness.getWinTrend().getGoodnessW()) + 
				"\t" + "null" + //Utils.redimString(homeResultGoodness.getWinFinal().getGoodnessW()) + 
				"\t | " + homeResultGoodness.getWinClean().getGoodnessD() + "\t\t | " +  
				Utils.redimString(homeResultGoodness.getWinClean().getGoodnessL()) + 
				"\t" + "null" +//Utils.redimString(homeMotivation) + 
				"\t" +"null" +  // Utils.redimString(homeResultGoodness.getWinMotivation().getGoodnessL()) + 
				"\t" + "null" +  //Utils.redimString(homeResultGoodness.getWinTrend().getGoodnessL()) + 
				"\t" + "null";  //Utils.redimString(homeResultGoodness.getWinFinal().getGoodnessL()) + 
		  
		return result;
	}

	private String get1x2StringA() {
		String result = "\n\tA GOOD\t | " + Utils.redimString(awayResultGoodness.getWinClean().getGoodnessL()) + 
						"\t" + "null" + //Utils.redimString(awayMotivation) + 
						"\t" + "null" +  //Utils.redimString(awayResultGoodness.getWinMotivation().getGoodnessL()) + 
						"\t" + "null" +//Utils.redimString(awayResultGoodness.getWinTrend().getGoodnessL()) + 
						"\t" + "null" + //Utils.redimString(awayResultGoodness.getWinFinal().getGoodnessL()) + 
						"\t | " + awayResultGoodness.getWinClean().getGoodnessD() + "\t\t | " +  
						Utils.redimString(awayResultGoodness.getWinClean().getGoodnessW()) + 
						"\t" +  "null" +//Utils.redimString(awayMotivation) + 
						"\t" + "null" +  //Utils.redimString(awayResultGoodness.getWinMotivation().getGoodnessW()) +
						"\t" +  "null" + //Utils.redimString(awayResultGoodness.getWinTrend().getGoodnessW()) + 
						"\t" +  "null" + //Utils.redimString(awayResultGoodness.getWinFinal().getGoodnessW()) + 
						"\n\n";
		return result;
	}

	private String get1x2Header() {
		String header = "\n\t\t | " + "clea" + "\t" + "mot" + "\t" + "withMot" + "\t" + "withTre" + "\t" + "withAll" + "\t | " + "clea" + "\t\t | "  + "clea" + "\t" + "mot" + "\t" + "withMot" + "\t" + "withTre" + "\t" + "withAll" + "\t | ";
		return header;
	}

	private String get1x2odds() {
		String odds = "\n\tQUOTE\t | " + "1 - " + "\t" + odds1 + "\t\t\t\t | " + "X - " + "\t" + oddsX + "\t | " + "2 - " + "\t" + odds2 + "\t\t\t\t | ";
				
		return odds;
	}

	//##################       EH      ##################
	
	private String getEhTrend(HomeVariationEnum homeVar) {
		String result = this.homeTrendEh.get(homeVar) + "\t-\t" + this.awayTrendEh.get(homeVar) + "";
		return result;
	}
	
	private String getEhOdds(Map<HomeVariationEnum, _1x2Leaf> ehMap, HomeVariationEnum homeVar) {
		String result = "";
		EnumSet<HomeVariationEnum> subSet = HomeVariationEnum.getSubSet();
		List<HomeVariationEnum> list = new ArrayList<HomeVariationEnum>();
		if (homeVar == null) 
			list.addAll(subSet);
		else
			list.add(homeVar);
		
		for (HomeVariationEnum key : list) {
			_1x2Leaf value = ehMap.get(key);
			String _1;
			String x;
			String _2;
			if (value != null) {
				_1 = Utils.forceLength(value.getOdd1(), 4);
				x = Utils.forceLength(value.getOddX(), 4);
				_2 = Utils.forceLength(value.getOdd2(), 4);
		
			}
			else {
				_1 = "n/a ";
				x = "n/a ";
				_2 = "n/a ";
			}
				result += key + ": " + _1 + " " + x  + " " + _2 + "\t|";
		}
		
		
		return result;
	}


	private String getEhHeader(HomeVariationEnum homeVar) {
		String result = "";
		
		EnumSet<HomeVariationEnum> subSet = HomeVariationEnum.getSubSet();
		List<HomeVariationEnum> list = new ArrayList<HomeVariationEnum>();
		if (homeVar == null) 
			list.addAll(subSet);
		else
			list.add(homeVar);
		
		for (HomeVariationEnum homeVarCurr : list) {
			result += homeVarCurr + ": " + "1    x    2" + "\t|";
		}
		return result;
	}


	private String getEhString(Map<HomeVariationEnum, ResultGoodnessWDLBean> ehMap, String playingField, HomeVariationEnum homeVar) {
		String result = "";
		
		EnumSet<HomeVariationEnum> subSet = HomeVariationEnum.getSubSet();
		List<HomeVariationEnum> list = new ArrayList<HomeVariationEnum>();
		if (homeVar == null) 
			list.addAll(subSet);
		else
			list.add(homeVar);
		
		for (HomeVariationEnum homeVarcurr : list) {
			if (ehMap.get(homeVarcurr) == null) {
				result += homeVarcurr + ": " + "n/a " + " " +  "n/a " + " " +  "n/a " + "\t|";
			}
			else {
				if (playingField == "H") {
					result += homeVarcurr + ": " + Utils.forceLength(ehMap.get(homeVarcurr).getGoodnessW(), 4) + " " + Utils.forceLength(ehMap.get(homeVarcurr).getGoodnessD(), 4)+ " " + Utils.forceLength(ehMap.get(homeVarcurr).getGoodnessL(), 4) + "\t|";
				}
				else {//if (playingField == "A") {
					result += homeVarcurr + ": " + Utils.forceLength(ehMap.get(homeVarcurr).getGoodnessL(), 4) + " " + Utils.forceLength(ehMap.get(homeVarcurr).getGoodnessD(), 4)+ " " + Utils.forceLength(ehMap.get(homeVarcurr).getGoodnessW(), 4) + "\t|";
				}
			}
		}
		return result;
	}


	
	

	//##################       UO      ##################
	
	private String getUoTrend(UoThresholdEnum uoThr) {
		String result = this.homeTrendUo.get(uoThr) + "\t-\t" + this.awayTrendUo.get(uoThr) + "";
		return result;
	}
	
	private String getUoOdds(Map<UoThresholdEnum, UoLeaf> uoMap, UoThresholdEnum uoThr) {
		String result = "";

		UoThresholdEnum[] array = UoThresholdEnum.values();
		List<UoThresholdEnum> list = new ArrayList<UoThresholdEnum>();
		if (uoThr == null) 
			list = Arrays.asList(array);
		else
			list.add(uoThr);
		
		for (UoThresholdEnum uoThrCurr : list) {
			UoLeaf value = uoMap.get(uoThrCurr);
			String u;
			String o;
			if (value != null) {
				u = Utils.forceLength(value.getU(), 4);
				o = Utils.forceLength(value.getO(), 4);
			}
			else {
				u = "n/a ";
				o = "n/a ";
			}
				result += uoThrCurr + ": " + u + " " + o + "\t|";
		}
		return result;
	}


	private String getUoHeader(UoThresholdEnum uoThr) {
		String result = "";

		UoThresholdEnum[] array = UoThresholdEnum.values();
		List<UoThresholdEnum> list = new ArrayList<UoThresholdEnum>();
		if (uoThr == null) 
			list = Arrays.asList(array);
		else
			list.add(uoThr);
		
		
		for (UoThresholdEnum thr : list) {
			result += thr + ": " + "U    O" + "\t\t|";
		}
		return result;
	}


	private String getUoString(Map<UoThresholdEnum, ResultGoodnessUoBean> uoMap, UoThresholdEnum uoThr) {
		String result = "";

		UoThresholdEnum[] array = UoThresholdEnum.values();
		List<UoThresholdEnum> list = new ArrayList<UoThresholdEnum>();
		if (uoThr == null) 
			list = Arrays.asList(array);
		else
			list.add(uoThr);
		
		
		for (UoThresholdEnum thr : list) {
			result += thr + ": " + Utils.forceLength(uoMap.get(thr).getGoodnessU(), 4) + " " + Utils.forceLength(uoMap.get(thr).getGoodnessO(), 4) + "\t|";
		}
		return result;
	}


	private String getFormattedDate() {
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        return dt1.format(date);
    }


	public String getHomeTeam() {
		return homeTeam;
	}



	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}



	public String getAwayTeam() {
		return awayTeam;
	}



	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}



	public Double getOdds1() {
		return odds1;
	}



	public void setOdds1(Double oddsH) {
		this.odds1 = oddsH;
	}



	public Double getOddsX() {
		return oddsX;
	}



	public void setOddsX(Double oddsD) {
		this.oddsX = oddsD;
	}



	public Double getOdds2() {
		return odds2;
	}



	public void setOdds2(Double oddsA) {
		this.odds2 = oddsA;
	}




	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}

	public ResultGoodnessBean getHomeResultGoodness() {
		return homeResultGoodness;
	}

	public void setHomeResultGoodness(ResultGoodnessBean homeResultGoodness) {
		this.homeResultGoodness = homeResultGoodness;
	}

	public ResultGoodnessBean getAwayResultGoodness() {
		return awayResultGoodness;
	}

	public void setAwayResultGoodness(ResultGoodnessBean awayResultGoodness) {
		this.awayResultGoodness = awayResultGoodness;
	}

	public ResultGoodnessBean getTotalResultGoodness() {
		return totalResultGoodness;
	}

	public void setTotalResultGoodness(ResultGoodnessBean totalResultGoodness) {
		this.totalResultGoodness = totalResultGoodness;
	}

	public String getHomeTrend() {
		return homeTrend;
	}

	public void setHomeTrend(String homeTrend) {
		this.homeTrend = homeTrend;
	}

	public String getAwayTrend() {
		return awayTrend;
	}

	public void setAwayTrend(String awayTrend) {
		this.awayTrend = awayTrend;
	}

	public Double getHomeMotivation() {
		return homeMotivation;
	}

	public void setHomeMotivation(Double homeMotivation) {
		this.homeMotivation = homeMotivation;
	}

	public Double getAwayMotivation() {
		return awayMotivation;
	}

	public void setAwayMotivation(Double awayMotivation) {
		this.awayMotivation = awayMotivation;
	}

	public TimeTypeEnum getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeTypeEnum timeTipe) {
		this.timeType = timeTipe;
	}

	public int compareTo(EventOddsBean eo) {
		if(date.before(eo.getDate()))
			return -1;
		return 1;

	}


	public Map<UoThresholdEnum, String> getHomeTrendUo() {
		return homeTrendUo;
	}


	public void setHomeTrendUo(Map<UoThresholdEnum, String> homeTrendUo) {
		this.homeTrendUo = homeTrendUo;
	}


	public Map<UoThresholdEnum, String> getAwayTrendUo() {
		return awayTrendUo;
	}


	public void setAwayTrendUo(Map<UoThresholdEnum, String> awayTrendUo) {
		this.awayTrendUo = awayTrendUo;
	}


	public Map<HomeVariationEnum, String> getHomeTrendEh() {
		return homeTrendEh;
	}


	public void setHomeTrendEh(Map<HomeVariationEnum, String> homeTrendEh) {
		this.homeTrendEh = homeTrendEh;
	}


	public Map<HomeVariationEnum, String> getAwayTrendEh() {
		return awayTrendEh;
	}


	public void setAwayTrendEh(Map<HomeVariationEnum, String> awayTrendEh) {
		this.awayTrendEh = awayTrendEh;
	}


	public Map<UoThresholdEnum, UoLeaf> getUoOddsMap() {
		return uoOddsMap;
	}


	public void setUoOddsMap(Map<UoThresholdEnum, UoLeaf> uoOddsMap) {
		this.uoOddsMap = uoOddsMap;
	}


	public Map<HomeVariationEnum, _1x2Leaf> getEhOddsMap() {
		return ehOddsMap;
	}


	public void setEhOddsMap(Map<HomeVariationEnum, _1x2Leaf> ehOddsMap) {
		this.ehOddsMap = ehOddsMap;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

//	public List<SingleBetBean> getBetResults() {
//		return betResults;
//	}
//
//	public void setBetResults(List<SingleBetBean> betResults) {
//		this.betResults = betResults;
//	}

	
}
