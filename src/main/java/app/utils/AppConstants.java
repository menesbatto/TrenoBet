package app.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
	
	
//	public static boolean PROXY_ACTIVE = true;
//	public static String PROXY_HOST = "10.0.1.251";
//	public static String PROXY_PORT = "3128";
//	
	

	
	//public static String SEASON_DAY_LINES_UP_URL_TEMPLATE = "http://leghe.fantagazzetta.com/" + LEAGUE_NAME + "/formazioni/[COMPETITION_ID]?g=";
	
	
//	public static String PREMIER_LEAGUE_RESULTS_1 = "http://www.oddsportal.com/soccer/england/premier-league/results/#/page/[PAGE]/";
//	public static String PREMIER_LEAGUE_ODDS = "http://www.oddsportal.com/soccer/england/premier-league/";
//	public static String SERIE_A_ODDS = "http://www.statto.com/football/odds/italy/serie-a";
	
	public static String SITE_URL 								= "http://www.oddsportal.com";

	//http://www.oddsportal.com/soccer/england/premier-league/
	
	
	
	
	
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
	
	
	
	//Italy
	public static String ITA_SERIE_A_RESULTS = 					SITE_URL + "/soccer/italy/serie-a/results";
	public static String ITA_SERIE_A_NEXT_MATCHES = 			SITE_URL + "/soccer/italy/serie-a";
	
	public static String ITA_SERIE_B_RESULTS = 					SITE_URL + "/soccer/italy/serie-b/results";
	public static String ITA_SERIE_B_NEXT_MATCHES = 			SITE_URL + "/soccer/italy/serie-b";
	
	
	//Netherlands
	public static String NED_EREDIVISIE_RESULTS = 				SITE_URL + "/soccer/netherlands/eredivisie/results";
	public static String NED_EREDIVISIE_NEXT_MATCHES = 		SITE_URL + "/soccer/netherlands/eredivisie";
	
	
	
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
	
	
	//########################################################################################################################
	//########################################################################################################################
	//########################################################################################################################
	
	
	


	public static String BEL_PRO_LEAGUE_RESULTS = 				SITE_URL + "/soccer/belgium/jupiler-league/results";
	public static String BEL_PRO_LEAGUE_NEXT_MATCHES = 			SITE_URL + "/soccer/belgium/jupiler-league";
	
	public static String TUR_SUPER_LIG_RESULTS = 				SITE_URL + "/soccer/turkey/super-lig/results";
	public static String TUR_SUPER_LIG_NEXT_MATCHES = 			SITE_URL + "/soccer/turkey/super-lig";

	
	public static String GRE_SOUPER_LIGKA_ELLADA_RESULTS = 		SITE_URL + "/soccer/greece/super-league/results";
	public static String GRE_SOUPER_LIGKA_ELLADA_NEXT_MATCHES = SITE_URL + "/soccer/greece/super-league";
	

	
	
	// Percentuale 10
	public static List<Double> RANGE_EDGES = Arrays.asList(1.0, 1.25, 1.43, 1.66, 2.0, 2.5, 3.3, 5.0, 10.0, 100.0);
	
	// Percentuale 5
//	public static List<Double> RANGE_EDGES = Arrays.asList(1.0, 1.33, 1.54, 1.82, 2.22, 2.85, 4.0, 6.6, 15.0, 100.0);

	// A senzazioni 1
//	public static List<Double> RANGE_EDGES = Arrays.asList(1.0, 1.35, 1.8, 2.1, 2.4, 2.8, 3.2, 3.8, 5.0, 10.0, 100.0);
	// A sensazioni 2
