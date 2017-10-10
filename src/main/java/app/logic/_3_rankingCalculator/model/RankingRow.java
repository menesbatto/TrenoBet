package app.logic._3_rankingCalculator.model;

import java.io.Serializable;
import java.util.ArrayList;

import app.utils.Utils;
//------------------   MOTIVATION   -----------------------	
public class RankingRow implements Serializable{

	private static final long serialVersionUID = 76079203258924215L;

	
	private Double motivationalIndex; 					// finale
	
	private Double motivationalIndexUpDis; 				// prende in considerazione la distanza
	private Double motivationalIndexDownDis; 			// prende in considerazione la distanza
	
	private Double motivationalIndexUpDisObb; 			// prende in considerazione la distanza e l'importanza dell'obbiettivo
	private Double motivationalIndexDownDisObb; 		// prende in considerazione la distanza e l'importanza dell'obbiettivo

	private Double motivationalIndexUpDisObbPoi; 		// prende in considerazione la distanza e l'importanza dell'obbiettivo e i punti disponibili
	private Double motivationalIndexDownDisObbPoi; 		// prende in considerazione la distanza e l'importanza dell'obbiettivo e i punti disponibili
	
	private String teamName;
	
	private Integer position;
	
	private ArrayList<Distances> distances;
	
//	private Double allExpectedPoints;
	
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
	
	@Override
	public String toString() {
//		return position  + "\t" + Utils.redimString(teamName, 17) + "\t" + allPoints  +  "\t" + Utils.redimString(allExpectedPoints) + "\t" + allPlayedMatches + 
		return 
				
					position  + "\t" + Utils.redimString(teamName, 17) + "\t" + allPoints  + "\t" + allPlayedMatches + "\t| " +  
					
					
					// ------------------   MOTIVATION   -----------------------				
					Utils.redimString(motivationalIndex) + "\t| " + 
					Utils.redimString(motivationalIndexUpDisObbPoi + motivationalIndexDownDisObbPoi) + "\t" + 
					Utils.redimString(motivationalIndexUpDisObbPoi) + "\t" + 
					Utils.redimString(motivationalIndexDownDisObbPoi) + "\t| " + 
					Utils.redimString(motivationalIndexUpDisObb + motivationalIndexDownDisObb) + "\t" + 
					Utils.redimString(motivationalIndexUpDisObb) + "\t" + 
					Utils.redimString(motivationalIndexDownDisObb) + "\t| " + 
					Utils.redimString(motivationalIndexUpDis + motivationalIndexDownDis) + "\t" + 
					Utils.redimString(motivationalIndexUpDis) + "\t" + 
					Utils.redimString(motivationalIndexDownDis) + "\t| " + 
					
					// ------------------   W - D - L   -----------------------
					
//					allWin + "\t" + allDraw + "\t" + allLose + "\t| " + 
//					allScoredGoals + "\t" + allTakenGoals + "\t" + getDifference() +  "\t| " + 
//
//					homeWin + "\t" + homeDraw + "\t" + homeLose +  "\t| " + 
////					homeScoredGoals + "\t" + homeTakenGoals + "\t" + getHomeDifference() +  "\t| " + 
//
//					awayWin + "\t" + awayDraw + "\t" + awayLose +  "\t| " + 
////					awayScoredGoals + "\t" + awayTakenGoals + "\t" + getAwayDifference() +  "\t| " + 
				
					// ------------------   DISTANCES   -----------------------


					printDistance(distances, 2) + 

					"\n";
					
	}
	
	
	
	private String printDistance(ArrayList<Distances> distances2, Integer number) {
		String returnString = "";
		if ( distances2.size() != 0 && number >= 1)
			returnString +=	distances.get(0) + "\t| ";
		if (distances.size() >= 2  && number >= 2)
			returnString +=	distances.get(1) + "\t| ";
		if (distances.size() >= 3  && number >= 3)
			returnString +=	distances.get(2) + "\t| ";
				
		return returnString;
	}



	private String getDifference(){
		String sg = "";
		Integer  diff = new Integer(allScoredGoals - allTakenGoals);
		if (diff > 0) sg += "+";
		return  sg +  diff;
	}
	private String getHomeDifference(){
		String sg = "";
		Integer  diff = new Integer(homeScoredGoals - homeTakenGoals);
		if (diff > 0) sg += "+";
		return  sg +  diff;
	}
	private String getAwayDifference(){
		String sg = "";
		Integer  diff = new Integer(awayScoredGoals - awayTakenGoals);
		if (diff > 0) sg += "+";
		return  sg +  diff;
	}
	
