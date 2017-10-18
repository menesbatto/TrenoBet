package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;

import app.utils.Utils;

public class SingleBetBean implements Serializable {

	private static final long serialVersionUID = 3683605236607953033L;

	private BetType betType;
	
	private MatchResultEnum matchResultEnum;
	
	private Double winOdds;
	
	private TimeTypeEnum timeTypeEnum;
	
	private Boolean win;
	
	private int matchId;
	
	private MatchResult match;
	
	private Integer seasonDay;
	

	public SingleBetBean() {
	}

	
	
	public SingleBetBean(BetType betType, MatchResultEnum matchResultEnum, Double winOdds, TimeTypeEnum timeTypeEnum) {
		this.betType = betType;
		this.matchResultEnum = matchResultEnum;
		this.winOdds = winOdds;
		this.timeTypeEnum = timeTypeEnum;
	}



	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public MatchResultEnum getMatchResultEnum() {
		return matchResultEnum;
	}

	public void setMatchResultEnum(MatchResultEnum matchResult) {
		this.matchResultEnum = matchResult;
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
		return "betType=" + betType + "\tmatchResultEnum=" + matchResultEnum + "\twinOdds=" + winOdds
				+ "\ttimeTypeEnum=" + Utils.redimString(timeTypeEnum.name(),8) + "\tseasonDay=" + seasonDay +  "\twin=" + win + "\tmatchId=" + matchId + "\n";
						//+ "match=" + match.getHomeTeam() + " - " + match.getAwayTeam() + " " + match.getFTHG() + "-" + match.getFTAG() + " (" + match.getHTHG() + "-" + match.getHTAG() + ") " + "\n";
	}



	public Integer getSeasonDay() {
		return seasonDay;
	}



	public void setSeasonDay(Integer seasonDay) {
		this.seasonDay = seasonDay;
	}

	
	
	
	
}
