package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;

public class _1x2Leaf implements Serializable{

	private static final long serialVersionUID = -2267374392760947736L;
	
	private Double odd1;
	private Double oddX;
	private Double odd2;
	
	
	
	
	public _1x2Leaf(Double odd1, Double oddX, Double odd2) {
		this.odd1 = odd1;
		this.oddX = oddX;
		this.odd2 = odd2;
	}
	public Double getOdd1() {
		return odd1;
	}
	public void setOdd1(Double odd1) {
		this.odd1 = odd1;
	}
	public Double getOddX() {
		return oddX;
	}
	public void setOddX(Double oddX) {
		this.oddX = oddX;
	}
	public Double getOdd2() {
		return odd2;
	}
	public void setOdd2(Double odd2) {
		this.odd2 = odd2;
	}
	
	@Override
	public String toString() {
		return odd1 + " - " + oddX + " - " + odd2 + "\n";
	}

	
	
	
	
}
