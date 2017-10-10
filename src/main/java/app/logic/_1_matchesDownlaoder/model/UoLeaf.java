package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;

public class UoLeaf implements Serializable{
	
	private static final long serialVersionUID = -6717783802870029273L;

	private Double u;
	private Double o;
	
	
	
	
	public UoLeaf(Double oddUnder, Double oddOver) {
		this.u = oddUnder;
		this.o = oddOver;
	}
	public Double getU() {
		return u;
	}
	public void setU(Double oddUnder) {
		this.u = oddUnder;
	}
	public Double getO() {
		return o;
	}
	public void setO(Double oddOver) {
		this.o = oddOver;
	}
	@Override
	public String toString() {
		return u + " - " + o + "\n";
	}
	
	
	
}
