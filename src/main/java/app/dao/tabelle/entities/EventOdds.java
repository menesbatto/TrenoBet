package app.dao.tabelle.entities;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;

@Entity
public class EventOdds {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Matcho match;
	
	@ManyToOne
	private TimeType timeType;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ResultGoodness homeResultGoodness;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ResultGoodness awayResultGoodness;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ResultGoodness totalResultGoodness;

	private Double homeMotivation;
	private Double awayMotivation;
	
	private String homeTrend;
	private String awayTrend;
	
	
	@ElementCollection
	private Map<UoThresholdType, String> homeTrendUo;
	
	@ElementCollection
	private Map<UoThresholdType, String> awayTrendUo;
	
	@ElementCollection
	private Map<HomeVariationType, String> homeTrendEh;

	@ElementCollection
	private Map<HomeVariationType, String> awayTrendEh;
	

	//Campi per simulare la scommessa
	@OneToMany(cascade = CascadeType.ALL)
	private List<BetResult> betResults;		
	
	
	
	
	public EventOdds() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Map<HomeVariationType, String> getHomeTrendEh() {
		return homeTrendEh;
	}

	public void setHomeTrendEh(Map<HomeVariationType, String> homeTrendEh) {
		this.homeTrendEh = homeTrendEh;
	}

	public Map<HomeVariationType, String> getAwayTrendEh() {
		return awayTrendEh;
	}

	public void setAwayTrendEh(Map<HomeVariationType, String> awayTrendEh) {
		this.awayTrendEh = awayTrendEh;
	}


	
	public Matcho getMatch() {
		return match;
	}

	public void setMatch(Matcho match) {
		this.match = match;
	}

	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}

	public ResultGoodness getHomeResultGoodness() {
		return homeResultGoodness;
	}

	public void setHomeResultGoodness(ResultGoodness homeResultGoodness) {
		this.homeResultGoodness = homeResultGoodness;
	}

	public ResultGoodness getAwayResultGoodness() {
		return awayResultGoodness;
	}

	public void setAwayResultGoodness(ResultGoodness awayResultGoodness) {
		this.awayResultGoodness = awayResultGoodness;
	}

	public ResultGoodness getTotalResultGoodness() {
		return totalResultGoodness;
	}

	public void setTotalResultGoodness(ResultGoodness totalResultGoodness) {
		this.totalResultGoodness = totalResultGoodness;
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

	public Map<UoThresholdType, String> getHomeTrendUo() {
		return homeTrendUo;
	}

	public void setHomeTrendUo(Map<UoThresholdType, String> homeTrendUo) {
		this.homeTrendUo = homeTrendUo;
	}

	public Map<UoThresholdType, String> getAwayTrendUo() {
		return awayTrendUo;
	}

	public void setAwayTrendUo(Map<UoThresholdType, String> awayTrendUo) {
		this.awayTrendUo = awayTrendUo;
	}

	public List<BetResult> getBetResults() {
		return betResults;
	}

	public void setBetResults(List<BetResult> betResults) {
		this.betResults = betResults;
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

	@Override
	public String toString() {
		return "id=" + id + ", match=" + match + ", timeType=" + timeType + "\n homeResultGoodness="
				+ homeResultGoodness + "\n awayResultGoodness=" + awayResultGoodness + "\n totalResultGoodness="
				+ totalResultGoodness + ", homeMotivation=" + homeMotivation + ", awayMotivation=" + awayMotivation
				+ ", homeTrend=" + homeTrend + ", awayTrend=" + awayTrend + ", homeTrendUo=" + homeTrendUo
				+ ", awayTrendUo=" + awayTrendUo + ", homeTrendEh=" + homeTrendEh + ", awayTrendEh=" + awayTrendEh
				+ ", betResults=" + betResults + "\n\n";
	}
	
	

}
