package app;

import java.util.List;

import javax.transaction.Transactional;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.dao.tabelle.ChampDao;
import app.dao.tabelle.ChampRepo;
import app.dao.tabelle.EventOddsDao;
import app.dao.tabelle.EventOddsRepo;
import app.dao.tabelle.GoalsStatsDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.MatchoRepo;
import app.dao.tabelle.SingleBetDao;
import app.dao.tabelle.SingleBetRepo;
import app.dao.tabelle.WinRangeStatsDao;
import app.dao.tabelle.entities.Matcho;
import app.dao.tipologiche.PenalityDao;
import app.logic.UtilityModel;
import app.logic._1_matchesDownlaoder.NextMatchesDownloader;
import app.logic._1_matchesDownlaoder.PastMatchesDownlaoder;
import app.logic._2_matchResultAnalyzer.ResultAnalyzer;
import app.logic._3_rankingCalculator.RankingCalculator;
import app.logic._4_trendCalculator.TrendCalculator;
import app.logic._5_goodnessCalculator.GoodnessCalculator;
import app.logic._6_betCreator.BetCreator;
import app.logic._7_betAnalyzer.BetAnalyzer;
import app.logic._9_alghoritmTester.AlghoritmTester;
import app.utils.AppConstants;
import app.utils.ChampEnum;
import app.utils.Utils;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/api2") // This means URL's start with /demo (after Application path)
public class FacadeController {

	@Autowired
	private UtilityModel utilityModel;

	@Autowired
	private NextMatchesDownloader nextMatchesDownloader;
	
	@Autowired
	private PastMatchesDownlaoder pastMatchesDownloader;
	
	@Autowired
	private ResultAnalyzer resultAnalyzer;
	
	@Autowired
	private RankingCalculator rankingCalculator;
	
	@Autowired
	private TrendCalculator trendCalculator;
	
	@Autowired
	private GoodnessCalculator goodnessCalculator;
	
	@Autowired
	private BetCreator betCreator;

	@Autowired
	private BetAnalyzer betAnalyzer;

	@Autowired
	private AlghoritmTester alghoritmTester;

	
	// ###################################################
	// ##########            1                ############
	// ###################################################
	
