package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.ElementCollection;

import java.util.SortedSet;
import java.util.TreeSet;

import app.dao.tabelle.entities.UoOdds;
import app.dao.tipologiche.entities.HomeVariationType;
import app.utils.Utils;

public class EventOddsBean implements Serializable, Comparable<EventOddsBean>{
	private static final long serialVersionUID = 7778211632531238253L;
	
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
	private Double oddsH;
	private Double oddsD;
	private Double oddsA;
	
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
	private BetType betType;
	
	private MatchResultEnum matchResult;
	
	private Double winOdds;

	@Override
	public String toString() {
		if (homeResultGoodness == null){
			return homeTeam + " - " + awayTeam + 
				
				"\n\tWIN\t" + oddsH + "\t" + oddsD + "\t" + oddsA + 
				
				"\n\tUO\t" + "via" + "\t" + "via" + "\n";
		}
		else {
			
			String uoOdds = getUoOdds(uoOddsMap);
			String uoHeader = getUoHeader();
			String ehOdds = getEhOdds(ehOddsMap);
			String ehHeader = getEhHeader();
			String uoH = getUoString(homeResultGoodness.getUoGoodness());
			String uoA = getUoString(awayResultGoodness.getUoGoodness());
			String ehH = getEhString(homeResultGoodness.getEhGoodness(), "H");
			String ehA = getEhString(awayResultGoodness.getEhGoodness(), "A");
			
			String s =  
	
				Utils.redimString(homeTeam,16) + " " + Utils.redimString(awayTeam,16) + "\t\t" + betType + "\t" + matchResult + "\t" + getFormattedDate() + "\t" + winOdds + "\n" + 
	
				Utils.redimString(homeTrend,16) + " " + "Utils.redimString(homeTrendUo,16)" + " - " + Utils.redimString(awayTrend,16) + " - " + "Utils.redimString(awayTrendUo,16)" +
				
				
				
				
				"\n\t        1x2          \n" + 
				"\n\tQUOTE\t | " + "1 - " + "\t" + oddsH + "\t\t\t\t | " + "X - " + "\t" + oddsD + "\t | " + "2 - " + "\t" + oddsA + "\t\t\t\t | " + 
				
//				"\n\tWIN\t | " + oddsH + "\t\t\t\t\t | " + oddsD + "\t | " + oddsD +"\t\t\t\t\t | " + oddsU + "\t\t" + oddsO +

				"\n\t\t | " + "clea" + "\t" + "mot" + "\t" + "withMot" + "\t" + "withTre" + "\t" + "withAll" + "\t | " + "clea" + "\t\t | "  + "clea" + "\t" + "mot" + "\t" + "withMot" + "\t" + "withTre" + "\t" + "withAll" + "\t | " + 
				
	//			"\n\tGOOD\t | " + homeResultGoodness.getGoodnessW() + "\t" + Utils.redimString(homeResultGoodness.getGoodnessWwithTrends()) + "\t | " + homeResultGoodness.getGoodnessD() + "\t | " + homeResultGoodness.getGoodnessL() + "\t" + Utils.redimString(homeResultGoodness.getGoodnessLwithTrends()) + "\t\t | " + homeResultGoodness.getGoodnessU() + "\t" + homeResultGoodness.getGoodnessO() +  
	//			
	//			"\n\tGOOD\t | " + awayResultGoodness.getGoodnessL() + "\t" + Utils.redimString(awayResultGoodness.getGoodnessLwithTrends()) + "\t | " + awayResultGoodness.getGoodnessD() + "\t | " + awayResultGoodness.getGoodnessW() + "\t" + Utils.redimString(awayResultGoodness.getGoodnessWwithTrends()) + "\t\t | " +awayResultGoodness.getGoodnessU() + "\t" + awayResultGoodness.getGoodnessO() + "\n"; 
				
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
				"\t" + "null" +  //Utils.redimString(homeResultGoodness.getWinFinal().getGoodnessL()) + 
				  
				
				"\n\tA GOOD\t | " + Utils.redimString(awayResultGoodness.getWinClean().getGoodnessL()) + 
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
				"\n\n" +
				"\t################### \n\n" +

	
				
				"\t        UO          \n\n" + 
				"\tQUOTE\t | " + uoOdds + "\n" + 
				"\t\t | " + uoHeader + "\n" + 
				"\n\tH GOOD\t |" +
				" " +  uoH  +  

				"\n\tA GOOD\t |" +
				" " +  uoA + "\n\n" +
				"\t################### \n\n" +
				
				
				
				
				
				"\t        EH          \n\n" + 
				"\tQUOTE\t | " + ehOdds + "\n" + 
				"\t\t | " + ehHeader + "\n" + 
				"\n\tH GOOD\t |" +
				" " +  ehH  +  
	
				"\n\tA GOOD\t |" +
				" " +  ehA + "\n\n" +
				"\t################### \n\n";
				
				
				
				
//				"\n\tA GOOD\t | " + awayResultGoodness.getGoodnessL() + "\t" + Utils.redimString(awayMotivation) + "\t | " + awayResultGoodness.getGoodnessD() + "\t | " + awayResultGoodness.getGoodnessW() + "\t" + Utils.redimString(awayMotivation) + "\t\t | " + awayResultGoodness.getGoodnessU() + "\t" + awayResultGoodness.getGoodnessO() + "\n\n"; 
	
			return s;
		}
	}


