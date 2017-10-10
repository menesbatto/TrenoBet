package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;

@Entity
public class UoOdds {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private BetHouse betHouse;

	@ManyToOne
	private TimeType timeType;
	
	@ManyToOne
	private UoThresholdType thresholdType;
	
	
	@ManyToOne
	private Matcho match;
	
	private Double u;
	private Double o;
	
	public UoOdds() {
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

	public UoThresholdType getThresholdType() {
		return thresholdType;
	}

	public void setThresholdType(UoThresholdType thresholdType) {
		this.thresholdType = thresholdType;
	}

	public Matcho getMatch() {
		return match;
	}

	public void setMatch(Matcho match) {
		this.match = match;
	}

	public Double getU() {
		return u;
	}

	public void setU(Double u) {
		this.u = u;
	}

	public Double getO() {
		return o;
	}

	public void setO(Double o) {
		this.o = o;
	}
	
	
}
