package app.dao.tabelle.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import app.dao.tipologiche.entities.RankingCriteria;

@Entity
public class Champ {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	
	private int startYear;
	
	private String nation;
	
	private String resultsUrl;
	private String nextMatchesUrl;
	

	@ElementCollection
	private List<Integer> importantPositions;

	@ElementCollection
	private List<RankingCriteria> rankingCriteria;
	
	
	
	public Champ() {
		super();
	}

	public Champ(	String name, 		int startYear, 		String nation,
					String resultsUrl, 	String nextMatchesUrl) {

		this.name= name;
		this.startYear= startYear;
		this.setNation(nation);
		
		this.resultsUrl = resultsUrl;
		this.nextMatchesUrl = nextMatchesUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getResultsUrl() {
		return resultsUrl;
	}

	public void setResultsUrl(String resultsUrl) {
		this.resultsUrl = resultsUrl;
	}

	public String getNextMatchesUrl() {
		return nextMatchesUrl;
	}

	public void setNextMatchesUrl(String nextMatchesUrl) {
		this.nextMatchesUrl = nextMatchesUrl;
	}

	public List<Integer> getImportantPositions() {
		return importantPositions;
	}

	public void setImportantPositions(List<Integer> importantPositions) {
		this.importantPositions = importantPositions;
	}

	public List<RankingCriteria> getRankingCriteria() {
		return rankingCriteria;
	}

	public void setRankingCriteria(List<RankingCriteria> rankingCriteria) {
		this.rankingCriteria = rankingCriteria;
	}

//	public ChampImpPos getImpPos() {
//		return impPos;
//	}
//
//	public void setImpPos(ChampImpPos impPos) {
//		this.impPos = impPos;
//	}
//
//	public ChampUrls getUrls() {
//		return urls;
//	}
//
//	public void setUrls(ChampUrls urls) {
//		this.urls = urls;
//	}
//
//	public ChampRanCri getRanCri() {
//		return ranCri;
//	}
//
//	public void setRanCri(ChampRanCri ranCri) {
//		this.ranCri = ranCri;
//	}
//	
	
	
}