	public RankingRow() {
		allPlayedMatches = 0;
		allPoints = 0;
		allWin = 0;
		allDraw = 0;
		allLose = 0;
		allScoredGoals = 0;
		allTakenGoals = 0;
		
		homePlayedMatches = 0;
		homePoints = 0;
		homeWin = 0;
		homeDraw = 0;
		homeLose = 0;
		homeScoredGoals = 0;
		homeTakenGoals = 0;
		
		awayPlayedMatches = 0;
		awayPoints = 0;
		awayWin = 0;
		awayDraw = 0;
		awayLose = 0;
		awayScoredGoals = 0;
		awayTakenGoals = 0;
		
		distances = new ArrayList<Distances>();
		
	}
	
	
	
	
	public Double getMotivationalIndex() {
		return motivationalIndex;
	}
	public void setMotivationalIndex(Double motivationalIndex) {
		this.motivationalIndex = motivationalIndex;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
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
	public Integer getAllPlayedMatches() {
		return allPlayedMatches;
	}
	public void setAllPlayedMatches(Integer allPlayedMatches) {
		this.allPlayedMatches = allPlayedMatches;
	}
	public Integer getHomePlayedMatches() {
		return homePlayedMatches;
	}
	public void setHomePlayedMatches(Integer homePlayedMatches) {
		this.homePlayedMatches = homePlayedMatches;
	}
	public Integer getAwayPlayedMatches() {
		return awayPlayedMatches;
	}
	public void setAwayPlayedMatches(Integer awayPlayedMatches) {
		this.awayPlayedMatches = awayPlayedMatches;
	}
	public Double getAllAvgTakenGoals() {
		return allAvgTakenGoals;
	}
	public void setAllAvgTakenGoals(Double allAvgTakenGoals) {
		this.allAvgTakenGoals = allAvgTakenGoals;
	}
	public Double getHomeAvgTakenGoals() {
		return homeAvgTakenGoals;
	}
	public void setHomeAvgTakenGoals(Double homeAvgTakenGoals) {
		this.homeAvgTakenGoals = homeAvgTakenGoals;
	}
	public Double getAwayAvgTakenGoals() {
		return awayAvgTakenGoals;
	}
	public void setAwayAvgTakenGoals(Double awayAvgTakenGoals) {
		this.awayAvgTakenGoals = awayAvgTakenGoals;
	}









	public Double getMotivationalIndexUpDisObb() {
		return motivationalIndexUpDisObb;
	}

	public void setMotivationalIndexUpDisObb(Double motivationalIndexUpDisObb) {
		this.motivationalIndexUpDisObb = motivationalIndexUpDisObb;
	}

	public Double getMotivationalIndexDownDisObb() {
		return motivationalIndexDownDisObb;
	}

	public void setMotivationalIndexDownDisObb(Double motivationalIndexDownDisObb) {
		this.motivationalIndexDownDisObb = motivationalIndexDownDisObb;
	}
	
	
	

	public Double getMotivationalIndexUpDis() {
		return motivationalIndexUpDis;
	}

	public void setMotivationalIndexUpDis(Double motivationalIndexDis) {
		this.motivationalIndexUpDis = motivationalIndexDis;
	}

	public Double getMotivationalIndexDownDis() {
		return motivationalIndexDownDis;
	}

	public void setMotivationalIndexDownDis(Double motivationalIndexDownDis) {
		this.motivationalIndexDownDis = motivationalIndexDownDis;
	}





	public ArrayList<Distances> getDistances() {
		return distances;
	}

	public void setDistances(ArrayList<Distances> distances) {
		this.distances = distances;
	}

	public Double getMotivationalIndexUpDisObbPoi() {
		return motivationalIndexUpDisObbPoi;
	}

	public void setMotivationalIndexUpDisObbPoi(Double motivationalIndexUpDisObbPoi) {
		this.motivationalIndexUpDisObbPoi = motivationalIndexUpDisObbPoi;
	}

	public Double getMotivationalIndexDownDisObbPoi() {
		return motivationalIndexDownDisObbPoi;
	}

	public void setMotivationalIndexDownDisObbPoi(Double motivationalIndexDownDisObbPoi) {
		this.motivationalIndexDownDisObbPoi = motivationalIndexDownDisObbPoi;
	}

	public Integer getMatchesToRecover() {
		return matchesToRecover;
	}

	public void setMatchesToRecover(Integer matchesToRecover) {
		this.matchesToRecover = matchesToRecover;
	}
//
//	public Double getAllExpectedPoints() {
//		return allExpectedPoints;
//	}
//
//
//
//	public void setAllExpectedPoints(Double allExpectedPoints) {
//		this.allExpectedPoints = allExpectedPoints;
//	}
	
}
