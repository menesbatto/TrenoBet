package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import app.dao.tipologiche.entities.HomeVariationType;

@Entity
public class ResultGoodnessWdl {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Double goodnessW;
	
	private Double goodnessD;

	private Double goodnessL;
	
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
	
	

	
}
