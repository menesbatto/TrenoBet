package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;

public class ResultGoodnessWDLBean implements Serializable{
	
	private static final long serialVersionUID = -8220776671620251113L;

	private Double goodnessW;
	
	private Double goodnessD;

	private Double goodnessL;
	
	private HomeVariationEnum homeVariationType;
	
	
	public ResultGoodnessWDLBean() {
	}

	@Override
	public String toString() {
		return  "\t" + goodnessW + "\t" + goodnessD + "\t" + goodnessL;
	}

	public Double getGoodnessW() {
		return goodnessW;
	}

	public void setGoodnessW(Double goodnessW) {
		this.goodnessW = goodnessW;
	}

	public Double getGoodnessD() {
		return goodnessD;
	}

	public void setGoodnessD(Double goodnessD) {
		this.goodnessD = goodnessD;
	}

	public Double getGoodnessL() {
		return goodnessL;
	}

	public void setGoodnessL(Double goodnessL) {
		this.goodnessL = goodnessL;
	}

	public HomeVariationEnum getHomeVariationType() {
		return homeVariationType;
	}

	public void setHomeVariationType(HomeVariationEnum homeVariationType) {
		this.homeVariationType = homeVariationType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
