package app.logic._7_betAnalyzer.model;

import java.io.Serializable;

import app.utils.Utils;

public class BetAnalysis implements Serializable{
	
	private static final long serialVersionUID = -8220296444420254113L;

	private Integer winHit;
	private Integer winTot;
	
	private Integer uoHit;
	private Integer uoTot;
	
	private Double winSpentAmount;
	private Double winGrossWinAmount;

	private Double uoSpentAmount;
	private Double uoGrossWinAmount;
	
	
	
	
	
	public BetAnalysis() {
		winHit = 0;
		winTot = 0;
		uoHit = 0;
		uoTot = 0;
		uoSpentAmount = 0.0;
		uoGrossWinAmount = 0.0;
		winSpentAmount = 0.0;
		winGrossWinAmount = 0.0;
		
	}
	
	@Override
	public String toString() {
		return 	"\n\tHit" + "\t" + "Tot" + "\t" + 					"Spesi" + "\t" + "Vinti" + "\t" + "Guadagnati" + "\t" + "Variazione %" + "\n" +   
				"WIN\t" + winHit + "\t" + winTot + "\t" + 			winSpentAmount + "\t" + Utils.redimString(winGrossWinAmount) + "\t" + Utils.redimString(getWinNetAmount()) + "\t\t" + Utils.redimString(getWinNetAmount()*100/winSpentAmount) + "\n" + 
				"UO\t" + uoHit + "\t" + uoTot + "\t" + 				uoSpentAmount + "\t" + Utils.redimString(uoGrossWinAmount) + "\t" + Utils.redimString(getUoNetAmount()) +  "\t\t" + Utils.redimString(getUoNetAmount()*100/uoSpentAmount) + "\n" +
				"ALL\t" + getAllHit() + "\t" + getAllTot() + "\t" + Utils.redimString(getAllSpentAmount()) + "\t" + Utils.redimString(getAllGrossAmount()) + "\t" + Utils.redimString(getAllNetAmount()) +  "\t\t" + Utils.redimString(getPercentageVariation()) + "\n";
	}

	public double getPercentageVariation() {
		return getAllNetAmount()*100/getAllSpentAmount();
	}
	
	
	public Double getWinNetAmount() {
		return winGrossWinAmount - winSpentAmount;
	}
	public Double getUoNetAmount() {
		return uoGrossWinAmount - uoSpentAmount;
	}
	
	public Double getAllNetAmount() {
		return getWinNetAmount() + getUoNetAmount();
	}

	public Double getAllGrossAmount() {
		return winGrossWinAmount + uoGrossWinAmount;
	}

	public Double getAllSpentAmount() {
		return winSpentAmount + uoSpentAmount;
	}



	public Integer getWinHit() {
		return winHit;
	}
	public void setWinHit(Integer winHit) {
		this.winHit = winHit;
	}
	public void upWinHit() {
		this.winHit += 1;
	}
	public Integer getWinTot() {
		return winTot;
	}
	public void setWinTot(Integer winTot) {
		this.winTot = winTot;
	}
	public void upWinTot() {
		this.winTot += 1;
	}
	public Integer getUoHit() {
		return uoHit;
	}
	public void setUoHit(Integer uoHit) {
		this.uoHit = uoHit;
	}
	public void upUoHit() {
		this.uoHit += 1;
	}
	public Integer getUoTot() {
		return uoTot;
	}
	public void setUoTot(Integer uoTot) {
		this.uoTot = uoTot;
	}
	public void upUoTot() {
		this.uoTot += 1;
	}
	
	
	public Integer getAllHit() {
		return winHit + uoHit;
	}


	public Integer getAllTot() {
		return winTot + uoTot;
	}

	public Double getWinSpentAmount() {
		return winSpentAmount;
	}

	public void setWinSpentAmount(Double winSpentAmount) {
		this.winSpentAmount = winSpentAmount;
	}

	public Double getWinGrossWinAmount() {
		return winGrossWinAmount;
	}

	public void setWinGrossWinAmount(Double winGrossWinAmount) {
		this.winGrossWinAmount = winGrossWinAmount;
	}
	public void upWinGrossWinAmount(Double winGrossWinAmount) {
		this.winGrossWinAmount += winGrossWinAmount;
	}

	
	
	
	public Double getUoSpentAmount() {
		return uoSpentAmount;
	}
	public void setUoSpentAmount(Double uoSpentAmount) {
		this.uoSpentAmount = uoSpentAmount;
	}
	
	
	public Double getUoGrossWinAmount() {
		return uoGrossWinAmount;
	}
	public void setUoGrossWinAmount(Double uoGrossWinAmount) {
		this.uoGrossWinAmount = uoGrossWinAmount;
	}
	public void upUoGrossWinAmount(Double uoGrossWinAmount) {
		this.uoGrossWinAmount += uoGrossWinAmount;
	}
	
	
	
	
	
}
