package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;

import app.utils.ChampEnum;
import app.utils.Utils;

public class SingleBetBean implements Serializable {

	private static final long serialVersionUID = 3683605236607953033L;

	private BetType betType;
	
	private MatchResultEnum matchResultForecast;
	
	private Double winOdds;
	
	private TimeTypeEnum timeTypeEnum;
	
	private Boolean win;
	
	private int matchId;
	
	private MatchResult match;
	
	private Integer seasonDay;
	
	private ChampEnum champ;
	

	public SingleBetBean() {
	}

	
	
	public SingleBetBean(BetType betType, MatchResultEnum matchResultForecast, Double winOdds, TimeTypeEnum timeTypeEnum, ChampEnum champ) {
		this.betType = betType;
		this.matchResultForecast = matchResultForecast;
		this.winOdds = winOdds;
		this.timeTypeEnum = timeTypeEnum;
		this.champ = champ;
	}



	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public MatchResultEnum getMatchResultForecast() {
		return matchResultForecast;
	}

	public void setMatchResultForecast(MatchResultEnum matchResult) {
		this.matchResultForecast = matchResult;
	}

	public Double getWinOdds() {
		return winOdds;
	}

	public void setWinOdds(Double winOdds) {
		this.winOdds = winOdds;
	}

	public TimeTypeEnum getTimeTypeEnum() {
		return timeTypeEnum;
	}



	public void setTimeTypeEnum(TimeTypeEnum timeTypeEnum) {
		this.timeTypeEnum = timeTypeEnum;
	}



	public int getMatchId() {
		return matchId;
	}



	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}



	public MatchResult getMatch() {
		return match;
	}



	public void setMatch(MatchResult match) {
		this.match = match;
	}



	public Boolean getWin() {
		return win;
	}



	public void setWin(Boolean win) {
		this.win = win;
	}



	@Override
	public String toString() {
		String matchString = "";
		if (match != null) {
			matchString =  match.getHomeTeam() + " - " + match.getAwayTeam() + " " + match.getFTHG() + "-" + match.getFTAG() + " (" + match.getHTHG() + "-" + match.getHTAG() + ") " + "\n";
		};
		
		return matchResultForecast + "\t" + betType + "\t" + Utils.redimString(timeTypeEnum.name(),8) + "\t" + winOdds + "\t\t\t\t\t\t\tseasonDay=" + seasonDay +  "\twin=" + win + "\tmatchId=" + matchId + "\n"
				+ matchString  + "\n";
	}



	public Integer getSeasonDay() {
		return seasonDay;
	}



	public void setSeasonDay(Integer seasonDay) {
		this.seasonDay = seasonDay;
	}



	public ChampEnum getChamp() {
		return champ;
	}



	public void setChamp(ChampEnum champ) {
		this.champ = champ;
	}

	
	
	
	
}
