
package app.utils;

import java.util.ArrayList;
import java.util.Arrays;



public enum ChampEnum {
	
	//##################################
	//##########   ARMENIA   ###########
	//##################################
		
	
	//##################################
	//##########   AUSTRIA   ###########
	//##################################
		
	
	//##################################
	//########   AZERBAIJAN   ##########
	//##################################
	
	
	
	//##################################
	//##########   BELGIUM   ###########
	//##################################
	
	BEL_JUPILER_LEAGUE_2017		(	AppConstants.BEL_JUPILER_LEAGUE_RESULTS,
									AppConstants.BEL_JUPILER_LEAGUE_NEXT_MATCHES,
									new Integer[]{ 6,15 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.MATCHES_WON, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED, 
														RankCritEnum.GOALS_SCORED_AWAY, 
														RankCritEnum.MATCHES_WON_AWAY},
									"Belgium",
									"Jupiler League",
									2017),
	
	BEL_PROXIMUS_LEAGUE_2017	(	AppConstants.BEL_PROXIMUS_LEAGUE_RESULTS,
									AppConstants.BEL_PROXIMUS_LEAGUE_NEXT_MATCHES,
									new Integer[]{ 1 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS	},
									"Belgium",
									"Proximus League",
									2017),
	
	
	//##################################
	//##########    BOSNIA   ###########
	//##################################
		
	
	
	//##################################
	//########     BULGARIA     ########
	//##################################
	
