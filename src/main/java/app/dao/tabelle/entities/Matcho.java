package app.dao.tabelle.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Matcho {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Team homeTeam;

	@ManyToOne
	private Team awayTeam;
	
	private Integer fullTimeHomeGoals;
	private Integer fullTimeAwayGoals;

	private Integer halfTimeHomeGoals;
	private Integer halfTimeAwayGoals;
	
	private Date matchDate;
	
	private String fullTimeResult;
	
	private String halfTimeResult;
	
	@ManyToOne
	private Champ champ;
	
	@OneToMany(cascade = CascadeType.ALL)//, mappedBy = "match")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "matcho")
	private List<_1X2Odds> _1X2;
	
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<EhOdds> eh;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<UoOdds> uo;
	
	private int seasonDay;
	
//	@OneToMany//(cascade = {CascadeType.ALL})HHH https://vladmihalcea.com/2017/03/29/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
//	private List<EventOdds> eventsOdds;

	public Matcho() {
	}

	
	public List<_1X2Odds> get_1X2() {
		return _1X2;
	}

	public void set_1X2(List<_1X2Odds> _1x2) {
		_1X2 = _1x2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Integer getFullTimeHomeGoals() {
		return fullTimeHomeGoals;
	}

	public void setFullTimeHomeGoals(Integer fullTimeHomeGoals) {
		this.fullTimeHomeGoals = fullTimeHomeGoals;
	}

	public Integer getFullTimeAwayGoals() {
		return fullTimeAwayGoals;
	}

	public void setFullTimeAwayGoals(Integer fullTimeAwayGoals) {
		this.fullTimeAwayGoals = fullTimeAwayGoals;
	}

	public Integer getHalfTimeHomeGoals() {
		return halfTimeHomeGoals;
	}

	public void setHalfTimeHomeGoals(Integer halfTimeHomeGoals) {
		this.halfTimeHomeGoals = halfTimeHomeGoals;
	}

	public Integer getHalfTimeAwayGoals() {
		return halfTimeAwayGoals;
	}

	public void setHalfTimeAwayGoals(Integer halfTimeAwayGoals) {
		this.halfTimeAwayGoals = halfTimeAwayGoals;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public Champ getChamp() {
		return champ;
	}

	public void setChamp(Champ champ) {
		this.champ = champ;
	}


	public List<EhOdds> getEh() {
		return eh;
	}


	public void setEh(List<EhOdds> eh) {
		this.eh = eh;
	}


	public List<UoOdds> getUo() {
		return uo;
	}


	public void setUo(List<UoOdds> uo) {
		this.uo = uo;
	}


	public String getFullTimeResult() {
		return fullTimeResult;
	}


	public void setFullTimeResult(String fullTimeResult) {
		this.fullTimeResult = fullTimeResult;
	}


	public String getHalfTimeResult() {
		return halfTimeResult;
	}


	public void setHalfTimeResult(String halfTimeResult) {
		this.halfTimeResult = halfTimeResult;
	}


	public int getSeasonDay() {
		return seasonDay;
	}


	public void setSeasonDay(int seasonDay) {
		this.seasonDay = seasonDay;
	}

//HHH
//	public List<EventOdds> getEventsOdds() {
//		return eventsOdds;
//	}
//
//
//	public void setEventsOdds(List<EventOdds> eventOdds) {
//		this.eventsOdds = eventOdds;
//	}


	
	
}
