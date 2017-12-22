package app.utils;

public class AppConstants {
	
	
	public static final int DAYS_FAR = 6;
	
	public static final int DAYS_FAR_BET_TO = 7;
	public static final int DAYS_FAR_BET_FROM = 0;
	
	public static final int BET_AMOUNT = 5;
	public static final int TREND_SIZE_UO = 10;
	public static final int TREND_SIZE_WIN = 5;
	public static final boolean ENABLE_DOWNLOAD_ONLY_NEAR_MATCHES = true;	//Scarica o calcola la bet solo i match entro i giorni definiti sopra ???

	public static final boolean DO_NOT_UPDATE_NEXT_MATCH = true;

	public static boolean PRINT_FASE = false;


	
	
	public static boolean ENABLE_ODD_IMPROVEMENTS_ALGHORITM = true;		//abiliata la ricerca nei range vicini, limita la goodness se esistono pochi precedenti. DISABILITA PER TEST
	public static boolean PERCENTIFY_ODDS_ON = false;					//elimina la percentuale che guadagna la casa su ogni scommessa, cosi' la somma delle odds di ogni scommessa fa 100% e non 103 o 104 o 110&.  DISABILITA PER TEST
	public static boolean USE_OTHER_PLAYING_FIELD = true;					
	
	
	public static String SITE_URL 								= "http://www.oddsportal.com";

	//http://www.oddsportal.com/soccer/england/premier-league/
	
	//Armenia
	public static String ARM_PREMIER_LEAGUE_RESULTS = 			SITE_URL + "/soccer/armenia/premier-league/results";
	public static String ARM_PREMIER_LEAGUE_NEXT_MATCHES = 		SITE_URL + "/soccer/armenia/premier-league";
	

	//Austria
	public static String AUT_TIPICO_BUNDESLIGA_RESULTS = 		SITE_URL + "/soccer/austria/tipico-bundesliga/results";
	public static String AUT_TIPICO_BUNDESLIGA_NEXT_MATCHES = 	SITE_URL + "/soccer/austria/tipico-bundesliga";
	
	public static String AUT_ERSTE_LIGA_RESULTS = 				SITE_URL + "/soccer/austria/erste-liga/results";
	public static String AUT_ERSTE_LIGA_NEXT_MATCHES = 			SITE_URL + "/soccer/austria/erste-liga";
	
	
	//Azerbaijan
	public static String AZE_PREMIER_LEAGUE_RESULTS = 			SITE_URL + "/soccer/azerbaijan/premier-league/results";
	public static String AZE_PREMIER_LEAGUE_NEXT_MATCHES = 		SITE_URL + "/soccer/azerbaijan/premier-league";

	
	
	//Belgium
	public static String BEL_JUPILER_LEAGUE_RESULTS = 			SITE_URL + "/soccer/belgium/jupiler-league/results";
	public static String BEL_JUPILER_LEAGUE_NEXT_MATCHES = 		SITE_URL + "/soccer/belgium/jupiler-league";
	
	public static String BEL_PROXIMUS_LEAGUE_RESULTS = 			SITE_URL + "/soccer/belgium/proximus-league/results";
	public static String BEL_PROXIMUS_LEAGUE_NEXT_MATCHES = 	SITE_URL + "/soccer/belgium/proximus-league";

	//Bosnia
	public static String BOS_PREMIER_LEAGUE_RESULTS = 			SITE_URL + "/soccer/bosnia-and-herzegovina/premier-league/results";
	public static String BOS_PREMIER_LEAGUE_NEXT_MATCHES = 		SITE_URL + "/soccer/bosnia-and-herzegovina/premier-league";
	
	
	
	//Bulgaria
	public static String BUL_PARVA_LIGA_RESULTS = 				SITE_URL + "/soccer/bulgaria/parva-liga/results";
	public static String BUL_PARVA_LIGA_NEXT_MATCHES = 			SITE_URL + "/soccer/bulgaria/parva-liga";
	
	
	//Croatia
	public static String CRO_1_HNL_RESULTS =					SITE_URL + "/soccer/croatia/1-hnl/results";
	public static String CRO_1_HNL_NEXT_MATCHES = 				SITE_URL + "/soccer/croatia/1-hnl";
	
	public static String CRO_2_HNL_RESULTS =					SITE_URL + "/soccer/croatia/2-hnl/results";
	public static String CRO_2_HNL_NEXT_MATCHES = 				SITE_URL + "/soccer/croatia/2-hnl";
	

	
	//Cyprus
	public static String CYP_FIRST_DIVISION_RESULTS =			SITE_URL + "/soccer/cyprus/first-division/results";
	public static String CYP_FIRST_DIVISION_NEXT_MATCHES = 		SITE_URL + "/soccer/cyprus/first-division";

	
	//Denmark
	public static String DEN_SUPERLIGA_RESULTS =				SITE_URL + "/soccer/denmark/superliga/results";
	public static String DEN_SUPERLIGA_NEXT_MATCHES = 			SITE_URL + "/soccer/denmark/superliga";

