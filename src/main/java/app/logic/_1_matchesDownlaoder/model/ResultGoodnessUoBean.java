package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;

import app.utils.Utils;

public class ResultGoodnessUoBean implements Serializable{
	
	private static final long serialVersionUID = -8220776671620254113L;

	private Double goodnessU;
	private Double goodnessO;
	private Integer totalEvents;
	
	public Double getGoodnessU() {
		return goodnessU;
	}

	public void setGoodnessU(Double goodnessU) {
		this.goodnessU = goodnessU;
	}

	public Double getGoodnessO() {
		return goodnessO;
	}

	public void setGoodnessO(Double goodnessO) {
		this.goodnessO = goodnessO;
	}

	@Override
	public String toString() {
		return  "\t" + Utils.redimString(goodnessU) + "\t" + Utils.redimString(goodnessO);// + "\t" + totalEvents;
	}

	public Integer getTotalEvents() {
		return totalEvents;
	}

	public void setTotalEvents(Integer totalEvents) {
		this.totalEvents = totalEvents;
	}
	
	
}
