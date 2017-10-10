package app;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.dao.tabelle.EventOddsDao;
import app.dao.tabelle.EventOddsRepo;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.MatchoRepo;
import app.dao.tabelle.entities.Matcho;
import app.logic.UtilityModel;
import app.logic._1_matchesDownlaoder.NextMatchesDownloader;
import app.logic._1_matchesDownlaoder.PastMatchesDownlaoder;
import app.logic._2_matchResultAnalyzer.ResultAnalyzer;
import app.logic._3_rankingCalculator.RankingCalculator;
import app.logic._4_trendCalculator.TrendCalculator;
import app.logic._5_goodnessCalculator.GoodnessCalculator;
import app.logic._6_betCreator.BetCreator;
import app.utils.ChampEnum;

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

	// ###################################################
	// ##########            2                ############
	// ###################################################
	@RequestMapping(value = "/downloadPastMatches", method = RequestMethod.GET)
	public ResponseEntity<String>  downloadPastMatches2() {
		matchDao.removeAllNextMatches();
		pastMatchesDownloader.execute();
		String body = "Downloading past matches COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}

	// ###################################################
	// ##########            3                ############
	// ###################################################
	@RequestMapping(value = "/calculateTeamsStats", method = RequestMethod.GET)
	public ResponseEntity<String>  calculateTeamsStats3() {
		long startTime = System.nanoTime();

		resetStats();
		resultAnalyzer.execute();

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("resultAnalyzer " + duration / 1000000);
		
		String body = "Calculating Teams Stats COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}
	
	// ###################################################
	// ##########            4                ############
	// ###################################################
	@RequestMapping(value = "/calculateRankings", method = RequestMethod.GET)
	public ResponseEntity<String>  calculateRankings4() {
		rankingCalculator.execute();
		
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
		
		removeAllEventOdds();
		goodnessCalculator.execute();

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("goodnessCalculator " + duration / 1000000);
		
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

		betCreator.execute();

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("betCreator " + duration / 1000000);
		
		String body = "Creating Bet COMPLETED";
		ResponseEntity<String> response = new ResponseEntity<String>(body, HttpStatus.OK);
		return response;
	}

	
	


	
	
	
	@Autowired
	private MatchoRepo matchRepo;
	
	@Autowired
	private MatchoDao matchDao;
	
	@Autowired
	private EventOddsDao eventOddsDao;
	
	@Autowired
	private EventOddsRepo eventOddsRepo;
	

	
	
	
	
	@RequestMapping(value = "/initTipologiche", method = RequestMethod.GET)
	public @ResponseBody void initTipologiche() {
		utilityModel.execute();
		initChampTable();
	}
	
	
	@RequestMapping(value = "/resetMatches", method = RequestMethod.GET)
	public @ResponseBody void resetMatches() {
		utilityModel.deleteAllMatches();
	}

	
	@RequestMapping(value = "/initChampTable", method = RequestMethod.GET)
	public @ResponseBody void initChampTable() {
        utilityModel.initChampsTable();
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


	@RequestMapping(value = "/resetStats", method = RequestMethod.GET)
	public @ResponseBody void resetStats() {
		long startTime = System.nanoTime();

		utilityModel.deleteAllWinRangeStats();
		utilityModel.deleteAllGoalsStats();

		long currentTime = System.nanoTime();
		long duration = (currentTime - startTime); // divide by 1000000 to get milliseconds.
		System.out.println("resetStats " + duration / 1000000);
	}

	
	@RequestMapping(value = "/removeAllEventOdds", method = RequestMethod.GET)
	public void removeAllEventOdds() {
		eventOddsRepo.deleteAll();
	}
	

	@RequestMapping(value = "/removeAllNextMatch", method = RequestMethod.GET)
	public void removeNextMatch() {
		matchDao.removeAllNextMatchesByChamp(ChampEnum.ENG_PREMIER);
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
		matchDao.deleteMatch(idMatch);
	}

	// @GetMapping(path="/all")
	// public @ResponseBody Iterable<User> getAllUsers() {
	// // This returns a JSON or XML with the users
	// return userRepository.findAll();
	// }
}