	private String getEhOdds(Map<HomeVariationEnum, _1x2Leaf> ehMap) {
		String result = "";
		
		for (HomeVariationEnum key : HomeVariationEnum.getSubSet()) {
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


	private String getEhHeader() {
		String result = "";
		for (HomeVariationEnum thr : HomeVariationEnum.getSubSet()) {
			result += thr + ": " + "1    x    2" + "\t|";
		}
		return result;
	}


	private String getEhString(Map<HomeVariationEnum, ResultGoodnessWDLBean> ehMap, String playingField) {
		String result = "";
		for (HomeVariationEnum homeVar : HomeVariationEnum.getSubSet()) {
			if (ehMap.get(homeVar) == null) {
				result += homeVar + ": " + "n/a " + " " +  "n/a " + " " +  "n/a " + "\t|";
			}
			else {
				if (playingField == "H") {
					result += homeVar + ": " + Utils.forceLength(ehMap.get(homeVar).getGoodnessW(), 4) + " " + Utils.forceLength(ehMap.get(homeVar).getGoodnessD(), 4)+ " " + Utils.forceLength(ehMap.get(homeVar).getGoodnessL(), 4) + "\t|";
				}
				else {//if (playingField == "A") {
					result += homeVar + ": " + Utils.forceLength(ehMap.get(homeVar).getGoodnessL(), 4) + " " + Utils.forceLength(ehMap.get(homeVar).getGoodnessD(), 4)+ " " + Utils.forceLength(ehMap.get(homeVar).getGoodnessW(), 4) + "\t|";
				}
			}
		}
		return result;
	}


	private String getUoOdds(Map<UoThresholdEnum, UoLeaf> uoMap) {
		String result = "";
		
		for (UoThresholdEnum key : UoThresholdEnum.values()) {
			UoLeaf value = uoMap.get(key);
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
				result += key + ": " + u + " " + o + "\t|";
		}
		return result;
	}


	private String getUoHeader() {
		String result = "";
		for (UoThresholdEnum thr : UoThresholdEnum.values()) {
			result += thr + ": " + "U    O" + "\t\t|";
		}
		return result;
	}


	private String getUoString(Map<UoThresholdEnum, ResultGoodnessUoBean> uoMap) {
		String result = "";
		for (UoThresholdEnum thr : UoThresholdEnum.values()) {
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



	public Double getOddsH() {
		return oddsH;
	}



	public void setOddsH(Double oddsH) {
		this.oddsH = oddsH;
	}



	public Double getOddsD() {
		return oddsD;
	}



	public void setOddsD(Double oddsD) {
		this.oddsD = oddsD;
	}



	public Double getOddsA() {
		return oddsA;
	}



	public void setOddsA(Double oddsA) {
		this.oddsA = oddsA;
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

	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public MatchResultEnum getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(MatchResultEnum matchResult) {
		this.matchResult = matchResult;
	}
	
	public Double getWinOdds() {
		return winOdds;
	}

	public void setWinOdds(Double winOdds) {
		this.winOdds = winOdds;
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

	
}
