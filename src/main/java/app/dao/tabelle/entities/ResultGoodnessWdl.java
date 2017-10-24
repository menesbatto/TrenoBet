package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import app.dao.tipologiche.entities.HomeVariationType;
import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;

@Entity
public class ResultGoodnessWdl {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Double goodnessW;
	private Integer totalEventsW;
	
	private Double goodnessD;
	private Integer totalEventsD;

	private Double goodnessL;
	private Integer totalEventsL;
	
	private Integer totalEvents;

	
	@ManyToOne
	private HomeVariationType homeVariationType;
	
	public ResultGoodnessWdl() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public HomeVariationType getHomeVariationType() {
		return homeVariationType;
	}

	public void setHomeVariationType(HomeVariationType homeVariationType) {
		this.homeVariationType = homeVariationType;
	}

	@Override
	public String toString() {
		return "id=" + id + ", goodnessW=" + goodnessW + ", goodnessD=" + goodnessD + ", goodnessL="
				+ goodnessL + ", homeVariationType=" + homeVariationType + "\n";
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

	public Integer getTotalEvents() {
		return totalEvents;
	}

	public void setTotalEvents(Integer totalEvents) {
		this.totalEvents = totalEvents;
	}
	
	

	
}
