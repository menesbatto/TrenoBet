package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;

public class ResultGoodnessWDLBean implements Serializable{
	
	private static final long serialVersionUID = -8220776671620251113L;

	private Double goodnessW;
	private Integer totalEventsW;
	
	private Double goodnessD;
	private Integer totalEventsD;

	private Double goodnessL;
	private Integer totalEventsL;
	
	private HomeVariationEnum homeVariationType;
	
	private Integer totalEvents;
	
	
	public ResultGoodnessWDLBean() {
	}

	@Override
	public String toString() {
		return  "\t" + goodnessW + "\t" + goodnessD + "\t" + goodnessL;
	}

	public String toStringAway() {
		return  "\t" + goodnessL + "\t" + goodnessD + "\t" + goodnessW;
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

	public Integer getTotalEvents() {
		return totalEvents;
	}

	public void setTotalEvents(Integer totalEvents) {
		this.totalEvents = totalEvents;
	}

	public Integer getTotalEventsW() {
		return totalEventsW;
	}

	public void setTotalEventsW(Integer totalEventsW) {
		this.totalEventsW = totalEventsW;
	}

	public Integer getTotalEventsD() {
		return totalEventsD;
	}

	public void setTotalEventsD(Integer totalEventsD) {
		this.totalEventsD = totalEventsD;
	}

	public Integer getTotalEventsL() {
		return totalEventsL;
	}

	public void setTotalEventsL(Integer totalEventsL) {
		this.totalEventsL = totalEventsL;
	}
}
