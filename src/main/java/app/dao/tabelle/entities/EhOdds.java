package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.TimeType;

@Entity
public class EhOdds {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private BetHouse betHouse;

	@ManyToOne
	private TimeType timeType;
	
	@ManyToOne
	private HomeVariationType homeVariationType;
	
	
	@ManyToOne
	private Matcho match;
	
	private Double _1;
	private Double _X;
	private Double _2;
	
	public EhOdds() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BetHouse getBetHouse() {
		return betHouse;
	}

	public void setBetHouse(BetHouse betHouse) {
		this.betHouse = betHouse;
	}

	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}

	public HomeVariationType getHomeVariationType() {
		return homeVariationType;
	}

	public void setHomeVariationType(HomeVariationType homeVariationType) {
		this.homeVariationType = homeVariationType;
	}

	public Matcho getMatch() {
		return match;
	}

	public void setMatch(Matcho match) {
		this.match = match;
	}

	public Double get_1() {
		return _1;
	}

	public void set_1(Double _1) {
		this._1 = _1;
	}

	public Double get_X() {
		return _X;
	}

	public void set_X(Double _X) {
		this._X = _X;
	}

	public Double get_2() {
		return _2;
	}

	public void set_2(Double _2) {
		this._2 = _2;
	}

	
	
}
