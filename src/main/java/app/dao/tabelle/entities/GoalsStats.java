package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;

@Entity
public class GoalsStats {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Team team;

	@ManyToOne
	private TimeType timeType;
	
	@ManyToOne
	private UoThresholdType threshold;

	private String playingField;
	
	private Integer strikedGoalsTotal;
	
	private Integer takenGoalsTotal;
	
	private Integer totalGoals;
	
	private Double underHit;

	private Double underPerc;

	private Double overHit;
	
	private Double overPerc;
	
	
	private Integer totalMatches;

	private String teamName;
	
	
	
	
	public GoalsStats() {
	}
	
	public GoalsStats(UoThresholdType threshold, Team team) {
		this.threshold = threshold;
		this.team = team;
		this.teamName = team.getName();
		this.strikedGoalsTotal = 0;
		this.takenGoalsTotal = 0;
		this.totalGoals = 0;
		this.underHit = 0.0;
		this.underPerc = 0.0;
		this.overHit = 0.0;
		this.overPerc = 0.0;
		this.totalMatches = 0;
		
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}

	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}

	public String getPlayingField() {
		return playingField;
	}

	public void setPlayingField(String playingField) {
		this.playingField = playingField;
	}

	public UoThresholdType getThreshold() {
		return threshold;
	}

	public void setThreshold(UoThresholdType threshold) {
		this.threshold = threshold;
	}

	public Integer getStrikedGoalsTotal() {
		return strikedGoalsTotal;
	}

	public void setStrikedGoalsTotal(Integer strikedGoalsTotal) {
		this.strikedGoalsTotal = strikedGoalsTotal;
	}

	public Integer getTakenGoalsTotal() {
		return takenGoalsTotal;
	}

	public void setTakenGoalsTotal(Integer takenGoalsTotal) {
		this.takenGoalsTotal = takenGoalsTotal;
	}

	public Integer getTotalGoals() {
		return totalGoals;
	}

	public void setTotalGoals(Integer totalGoals) {
		this.totalGoals = totalGoals;
	}

	public Integer getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(Integer totalMatches) {
		this.totalMatches = totalMatches;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Double getUnderHit() {
		return underHit;
	}

	public void setUnderHit(Double underHit) {
		this.underHit = underHit;
	}

	public Double getUnderPerc() {
		return underPerc;
	}

	public void setUnderPerc(Double underPerc) {
		this.underPerc = underPerc;
	}

	public Double getOverHit() {
		return overHit;
	}

	public void setOverHit(Double overHit) {
		this.overHit = overHit;
	}

	public Double getOverPerc() {
		return overPerc;
	}

	public void setOverPerc(Double overPerc) {
		this.overPerc = overPerc;
	}
	
	@Override
	public String toString() {
		return "GoalsStats [id=" + id + ", team=" + team + ", threshold=" + threshold + ", timeType=" + timeType
				+ ", playingField=" + playingField + ", threshold=" + threshold + ", strikedGoalsTotal="
				+ strikedGoalsTotal + ", takenGoalsTotal=" + takenGoalsTotal + ", totalGoals=" + totalGoals
				+ ", underHit=" + underHit + ", underPerc=" + underPerc + ", overHit=" + overHit + ", overPerc="
				+ overPerc + ", totalMatches=" + totalMatches + ", teamName=" + teamName + "]";
	}

	
	
}
