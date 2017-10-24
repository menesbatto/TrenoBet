package app.logic._9_alghoritmTester;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.EventOddsDao;
import app.dao.tabelle.GoalsStatsDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.SingleBetDao;
import app.dao.tabelle.WinRangeStatsDao;
import app.logic._1_matchesDownlaoder.model.BetType;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.SingleBetBean;
import app.logic._2_matchResultAnalyzer.ResultAnalyzer;
import app.logic._3_rankingCalculator.RankingCalculator;
import app.logic._5_goodnessCalculator.GoodnessCalculator;
import app.logic._6_betCreator.BetCreator;
import app.logic._7_betAnalyzer.BetAnalyzer;
import app.utils.Utils;

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
		
	
		
		
		List<SingleBetBean> allChampsSingleBets = new ArrayList<SingleBetBean>();
		
		for (int seasonDay = 7; seasonDay < 10; seasonDay++) {
//			Date dateOfBet = getDateOfBet(seasonDay);
			
			if (isFirstTime) {
				resultAnalyzer.execute(seasonDay);
				rankingCalculator.execute(seasonDay);
			}
		
			goodnessCalculator.execute(seasonDay);
//			
			betCreator.execute(seasonDay);
			
			List<SingleBetBean> bets = betAnalyzer.execute(seasonDay);
			
			allChampsSingleBets.addAll(bets);
			
			System.out.println(seasonDay);
			printBetStats(bets);
		
		}
		
		
//		0 amount = 50.0;
		System.out.println("Total");
		System.out.println();
		printBetStats(allChampsSingleBets);
		
		// Calcola la goodness fino a quel momento
		
		// Crea la bet
		
		// Verifica se è vinta
		
		
		
		
		
		
		
		
		
	}


	private void printBetStats(List<SingleBetBean> allChampsSingleBets) {
		
		
		System.out.println("\tTOT\tWIN\tLOST\t€");
		
		Double totalWinAmount = 0.0;
		Integer totalBetNum = 0;
		Integer totalWinBetNum = 0;

		
		
		Double xWinAmount = 0.0;
		Integer xBetNum = 0;
		Integer xWinBetNum = 0;
		
		
		Double _12WinAmount = 0.0;
		Integer _12BetNum = 0;
		Integer _12WinBetNum = 0;
		
		Double uoWinAmount = 0.0;
		Integer uoBetNum = 0;
		Integer uoWinBetNum = 0;
		
		Double ehWinAmount = 0.0;
		Integer ehBetNum = 0;
		Integer ehWinBetNum = 0;
		
		
		for (SingleBetBean bet : allChampsSingleBets) {
			
			Double winOdds = bet.getWinOdds();
			if (bet.getBetType().equals(BetType._1x2)) {
				if ( bet.getMatchResultForecast().name().equals(MatchResultEnum.D.name())) {
					if (bet.getWin()) {
						xWinBetNum++;
						xWinAmount += winOdds;
					}
					xBetNum++;
					xWinAmount--;
						
				}
				else {
					if (bet.getWin()) {
						_12WinBetNum++;
						_12WinAmount += winOdds;
					}
					_12BetNum++;
					_12WinAmount--;
				}
			}
			else if (bet.getBetType().name().contains("_5")) {
				if (bet.getWin()) {
					uoWinBetNum++;
					uoWinAmount += winOdds;
				}
				uoBetNum++;
				uoWinAmount--;
			}
			else if (bet.getBetType().name().contains("m")||bet.getBetType().name().contains("p")) {
				if (bet.getWin()) {
					ehWinBetNum++;
					ehWinAmount += winOdds;
				}
				ehBetNum++;
				ehWinAmount--;
			}
			
			if (bet.getWin()) {
				totalWinBetNum++;
				totalWinAmount += winOdds;
			}
			totalBetNum++;
			totalWinAmount--;
			
		}
		System.out.println("12"+ 			"\t" + _12BetNum + 			"\t" + _12WinBetNum + 	"\t" + (_12BetNum-_12WinBetNum)+ 	"\t" + Utils.redimString(_12WinAmount) + "");
		
		System.out.println("X"+ 			"\t"+ xBetNum + 			"\t" + xWinBetNum + 	"\t" + (xBetNum-xWinBetNum)+ 	"\t" + Utils.redimString(xWinAmount) + "");
		
		System.out.println("UO"+ 			"\t" + uoBetNum + 			"\t" + uoWinBetNum + 	"\t" + (uoBetNum-uoWinBetNum)+ 	"\t" + Utils.redimString(uoWinAmount) + "");
			
		System.out.println("EH"+ 			"\t"+ehBetNum + 			"\t" + ehWinBetNum + 	"\t" + (ehBetNum-ehWinBetNum)+ 	"\t" +  Utils.redimString(ehWinAmount) + "");

		System.out.println("TOTAL" + 			"\t"+ totalBetNum + 			"\t" + totalWinBetNum + "\t" + Utils.redimString(totalWinAmount));
		System.out.println("\n#################################\n");
	}
	
	

	
	
}
