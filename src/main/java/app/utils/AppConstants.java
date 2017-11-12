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
	
	
	public static boolean ENABLE_ODD_IMPROVEMENTS_ALGHORITM = true;		//abiliata la ricerca nei range vicini, limita la goodness se esistono pochi precedenti. DISABILITA PER TEST
	public static boolean PERCENTIFY_ODDS_ON = false;					//elimina la percentuale che guadagna la casa su ogni scommessa, cosi' la somma delle odds di ogni scommessa fa 100% e non 103 o 104 o 110&.  DISABILITA PER TEST
	public static boolean USE_OTHER_PLAYING_FIELD = true;					
	
	
	public static String SITE_URL 								= "http://www.oddsportal.com";

	//http://www.oddsportal.com/soccer/england/premier-league/
	
	//Belgium
	public static String BEL_PRO_LEAGUE_RESULTS = 				SITE_URL + "/soccer/belgium/jupiler-league/results";
	public static String BEL_PRO_LEAGUE_NEXT_MATCHES = 			SITE_URL + "/soccer/belgium/jupiler-league";
	
	
	
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
	
	
	
	//Greece
	public static String GRE_SUPER_LEAGUE_RESULTS = 			SITE_URL + "/soccer/greece/super-league/results";
	public static String GRE_SUPER_LEAGUE_NEXT_MATCHES = 		SITE_URL + "/soccer/greece/super-league";
	
	
	
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
	
	
	
	//Portugal
	public static String POR_PRIMERA_LIGA_RESULTS = 			SITE_URL + "/soccer/portugal/primeira-liga/results";
	public static String POR_PRIMERA_LIGA_NEXT_MATCHES = 		SITE_URL + "/soccer/portugal/primeira-liga";
	
	
	
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
	
	
	
	
	//Turkey
	public static String TUR_SUPER_LIG_RESULTS = 				SITE_URL + "/soccer/turkey/super-lig/results";
	public static String TUR_SUPER_LIG_NEXT_MATCHES = 			SITE_URL + "/soccer/turkey/super-lig";
	
	
}