//	public static List<Double> RANGE_EDGES = Arrays.asList(1.0, 1.31, 1.65, 1.9, 2.2, 2.5, 3.2, 4.5,  10.0, 100.0);
	
	public static String ABSOLUT_PATH = "C:\\trenobet\\";

	public static String HISTORY_MATCHES_RESULTS_PATH = ABSOLUT_PATH + "matchesResults\\";
	
	
	public static String ITA_SERIE_A_HISTORY_RESULTS = 			HISTORY_MATCHES_RESULTS_PATH + "I1.csv";
	public static String ENG_PREMIER_HISTORY_RESULTS = 			HISTORY_MATCHES_RESULTS_PATH + "E0.csv";
	public static String ENG_CHAMPIONSHIP_HISTORY_RESULTS = 	HISTORY_MATCHES_RESULTS_PATH + "E1.csv";
	public static String FRA_LIGUE_1_HISTORY_RESULTS = 			HISTORY_MATCHES_RESULTS_PATH + "F1.csv";
	public static String GER_BUNDESLIGA_HISTORY_RESULTS = 		HISTORY_MATCHES_RESULTS_PATH + "D1.csv";
	public static String SPA_LIGA_HISTORY_RESULTS = 			HISTORY_MATCHES_RESULTS_PATH + "SP1.csv";
	public static String OLA_ERE_DIVISIE_HISTORY_RESULTS = 		HISTORY_MATCHES_RESULTS_PATH + "N1.csv";
	
	public static String BEL_PRO_LEAGUE_HISTORY_RESULTS = 		HISTORY_MATCHES_RESULTS_PATH + "B1.csv";
	public static String TUR_SUPER_LIG_HISTORY_RESULTS = 		HISTORY_MATCHES_RESULTS_PATH + "T1.csv";
	public static String GRE_SOUPER_LIGKA_ELLADA_HISTORY_RESULTS = 		HISTORY_MATCHES_RESULTS_PATH + "G1.csv";
	public static String POR_PRIMERA_LIGA_HISTORY_RESULTS = 	HISTORY_MATCHES_RESULTS_PATH + "P1.csv";
	
	public static String ENG_LEAGUE_ONE_HISTORY_RESULTS = 		HISTORY_MATCHES_RESULTS_PATH + "E2.csv";
	public static String ENG_LEAGUE_TWO_HISTORY_RESULTS = 		HISTORY_MATCHES_RESULTS_PATH + "E3.csv";
	public static String SCO_PREMIERSHIP_HISTORY_RESULTS = 		HISTORY_MATCHES_RESULTS_PATH + "SC0.csv";
	public static String SCO_CHAMPIONSHIP_HISTORY_RESULTS = 	HISTORY_MATCHES_RESULTS_PATH + "SC1.csv";
	public static String SCO_LEAGUE_ONE_HISTORY_RESULTS = 		HISTORY_MATCHES_RESULTS_PATH + "SC2.csv";
	public static String SCO_LEAGUE_TWO_HISTORY_RESULTS = 		HISTORY_MATCHES_RESULTS_PATH + "SC3.csv";
	
	
	
	
	public static String MATCHES_RESULTS_PATH = AppConstants.ABSOLUT_PATH + "matches";
	public static String MATCHES_RESULTS_PATH_2 = AppConstants.ABSOLUT_PATH + "a\\matches";
	public static String TEAMS_PATH = AppConstants.ABSOLUT_PATH + "teams";
	public static String EVENTS_ODDS_PATH = AppConstants.ABSOLUT_PATH + "eventsOdds";
	public static String MATCHES_ODDS_WITH_GOODNESS_PATH = AppConstants.ABSOLUT_PATH + "matchesOddsWithGoodness";
	public static String MAIN_BET_PATH = AppConstants.ABSOLUT_PATH + "mainBet";
	
	public static String TEAM_TO_RANGE_STATS_WIN_HOME_PATH = AppConstants.ABSOLUT_PATH + "teamToRangeWinStatsHome";
	public static String TEAM_TO_RANGE_STATS_WIN_AWAY_PATH = AppConstants.ABSOLUT_PATH + "teamToRangeWinStatsAway";
	public static String TEAM_TO_RANGE_STATS_WIN_ALL_PATH = AppConstants.ABSOLUT_PATH + "teamToRangeWinStatsAll";
	
	public static String TEAM_TO_RANGE_STATS_UO_HOME_PATH = AppConstants.ABSOLUT_PATH + "teamToRangeUoStatsHome";
	public static String TEAM_TO_RANGE_STATS_UO_AWAY_PATH = AppConstants.ABSOLUT_PATH + "teamToRangeUoStatsAway";
	
	public static String TEAM_TO_MATCHES_ALL_PATH = AppConstants.ABSOLUT_PATH + "teamToMatchesAll";

	public static String RANKINGS = AppConstants.ABSOLUT_PATH + "rankings";
	public static String TRENDS = AppConstants.ABSOLUT_PATH + "trends";
	public static String MOTIVATIONAL_INDEXES = AppConstants.ABSOLUT_PATH + "motivationalIndex";
	
