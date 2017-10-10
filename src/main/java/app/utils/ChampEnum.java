
package app.utils;

import java.util.ArrayList;
import java.util.Arrays;



public enum ChampEnum {
	
	ENG_PREMIER			(	AppConstants.ENG_PREMIER_RESULTS,
							AppConstants.ENG_PREMIER_NEXT_MATCHES, 	
							new Integer[]{ 1,3,4,7,17 }, 
							new RankCritEnum[]{	RankCritEnum.POINTS, 
												RankCritEnum.GOALS_DIFFERENCE, 
												RankCritEnum.GOALS_SCORED},
							"England",
							"Premier League",
							2017),
//							
//	SPA_LIGA 			(	AppConstants.SPA_LIGA_RESULTS,
//							AppConstants.SPA_LIGA_WIN_ODDS, 	
//							AppConstants.SPA_LIGA_UO_ODDS, 
//							AppConstants.SPA_LIGA_HALF_TIME_ODDS,
//							AppConstants.SPA_LIGA_HISTORY_RESULTS,
//							new Integer[]{ 1,3,4,7,17 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED, }),
//							
//	GER_BUNDESLIGA		(	AppConstants.GER_BUNDESLIGA_RESULTS,
//							AppConstants.GER_BUNDESLIGA_WIN_ODDS, 	
//							AppConstants.GER_BUNDESLIGA_UO_ODDS, 
//							AppConstants.GER_BUNDESLIGA_HALF_TIME_ODDS,
//							AppConstants.GER_BUNDESLIGA_HISTORY_RESULTS,
//							new Integer[]{ 1,3,4,7,15,16 }, 	 
//							new RankCritEnum[]{	RankCritEnum.POINTS,
//												RankCritEnum.GOALS_DIFFERENCE,
//												RankCritEnum.GOALS_SCORED,
//												RankCritEnum.HEAD_TO_HEAD_POINTS,
//												RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE,
//												RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY,
//												RankCritEnum.GOALS_SCORED_AWAY
//												}),
//							
//	ITA_SERIE_A 		(	AppConstants.ITA_SERIE_A_RESULTS,
//							AppConstants.ITA_SERIE_A_WIN_ODDS, 	
//							AppConstants.ITA_SERIE_A_UO_ODDS, 
//							AppConstants.ITA_SERIE_A_HALF_TIME_ODDS,
//							AppConstants.ITA_SERIE_A_HISTORY_RESULTS,
//							new Integer[]{ 1,2,3,6,17 },   
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED,}),
//							
//	FRA_LIGUE_1 		(	AppConstants.FRA_LIGUE_1_RESULTS,
//							AppConstants.FRA_LIGUE_1_WIN_ODDS, 	
//							AppConstants.FRA_LIGUE_1_UO_ODDS, 
//							AppConstants.FRA_LIGUE_1_HALF_TIME_ODDS,
//							AppConstants.FRA_LIGUE_1_HISTORY_RESULTS,
//							new Integer[]{ 1,2,3,6,17 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS,
//												RankCritEnum.GOALS_DIFFERENCE,
//												RankCritEnum.GOALS_SCORED,
//												RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE}),
//							
//	OLA_ERE_DIVISIE		(	AppConstants.OLA_ERE_DIVISIE_RESULTS,
//							AppConstants.OLA_ERE_DIVISIE_WIN_ODDS, 	
//							AppConstants.OLA_ERE_DIVISIE_UO_ODDS, 
//							AppConstants.OLA_ERE_DIVISIE_HALF_TIME_ODDS,
//							AppConstants.OLA_ERE_DIVISIE_HISTORY_RESULTS,
//							new Integer[]{ 1,2,3,7,15,17 },	 
//							new RankCritEnum[]{	RankCritEnum.POINTS,
//												RankCritEnum.GOALS_DIFFERENCE,
//												RankCritEnum.GOALS_SCORED,
//												RankCritEnum.HEAD_TO_HEAD_POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE,
//												RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY}),
//							
//								
//	BEL_PRO_LEAGUE		(	AppConstants.BEL_PRO_LEAGUE_RESULTS,
//							AppConstants.BEL_PRO_LEAGUE_WIN_ODDS, 	
//							AppConstants.BEL_PRO_LEAGUE_UO_ODDS, 
//							AppConstants.BEL_PRO_LEAGUE_HALF_TIME_ODDS,
//							AppConstants.BEL_PRO_LEAGUE_HISTORY_RESULTS,
//							new Integer[]{ 6,15 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.MATCHES_WON, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED, 
//												RankCritEnum.GOALS_SCORED_AWAY, 
//												RankCritEnum.MATCHES_WON_AWAY}),
//							
//	TUR_SUPER_LIG		(	AppConstants.TUR_SUPER_LIG_RESULTS,
//							AppConstants.TUR_SUPER_LIG_WIN_ODDS, 	
//							AppConstants.TUR_SUPER_LIG_UO_ODDS, 
//							AppConstants.TUR_SUPER_LIG_HALF_TIME_ODDS,
//							AppConstants.TUR_SUPER_LIG_HISTORY_RESULTS,
//							new Integer[]{ 1,2,4,15 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//												RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED}),
//							
//	GRE_SOUPER_LIGKA_ELLADA	(AppConstants.GRE_SOUPER_LIGKA_ELLADA_RESULTS,
//							AppConstants.GRE_SOUPER_LIGKA_ELLADA_WIN_ODDS, 	
//							AppConstants.GRE_SOUPER_LIGKA_ELLADA_UO_ODDS, 
//							AppConstants.GRE_SOUPER_LIGKA_ELLADA_HALF_TIME_ODDS,
//							AppConstants.GRE_SOUPER_LIGKA_ELLADA_HISTORY_RESULTS,
//							new Integer[]{ 1,5,14 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED, 
//												RankCritEnum.GOALS_TAKEN, }),
//							
//	POR_PRIMERA_LIGA	(	AppConstants.POR_PRIMERA_LIGA_RESULTS,
//							AppConstants.POR_PRIMERA_LIGA_WIN_ODDS, 	
//							AppConstants.POR_PRIMERA_LIGA_UO_ODDS, 
//							AppConstants.POR_PRIMERA_LIGA_HALF_TIME_ODDS,
//							AppConstants.POR_PRIMERA_LIGA_HISTORY_RESULTS,
//							new Integer[]{ 1,2,3,4,5,16 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//												RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY,
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.MATCHES_WON, 
//												RankCritEnum.GOALS_SCORED}),
//							
//	ENG_CHAMPIONSHIP	(	AppConstants.ENG_CHAMPIONSHIP_RESULTS,
//							AppConstants.ENG_CHAMPIONSHIP_WIN_ODDS, 	
//							AppConstants.ENG_CHAMPIONSHIP_UO_ODDS, 
//							AppConstants.ENG_CHAMPIONSHIP_HALF_TIME_ODDS,
//							AppConstants.ENG_CHAMPIONSHIP_HISTORY_RESULTS,
//							new Integer[]{ 2,6,21 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED, 
//												RankCritEnum.HEAD_TO_HEAD_POINTS, 
//												RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//												RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY}),
//							
//	ENG_LEAGUE_ONE	(		AppConstants.ENG_LEAGUE_ONE_RESULTS,
//							AppConstants.ENG_LEAGUE_ONE_WIN_ODDS, 	
//							AppConstants.ENG_LEAGUE_ONE_UO_ODDS, 
//							AppConstants.ENG_LEAGUE_ONE_HALF_TIME_ODDS,
//							AppConstants.ENG_LEAGUE_ONE_HISTORY_RESULTS,
//							new Integer[]{ 2,6,20 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED}),
//							
//	ENG_LEAGUE_TWO	(		AppConstants.ENG_LEAGUE_TWO_RESULTS,
//							AppConstants.ENG_LEAGUE_TWO_WIN_ODDS, 	
//							AppConstants.ENG_LEAGUE_TWO_UO_ODDS, 
//							AppConstants.ENG_LEAGUE_TWO_HALF_TIME_ODDS,
//							AppConstants.ENG_LEAGUE_TWO_HISTORY_RESULTS,
//							new Integer[]{ 3,7,22 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED}),
//							
//	SCO_PREMIERSHIP	(		AppConstants.SCO_PREMIERSHIP_RESULTS,
//							AppConstants.SCO_PREMIERSHIP_WIN_ODDS, 	
//							AppConstants.SCO_PREMIERSHIP_UO_ODDS, 
//							AppConstants.SCO_PREMIERSHIP_HALF_TIME_ODDS,
//							AppConstants.SCO_PREMIERSHIP_HISTORY_RESULTS,
//							new Integer[]{ 1,4,10,11 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED}),
//							
//	SCO_CHAMPIONSHIP	(	AppConstants.SCO_CHAMPIONSHIP_RESULTS,
//							AppConstants.SCO_CHAMPIONSHIP_WIN_ODDS, 	
//							AppConstants.SCO_CHAMPIONSHIP_UO_ODDS, 
//							AppConstants.SCO_CHAMPIONSHIP_HALF_TIME_ODDS,
//							AppConstants.SCO_CHAMPIONSHIP_HISTORY_RESULTS,
//							new Integer[]{ 1,4,8,9 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED}),
//							
//	SCO_LEAGUE_ONE	(		AppConstants.SCO_LEAGUE_ONE_RESULTS,
//							AppConstants.SCO_LEAGUE_ONE_WIN_ODDS, 	
//							AppConstants.SCO_LEAGUE_ONE_UO_ODDS, 
//							AppConstants.SCO_LEAGUE_ONE_HALF_TIME_ODDS,
//							AppConstants.SCO_LEAGUE_ONE_HISTORY_RESULTS,
//							new Integer[]{ 1,4,8,9 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED}),
//							
//	SCO_LEAGUE_TWO	(		AppConstants.SCO_LEAGUE_TWO_RESULTS,
//							AppConstants.SCO_LEAGUE_TWO_WIN_ODDS, 	
//							AppConstants.SCO_LEAGUE_TWO_UO_ODDS, 
//							AppConstants.SCO_LEAGUE_TWO_HALF_TIME_ODDS,
//							AppConstants.SCO_LEAGUE_TWO_HISTORY_RESULTS,
//							new Integer[]{ 1,4,9 }, 
//							new RankCritEnum[]{	RankCritEnum.POINTS, 
//												RankCritEnum.GOALS_DIFFERENCE, 
//												RankCritEnum.GOALS_SCORED}),
//											
							
							
							
							
							
							
							
							
							
							
							
							
	//OK