	BUL_PARVA_LIGA_2017			(	AppConstants.BUL_PARVA_LIGA_RESULTS,
									AppConstants.BUL_PARVA_LIGA_NEXT_MATCHES,
									new Integer[]{ 6 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"Bulgaria",
									"Parva Liga",
									2017),
	//##################################
	//##########   CROATIA   ###########
	//##################################
	
	CRO_1_HNL_2017				(	AppConstants.CRO_1_HNL_RESULTS,
									AppConstants.CRO_1_HNL_NEXT_MATCHES,
									new Integer[]{ 1,3,8,9 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"Croatia",
									"1 hnl",
									2017),
	

	
	//##################################
	//##########   CYPRUS    ###########
	//##################################
	CYP_FIRST_DIVISION_2017		(	AppConstants.CYP_FIRST_DIVISION_RESULTS,
									AppConstants.CYP_FIRST_DIVISION_NEXT_MATCHES,
									new Integer[]{ 6, 12 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"Cyprus",
									"First Division",
									2017),
	
	
	//##################################
	//##########   DENMARK   ###########
	//##################################
	
	DEN_SUPERLIGA_2017			(	AppConstants.DEN_SUPERLIGA_RESULTS,
									AppConstants.DEN_SUPERLIGA_NEXT_MATCHES,
									new Integer[]{ 6 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED },
									"Denmark",
									"Superliga",
									2017),
	
	DEN_1ST_DIVISION_2017		(	AppConstants.DEN_1ST_DIVISION_RESULTS,
									AppConstants.DEN_1ST_DIVISION_NEXT_MATCHES,
									new Integer[]{ 1, 3, 10 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED },
									"Denmark",
									"1st Division",
									2017),
	
	//##################################
	//##########   ENGLAND   ###########
	//##################################
	
	ENG_PREMIER_2017			(	AppConstants.ENG_PREMIER_RESULTS,
									AppConstants.ENG_PREMIER_NEXT_MATCHES, 	
									new Integer[]{ 1,3,4,7,17 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"England",
									"Premier League",
									2017),
	
	ENG_CHAMPIONSHIP_2017		(	AppConstants.ENG_CHAMPIONSHIP_RESULTS,
									AppConstants.ENG_CHAMPIONSHIP_NEXT_MATCHES,
									new Integer[]{ 2,6,21 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY},
									"England",
									"Championship",
									2017),
	
	
	ENG_LEAGUE_ONE_2017			(	AppConstants.ENG_LEAGUE_ONE_RESULTS,
									AppConstants.ENG_LEAGUE_ONE_NEXT_MATCHES, 	
									new Integer[]{ 2,6,20 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"England",
									"League One",
									2017),
			
	ENG_LEAGUE_TWO_2017			(	AppConstants.ENG_LEAGUE_TWO_RESULTS,
									AppConstants.ENG_LEAGUE_TWO_NEXT_MATCHES, 	
									new Integer[]{ 3,7,22 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"England",
									"League Two",
									2017),
	
	
	//##################################
	//##########   FRANCE    ###########
	//##################################
	
	FRA_LIGUE_1_2017 			(	AppConstants.FRA_LIGUE_1_RESULTS,
									AppConstants.FRA_LIGUE_1_NEXT_MATCHES, 	
									new Integer[]{ 1,2,3,6,17 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS,
														RankCritEnum.GOALS_DIFFERENCE,
														RankCritEnum.GOALS_SCORED,
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE},
									"France",
									"Ligue 1",
									2017),
	
	FRA_LIGUE_2_2017 			(	AppConstants.FRA_LIGUE_2_RESULTS,
									AppConstants.FRA_LIGUE_2_NEXT_MATCHES, 	
									new Integer[]{ 2,3,6,17,18 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS,
											RankCritEnum.GOALS_DIFFERENCE,
											RankCritEnum.GOALS_SCORED,
											RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE },
									"France",
									"Ligue 2",
									2017),
	
	
	//##################################
	//##########   GERMANY   ###########
	//##################################
	GER_BUNDESLIGA_2017			(	AppConstants.GER_BUNDESLIGA_RESULTS,
									AppConstants.GER_BUNDESLIGA_NEXT_MATCHES, 
									new Integer[]{ 1,3,4,7,15,16 }, 	 
									new RankCritEnum[]{	RankCritEnum.POINTS,
														RankCritEnum.GOALS_DIFFERENCE,
														RankCritEnum.GOALS_SCORED,
														RankCritEnum.HEAD_TO_HEAD_POINTS,
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE,
														RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY,
														RankCritEnum.GOALS_SCORED_AWAY
														},
									"Germany",
									"Bundesliga",
									2017),
	
	GER_2_BUNDESLIGA_2017		(	AppConstants.GER_2_BUNDESLIGA_RESULTS,
									AppConstants.GER_2_BUNDESLIGA_NEXT_MATCHES, 
									new Integer[]{ 2,3,15 }, 	 
									new RankCritEnum[]{	RankCritEnum.POINTS,
														RankCritEnum.GOALS_DIFFERENCE,
														RankCritEnum.GOALS_SCORED
														},
									"Germany",
									"2 Bundesliga",
									2017),

	GER_3_LIGA_2017				(	AppConstants.GER_3_LIGA_RESULTS,
									AppConstants.GER_3_LIGA_NEXT_MATCHES, 
									new Integer[]{ 2,3,17 }, 	 
									new RankCritEnum[]{	RankCritEnum.POINTS,
														RankCritEnum.GOALS_DIFFERENCE,
														RankCritEnum.GOALS_SCORED
														},
									"Germany",
									"3 Liga",
									2017),
	

	
	//##################################
	//##########    GREEK    ###########
	//##################################
	GRE_SUPER_LEAGUE_2017		(	AppConstants.GRE_SUPER_LEAGUE_RESULTS,
									AppConstants.GRE_SUPER_LEAGUE_NEXT_MATCHES,
									new Integer[]{ 1,5,14 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED, 
														RankCritEnum.GOALS_TAKEN, },
									"Greece",
									"Super League",
									2017),

	GRE_FOOTBALL_LEAGUE_2017	(	AppConstants.GRE_FOOTBALL_LEAGUE_RESULTS,
									AppConstants.GRE_FOOTBALL_LEAGUE_NEXT_MATCHES,
									new Integer[]{ 2, 12 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED, 
														RankCritEnum.GOALS_TAKEN, },
									"Greece",
									"Football League",
									2017),
	
	
	
	//##################################
	//##########    ITALY    ###########
	//##################################
	
	ITA_SERIE_A_2017 			(	AppConstants.ITA_SERIE_A_RESULTS,
									AppConstants.ITA_SERIE_A_NEXT_MATCHES,
									new Integer[]{ 1,4,5,6,17 },   
										new RankCritEnum[]{	RankCritEnum.POINTS, 
													RankCritEnum.HEAD_TO_HEAD_POINTS, 
													RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
													RankCritEnum.GOALS_DIFFERENCE, 
													RankCritEnum.GOALS_SCORED,},
									"Italy",
									"Serie A",
									2017),
	
	ITA_SERIE_B_2017			(	AppConstants.ITA_SERIE_B_RESULTS,
									AppConstants.ITA_SERIE_B_NEXT_MATCHES,
									new Integer[]{ 2, 8, 17, 19 },   
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED,},
									"Italy",
									"Serie B",
									2017),

	
	
	ITA_SERIE_C_A_2017			(	AppConstants.ITA_SERIE_C_A_RESULTS,
									AppConstants.ITA_SERIE_C_A_NEXT_MATCHES,
									new Integer[]{1, 10, 15 },   
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED,},
									"Italy",
									"Serie C - A",
									2017),
	
	
	ITA_SERIE_C_B_2017			(	AppConstants.ITA_SERIE_C_B_RESULTS,
									AppConstants.ITA_SERIE_C_B_NEXT_MATCHES,
									new Integer[]{1, 10, 15 },   
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED,},
									"Italy",
									"Serie C - B",
									2017),

	
	ITA_SERIE_C_C_2017			(	AppConstants.ITA_SERIE_C_C_RESULTS,
									AppConstants.ITA_SERIE_C_C_NEXT_MATCHES,
									new Integer[]{1, 10, 15 },   
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED,},
									"Italy",
									"Serie C - C",
									2017),

	
	
	//##################################
	//########    NETHERLANDS   ########
	//##################################
	
	NED_EREDIVISIE_2017		(		AppConstants.NED_EREDIVISIE_RESULTS,
									AppConstants.NED_EREDIVISIE_NEXT_MATCHES, 	
									new Integer[]{ 1,2,3,7,15,17 },	 
									new RankCritEnum[]{	RankCritEnum.POINTS,
														RankCritEnum.GOALS_DIFFERENCE,
														RankCritEnum.GOALS_SCORED,
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE,
														RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY},
									"Netherlands",
									"Eredivisie",
									2017),
		
	NED_EERSTE_DIVISIE_2017		(	AppConstants.NED_EERSTE_DIVISIE_RESULTS,
									AppConstants.NED_EERSTE_DIVISIE_NEXT_MATCHES, 	
									new Integer[]{ 1 },	 
									new RankCritEnum[]{	RankCritEnum.POINTS
													},
									"Netherlands",
									"Eereste divisie",
									2017),
	
	
	//##################################
	//##########   PORTUGAL   ##########
	//##################################
	
	POR_PRIMERA_LIGA_2017		(	AppConstants.POR_PRIMERA_LIGA_RESULTS,
									AppConstants.POR_PRIMERA_LIGA_NEXT_MATCHES, 	
									new Integer[]{ 1,2,3,4,5,16 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY,
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.MATCHES_WON, 
														RankCritEnum.GOALS_SCORED},
									"Portugal",
									"Primeira Liga",
									2017),

	POR_SEGUNDA_LIGA_2017		(	AppConstants.POR_SEGUNDA_LIGA_RESULTS,
									AppConstants.POR_SEGUNDA_LIGA_NEXT_MATCHES, 	
									new Integer[]{ 2, 16, 18 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY,
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.MATCHES_WON, 
														RankCritEnum.GOALS_SCORED},
									"Portugal",
									"Segunda Liga",
									2017),
	
	
	
	//##################################
	//##########   SCOTLAND   ##########
	//##################################

	SCO_PREMIERSHIP_2017		(	AppConstants.SCO_PREMIERSHIP_RESULTS,
									AppConstants.SCO_PREMIERSHIP_NEXT_MATCHES, 	
									new Integer[]{ 1,4,10,11 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"Scotland",
									"Premiership",
									2017),
	
	SCO_CHAMPIONSHIP_2017		(	AppConstants.SCO_CHAMPIONSHIP_RESULTS,
									AppConstants.SCO_CHAMPIONSHIP_NEXT_MATCHES, 	
									new Integer[]{ 1,4,8,9 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"Scotland",
									"Championship",
									2017),
	
	SCO_LEAGUE_ONE_2017			(	AppConstants.SCO_LEAGUE_ONE_RESULTS,
									AppConstants.SCO_LEAGUE_ONE_NEXT_MATCHES, 	
									new Integer[]{ 1,4,8,9 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"Scotland",
									"League One",
									2017),
	
	SCO_LEAGUE_TWO_2017			(	AppConstants.SCO_LEAGUE_TWO_RESULTS,
									AppConstants.SCO_LEAGUE_TWO_NEXT_MATCHES, 	
									new Integer[]{ 1,4,9 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"Scotland",
									"League Two",
									2017),

	
	//##################################
	//##########    SPAIN    ###########
	//##################################
		
		
	SPA_LA_LIGA_2017 			(	AppConstants.SPA_LA_LIGA_RESULTS,
									AppConstants.SPA_LA_LIGA_NEXT_MATCHES, 	
									new Integer[]{ 1,3,4,7,17 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED, },
									"Spain",
									"La Liga",
									2017),	
	
	
	
	SPA_LA_LIGA_2_2017 			(	AppConstants.SPA_LA_LIGA_2_RESULTS,
									AppConstants.SPA_LA_LIGA_2_NEXT_MATCHES, 	
									new Integer[]{ 2,6,18 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED },
									"Spain",
									"La Liga 2",
									2017),

	


	
	

	
	//##################################
	//###########   TURKEY   ###########
	//##################################

	TUR_SUPER_LIG_2017			(	AppConstants.TUR_SUPER_LIG_RESULTS,
									AppConstants.TUR_SUPER_LIG_NEXT_MATCHES,
									new Integer[]{ 1,2,4,15 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"Turkey",
									"Super Lig",
									2017),
	
	TUR_TFF_1_LIG_2017			(	AppConstants.TUR_TFF_1_LIG_RESULTS,
									AppConstants.TUR_TFF_1_LIG_NEXT_MATCHES,
									new Integer[]{ 2, 6, 15 }, 
									new RankCritEnum[]{	RankCritEnum.POINTS, 
														RankCritEnum.HEAD_TO_HEAD_POINTS, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
														RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY, 
														RankCritEnum.GOALS_DIFFERENCE, 
														RankCritEnum.GOALS_SCORED},
									"Turkey",
									"TFF 1 Lig",
									2017),
	
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

	
	
//	SPA_SEGUNDA_DIVISION_B_1_2017 	(AppConstants.SPA_SEGUNDA_DIVISION_B_1_RESULTS,
//	AppConstants.SPA_SEGUNDA_DIVISION_B_1_NEXT_MATCHES, 	
//	new Integer[]{ 2,6,18 }, 
//	new RankCritEnum[]{	RankCritEnum.POINTS, 
//						RankCritEnum.HEAD_TO_HEAD_POINTS, 
//						RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//						RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY,
//						RankCritEnum.GOALS_DIFFERENCE, 
//						RankCritEnum.MATCHES_WON,
//						RankCritEnum.GOALS_SCORED },
//	"Spain",
//	"Segunda Division B - 1",
//	2017),
//
//SPA_SEGUNDA_DIVISION_B_2_2017 	(AppConstants.SPA_SEGUNDA_DIVISION_B_2_RESULTS,
//	AppConstants.SPA_SEGUNDA_DIVISION_B_2_NEXT_MATCHES, 	
//	new Integer[]{ 2,6,18 }, 
//	new RankCritEnum[]{	RankCritEnum.POINTS, 
//						RankCritEnum.HEAD_TO_HEAD_POINTS, 
//						RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//						RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY,
//						RankCritEnum.GOALS_DIFFERENCE, 
//						RankCritEnum.MATCHES_WON,
//						RankCritEnum.GOALS_SCORED },
//	"Spain",
//	"Segunda Division B - 2",
//	2017),
//
//SPA_SEGUNDA_DIVISION_B_3_2017 	(AppConstants.SPA_SEGUNDA_DIVISION_B_3_RESULTS,
//	AppConstants.SPA_SEGUNDA_DIVISION_B_3_NEXT_MATCHES, 	
//	new Integer[]{ 2,6,18 }, 
//	new RankCritEnum[]{	RankCritEnum.POINTS, 
//						RankCritEnum.HEAD_TO_HEAD_POINTS, 
//						RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//						RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY,
//						RankCritEnum.GOALS_DIFFERENCE, 
//						RankCritEnum.MATCHES_WON,
//						RankCritEnum.GOALS_SCORED },
//	"Spain",
//	"Segunda Division B - 3",
//	2017),
//
//SPA_SEGUNDA_DIVISION_B_4_2017 	(AppConstants.SPA_SEGUNDA_DIVISION_B_4_RESULTS,
//	AppConstants.SPA_SEGUNDA_DIVISION_B_4_NEXT_MATCHES, 	
//	new Integer[]{ 2,6,18 }, 
//	new RankCritEnum[]{	RankCritEnum.POINTS, 
//						RankCritEnum.HEAD_TO_HEAD_POINTS, 
//						RankCritEnum.HEAD_TO_HEAD_GOALS_DIFFERENCE, 
//						RankCritEnum.HEAD_TO_HEAD_GOALS_SCORED_AWAY,
//						RankCritEnum.GOALS_DIFFERENCE, 
//						RankCritEnum.MATCHES_WON,
//						RankCritEnum.GOALS_SCORED },
//	"Spain",
//	"Segunda Division B - 4",
//	2017),
	
}


