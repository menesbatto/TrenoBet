package app.logic._5_goodnessCalculator;

public class GoodnessInfo {
	
	private Double goodness;
		
	
	private Integer totalEvents;

	
	
	public GoodnessInfo() {
		this.goodness = 0.0;
		this.totalEvents =  0;
	}

	public Double getGoodness() {
		return goodness;
	}

	public void setGoodness(Double goodness) {
		this.goodness = goodness;
	}

	public Integer getTotalEvents() {
		return totalEvents;
	}

	public void setTotalEvents(Integer totalEvent) {
		this.totalEvents = totalEvent;
	}

	@Override
	public String toString() {
		return "GoodnessInfo [goodness=" + goodness + ", totalEvent=" + totalEvents + "]";
	}	
	
	
	
}
