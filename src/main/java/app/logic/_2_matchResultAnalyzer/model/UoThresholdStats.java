package app.logic._2_matchResultAnalyzer.model;

import java.io.Serializable;

public class UoThresholdStats implements Serializable{
	
	private static final long serialVersionUID = -7213840052506253916L;

	private Double underHit;
	private Double underPerc;
	private Double overHit;
	private Double overPerc;

	
	
	public UoThresholdStats() {
		this.underHit = 0.0;
		this.underPerc = 0.0;
		this.overHit = 0.0;
		this.overPerc = 0.0;
	}

	public UoThresholdStats(Double underHit, Double underPerc, Double overHit, Double overPerc) {
		this.underHit = underHit;
		this.underPerc = underPerc;
		this.overHit = overHit;
		this.overPerc = overPerc;
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
		return "UoThresholdStats [underHit=" + underHit + ", underPerc=" + underPerc + ", overHit=" + overHit
				+ ", overPerc=" + overPerc + "]\n";
	}

	
	
}