//	1.0, 	1.3, 	1.6, 	1.8,	2.0, 	2.3, 	2.7, 	3.0, 	3.5, 	4.0, 	5.0, 	7.0, 	10.0,	100.0
//			76		62,5	55,5	50		43,5	37,1	33,3	28,57	25		20		14,3	10		1
//
//	1,25	1,43	1,66	2	2,5		3,3		5	10
//	80		70		60		50	40		30		20	10
	
	//http://www.paddypower.it/scommesse-calcio/partite/giornata-serie-a/Sassuolo%2d%2d%2dLazio-1634577.html
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public static boolean DEBUG_MODE = false;
//	public static boolean FAST_MODE_ACTIVE = false;
//	public static Boolean FORCE_INVERT_HOME_AWAY = false;
//	public static Boolean FORCE_WINNING_FOR_DISTANCE = false;
//	
//	public static Boolean FORCE_GOALKEEPER_MODIFIER_DISABLED = false;
//	public static Boolean FORCE_MIDDLEFIELD_MODIFIER_DISABLED = false;
//	public static Boolean FORCE_DEFENDER_MODIFIER_DISABLED = false;
//	public static Boolean FORCE_STRIKER_MODIFIER_DISABLED = false;
//	
//	
//	
//	public static List<Double> GOAL_POINTS = Arrays.asList(66.0, 72.0, 77.0, 81.0, 85.0, 89.0, 93.0, 97.0, 101.0, 105.0, 109.0, 113.0, 117.0);
//	public static List<Double> FORMULA_UNO_POINTS = Arrays.asList(10.0, 8.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0);
//	
//	@Deprecated
//	public static Set<Integer> SERIE_A_SEASON_DAYS_TO_WAIT =  new HashSet();
//	@Deprecated
//	public static List<Integer> SEASON_DAYS_TO_WAIT =  Arrays.asList(16);
//	@Deprecated
//	public static Double movementsPlayerOfficeVote = 4.0;
//	@Deprecated
//	public static Double goalkeeperPlayerOfficeVote = 3.0;
//	@Deprecated
//	public static Integer SERIE_A_ACTUAL_SEASON_DAY =  22;
//	@Deprecated
//	public static Integer FANTACALCIO_STARTING_SERIE_A_SEASON_DAY = 3;
//	public static Integer FANTACALCIO_STARTING_SERIE_A_SEASON_DAY = 5;
//	
//	public static String LEAGUE_NAME = "mefaipvopviopenaleague";
//	public static String COMPETITION_NAME = "ME FAI PVOPVIO PENA LEAGUE";
//	public static String LEAGUE_DIR = "ale\\";
//	public static String user = "Ale.Dima.9";
//	public static String password = "9Vucinic";
//	
//	
//	public static String LEAGUE_NAME = "hppomezialeague";
//	public static String COMPETITION_NAME = "CAMPIONATOSEI";
//	public static String LEAGUE_DIR = "hppomezialeague\\";
//	public static String user = "nick23asr";
//	public static String password = "asroma23";
//	
//	
//	public static String LEAGUE_NAME = "accaniti-division";
//	public static String COMPETITION_NAME = "SERIE ACCANITI";
//	public static String LEAGUE_DIR = "accanitidivision\\";
//	public static String user = "menesbatto";
//	public static String password = "suppaman";
//
//	
//	
//	public static String RULES_1_BONUS_MALUS_URL = "http://leghe.fantagazzetta.com/" + LEAGUE_NAME + "/visualizza-opzioni-calcolo1";
//	public static String RULES_2_SOURCE_URL = "http://leghe.fantagazzetta.com/" + LEAGUE_NAME + "/visualizza-opzioni-calcolo2";
//	public static String RULES_3_SUBSTITUTIONS_URL = "http://leghe.fantagazzetta.com/" + LEAGUE_NAME + "/visualizza-opzioni-calcolo3";
//	public static String RULES_4_POINTS_URL = "http://leghe.fantagazzetta.com/" + LEAGUE_NAME + "/visualizza-opzioni-calcolo4";
//	public static String RULES_5_MODIFIERS_URL = "http://leghe.fantagazzetta.com/" + LEAGUE_NAME + "/visualizza-opzioni-calcolo5";
//	
//	public static String SEASON_DAY_LINES_UP_URL_TEMPLATE = "http://leghe.fantagazzetta.com/" + LEAGUE_NAME + "/formazioni/[COMPETITION_ID]?g=";
//	
//	public static String SEASON_DAY_ALL_VOTES_URL = "http://www.fantagazzetta.com/voti-serie-a/2016-17";
//	public static String SEASON_DAY_VOTES_URL_TEMPLATE = "https://www.fantagazzetta.com/Servizi/Voti.ashx?s=2016-17&g=[SEASON_DAY]&tv=[DATE_TIME_MILLIS]&t=[GAZZETTA_TEAM_ID]";
//	public static String TEAMS_IDS_URL = "https://www.fantagazzetta.com/voti-serie-a/2016-17/1/";
//	public static String CALENDAR_URL_TEMPLATE = "http://leghe.fantagazzetta.com/" + LEAGUE_NAME + "/calendario/[COMPETITION_ID]";
//	
//	
//	
//	"http://www.fantagazzetta.com/Servizi/Voti.ashx?s=2016-17&g=14&tv=222851700781&t=[GAZZETTA_TEAM_ID]";
//	"http://www.fantagazzetta.com/Servizi/Voti.ashx?s=2016-17&g=14&tv=224080328220&t=12"
//	
//	
//	
//	
//	
//
//
//	@Deprecated
//	public static String CALENDAR_DIR = ABSOLUT_PATH + LEAGUE_DIR + "Calendar\\";
//	@Deprecated
//	public static String CALENDAR_FILE_NAME = "Calendario.xlsx";
//	
//	public static String SEASON_REASULTS_DIR = ABSOLUT_PATH + LEAGUE_DIR + "Calendar\\results\\";
//	public static String SEASON_REASULTS_FILE_NAME = "Pattern";
//	
//	
//	public static String PLAYERS_DIR = ABSOLUT_PATH + LEAGUE_DIR + "Players\\";
//	public static String PLAYERS_FILE_NAME = "players";
//	
//	
//	//Estrazione dei valori delle giornate del campionato reale
//	@Deprecated
//	public static String SEASON_DAY_DIR = ABSOLUT_PATH + LEAGUE_DIR + "RealChampionshipSeasonDays\\";
//	@Deprecated
//	public static String SEASON_DAY_PATTERN_FILE_NAME = "Formazioni-XXX.xlsx";
//	
//	public static String SEASON_DAYS_RESULTS_DIR = ABSOLUT_PATH + LEAGUE_DIR + "RealChampionshipSeasonDays\\results\\";
//	public static String SEASON_DAYS_RESULTS_FILE_NAME = "SeasonDays";
//
//	//MAPPA DI TUTTI I VOTI DI TUTTE LE GIORNATE DI TUTTE LE PARTITE DEL CAMPIONATO
//	public static String REAL_CHAMPIONSHIP_VOTES_DIR = ABSOLUT_PATH + LEAGUE_DIR + "RealChampionshipVotes\\";
//	public static String REAL_CHAMPIONSHIP_VOTES_FILE_NAME = "Votes";
//
//	//MAPPA [NOME_SQUADRA] - [ID_SQUADRA] DI FANTAGAZZETTA
//	public static String REAL_CHAMPIONSHIP_TEAMS_IDS_DIR = ABSOLUT_PATH + LEAGUE_DIR + "RealChampionshipTeamsIds\\";
//	public static String REAL_CHAMPIONSHIP_TEAMS_IDS_FILE_NAME = "TeamsId";
//	
//	//TUTTE LE CLASSIFICHE DI TUTTI I POSSIBILI 40000 CAMPIONATI
//	public static String RANKING_DIR = ABSOLUT_PATH + LEAGUE_DIR + "Rankings\\";
//	public static String RANKING_FILE_NAME = "Rankings";
//
//	//LA CLASSIFICA COMPLETA REALE
//	public static String REAL_RANKING_DIR = ABSOLUT_PATH + LEAGUE_DIR + "Rankings\\";
//	public static String REAL_RANKING_FILE_NAME = "RealChampionshipRanking";
//
//	//LE REGOLE
//	public static String RULES_DIR = ABSOLUT_PATH + LEAGUE_DIR + "Rules\\";
//	public static String RULES_FILE_NAME = "Rules";
//	
//	
//	public static String GENERATED_SEASONS_DIR = ABSOLUT_PATH + LEAGUE_DIR + "GeneratedSeasons\\"; 
//	public static String GENERATED_SEASONS_FILE_NAME = "generatedSeasons"; 
//
//	public static String ALL_INPUT_PERMUTATIONS_DIR = ABSOLUT_PATH + LEAGUE_DIR + "AllInputPermutations\\"; 
//	public static String ALL_INPUT_PERMUTATIONS_FILE_NAME = "inputPermutation";
}
