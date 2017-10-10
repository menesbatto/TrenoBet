package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import app.dao.tipologiche.entities.UoThresholdType;

@Entity
public class ResultGoodnessUo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Double goodnessU;
	
	private Double goodnessO;
	
	@ManyToOne
	private UoThresholdType threshold; 

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

	public UoThresholdType getThreshold() {
		return threshold;
	}

	public void setThreshold(UoThresholdType threshold) {
		this.threshold = threshold;
	}

	@Override
	public String toString() {
		return "id=" + id + ", goodnessU=" + goodnessU + ", goodnessO=" + goodnessO + ", threshold="
				+ threshold + "\n";
	}
	
	
	
	
}