	@RequestMapping(value = "/downloadNextMatches", method = RequestMethod.GET)
	public ResponseEntity<String> downloadNextMatches1() {
		nextMatchesDownloader.execute();
		String body = "Downloading next matches COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value = "/downloadNextMatches/{champId}", method = RequestMethod.GET)
	public ResponseEntity<String> downloadNextMatchesByChamp(@PathVariable Integer champId) {
		ChampEnum champ = champDao.findChampEnumById(champId);
//		ChampEnum champ = ChampEnum.valueOf(champName);
		ChampEnum[] champs = new ChampEnum[] {champ};
		nextMatchesDownloader.execute(champs);
		String body = "Downloading next matches for champ " + champ.getName() + " COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}
	
	// ###################################################
	// ##########            2                ############
	// ###################################################
	@RequestMapping(value = "/downloadPastMatches", method = RequestMethod.GET)
	public ResponseEntity<String>  downloadPastMatches2() {
		pastMatchesDownloader.execute();
		String body = "Downloading past matches COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}

	@Autowired
	private ChampDao champDao;
	
	@RequestMapping(value = "/downloadPastMatches/{champId}", method = RequestMethod.GET)
	public ResponseEntity<String> downloadPastMatchesByChamp(@PathVariable Integer champId) {
		ChampEnum champ = champDao.findChampEnumById(champId);
//		ChampEnum champ = ChampEnum.valueOf(champId);
		ChampEnum[] champs = new ChampEnum[] {champ};
		pastMatchesDownloader.execute(champs);
		String body = "Downloading past matches for champ " + champ.getName() + " COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}
	// ###################################################
	// ##########            3                ############
	// ###################################################
	@RequestMapping(value = "/calculateTeamsStats", method = RequestMethod.GET)
	public ResponseEntity<String>  calculateTeamsStats3() {
		long startTime = System.nanoTime();

		//calcola le statistiche su tutte le partite prima del primo giorno della season day,
		// se gli passo la season day precedente a quella di oggi allora si perde tutta la scorsa settimana
		
		Integer actualSeasonDay = Utils.getActualTrenoSeasonDay();
//		resetStats(actualSeasonDay);
		resultAnalyzer.execute(actualSeasonDay);

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("resultAnalyzer " +new Double (duration) / 1000000.0/ 1000.0);
		
		String body = "Calculating Teams Stats COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}
	
	// ###################################################
	// ##########            4                ############
	// ###################################################
	@RequestMapping(value = "/calculateRankings", method = RequestMethod.GET)
	public ResponseEntity<String>  calculateRankings4() {
		
		Integer actualSeasonDay = Utils.getActualTrenoSeasonDay();
		rankingCalculator.execute(actualSeasonDay);
		
		String body = "Calculating rankings COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}

	// ###################################################
	// ##########            5                ############
	// ###################################################
	@RequestMapping(value = "/calculateTrends", method = RequestMethod.GET)
	public ResponseEntity<String>  calculateTrends5() {
		trendCalculator.execute();
		
		String body = "Calculating Trends COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}

	// ###################################################
	// ##########            6                ############
	// ###################################################
	@RequestMapping(value = "/calculateOddsGoodness", method = RequestMethod.GET)
	public ResponseEntity<String>  calculateOddsGoodness6() {
		long startTime = System.nanoTime();
		
		Integer actualSeasonDay = Utils.getActualTrenoSeasonDay();
//		removeAllEventOdds(actualSeasonDay);
//		ChampEnum[] champs = {ChampEnum.ITA_SERIE_A_2017};
//		goodnessCalculator.execute(actualSeasonDay-1, champs);
		goodnessCalculator.execute(actualSeasonDay);

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("goodnessCalculator " + new Double (duration) / 1000000.0/ 1000.0);
		
		String body = "Calculating Next Matches Odds Goodness COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}

	// ###################################################
	// ##########            7                ############
	// ###################################################
	@RequestMapping(value = "/createBet", method = RequestMethod.GET)
	public ResponseEntity<String>  createBet6() {
		long startTime = System.nanoTime();
		Integer actualSeasonDay = Utils.getActualTrenoSeasonDay();
//		ChampEnum[] champs = {ChampEnum.ITA_SERIE_A_2017};
//		betCreator.execute(actualSeasonDay-1, champs);
		betCreator.execute(actualSeasonDay);
		
		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("betCreator " + new Double (duration) / 1000000.0/ 1000.0);
		
		String body = "Creating Bet COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}

	// ###################################################
	// ##########            8                ############
	// ###################################################
	@RequestMapping(value = "/analyzeBet", method = RequestMethod.GET)
	public ResponseEntity<String>  analyzeBet8() {
		long startTime = System.nanoTime();

		Integer actualSeasonDay = Utils.getActualTrenoSeasonDay();
		betAnalyzer.execute(actualSeasonDay);

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("betAnalyzer " + new Double (duration) / 1000000.0/ 1000.0);
		
		String body = "Analyzing Bet COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}
	
	
	// ###################################################
	// ##########            9                ############
	// ###################################################
	@RequestMapping(value = "/testAlghoritm", method = RequestMethod.GET)
	public ResponseEntity<String>  testAlghoritm9() {
		long startTime = System.nanoTime();
		AppConstants.PRINT_FASE = true;
		alghoritmTester.execute();

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("betAnalyzer " +new Double (duration) / 1000000.0/ 1000.0);
		
		String body = "Analyzing Bet COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}
	
	

	@Autowired
	private SingleBetRepo singleBetRepo;

	@RequestMapping(value = "/deleteBet", method = RequestMethod.GET)
	public void  deleteBet() {
		singleBetRepo.deleteAll();
	}
	
	
	@Autowired
	private MatchoRepo matchRepo;
	
	@Autowired
	private MatchoDao matchDao;
	
	@Autowired
	private EventOddsDao eventOddsDao;
	
	@Autowired
	private EventOddsRepo eventOddsRepo;
	
	@Autowired
	private SingleBetDao singleBetDao;

	@Autowired
	private PenalityDao penalityDao;
	
	

	@RequestMapping(value = "/insertPenality", params= {"champName", "teamName", "points"}, method = RequestMethod.GET)
	public @ResponseBody void insertPenality(	@RequestParam (value = "champName") String champName, 
												@RequestParam (value = "teamName") String teamName,
												@RequestParam (value = "points") Integer points) {
		ChampEnum champEnum = ChampEnum.valueOf(champName);
		penalityDao.insertPenality(champEnum, teamName, points);
//		
//		Map<String, Integer> penalityMap = new HashMap<String, Integer>();
//		penalityMap.put("Andria", 1);
//		penalityMap.put("Catanzaro", 1);
//		penalityMap.put("Matera", 2);
//		penalityMap.put("Akragas", 3);
//		
//		penalityMap.put("Santarcangelo", 1);
//		
//		penalityMap.put("Arezzo", 2);
//		
		
		
	}
	
	
	

	
	@RequestMapping(value = "/saveChamp/{champName}", method = RequestMethod.GET)
	public @ResponseBody void saveChamp(@PathVariable String champName) {
		ChampEnum champEnum = ChampEnum.valueOf(champName);
		utilityModel.saveChamp(champEnum);
		
	}
	
	
	@RequestMapping(value = "/initTipologiche", method = RequestMethod.GET)
	public @ResponseBody void initTipologiche() {
		utilityModel.execute();
	}
	
	
	@RequestMapping(value = "/resetMatches", method = RequestMethod.GET)
	public @ResponseBody void resetMatches() {
		utilityModel.deleteAllMatches();
	}

	
	@RequestMapping(value = "/getAllMatchResults", method = RequestMethod.GET)
	public @ResponseBody List<Matcho> getAllMatchResults() {
		List<Matcho> findAll = matchRepo.findAll();
		return findAll;
	}

	
	@Transactional
	@RequestMapping(value = "/scaricaPassatiSenzaCancellareNuovi", method = RequestMethod.GET)
	public void scaricaPassatiSenzaCancellareNuovi() {
		pastMatchesDownloader.execute();
	}


	@RequestMapping(value = "/resetRankings", method = RequestMethod.GET)
	public @ResponseBody void resetRankings() {
		long startTime = System.nanoTime();

		utilityModel.deleteAllRankings();

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("resetRankings " + new Double (duration) / 1000000.0/ 1000.0);
	}
	
	@RequestMapping(value = "/resetAllStats", method = RequestMethod.GET)
	public @ResponseBody void resetAllStats() {
		long startTime = System.nanoTime();

		utilityModel.deleteAllWinRangeStats();
		utilityModel.deleteAllGoalsStats();;

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("resetStats " + new Double (duration) / 1000000.0/ 1000.0);
	}
	
	@Transactional
	@RequestMapping(value = "/resetStats/{seasonDay}", method = RequestMethod.GET)
	public @ResponseBody void resetStats(@PathVariable Integer seasonDay) {
		long startTime = System.nanoTime();

		utilityModel.deleteWinRangeStatsBySeasonDay(seasonDay);
		utilityModel.deleteGoalsStatsBySeasonDay(seasonDay);

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("resetStats " + new Double (duration) / 1000000.0/ 1000.0);
	}

	

	@RequestMapping(value = "/removeAllEventOdds", method = RequestMethod.GET)
	public void removeAllEventOdds() {
		
		eventOddsRepo.deleteAll();
	}
	
	
	@RequestMapping(value = "/removeEventOdds/{seasonDay}", method = RequestMethod.GET)
	@Transactional
	public void removeAllEventOdds(@PathVariable Integer seasonDay) {
		
		eventOddsRepo.deleteBySeasonDay(seasonDay);
	}
	

	@RequestMapping(value = "/removeAllNextMatchNoChamp", method = RequestMethod.GET)
	public void removeAllNextMatchNoChamp() {
		matchDao.removeAllNextMatchesByNoChamp();
	}

	
	@RequestMapping(value = "/updateEhOdds", method = RequestMethod.GET)
	public void updateEhOdds() {
		matchDao.updateEhOdds();
	}

	
	@RequestMapping(value = "/findSpecificMatch", method = RequestMethod.GET)
	public Matcho findByHomeTeamNameAndAwayTeamName() {
		return matchDao.findByHomeTeamNameAndAwayTeamName("Liverpool", "CrystalPalace");
	}


	@RequestMapping(value = "/deleteMatch/{idMatch}", method = RequestMethod.GET)
	@Transactional
	public void deleteMatch(@PathVariable Integer idMatch) {
		
			eventOddsDao.removeByMatchId(idMatch);
			singleBetDao.removeByMatchId(idMatch);
			matchDao.deleteMatch(idMatch);
	}
	

	@Autowired
	private WinRangeStatsDao winRangeStatsDao;
	@Autowired
	private GoalsStatsDao goalsStatsDao;
	
	@RequestMapping(value = "/deleteChamp/{champId}", method = RequestMethod.GET)
	@Transactional
	public void deleteChamp(@PathVariable Integer champId) {
		
		eventOddsDao.deleteByChampId(champId);
		singleBetDao.deleteByChampId(champId);
		matchDao.deleteByChampId(champId);
		winRangeStatsDao.deleteWinRangeStatsByChampId(champId);
		goalsStatsDao.deleteGoalsStatsByChampId(champId);
		matchDao.deleteByChampId(champId);
		//elimina team e champ
		
	}
	
	
	// @GetMapping(path="/all")
	// public @ResponseBody Iterable<User> getAllUsers() {
	// // This returns a JSON or XML with the users
	// return userRepository.findAll();
	// }
	
	@RequestMapping(value = "/getActualSeasonDay", method = RequestMethod.GET)
	public ResponseEntity<String>  getActualSeasonDay() {
		Integer actualSeasonDay = Utils.getActualTrenoSeasonDay();
		ResponseEntity<String> response = new ResponseEntity<String>(actualSeasonDay+"", HttpStatus.OK);
		return response;
	}
}