package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;

public class ResultGoodnessUoBean implements Serializable{
	
	private static final long serialVersionUID = -8220776671620254113L;

	private Double goodnessU;
	private Double goodnessO;
	
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
		return  "\t" + goodnessU + "\t" + goodnessO;
	}
	
	
}
