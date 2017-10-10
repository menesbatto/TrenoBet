package app.logic._2_matchResultAnalyzer.model;

import java.io.Serializable;

import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;

public class WinRangeStatsBean implements Serializable{

	private static final long serialVersionUID = 1267168643269362884L;
	
	private String teamName;
	
	private Double edgeUp;
	
	private Double edgeDown;
	
	private String range;
	
	private Integer homeHits;
	
	private Integer homeMisses;
	
	private Double winPerc;
	
	private Integer drawHits;
	
	private Integer drawMisses;

	private Double drawPerc;
	
	private Integer awayHits;
	
	private Integer awayMisses;
	
	private Double losePerc;

	private Integer total;
	
	private TimeTypeEnum timeTypeBean;
	
	private String playingField;
	
	private HomeVariationEnum homeVariationBean;
	
	
	public WinRangeStatsBean() {
		homeHits = 0;
		homeMisses = 0;
		drawHits = 0;
		drawMisses = 0;
		awayHits = 0;
		awayMisses = 0;
		total = 0;
	}


	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	public Double getEdgeUp() {
		return edgeUp;
	}


	public void setEdgeUp(Double edgeUp) {
		this.edgeUp = edgeUp;
	}


	public Double getEdgeDown() {
		return edgeDown;
	}


	public void setEdgeDown(Double edgeDown) {
		this.edgeDown = edgeDown;
	}


	public String getRange() {
		return range;
	}


	public void setRange(String range) {
		this.range = range;
	}


	public Integer getHomeHits() {
		return homeHits;
	}


	public void setHomeHits(Integer homeHits) {
		this.homeHits = homeHits;
	}


	public Integer getHomeMisses() {
		return homeMisses;
	}


	public void setHomeMisses(Integer homeMisses) {
		this.homeMisses = homeMisses;
	}


	public Integer getDrawHits() {
		return drawHits;
	}


	public void setDrawHits(Integer drawHits) {
		this.drawHits = drawHits;
	}


	public Integer getDrawMisses() {
		return drawMisses;
	}


	public void setDrawMisses(Integer drawMisses) {
		this.drawMisses = drawMisses;
	}


	public Integer getAwayHits() {
		return awayHits;
	}


	public void setAwayHits(Integer awayHits) {
		this.awayHits = awayHits;
	}


	public Integer getAwayMisses() {
		return awayMisses;
	}


	public void setAwayMisses(Integer awayMisses) {
		this.awayMisses = awayMisses;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "teamName=" + teamName + ", \n\trange= "
				+ range + ",\n\thomeHits= " + homeHits + ", homeMisses= " + homeMisses + ", \n\tdrawHits= " + drawHits
				+ ", drawMisses= " + drawMisses + ",\n\tawayHits= " + awayHits + ", awayMisses= " + awayMisses + ",\n\ttotal="
				+ total + 
				",\n\twinPerc= " + winPerc + ", drawPerc= " + drawPerc + " losePerc= " + losePerc + 
				"\n\n";
	}
//	@Override
//	public String toString() {
//		return "\nteamName=" + getStandardName() + " range= " + range + ",\thomeHits= " + homeHits + ", homeMisses= " + homeMisses + "\ttotal="
//				+ total + ", \n\t\t\t\t\tdrawHits= " + drawHits
//				+ ", drawMisses= " + drawMisses + ",\n\t\t\t\t\tawayHits= " + awayHits + ", awayMisses= " + awayMisses + "\n\n";
//	}


	private String getStandardName() {
		Integer spaces = 12 - teamName.length();
		String returnedName = teamName;
		for (int i = 0; i<spaces; i++)
			returnedName = returnedName + " ";
		return returnedName;
	}


	public Double getWinPerc() {
		return winPerc;
	}


	public void setWinPerc(Double winPerc) {
		this.winPerc = winPerc;
	}


	public Double getDrawPerc() {
		return drawPerc;
	}


	public void setDrawPerc(Double drawPerc) {
		this.drawPerc = drawPerc;
	}


	public Double getLosePerc() {
		return losePerc;
	}


	public void setLosePerc(Double losePerc) {
		this.losePerc = losePerc;
	}


	public TimeTypeEnum getTimeTypeBean() {
		return timeTypeBean;
	}


	public void setTimeTypeBean(TimeTypeEnum timeTypeBean) {
		this.timeTypeBean = timeTypeBean;
	}


	public String getPlayingField() {
		return playingField;
	}


	public void setPlayingField(String playingField) {
		this.playingField = playingField;
	}


	public HomeVariationEnum getHomeVariationBean() {
		return homeVariationBean;
	}


	public void setHomeVariationBean(HomeVariationEnum homeVariationBean) {
		this.homeVariationBean = homeVariationBean;
	}
	

	
	
	
}
