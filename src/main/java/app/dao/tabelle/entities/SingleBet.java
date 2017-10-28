package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import app.dao.tipologiche.entities.TimeType;

@Entity
public class SingleBet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	//##########    Previsione    ##########
	private String betType; 						//private BetType betType; WIN, UO_2, 
	
	private String matchResultForecast; 			//private MatchResultEnum matchResult;
	
	private Double winOdds;
	
	@ManyToOne
	private TimeType timeType;
	
	
	//##########    Esito    ##########	

	private Boolean win;
	
	
	@ManyToOne
	private Matcho match;

	private Integer seasonDay;
	
	@ManyToOne
	private Champ champ;
	
	
	public SingleBet() {
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

	public String getMatchResultForecast() {
		return matchResultForecast;
	}

	public void setMatchResultForecast(String matchResult) {
		this.matchResultForecast = matchResult;
	}

	public Double getWinOdds() {
		return winOdds;
	}

	public void setWinOdds(Double winOdds) {
		this.winOdds = winOdds;
	}

	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}

	@Override
	public String toString() {
		return "id=" + id + ", betType=" + betType + ", matchResultForecast=" + matchResultForecast + ", winOdds=" + winOdds
				+ ", timeType=" + timeType + "\n";
	}

	public Matcho getMatch() {
		return match;
	}

	public void setMatch(Matcho match) {
		this.match = match;
	}

	public Boolean getWin() {
		return win;
	}

	public void setWin(Boolean win) {
		this.win = win;
	}

	public Integer getSeasonDay() {
		return seasonDay;
	}

	public void setSeasonDay(Integer seasonDay) {
		this.seasonDay = seasonDay;
	}

	public Champ getChamp() {
		return champ;
	}

	public void setChamp(Champ champ) {
		this.champ = champ;
	}


	
	

}
