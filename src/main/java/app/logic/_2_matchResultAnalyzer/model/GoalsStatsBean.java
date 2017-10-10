package app.logic._2_matchResultAnalyzer.model;

import java.io.Serializable;
import java.util.HashMap;

import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;


public class GoalsStatsBean implements Serializable {
	
	private static final long serialVersionUID = 7120211051979245624L;

	private Integer strikedGoalsTotal;
	private Integer takenGoalsTotal;
	private Integer totalGoals;
		
	private Integer totalMatches;

	private String teamName;
	
	private TimeTypeEnum timeTypeBean;
	private String playingField;
	private HashMap<UoThresholdEnum, UoThresholdStats> thresholdMap;
	
	
	
	
	
	public GoalsStatsBean() {
		strikedGoalsTotal = 0;
		takenGoalsTotal = 0;
		totalGoals = 0;
		totalMatches = 0;
			
		thresholdMap = new HashMap<UoThresholdEnum, UoThresholdStats>();
		for (UoThresholdEnum val : UoThresholdEnum.values())
			thresholdMap.put(val, new UoThresholdStats());
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

	public Integer getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(Integer totalMatches) {
		this.totalMatches = totalMatches;
	}

	public Integer getTotalGoals() {
		return totalGoals;
	}

	public void setTotalGoals(Integer totalGoalsTotal) {
		this.totalGoals = totalGoalsTotal;
	}

//	@Override
//	public String toString() {
//		return "\tGF " + strikedGoalsTotal + "\tGS " + takenGoalsTotal+ "\tG " + totalGoals + "\tmatch " + totalMatches + "\n" +
//				"\nUNDER" + "\t0,5\t" + "1,5\t" + "2,5\t" + "3,5\t" + "4,5\n" +
//				
//				"#\t" + under0_5Hit + "\t"	+ under1_5Hit + "\t" + under2_5Hit + "\t" + under3_5Hit + "\t"	+ under4_5Hit + ", " + "\n" +
//				"%\t" + Utils.redimString(under0_5Perc) + "\t" + Utils.redimString(under1_5Perc) + "\t"	+ Utils.redimString(under2_5Perc) + "\t" + Utils.redimString(under3_5Perc) + "\t" + Utils.redimString(under4_5Perc) + "\n" + 
//				
//				"\nOVER" + "\t0,5\t" + "1,5\t" + "2,5\t" + "3,5\t" + "4,5\n" +
//
//				"#\t" + over0_5Hit + "\t"	+ over1_5Hit + "\t" + over2_5Hit + "\t" + over3_5Hit + "\t"	+ over4_5Hit + ", " + "\n" +
//				"%\t" + Utils.redimString(over0_5Perc) + "\t" + Utils.redimString(over1_5Perc) + "\t"	+ Utils.redimString(over2_5Perc) + "\t" + Utils.redimString(over3_5Perc) + "\t" + Utils.redimString(over4_5Perc) + "\n" +
//				
//				"\n\n";
//	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public HashMap<UoThresholdEnum, UoThresholdStats> getThresholdMap() {
		return thresholdMap;
	}

	public void setThresholdMap(HashMap<UoThresholdEnum, UoThresholdStats> thresholdMap) {
		this.thresholdMap = thresholdMap;
	}


	public TimeTypeEnum getTimeTypeBean() {
		return timeTypeBean;
	}


	public void setTimeTypeBean(TimeTypeEnum timeTypeBean) {
		this.timeTypeBean = timeTypeBean;
	}


	public String getPlayingField() {
		return playingField;
	}


	public void setPlayingField(String playingField) {
		this.playingField = playingField;
	}
	
	
	
	
}
