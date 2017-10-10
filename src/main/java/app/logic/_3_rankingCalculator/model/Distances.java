package app.logic._3_rankingCalculator.model;

import java.io.Serializable;

public class Distances implements Serializable{

	private static final long serialVersionUID = 6359576182015293683L;

	
	private Integer pointOfDisadvantageFromUpTarget;
	private Integer teamsFromUpTarget;
	private Integer distanceFromUpTarget;
	
	
	
	private Integer pointOfAdvantageFromDownTarget;
	private Integer teamsFromDownTarget;
	private Integer distanceFromDownTarget;
	
	
	@Override
	public String toString() {
		return pointOfDisadvantageFromUpTarget + "\t" + distanceFromUpTarget + "\t" + 
				pointOfAdvantageFromDownTarget + "\t" + distanceFromDownTarget;
	}
	
	
	public Integer getPointOfDisadvantageFromUpTarget() {
		return pointOfDisadvantageFromUpTarget;
	}
	public void setPointOfDisadvantageFromUpTarget(Integer pointOfDisadvantageFromUpTarget) {
		this.pointOfDisadvantageFromUpTarget = pointOfDisadvantageFromUpTarget;
	}
	public Integer getTeamsFromUpTarget() {
		return teamsFromUpTarget;
	}
	public void setTeamsFromUpTarget(Integer teamsFromUpTarget) {
		this.teamsFromUpTarget = teamsFromUpTarget;
	}
	public Integer getDistanceFromUpTarget() {
		return distanceFromUpTarget;
	}
	public void setDistanceFromUpTarget(Integer distanceFromUpTarget) {
		this.distanceFromUpTarget = distanceFromUpTarget;
	}
	public Integer getPointOfAdvantageFromDownTarget() {
		return pointOfAdvantageFromDownTarget;
	}
	public void setPointOfAdvantageFromDownTarget(Integer pointOfAdvantageFromDownTarget) {
		this.pointOfAdvantageFromDownTarget = pointOfAdvantageFromDownTarget;
	}
	public Integer getTeamsFromDownTarget() {
		return teamsFromDownTarget;
	}
	public void setTeamsFromDownTarget(Integer teamsFromDownTarget) {
		this.teamsFromDownTarget = teamsFromDownTarget;
	}
	public Integer getDistanceFromDownTarget() {
		return distanceFromDownTarget;
	}
	public void setDistanceFromDownTarget(Integer distanceFromDownTarget) {
		this.distanceFromDownTarget = distanceFromDownTarget;
	}

	
	
	
}
