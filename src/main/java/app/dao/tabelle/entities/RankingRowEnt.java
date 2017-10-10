package app.dao.tabelle.entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import app.logic._3_rankingCalculator.model.Distances;

@Entity
public class RankingRowEnt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	private Team team;
	
	@ManyToOne
	private Champ champ;
	
	private Integer position;
	
	private Double motivationalIndex; 					// finale
	
//	private Double motivationalIndexUpDis; 				// prende in considerazione la distanza
//	
//	private Double motivationalIndexDownDis; 			// prende in considerazione la distanza
//	
//	private Double motivationalIndexUpDisObb; 			// prende in considerazione la distanza e l'importanza dell'obbiettivo
//	private Double motivationalIndexDownDisObb; 		// prende in considerazione la distanza e l'importanza dell'obbiettivo
//
//	private Double motivationalIndexUpDisObbPoi; 		// prende in considerazione la distanza e l'importanza dell'obbiettivo e i punti disponibili
//	private Double motivationalIndexDownDisObbPoi; 		// prende in considerazione la distanza e l'importanza dell'obbiettivo e i punti disponibili
//	

	private Integer matchesToRecover;

	private Integer allPlayedMatches;
	private Integer allPoints;
	private Integer allWin;
	private Integer allDraw;
	private Integer allLose;
	private Integer allScoredGoals;
	private Integer allTakenGoals;
	private Double allAvgScoredGoals;
	private Double allAvgTakenGoals;
	
	private Integer homePlayedMatches;
	private Integer homePoints;
	private Integer homeWin;
	private Integer homeDraw;
	private Integer homeLose;
	private Integer homeScoredGoals;
	private Integer homeTakenGoals;
	private Double homeAvgScoredGoals;
	private Double homeAvgTakenGoals;
	
	private Integer awayPlayedMatches;
	private Integer awayPoints;
	private Integer awayWin;
	private Integer awayDraw;
	private Integer awayLose;
	private Integer awayScoredGoals;
	private Integer awayTakenGoals;
	private Double awayAvgScoredGoals;
	private Double awayAvgTakenGoals;
	
	
	
	public RankingRowEnt() {
	}
	public RankingRowEnt(Team team, Champ champ) {
		this.team = team;
		this.champ = champ;
	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Champ getChamp() {
		return champ;
	}
	public void setChamp(Champ champ) {
		this.champ = champ;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Double getMotivationalIndex() {
		return motivationalIndex;
	}
	public void setMotivationalIndex(Double motivationalIndex) {
		this.motivationalIndex = motivationalIndex;
	}
	public Integer getMatchesToRecover() {
		return matchesToRecover;
	}
	public void setMatchesToRecover(Integer matchesToRecover) {
		this.matchesToRecover = matchesToRecover;
	}
	public Integer getAllPlayedMatches() {
		return allPlayedMatches;
	}
	public void setAllPlayedMatches(Integer allPlayedMatches) {
		this.allPlayedMatches = allPlayedMatches;
	}
	public Integer getAllPoints() {
		return allPoints;
	}
	public void setAllPoints(Integer allPoints) {
		this.allPoints = allPoints;
	}
	public Integer getAllWin() {
		return allWin;
	}
	public void setAllWin(Integer allWin) {
		this.allWin = allWin;
	}
	public Integer getAllDraw() {
		return allDraw;
	}
	public void setAllDraw(Integer allDraw) {
		this.allDraw = allDraw;
	}
	public Integer getAllLose() {
		return allLose;
	}
	public void setAllLose(Integer allLose) {
		this.allLose = allLose;
	}
	public Integer getAllScoredGoals() {
		return allScoredGoals;
	}
	public void setAllScoredGoals(Integer allScoredGoals) {
		this.allScoredGoals = allScoredGoals;
	}
	public Integer getAllTakenGoals() {
		return allTakenGoals;
	}
	public void setAllTakenGoals(Integer allTakenGoals) {
		this.allTakenGoals = allTakenGoals;
	}
	public Double getAllAvgScoredGoals() {
		return allAvgScoredGoals;
	}
	public void setAllAvgScoredGoals(Double allAvgScoredGoals) {
		this.allAvgScoredGoals = allAvgScoredGoals;
	}
	public Double getAllAvgTakenGoals() {
		return allAvgTakenGoals;
	}
	public void setAllAvgTakenGoals(Double allAvgTakenGoals) {
		this.allAvgTakenGoals = allAvgTakenGoals;
	}
	public Integer getHomePlayedMatches() {
		return homePlayedMatches;
	}
	public void setHomePlayedMatches(Integer homePlayedMatches) {
		this.homePlayedMatches = homePlayedMatches;
	}
	public Integer getHomePoints() {
		return homePoints;
	}
	public void setHomePoints(Integer homePoints) {
		this.homePoints = homePoints;
	}
	public Integer getHomeWin() {
		return homeWin;
	}
	public void setHomeWin(Integer homeWin) {
		this.homeWin = homeWin;
	}
	public Integer getHomeDraw() {
		return homeDraw;
	}
	public void setHomeDraw(Integer homeDraw) {
		this.homeDraw = homeDraw;
	}
	public Integer getHomeLose() {
		return homeLose;
	}
	public void setHomeLose(Integer homeLose) {
		this.homeLose = homeLose;
	}
	public Integer getHomeScoredGoals() {
		return homeScoredGoals;
	}
	public void setHomeScoredGoals(Integer homeScoredGoals) {
		this.homeScoredGoals = homeScoredGoals;
	}
	public Integer getHomeTakenGoals() {
		return homeTakenGoals;
	}
	public void setHomeTakenGoals(Integer homeTakenGoals) {
		this.homeTakenGoals = homeTakenGoals;
	}
	public Double getHomeAvgScoredGoals() {
		return homeAvgScoredGoals;
	}
	public void setHomeAvgScoredGoals(Double homeAvgScoredGoals) {
		this.homeAvgScoredGoals = homeAvgScoredGoals;
	}
	public Double getHomeAvgTakenGoals() {
		return homeAvgTakenGoals;
	}
	public void setHomeAvgTakenGoals(Double homeAvgTakenGoals) {
		this.homeAvgTakenGoals = homeAvgTakenGoals;
	}
	public Integer getAwayPlayedMatches() {
		return awayPlayedMatches;
	}
	public void setAwayPlayedMatches(Integer awayPlayedMatches) {
		this.awayPlayedMatches = awayPlayedMatches;
	}
	public Integer getAwayPoints() {
		return awayPoints;
	}
	public void setAwayPoints(Integer awayPoints) {
		this.awayPoints = awayPoints;
	}
	public Integer getAwayWin() {
		return awayWin;
	}
	public void setAwayWin(Integer awayWin) {
		this.awayWin = awayWin;
	}
	public Integer getAwayDraw() {
		return awayDraw;
	}
	public void setAwayDraw(Integer awayDraw) {
		this.awayDraw = awayDraw;
	}
	public Integer getAwayLose() {
		return awayLose;
	}
	public void setAwayLose(Integer awayLose) {
		this.awayLose = awayLose;
	}
	public Integer getAwayScoredGoals() {
		return awayScoredGoals;
	}
	public void setAwayScoredGoals(Integer awayScoredGoals) {
		this.awayScoredGoals = awayScoredGoals;
	}
	public Integer getAwayTakenGoals() {
		return awayTakenGoals;
	}
	public void setAwayTakenGoals(Integer awayTakenGoals) {
		this.awayTakenGoals = awayTakenGoals;
	}
	public Double getAwayAvgScoredGoals() {
		return awayAvgScoredGoals;
	}
	public void setAwayAvgScoredGoals(Double awayAvgScoredGoals) {
		this.awayAvgScoredGoals = awayAvgScoredGoals;
	}
	public Double getAwayAvgTakenGoals() {
		return awayAvgTakenGoals;
	}
	public void setAwayAvgTakenGoals(Double awayAvgTakenGoals) {
		this.awayAvgTakenGoals = awayAvgTakenGoals;
	}

}
