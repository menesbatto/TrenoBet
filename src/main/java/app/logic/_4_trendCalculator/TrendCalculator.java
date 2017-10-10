package app.logic._4_trendCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.MatchoDao;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._2_matchResultAnalyzer.ResultAnalyzer;
import app.utils.AppConstants;
import app.utils.ChampEnum;

@Service
public class TrendCalculator {
	

	@Autowired
	private ResultAnalyzer resultAnalyzer;
	
	@Autowired
	private MatchoDao matchDao;
	
	
	public void execute(){
		
		for (ChampEnum champ : ChampEnum.values()){
			calculateChampTrend(champ);
		}

	}
	
	private void calculateChampTrend(ChampEnum champ) {
		ArrayList<MatchResult> teamsMatches = matchDao.getDownloadedPastMatchByChampLight(champ);
		Map<String, ArrayList<MatchResult>> matchesMap = new HashMap<String, ArrayList<MatchResult>>();
		resultAnalyzer.createMatchMap(matchesMap, teamsMatches, "H");
		resultAnalyzer.createMatchMap(matchesMap, teamsMatches, "A");
		
		
		ArrayList<MatchResult> allMatches = null;
		ArrayList<MatchResult> lastFive;
		for (Map.Entry<String, ArrayList<MatchResult>> entry : matchesMap.entrySet()) {
			allMatches = entry.getValue();
			String teamName = entry.getKey();
			Collections.sort(allMatches, new Comparator<MatchResult>() {
				public int compare(MatchResult o1, MatchResult o2) {
					if (o1.getMatchDate().before(o2.getMatchDate()))
						return 1;
					return -1;
				}
			});
			
			int lastMatches = allMatches.size() < AppConstants.TREND_SIZE_UO ? allMatches.size() : AppConstants.TREND_SIZE_UO;
			lastFive = new ArrayList<MatchResult>(allMatches.subList(0, lastMatches));
			
		}
	}
}