	public static String DEN_1ST_DIVISION_RESULTS =				SITE_URL + "/soccer/denmark/1st-division/results";
	public static String DEN_1ST_DIVISION_NEXT_MATCHES = 		SITE_URL + "/soccer/denmark/1st-division";
	
	
	
	// England
	public static String ENG_PREMIER_RESULTS = 					SITE_URL + "/soccer/england/premier-league/results";
	public static String ENG_PREMIER_NEXT_MATCHES = 			SITE_URL + "/soccer/england/premier-league";
	
	public static String ENG_CHAMPIONSHIP_RESULTS = 			SITE_URL + "/soccer/england/championship/results";
	public static String ENG_CHAMPIONSHIP_NEXT_MATCHES = 		SITE_URL + "/soccer/england/championship";
	
	public static String ENG_LEAGUE_ONE_RESULTS = 				SITE_URL + "/soccer/england/league-one/results";
	public static String ENG_LEAGUE_ONE_NEXT_MATCHES = 			SITE_URL + "/soccer/england/league-one";
	
	public static String ENG_LEAGUE_TWO_RESULTS = 				SITE_URL + "/soccer/england/league-two/results";
	public static String ENG_LEAGUE_TWO_NEXT_MATCHES = 			SITE_URL + "/soccer/england/league-two";
	

	
	//France
	public static String FRA_LIGUE_1_RESULTS  = 				SITE_URL + "/soccer/france/ligue-1/results";
	public static String FRA_LIGUE_1_NEXT_MATCHES = 			SITE_URL + "/soccer/france/ligue-1";
	
	public static String FRA_LIGUE_2_RESULTS  = 				SITE_URL + "/soccer/france/ligue-2/results";
	public static String FRA_LIGUE_2_NEXT_MATCHES = 			SITE_URL + "/soccer/france/ligue-2";
	
	
	// Germany
	public static String GER_BUNDESLIGA_RESULTS = 				SITE_URL + "/soccer/germany/bundesliga/results";
	public static String GER_BUNDESLIGA_NEXT_MATCHES = 			SITE_URL + "/soccer/germany/bundesliga";
	
	public static String GER_2_BUNDESLIGA_RESULTS = 			SITE_URL + "/soccer/germany/2-bundesliga/results";
	public static String GER_2_BUNDESLIGA_NEXT_MATCHES = 		SITE_URL + "/soccer/germany/2-bundesliga";
	
	public static String GER_3_LIGA_RESULTS = 					SITE_URL + "/soccer/germany/3-liga/results";
	public static String GER_3_LIGA_NEXT_MATCHES = 				SITE_URL + "/soccer/germany/3-liga";
	
	
	//Greece
	public static String GRE_SUPER_LEAGUE_RESULTS = 			SITE_URL + "/soccer/greece/super-league/results";
	public static String GRE_SUPER_LEAGUE_NEXT_MATCHES = 		SITE_URL + "/soccer/greece/super-league";

	public static String GRE_FOOTBALL_LEAGUE_RESULTS = 			SITE_URL + "/soccer/greece/football-league/results";
	public static String GRE_FOOTBALL_LEAGUE_NEXT_MATCHES = 	SITE_URL + "/soccer/greece/football-league";
	
	
	
	//Italy
	public static String ITA_SERIE_A_RESULTS = 					SITE_URL + "/soccer/italy/serie-a/results";
	public static String ITA_SERIE_A_NEXT_MATCHES = 			SITE_URL + "/soccer/italy/serie-a";
	
	public static String ITA_SERIE_B_RESULTS = 					SITE_URL + "/soccer/italy/serie-b/results";
	public static String ITA_SERIE_B_NEXT_MATCHES = 			SITE_URL + "/soccer/italy/serie-b";
	
	public static String ITA_SERIE_C_A_RESULTS = 				SITE_URL + "/soccer/italy/serie-c-group-a/results";
	public static String ITA_SERIE_C_A_NEXT_MATCHES = 			SITE_URL + "/soccer/italy/serie-c-group-a";
	
	public static String ITA_SERIE_C_B_RESULTS = 				SITE_URL + "/soccer/italy/serie-c-group-b/results";
	public static String ITA_SERIE_C_B_NEXT_MATCHES = 			SITE_URL + "/soccer/italy/serie-c-group-b";
	
