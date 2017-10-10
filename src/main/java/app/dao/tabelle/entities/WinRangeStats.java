package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.OddsRange;
import app.dao.tipologiche.entities.TimeType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class WinRangeStats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private HomeVariationType homeVariation;
	
	@ManyToOne
//	@JoinColumn(name = "champ_id")
	private Team team;

//	private String teamName;
	@OneToOne
	private OddsRange range;
	
	@ManyToOne
	private TimeType timeType;
	
	private String playingField;
	
//	private String range;
	
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
	

	
	
	
	
	
	
	
	
	public WinRangeStats() {
	}
	public WinRangeStats(OddsRange range, Team team) {
		this.range = range;
		this.team = team;
	}

	
//	
//	public WinRangeStats(Team team, OddsRange range, TimeType timeType, String playingField, Integer homeHits,
//			Integer homeMisses, Double winPerc, Integer drawHits, Integer drawMisses, Double drawPerc, Integer awayHits,
//			Integer awayMisses, Double losePerc, Integer total) {
//		super();
//		this.team = team;
//		this.range = range;
//		this.timeType = timeType;
//		this.playingField = playingField;
//		this.homeHits = homeHits;
//		this.homeMisses = homeMisses;
//		this.winPerc = winPerc;
//		
//		this.drawHits = drawHits;
//		this.drawMisses = drawMisses;
//		this.drawPerc = drawPerc;
//		this.awayHits = awayHits;
//		this.awayMisses = awayMisses;
//		this.losePerc = losePerc;
//		this.total = total;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OddsRange getRange() {
		return range;
	}
	public void setRange(OddsRange range) {
		this.range = range;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
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
	public Double getWinPerc() {
		return winPerc;
	}
	public void setWinPerc(Double winPerc) {
		this.winPerc = winPerc;
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
	public Double getDrawPerc() {
		return drawPerc;
	}
	public void setDrawPerc(Double drawPerc) {
		this.drawPerc = drawPerc;
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
	public Double getLosePerc() {
		return losePerc;
	}
	public void setLosePerc(Double losePerc) {
		this.losePerc = losePerc;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getPlayingField() {
		return playingField;
	}
	public void setPlayingField(String playingField) {
		this.playingField = playingField;
	}
	public TimeType getTimeType() {
		return timeType;
	}
	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}
	@Override
	public String toString() {
		
		String homeVarString = "null";//"homeVariation = " + homeVariation != null ? homeVariation.getValue() : "null" ;
		
				return "id=" + id + ",\t team=" + team.getName() + ",\t"+ homeVarString + ",\t range=" + range.getValueUp() + ",\t timeType=" + timeType.getValue()
				+ ",\t playingField=" + playingField + ",\t homeHits=" + homeHits + ",\t homeMisses=" + homeMisses
				+ ",\t winPerc=" + winPerc + ",\t drawHits=" + drawHits + ",\t drawMisses=" + drawMisses + ",\t drawPerc="
				+ drawPerc + ",\t awayHits=" + awayHits + ",\t awayMisses=" + awayMisses + ",\t losePerc=" + losePerc
				+ ",\t total=" + total + "]\n";
	}
	public HomeVariationType getHomeVariation() {
		return homeVariation;
	}
	public void setHomeVariation(HomeVariationType homeVariation) {
		this.homeVariation = homeVariation;
	}


	

}
