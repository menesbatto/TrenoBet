//package app.dao.tabelle.entities;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//
//import app.dao.tipologiche.entities.HomeVariationType;
//import app.dao.tipologiche.entities.OddsRange;
//import app.dao.tipologiche.entities.TimeType;
//
//@Entity
//public class WinEhRangeStats{
//	
//
//	@ManyToOne
//	private HomeVariationType homeVariation;
//	
//	
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private int id;
//
//	@ManyToOne
////	@JoinColumn(name = "champ_id")
//	private Team team;
//
////	private String teamName;
//	@OneToOne
//	private OddsRange range;
//	
//	@ManyToOne
//	private TimeType timeType;
//	
//	private String playingField;
//	
////	private String range;
//	
//	private Integer homeHits;
//	
//	private Integer homeMisses;
//	
//	private Double winPerc;
//	
//	private Integer drawHits;
//	
//	private Integer drawMisses;
//
//	private Double drawPerc;
//	
//	private Integer awayHits;
//	
//	private Integer awayMisses;
//	
//	private Double losePerc;
//
//	private Integer total;
//	
//
//	
//	
//	public WinEhRangeStats() {
//	}
//
////	public WinEhRangeStats(WinRangeStats x) {
////		super(	
////				x.getTeam(), x.getRange(), 
////				
////				x.getTimeType(), x.getPlayingField(), 
////				
////				x.getHomeHits(), x.getHomeMisses(), 
////				
////				x.getWinPerc(), x.getDrawHits(), 
////				
////				x.getDrawMisses(), x.getDrawPerc(), 
////				
////				x.getAwayHits(), x.getAwayMisses(), 
////				
////				x.getLosePerc(), x.getTotal()
////				
////				);
////	}
//
//	public HomeVariationType getHomeVariation() {
//		return homeVariation;
//	}
//
//	public void setHomeVariation(HomeVariationType homeVariation) {
//		this.homeVariation = homeVariation;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Team getTeam() {
//		return team;
//	}
//
//	public void setTeam(Team team) {
//		this.team = team;
//	}
//
//	public OddsRange getRange() {
//		return range;
//	}
//
//	public void setRange(OddsRange range) {
//		this.range = range;
//	}
//
//	public TimeType getTimeType() {
//		return timeType;
//	}
//
//	public void setTimeType(TimeType timeType) {
//		this.timeType = timeType;
//	}
//
//	public String getPlayingField() {
//		return playingField;
//	}
//
//	public void setPlayingField(String playingField) {
//		this.playingField = playingField;
//	}
//
//	public Integer getHomeHits() {
//		return homeHits;
//	}
//
//	public void setHomeHits(Integer homeHits) {
//		this.homeHits = homeHits;
//	}
//
//	public Integer getHomeMisses() {
//		return homeMisses;
//	}
//
//	public void setHomeMisses(Integer homeMisses) {
//		this.homeMisses = homeMisses;
//	}
//
//	public Double getWinPerc() {
//		return winPerc;
//	}
//
//	public void setWinPerc(Double winPerc) {
//		this.winPerc = winPerc;
//	}
//
//	public Integer getDrawHits() {
//		return drawHits;
//	}
//
//	public void setDrawHits(Integer drawHits) {
//		this.drawHits = drawHits;
//	}
//
//	public Integer getDrawMisses() {
//		return drawMisses;
//	}
//
//	public void setDrawMisses(Integer drawMisses) {
//		this.drawMisses = drawMisses;
//	}
//
//	public Double getDrawPerc() {
//		return drawPerc;
//	}
//
//	public void setDrawPerc(Double drawPerc) {
//		this.drawPerc = drawPerc;
//	}
//
//	public Integer getAwayHits() {
//		return awayHits;
//	}
//
//	public void setAwayHits(Integer awayHits) {
//		this.awayHits = awayHits;
//	}
//
//	public Integer getAwayMisses() {
//		return awayMisses;
//	}
//
//	public void setAwayMisses(Integer awayMisses) {
//		this.awayMisses = awayMisses;
//	}
//
//	public Double getLosePerc() {
//		return losePerc;
//	}
//
//	public void setLosePerc(Double losePerc) {
//		this.losePerc = losePerc;
//	}
//
//	public Integer getTotal() {
//		return total;
//	}
//
//	public void setTotal(Integer total) {
//		this.total = total;
//	}
//
//
//
//	
//
//}