	public static String ITA_SERIE_C_C_RESULTS = 				SITE_URL + "/soccer/italy/serie-c-group-c/results";
	public static String ITA_SERIE_C_C_NEXT_MATCHES = 			SITE_URL + "/soccer/italy/serie-c-group-c";
	
	
	//Netherlands
	public static String NED_EREDIVISIE_RESULTS = 				SITE_URL + "/soccer/netherlands/eredivisie/results";
	public static String NED_EREDIVISIE_NEXT_MATCHES = 			SITE_URL + "/soccer/netherlands/eredivisie";

	public static String NED_EERSTE_DIVISIE_RESULTS = 			SITE_URL + "/soccer/netherlands/eerste-divisie/results";
	public static String NED_EERSTE_DIVISIE_NEXT_MATCHES = 		SITE_URL + "/soccer/netherlands/eerste-divisie";
	
	
	
	//Portugal
	public static String POR_PRIMERA_LIGA_RESULTS = 			SITE_URL + "/soccer/portugal/primeira-liga/results";
	public static String POR_PRIMERA_LIGA_NEXT_MATCHES = 		SITE_URL + "/soccer/portugal/primeira-liga";
	
	public static String POR_SEGUNDA_LIGA_RESULTS = 			SITE_URL + "/soccer/portugal/segunda-liga/results";
	public static String POR_SEGUNDA_LIGA_NEXT_MATCHES = 		SITE_URL + "/soccer/portugal/segunda-liga";
	
	
	
	//Scotland
	public static String SCO_PREMIERSHIP_RESULTS = 				SITE_URL + "/soccer/scotland/premiership/results";
	public static String SCO_PREMIERSHIP_NEXT_MATCHES = 		SITE_URL + "/soccer/scotland/premiership";
	
	public static String SCO_CHAMPIONSHIP_RESULTS = 			SITE_URL + "/soccer/scotland/championship/results";
	public static String SCO_CHAMPIONSHIP_NEXT_MATCHES = 		SITE_URL + "/soccer/scotland/championship";
	
	public static String SCO_LEAGUE_ONE_RESULTS = 				SITE_URL + "/soccer/scotland/league-one/results";
	public static String SCO_LEAGUE_ONE_NEXT_MATCHES = 			SITE_URL + "/soccer/scotland/league-one";
	
	public static String SCO_LEAGUE_TWO_RESULTS = 				SITE_URL + "/soccer/scotland/league-two/results";
	public static String SCO_LEAGUE_TWO_NEXT_MATCHES = 			SITE_URL + "/soccer/scotland/league-two";
	
	
	
	//Spain
	public static String SPA_LA_LIGA_RESULTS  = 				SITE_URL + "/soccer/spain/laliga/results";
	public static String SPA_LA_LIGA_NEXT_MATCHES = 			SITE_URL + "/soccer/spain/laliga";
	
	public static String SPA_LA_LIGA_2_RESULTS  = 				SITE_URL + "/soccer/spain/laliga2/results";
	public static String SPA_LA_LIGA_2_NEXT_MATCHES = 			SITE_URL + "/soccer/spain/laliga2";
	
	public static String SPA_SEGUNDA_DIVISION_B_1_RESULTS  = 		SITE_URL + "/soccer/spain/segunda-division-b-group-1/results";
	public static String SPA_SEGUNDA_DIVISION_B_1_NEXT_MATCHES = 	SITE_URL + "/soccer/spain/segunda-division-b-group-1";
	
	public static String SPA_SEGUNDA_DIVISION_B_2_RESULTS  = 		SITE_URL + "/soccer/spain/segunda-division-b-group-2/results";
	public static String SPA_SEGUNDA_DIVISION_B_2_NEXT_MATCHES = 	SITE_URL + "/soccer/spain/segunda-division-b-group-2";
	
	public static String SPA_SEGUNDA_DIVISION_B_3_RESULTS  = 		SITE_URL + "/soccer/spain/segunda-division-b-group-3/results";
	public static String SPA_SEGUNDA_DIVISION_B_3_NEXT_MATCHES = 	SITE_URL + "/soccer/spain/segunda-division-b-group-3";
	
	public static String SPA_SEGUNDA_DIVISION_B_4_RESULTS  = 		SITE_URL + "/soccer/spain/segunda-division-b-group-4/results";
	public static String SPA_SEGUNDA_DIVISION_B_4_NEXT_MATCHES = 	SITE_URL + "/soccer/spain/segunda-division-b-group-4";
	
	
	
	//Turkey
	public static String TUR_SUPER_LIG_RESULTS = 				SITE_URL + "/soccer/turkey/super-lig/results";
	public static String TUR_SUPER_LIG_NEXT_MATCHES = 			SITE_URL + "/soccer/turkey/super-lig";
	
	public static String TUR_TFF_1_LIG_RESULTS = 				SITE_URL + "/soccer/turkey/tff-1-lig/results";
	public static String TUR_TFF_1_LIG_NEXT_MATCHES = 			SITE_URL + "/soccer/turkey/tff-1-lig";
	
	
	
	
}