//	FRA_LIGUE_2 			(2,3,6,17,18),
//							RankCritEnum.POINTS,
//							RankCritEnum.GOALS_DIFFERENCE,
//							RankCritEnum.GOALS_SCORED,
//							RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE

//	GER_2_BUNDESLIGA		(2,3,15),	
//							RankCritEnum.POINTS,
//							RankCritEnum.GOALS_DIFFERENCE,
//							RankCritEnum.GOALS_SCORED,

//	SPA_SEGUNDA_DIVISON		(2,6,18),
//							RankCritEnum.POINTS, 
//							RankCritEnum.HEAD_TO_HEAD_POINTS, 
//							RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//							RankCritEnum.GOALS_DIFFERENCE, 
//							RankCritEnum.GOALS_SCORED, 

//	ITA_SERIE_B 			(2,8,19),
//							RankCritEnum.POINTS, 
//							RankCritEnum.HEAD_TO_HEAD_POINTS, 
//							RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//							RankCritEnum.GOALS_DIFFERENCE, 
//							RankCritEnum.GOALS_SCORED,
							
							

//	
	
	
	;
	
	
	
	
	
	private ChampEnum(String resultsUrl, String nextMatchesUrl, Integer[] importantPositions, RankCritEnum[] rankCriteria, String nation, String name, int startYear ){
		this.importantPositions = new ArrayList<Integer>(Arrays.asList(importantPositions));
		this.rankCriteria = new ArrayList<RankCritEnum>(Arrays.asList(rankCriteria));
		this.resultsUrl = resultsUrl;
		this.nextMatchesUrl = nextMatchesUrl;
		this.nation = nation;
		this.name = name;
		this.startYear = startYear;
    }
	
    private final ArrayList<Integer> importantPositions;
    private final ArrayList<RankCritEnum> rankCriteria;
    private final String resultsUrl;
    private final String nextMatchesUrl;
    private final String nation;
    private final String name;
    private final int startYear;
    
    public ArrayList<RankCritEnum> getRankCriteria(){
    	return rankCriteria;
    }

    public ArrayList<Integer> getImportantPositions(){
        return importantPositions;
    }

	public String getNextMatchesUrl() {
		return nextMatchesUrl;
	}

	public String getResultsUrl() {
		return resultsUrl;
	}

	public String getNation() {
		return nation;
	}

	public String getName() {
		return name;
	}

	public int getStartYear() {
		return startYear;
	}

}


