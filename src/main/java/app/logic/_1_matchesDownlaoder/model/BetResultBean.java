package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;

public class BetResultBean implements Serializable {

	private static final long serialVersionUID = 3683605236607953033L;

	private BetType betType;
	
	private MatchResultEnum matchResult;
	
	private Double winOdds;
	
	private TimeTypeEnum timeTypeEnum;

	public BetResultBean() {
	}

	
	
	public BetResultBean(BetType betType, MatchResultEnum matchResult, Double winOdds, TimeTypeEnum timeTypeEnum) {
		this.betType = betType;
		this.matchResult = matchResult;
		this.winOdds = winOdds;
		this.timeTypeEnum = timeTypeEnum;
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

	@Override
	public String toString() {
		return "betType=" + betType + "\tmatchResult=" + matchResult + "\twinOdds=" + winOdds + "\ttimeTypeEnum=" + timeTypeEnum + "\n";
	}



	public TimeTypeEnum getTimeTypeEnum() {
		return timeTypeEnum;
	}



	public void setTimeTypeEnum(TimeTypeEnum timeTypeEnum) {
		this.timeTypeEnum = timeTypeEnum;
	}


	
	
	
}
