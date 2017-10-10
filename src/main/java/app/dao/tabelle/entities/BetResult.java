package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BetResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
//	@ManyToOne
//	private EventOdds eventOdds;
	
	private String betType; 				//private BetType betType; WIN, UO_2, 
	
	private String matchResult; 			//private MatchResultEnum matchResult;
	
	private Double winOdds;

	public BetResult() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public EventOdds getEventOdds() {
//		return eventOdds;
//	}
//
//	public void setEventOdds(EventOdds eventOdds) {
//		this.eventOdds = eventOdds;
//	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public String getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

	public Double getWinOdds() {
		return winOdds;
	}

	public void setWinOdds(Double winOdds) {
		this.winOdds = winOdds;
	}

	
	

}
