package app.logic._9_alghoritmTester;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.EventOddsDao;
import app.dao.tabelle.GoalsStatsDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.SingleBetDao;
import app.dao.tabelle.WinRangeStatsDao;
import app.logic._2_matchResultAnalyzer.ResultAnalyzer;
import app.logic._3_rankingCalculator.RankingCalculator;
import app.logic._5_goodnessCalculator.GoodnessCalculator;
import app.logic._6_betCreator.BetCreator;
import app.logic._7_betAnalyzer.BetAnalyzer;

@Service
public class AlghoritmTester {
	
	@Autowired
	private ResultAnalyzer resultAnalyzer;
	
	@Autowired
	private RankingCalculator rankingCalculator;
	
	@Autowired
	private GoodnessCalculator goodnessCalculator;

	@Autowired
	private BetCreator betCreator;
	
	@Autowired
	private BetAnalyzer betAnalyzer;
	
	
	public void execute(){
		
		// Prende le partite settimana per settimana

		// Scorre settimana dopo settimana e calcola e salva le stats per fino a quel momento
		// e anche le classifiche
		// N.B. è una scrittura viene fatta una volta soltanto, poichè sono dati oggettivi
		// calcolati senza alcun algoritmo, (percentuali di risultati e classifiche)

		boolean isFirstTime = false;; //
		for (int seasonDay = 7; seasonDay < 10; seasonDay++) {
//			Date dateOfBet = getDateOfBet(seasonDay);
			
			if (isFirstTime) {
				resultAnalyzer.execute(seasonDay);
//				rankingCalculator.execute(seasonDay);
			}
		
			goodnessCalculator.execute(seasonDay);
			
			betCreator.execute(seasonDay);
			
			betAnalyzer.execute(seasonDay);
			
		
		}
		
		
		
		
		// Calcola la goodness fino a quel momento
		
		// Crea la bet
		
		// Verifica se è vinta
		
		
		
		
		
		
		
		
		
	}
	
	

	
	
}
