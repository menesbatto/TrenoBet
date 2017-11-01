package app.logic._9_alghoritmTester;

import app.utils.Utils;

public class SeasonDayBetResultInfo {

	private Integer _12BetNum;
	private Integer xBetNum;
	private Integer uoBetNum;
	private Integer ehBetNum;
	private Integer totalBetNum;
	
	private Integer _12WinBetNum;
	private Integer xWinBetNum;
	private Integer uoWinBetNum;
	private Integer ehWinBetNum;
	private Integer totalWinBetNum;
	
	private Double _12WinAmount;
	private Double xWinAmount;
	private Double uoWinAmount;
	private Double ehWinAmount;
	private Double totalWinAmount;
	
	
	
	
	public SeasonDayBetResultInfo() {
		this._12BetNum = 0;
		this.xBetNum = 0;
		this.uoBetNum = 0;
		this.ehBetNum = 0;
		this.totalBetNum = 0;
		this._12WinBetNum = 0;
		this.xWinBetNum = 0;
		this.uoWinBetNum = 0;
		this.ehWinBetNum = 0;
		this.totalWinBetNum = 0;
		this._12WinAmount = 0.0;
		this.xWinAmount = 0.0;
		this.uoWinAmount = 0.0;
		this.ehWinAmount = 0.0;
		this.totalWinAmount = 0.0;
	}
	public SeasonDayBetResultInfo(SeasonDayBetResultInfo s) {
			this(s.get_12BetNum(), s.getxBetNum(), s.getUoBetNum(), s.getEhBetNum(), s.getTotalBetNum(), 
				s.get_12WinBetNum(), s.getxWinBetNum(), s.getUoWinBetNum(), s.getEhWinBetNum(), 
				s.getTotalWinBetNum(), s.get_12WinAmount(), s.getxWinAmount(), s.getUoWinAmount(), 
				s.getEhWinAmount(), s.getTotalWinAmount());
	}
	
	
	 
	
	public SeasonDayBetResultInfo(Integer _12BetNum, Integer xBetNum, Integer uoBetNum, Integer ehBetNum,
			Integer totalBetNum, Integer _12WinBetNum, Integer xWinBetNum, Integer uoWinBetNum, Integer ehWinBetNum,
			Integer totalWinBetNum, Double _12WinAmount, Double xWinAmount, Double uoWinAmount, Double ehWinAmount,
			Double totalWinAmount) {
		super();
		this._12BetNum = _12BetNum;
		this.xBetNum = xBetNum;
		this.uoBetNum = uoBetNum;
		this.ehBetNum = ehBetNum;
		this.totalBetNum = totalBetNum;
		this._12WinBetNum = _12WinBetNum;
		this.xWinBetNum = xWinBetNum;
		this.uoWinBetNum = uoWinBetNum;
		this.ehWinBetNum = ehWinBetNum;
		this.totalWinBetNum = totalWinBetNum;
		this._12WinAmount = _12WinAmount;
		this.xWinAmount = xWinAmount;
		this.uoWinAmount = uoWinAmount;
		this.ehWinAmount = ehWinAmount;
		this.totalWinAmount = totalWinAmount;
	}
	
	public Integer get_12BetNum() {
		return _12BetNum;
	}
	public void set_12BetNum(Integer _12BetNum) {
		this._12BetNum = _12BetNum;
	}
	public Integer getxBetNum() {
		return xBetNum;
	}
	public void setxBetNum(Integer xBetNum) {
		this.xBetNum = xBetNum;
	}
	public Integer getUoBetNum() {
		return uoBetNum;
	}
	public void setUoBetNum(Integer uoBetNum) {
		this.uoBetNum = uoBetNum;
	}
	public Integer getEhBetNum() {
		return ehBetNum;
	}
	public void setEhBetNum(Integer ehBetNum) {
		this.ehBetNum = ehBetNum;
	}
	public Integer getTotalBetNum() {
		return totalBetNum;
	}
	public void setTotalBetNum(Integer totalBetNum) {
		this.totalBetNum = totalBetNum;
	}
	public Integer get_12WinBetNum() {
		return _12WinBetNum;
	}
	public void set_12WinBetNum(Integer _12WinBetNum) {
		this._12WinBetNum = _12WinBetNum;
	}
	public Integer getxWinBetNum() {
		return xWinBetNum;
	}
	public void setxWinBetNum(Integer xWinBetNum) {
		this.xWinBetNum = xWinBetNum;
	}
	public Integer getUoWinBetNum() {
		return uoWinBetNum;
	}
	public void setUoWinBetNum(Integer uoWinBetNum) {
		this.uoWinBetNum = uoWinBetNum;
	}
	public Integer getEhWinBetNum() {
		return ehWinBetNum;
	}
	public void setEhWinBetNum(Integer ehWinBetNum) {
		this.ehWinBetNum = ehWinBetNum;
	}
	public Integer getTotalWinBetNum() {
		return totalWinBetNum;
	}
	public void setTotalWinBetNum(Integer totalWinBetNum) {
		this.totalWinBetNum = totalWinBetNum;
	}
	public Double get_12WinAmount() {
		return _12WinAmount;
	}
	public void set_12WinAmount(Double _12WinAmount) {
		this._12WinAmount = _12WinAmount;
	}
	public Double getxWinAmount() {
		return xWinAmount;
	}
	public void setxWinAmount(Double xWinAmount) {
		this.xWinAmount = xWinAmount;
	}
	public Double getUoWinAmount() {
		return uoWinAmount;
	}
	public void setUoWinAmount(Double uoWinAmount) {
		this.uoWinAmount = uoWinAmount;
	}
	public Double getEhWinAmount() {
		return ehWinAmount;
	}
	public void setEhWinAmount(Double ehWinAmount) {
		this.ehWinAmount = ehWinAmount;
	}
	public Double getTotalWinAmount() {
		return totalWinAmount;
	}
	public void setTotalWinAmount(Double totalWinAmount) {
		this.totalWinAmount = totalWinAmount;
	}

}
