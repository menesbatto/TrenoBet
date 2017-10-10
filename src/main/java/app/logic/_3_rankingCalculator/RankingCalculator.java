package app.logic._3_rankingCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.RankingRowDao;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._2_matchResultAnalyzer.ResultAnalyzer;
import app.logic._3_rankingCalculator.model.Distances;
import app.logic._3_rankingCalculator.model.RankingRow;
import app.utils.ChampEnum;
import app.utils.RankCritEnum;
import app.utils.Utils;

@Service
public class RankingCalculator {
	
	
	private static HashMap<ChampEnum, HashMap<String, ArrayList<MatchResult>>> teamsToMatchesAll; 

	@Autowired
	private MatchoDao matchDao;
	
	@Autowired
	private RankingRowDao rankingRowDao;

	@Autowired
	private ResultAnalyzer resultAnalyzer;
	
	
	public void execute(){
		
		for (ChampEnum champ : ChampEnum.values()){
			calculateChampRanking(champ);
		}

	}
	
	private void calculateChampRanking(ChampEnum champ) {
		ArrayList<RankingRow> ranking = new ArrayList<RankingRow>();
		RankingRow rr;
		ArrayList<MatchResult> teamsMatches = matchDao.getDownloadedPastMatchByChampFull(champ);
		Map<String, ArrayList<MatchResult>> matchesMap = new HashMap<String, ArrayList<MatchResult>>();
		resultAnalyzer.createMatchMap(matchesMap, teamsMatches, "H");
		resultAnalyzer.createMatchMap(matchesMap, teamsMatches, "A");
		
		for (Map.Entry<String, ArrayList<MatchResult>> entry : matchesMap.entrySet()) {
			rr = new RankingRow();
			String teamName = entry.getKey();
			rr.setTeamName(teamName);
			for (MatchResult m : entry.getValue()){
				if (m.getFTHG() != null)		// se i risultati ci sono
					updateRankingRow(rr, teamName, m);
			}
			calculateAvgs(rr);
			ranking.add(rr);
		}
		
		applyCriteria(champ, ranking);
		
		Integer maxPlayedMatches = calculateMaxPlayedMatches(ranking);
		
		boolean thereAreMatchesToRecover = false;
		for (int i = 0; i < ranking.size(); i++){
			ranking.get(i).setPosition(i+1);
			ranking.get(i).setMatchesToRecover(maxPlayedMatches - ranking.get(i).getAllPlayedMatches());
			if (maxPlayedMatches - ranking.get(i).getAllPlayedMatches() > 0){
				thereAreMatchesToRecover = true;
			}
		}

		calculateMotivationalIndexes(ranking, champ, thereAreMatchesToRecover);
		
		
		printRanking(ranking, champ);
		System.out.println("########################\n\n\n");
		rankingRowDao.saveRanking(champ, ranking);
		
	}


	private static void calculateMotivationalIndexes(ArrayList<RankingRow> ranking, ChampEnum champ, boolean thereAreMatchesToRecover) {
			

		if (champ.getImportantPositions().get(champ.getImportantPositions().size()-1) != ranking.size())
			champ.getImportantPositions().add(ranking.size());
		
		for (RankingRow rr: ranking){
			
			if (thereAreMatchesToRecover) {
				ArrayList<RankingRow> pessimisticRanking =  calculatePessimisticRanking(ranking, rr, champ);
				RankingRow pessimisticRR = getRrFromRanking(pessimisticRanking, rr);
				calculateMotivationIndexForSpecificRanking(pessimisticRanking, champ, pessimisticRR);
				
				ArrayList<RankingRow> optimisticRanking =  calculateOptimisticRanking(ranking, rr, champ);
				RankingRow optimisticRR = getRrFromRanking(optimisticRanking, rr);
				calculateMotivationIndexForSpecificRanking(optimisticRanking, champ, optimisticRR);
				
				ArrayList<RankingRow> allWinRanking =  calculateAllWinRanking(ranking, rr, champ);
				RankingRow allWinRankingRR = getRrFromRanking(allWinRanking, rr);
				calculateMotivationIndexForSpecificRanking(allWinRanking, champ, allWinRankingRR);
				
				ArrayList<RankingRow> allLoseRanking =  calculateAllLoseRanking(ranking, rr, champ);
				RankingRow allLoseRankingRR = getRrFromRanking(allLoseRanking, rr);
				calculateMotivationIndexForSpecificRanking(allLoseRanking, champ, allLoseRankingRR);
				
				Double motivationIndexAvg = avgNullSafe(pessimisticRR.getMotivationalIndex(), optimisticRR.getMotivationalIndex(), allWinRankingRR.getMotivationalIndex(), allLoseRankingRR.getMotivationalIndex());
				rr.setMotivationalIndex(motivationIndexAvg);
				
				Double motivationalIndexDownAvg = avgNullSafe(pessimisticRR.getMotivationalIndexDownDisObb(), optimisticRR.getMotivationalIndexDownDisObb(), allWinRankingRR.getMotivationalIndexDownDisObb(), allLoseRankingRR.getMotivationalIndexDownDisObb());
				rr.setMotivationalIndexDownDisObb(motivationalIndexDownAvg);
				
				Double motivationalIndexDownCleanAvg = avgNullSafe(pessimisticRR.getMotivationalIndexDownDis(), optimisticRR.getMotivationalIndexDownDis(), allWinRankingRR.getMotivationalIndexDownDis(), allLoseRankingRR.getMotivationalIndexDownDis());
				rr.setMotivationalIndexDownDis(motivationalIndexDownCleanAvg);
				
				Double motivationalIndexDownPointsAvg = avgNullSafe(pessimisticRR.getMotivationalIndexDownDisObbPoi(), optimisticRR.getMotivationalIndexDownDisObbPoi(), allWinRankingRR.getMotivationalIndexDownDisObbPoi(), allLoseRankingRR.getMotivationalIndexDownDisObbPoi());
				rr.setMotivationalIndexDownDisObbPoi(motivationalIndexDownPointsAvg);
				
				Double motivationalIndexUpAvg = avgNullSafe(pessimisticRR.getMotivationalIndexUpDisObb(), optimisticRR.getMotivationalIndexUpDisObb(), allWinRankingRR.getMotivationalIndexUpDisObb(), allLoseRankingRR.getMotivationalIndexUpDisObb());
				rr.setMotivationalIndexUpDisObb(motivationalIndexUpAvg);
				
				Double motivationalIndexUpCleanAvg = avgNullSafe(pessimisticRR.getMotivationalIndexUpDis(), optimisticRR.getMotivationalIndexUpDis(), allWinRankingRR.getMotivationalIndexUpDis(), allLoseRankingRR.getMotivationalIndexUpDis());
				rr.setMotivationalIndexUpDis(motivationalIndexUpCleanAvg);
				
				Double motivationalIndexUpPointsAvg = avgNullSafe(pessimisticRR.getMotivationalIndexUpDisObbPoi(), optimisticRR.getMotivationalIndexUpDisObbPoi(),allWinRankingRR.getMotivationalIndexUpDisObbPoi(), allLoseRankingRR.getMotivationalIndexUpDisObbPoi());
				rr.setMotivationalIndexUpDisObbPoi(motivationalIndexUpPointsAvg);
				
//				rr.setDistances(optimisticRR.getDistances());
			
			}
			else {
				calculateMotivationIndexForSpecificRanking(ranking, champ, rr);
			}
			

		}
		
	}



	private static Double avgNullSafe(Double index1, Double index2, Double index3, Double index4) {
		if (index1 == null)
			index1 = 0.0;
		if (index2 == null)
			index2 = 0.0;
		if (index3 == null)
			index3 = 0.0;
		if (index4 == null)
			index4 = 0.0;
		Double avg = (index1 + index2 + index3 + index4) / 4.0;
		return avg;
	}



	private static RankingRow getRrFromRanking(ArrayList<RankingRow> ranking, RankingRow rr) {
		for (RankingRow current : ranking){
			if (current.getTeamName().equals(rr.getTeamName()))
				return current;
		}
		return null;
	}



	private static void calculateMotivationIndexForSpecificRanking(ArrayList<RankingRow> ranking, ChampEnum champ, RankingRow rr) {
		for (int proximityTarget = 0; proximityTarget <= 2; proximityTarget++){
			calculateDistanceForTarget(ranking, champ, rr, proximityTarget);
		}
		
		calculateMotivationalIndex(rr, champ, ranking);
	}



	


	private static Integer calculateMaxPlayedMatches(ArrayList<RankingRow> ranking) {
		Integer maxPlayedMatches = 0;
		for (RankingRow rr: ranking){
			if (rr.getAllPlayedMatches() > maxPlayedMatches){
				maxPlayedMatches = rr.getAllPlayedMatches();
			}
		}
		return maxPlayedMatches;
	}



	private static void calculateMotivationalIndex(RankingRow rr, ChampEnum champ, ArrayList<RankingRow> ranking) {
		Double motivationalIndexDownTotalDis = 0.0;
		Double motivationalIndexUpTotalDis = 0.0;

		Double motivationalIndexDownTotalDisObbPoi = 0.0;
		Double motivationalIndexUpTotalDisObbPoi = 0.0;
		
		Double motivationalIndexDownTotalDisObb = 0.0;
		Double motivationalIndexUpTotalDisObb = 0.0;
		int proximityIndex = 0;
		
		Integer availablePointsOwn = getAvailablePoints(ranking.size(), rr.getAllPlayedMatches());

		for (Distances distances : rr.getDistances()) {

//			Integer availablePointsDownTargetTeam = null;
//			if (distances.getDistanceFromDownTarget() != null){
//				Integer rankingPointsDownTargetTeam = rr.getAllPoints() - distances.getDistanceFromDownTarget();
//				availablePointsDownTargetTeam = getAvailablePointsDownTargetTeam(rankingPointsDownTargetTeam, ranking);
//			}
			 
			Double pointsOfDisvantageFromUp = distances.getPointOfDisadvantageFromUpTarget() != null && distances.getPointOfDisadvantageFromUpTarget() < 20 ? new Double(distances.getPointOfDisadvantageFromUpTarget()) : null;
			Double pointsOfAdvantageFromDown = distances.getPointOfAdvantageFromDownTarget() != null &&  distances.getPointOfAdvantageFromDownTarget() < 20 ? new Double(distances.getPointOfAdvantageFromDownTarget()) : null;

			Double distanceFromUp = distances.getDistanceFromUpTarget() != null && distances.getDistanceFromUpTarget() < 20 ? new Double(distances.getDistanceFromUpTarget()) : null;
			Double distanceFromDown = distances.getDistanceFromDownTarget() != null &&  distances.getDistanceFromDownTarget() < 20 ? new Double(distances.getDistanceFromDownTarget()) : null;

			if (distanceFromUp != null && distanceFromUp > availablePointsOwn){
				pointsOfDisvantageFromUp = null;
			}
				
			if (distanceFromDown != null && distanceFromDown > availablePointsOwn){
				pointsOfAdvantageFromDown = null;
			}

			Double motivationalIndexUpDis = 0.0;
			Double motivationalIndexDownDis = 0.0;

			Double motivationalIndexUpDisObbPoi = 0.0;
			Double motivationalIndexDownDisObbPoi = 0.0;
			
			
			if (pointsOfDisvantageFromUp != null)
				motivationalIndexUpDis = (20.0 - pointsOfDisvantageFromUp) / 20;
			if (pointsOfAdvantageFromDown != null)
				motivationalIndexDownDis = (20.0 - pointsOfAdvantageFromDown) / 20;
			
			
			Double motivationalIndexUpDisObb = motivationalIndexUpDis;
			Double motivationalIndexDownDisObb = motivationalIndexDownDis;

			
			// ###############################################################
			// ###  Aumenta motivazioni per obiettivo vittoria campionato  ###
			// ###############################################################

			Integer winPosition = champ.getImportantPositions().get(0);
			Integer distanceToWin = ranking.get(winPosition-1).getAllPoints() - rr.getAllPoints();
			
			
			Integer teamRange = getRange(rr.getPosition(), champ.getImportantPositions());
			
			if (distanceToWin < 15){
				if (distanceToWin == 0) {
					if (teamRange.equals(proximityIndex)){		// permette di moltiplicare soltanto la motivazione relativa alla vittoria, e non le altre
						if (distances.getDistanceFromDownTarget() != null){
							distanceToWin = distances.getDistanceFromDownTarget();
							double motivationModificator = getWinMotivationModificator(distanceToWin);
							motivationalIndexDownDisObb = motivationalIndexDownDis * motivationModificator;
						}
					}
					else if (teamRange.equals(proximityIndex + 1)){		// permette di moltiplicare soltanto la motivazione relativa alla vittoria, e non le altre
						if (distances.getDistanceFromDownTarget() != null){
							distanceToWin = distances.getDistanceFromDownTarget();
							double motivationModificator = getWinMotivationModificator(distanceToWin);
							motivationalIndexDownDisObb = motivationalIndexDownDis * motivationModificator;
						}
					}
				}
				else {
					if (teamRange.equals(proximityIndex+1)){	// permette di moltiplicare soltanto la motivazione relativa alla vittoria, e non le altre
						double motivationModificator = getWinMotivationModificator(distanceToWin);
						motivationalIndexUpDisObb = motivationalIndexUpDis * motivationModificator;
					}
				}
				
			}
			
			
			// ###############################################################
			// ######    Aumenta motivazioni per obiettivo salvezza     ######
			// ###############################################################
			
			Integer savingPosition = champ.getImportantPositions().get(champ.getImportantPositions().size()-2);
			Integer distanceToSavePosition =  rr.getAllPoints() - ranking.get(savingPosition - 1).getAllPoints();
			Integer distanceFromOpponent;
			
			if (distanceToSavePosition <= 10 && distanceToSavePosition > 0){ //SOPRA
				if (teamRange.equals(champ.getImportantPositions().size() - 1 - 1 - proximityIndex)){
					distanceFromOpponent = distances.getDistanceFromDownTarget();
					if (distanceFromOpponent != null){
						double motivationModificator = getSaveMotivationModifier(distanceFromOpponent,  distances.getPointOfAdvantageFromDownTarget());
						motivationalIndexDownDisObb = motivationalIndexDownDis * motivationModificator;
					}
				}
			}
			else if (distanceToSavePosition >= -10 && distanceToSavePosition < 0){ //SOTTO
				if (teamRange.equals(champ.getImportantPositions().size() - 1 - proximityIndex)){
					distanceFromOpponent = distances.getDistanceFromUpTarget();
					if (distanceFromOpponent != null){
						double motivationModificator = getSaveMotivationModifier(distanceFromOpponent, distances.getPointOfDisadvantageFromUpTarget());
						motivationalIndexUpDisObb = motivationalIndexUpDis * motivationModificator;
					}
				}
			}
			else if ( distanceToSavePosition==0 ){ //la prima squadra che si salva
//				if (teamRange.equals(champ.getImportantPositions().size() - 1 - 1 - proximityIndex)){
					distanceFromOpponent = distances.getDistanceFromDownTarget();
					if (distanceFromOpponent != null){
						double motivationModificator = getSaveMotivationModifier(distanceFromOpponent, 0);
						motivationalIndexDownDisObb = motivationalIndexDownDis * motivationModificator;
					}
//				}
//				else if (teamRange.equals(champ.getImportantPositions().size() - 1 - proximityIndex)){
//					distanceFromOpponent = distances.getDistanceFromDownTarget();
//					if (distanceFromOpponent != null){
//						double motivationModificator = getSaveMotivationModifier(distanceFromOpponent, 0);
//						motivationalIndexDownFinal = motivationalIndexDownClean * motivationModificator;
//					}
//				}
//				else if (teamRange.equals(champ.getImportantPositions().size() - 1 - 1 - 1 -proximityIndex)){
//					distanceFromOpponent = distances.getDistanceFromDownTarget();
//					if (distanceFromOpponent != null){
//						double motivationModificator = getSaveMotivationModifier(distanceFromOpponent, 0);
//						motivationalIndexDownFinal = motivationalIndexDownClean * motivationModificator;
//					}
//				}
			}
			
			// Modifica della motivazione sulla base dei punti che mancano alla fine
			if (distanceFromUp != null && distanceFromUp > 3){
				motivationalIndexUpDisObbPoi = motivationalIndexUpDisObb * ( 1 - ( (distanceFromUp - 1) / availablePointsOwn) );
				if (motivationalIndexUpDisObbPoi < 0) 
					motivationalIndexUpDisObbPoi = motivationalIndexUpDisObb; 
			} else {
				motivationalIndexUpDisObbPoi = motivationalIndexUpDisObb; 
			}
			
			if (distanceFromDown != null && distanceFromDown > 3){
				motivationalIndexDownDisObbPoi = motivationalIndexDownDisObb * ( 1 - ( (distanceFromDown - 1) / availablePointsOwn) );
				if (motivationalIndexDownDisObbPoi < 0) 
					motivationalIndexDownDisObbPoi = motivationalIndexDownDisObb; 
			}
			else {
				motivationalIndexDownDisObbPoi = motivationalIndexDownDisObb; 
			}
			
			
			
			
			motivationalIndexUpTotalDisObbPoi += motivationalIndexUpDisObbPoi;
			motivationalIndexDownTotalDisObbPoi += motivationalIndexDownDisObbPoi;
			
			motivationalIndexUpTotalDisObb += motivationalIndexUpDisObb;
			motivationalIndexDownTotalDisObb += motivationalIndexDownDisObb;
			
			motivationalIndexUpTotalDis += motivationalIndexUpDis;
			motivationalIndexDownTotalDis += motivationalIndexDownDis;
			
			proximityIndex++;
		}
		
		rr.setMotivationalIndexUpDisObbPoi(motivationalIndexUpTotalDisObbPoi);
		rr.setMotivationalIndexDownDisObbPoi(motivationalIndexDownTotalDisObbPoi);

		rr.setMotivationalIndexUpDisObb(motivationalIndexUpTotalDisObb);
		rr.setMotivationalIndexDownDisObb(motivationalIndexDownTotalDisObb);
		
		rr.setMotivationalIndexUpDis(motivationalIndexUpTotalDis);
		rr.setMotivationalIndexDownDis(motivationalIndexDownTotalDis);
		
		
//		Double motivationalIndex = (motivationalIndexUpTotalFinal + motivationalIndexDownTotalFinal); 
		Double motivationalIndex = (motivationalIndexUpTotalDisObbPoi + motivationalIndexDownTotalDisObbPoi); 
		
		rr.setMotivationalIndex(motivationalIndex);
		
	}



	private static ArrayList<RankingRow> calculatePessimisticRanking(ArrayList<RankingRow> ranking, RankingRow specificTeamRR, ChampEnum champ) {
		//	Simula il fatto che:
		//	- tutte le squadre a cui mancano partite le vincano
		//	- la squadra attuale invece le perda tutte
		ArrayList<RankingRow> pessimisticRanking = SerializationUtils.clone(ranking);

		for (int j = 0; j < pessimisticRanking.size(); j++) {
			RankingRow rr = pessimisticRanking.get(j);
			if ( !specificTeamRR.getTeamName().equals(rr.getTeamName()) ){
				Integer matchesToRecover = rr.getMatchesToRecover();
				if (matchesToRecover > 0){
					Integer actualAllPoints = rr.getAllPoints();
					rr.setAllPoints( actualAllPoints + matchesToRecover * 3);
				}
			}
		}
		
		applyCriteria(champ, pessimisticRanking);
		
		Integer maxPlayerMatches = pessimisticRanking.get(0).getAllPlayedMatches() + pessimisticRanking.get(0).getMatchesToRecover();
		for (int i = 0; i < pessimisticRanking.size(); i++){
			pessimisticRanking.get(i).setPosition(i+1);
			pessimisticRanking.get(i).setAllPlayedMatches(maxPlayerMatches);
			pessimisticRanking.get(i).setMatchesToRecover(0);
		}
		
		return pessimisticRanking;
		
	}

	private static ArrayList<RankingRow> calculateAllLoseRanking(ArrayList<RankingRow> ranking, RankingRow specificTeamRR, ChampEnum champ) {
		//		Simula il fatto che:
		//	- tutte le squadre perdano le partite che gli mancano
		ArrayList<RankingRow> allLoseRanking = SerializationUtils.clone(ranking);

		Integer maxPlayerMatches = allLoseRanking.get(0).getAllPlayedMatches() + allLoseRanking.get(0).getMatchesToRecover();
		for (int i = 0; i < allLoseRanking.size(); i++){
			allLoseRanking.get(i).setAllPlayedMatches(maxPlayerMatches);
			allLoseRanking.get(i).setMatchesToRecover(0);
		}
		
		return allLoseRanking;
	}
	
	
	private static ArrayList<RankingRow> calculateAllWinRanking(ArrayList<RankingRow> ranking, RankingRow specificTeamRR, ChampEnum champ) {
		//	Simula il fatto che:
		//	- tutte le squadre vincano le partite che gli mancano
		ArrayList<RankingRow> allWinRanking = SerializationUtils.clone(ranking);

		for (int j = 0; j < allWinRanking.size(); j++) {
			RankingRow rr = allWinRanking.get(j);
//			if ( !specificTeamRR.getTeamName().equals(rr.getTeamName()) ){
				Integer matchesToRecover = rr.getMatchesToRecover();
				if (matchesToRecover > 0){
					Integer actualAllPoints = rr.getAllPoints();
					rr.setAllPoints( actualAllPoints + matchesToRecover * 3);
				}
//			}
		}
		
		applyCriteria(champ, allWinRanking);
		
		Integer maxPlayerMatches = allWinRanking.get(0).getAllPlayedMatches() + allWinRanking.get(0).getMatchesToRecover();
		for (int i = 0; i < allWinRanking.size(); i++){
			allWinRanking.get(i).setPosition(i+1);
			allWinRanking.get(i).setAllPlayedMatches(maxPlayerMatches);
			allWinRanking.get(i).setMatchesToRecover(0);
		}
		
		return allWinRanking;
	}
	
	
	private static ArrayList<RankingRow> calculateOptimisticRanking(ArrayList<RankingRow> ranking, RankingRow specificTeamRR, ChampEnum champ) {
		//	Simula il fatto che:
		//	- tutte le squadre a cui mancano partite le perdano
		//	- la squadra attuale invece le vinca tutte		
		
		ArrayList<RankingRow> optimisticRanking = SerializationUtils.clone(ranking);
		RankingRow currentRr = optimisticRanking.get( specificTeamRR.getPosition() - 1 );
		
		Integer matchesToRecover = currentRr.getMatchesToRecover();

		if (matchesToRecover > 0){
			Integer expectedPoints = currentRr.getAllPoints() + 3 * matchesToRecover;
			currentRr.setAllPoints(expectedPoints);
			if ( currentRr.getPosition() != 1 ){
				Integer upTeamPoints = optimisticRanking.get(currentRr.getPosition() -1 -1).getAllPoints();
				// Limito al minimo il ricalcolo di tutta la classifica, solo se la currentRR ha soprassato quella che la precedeva 
				if (expectedPoints > upTeamPoints){
					applyCriteria(champ, optimisticRanking);
				
				}
			}
		}
			
		Integer maxPlayerMatches = optimisticRanking.get(0).getAllPlayedMatches() + optimisticRanking.get(0).getMatchesToRecover();
		for (int i = 0; i < optimisticRanking.size(); i++){
			optimisticRanking.get(i).setPosition(i+1);
			optimisticRanking.get(i).setAllPlayedMatches(maxPlayerMatches);
			optimisticRanking.get(i).setMatchesToRecover(0);
		}

		return optimisticRanking;

	}


	private static Integer getAvailablePointsDownTargetTeam(Integer pointsDownTargetTeam, ArrayList<RankingRow> ranking) {
		Integer maxAvailablePoints = 0;
		for (RankingRow rr: ranking){
			if (rr.getAllPoints().equals(pointsDownTargetTeam)){
				Integer availablePoints = getAvailablePoints(ranking.size(), rr.getAllPlayedMatches());
				if (availablePoints > maxAvailablePoints){
					maxAvailablePoints = availablePoints;
				}
			}
			if (rr.getAllPoints() < pointsDownTargetTeam){
				Integer availablePoints = getAvailablePoints(ranking.size(), rr.getAllPlayedMatches());
				Integer distanceFromDownTeam = pointsDownTargetTeam - rr.getAllPoints();
				if (distanceFromDownTeam > 9 ){
					break;
				}
				if (availablePoints - distanceFromDownTeam > maxAvailablePoints){
					maxAvailablePoints = availablePoints;
				}
			}
		}
		return maxAvailablePoints;
	}



	private static Integer getAvailablePoints(int size, Integer allPlayedMatches) {
		Integer champMatchesTot = (size-1) * 2;
		if (champMatchesTot < 30)
			champMatchesTot *= 2;
		Integer remainMatches = champMatchesTot - allPlayedMatches;;
		return remainMatches * 3;
	}





	private static Integer getRange(Integer position, ArrayList<Integer> importantPositions) {
		for (int j = 0; j < importantPositions.size(); j++) {
			Integer currentPos = importantPositions.get(j);
			if (position <= currentPos) {
				return j;
			}
		}
		return importantPositions.size()-1;
	}



	private static void calculateDistanceForTarget(ArrayList<RankingRow> ranking, ChampEnum champ, RankingRow rr, int proximityTarget) {
		Distances distances = new Distances();
		calculatePointsToUpTarget(distances, rr, champ.getImportantPositions(), ranking, proximityTarget, champ);
		
		calculatePointsToDownTarget(distances, rr, champ.getImportantPositions(), ranking, proximityTarget);

		if (rr.getPosition()!=1){
			RankingRow prevRR = ranking.get(rr.getPosition()-2);
			if (prevRR.getAllPoints() == rr.getAllPoints()){
				if (prevRR.getDistances().size() != 0) {
					Distances prevDistances = prevRR.getDistances().get(proximityTarget);
					
					distances.setDistanceFromUpTarget(prevDistances.getDistanceFromUpTarget());
					distances.setTeamsFromUpTarget(prevDistances.getTeamsFromUpTarget());
					distances.setPointOfDisadvantageFromUpTarget(prevDistances.getPointOfDisadvantageFromUpTarget());
					distances.setDistanceFromDownTarget(prevDistances.getDistanceFromDownTarget());
					distances.setTeamsFromDownTarget(prevDistances.getTeamsFromDownTarget());
					distances.setPointOfAdvantageFromDownTarget(prevDistances.getPointOfAdvantageFromDownTarget());
				}
				
			}
		}
		

		
		
		
		rr.getDistances().add(proximityTarget, distances);
		
	}


	private static double getSaveMotivationModifier(Integer distanceToSave, Integer  pointsAgainstAllOpponents) {
		distanceToSave = distanceToSave >= 0 ? distanceToSave : -distanceToSave;
		Double motivationalModifier;
		if (distanceToSave <= 2 && pointsAgainstAllOpponents < 15)
			motivationalModifier = 1.9;
		else if (distanceToSave <= 4 && pointsAgainstAllOpponents < 15)
			motivationalModifier = 1.7;
		else if (distanceToSave <= 6 && pointsAgainstAllOpponents < 15)
			motivationalModifier = 1.5;
		else if (distanceToSave <= 8 && pointsAgainstAllOpponents < 15)
			motivationalModifier = 1.3;
		else //if (&& distanceToSave <= 10)
			motivationalModifier = 1.1;
		return motivationalModifier;
	}



	private static double getWinMotivationModificator(Integer distanceToWin) {
		
		Double motivationalModifier;
		if (distanceToWin < 2)
			motivationalModifier = 2.0;
		else if (distanceToWin < 4)
			motivationalModifier = 1.8;
		else if (distanceToWin < 6)
			motivationalModifier = 1.6;
		else if (distanceToWin < 8)
			motivationalModifier = 1.4;
		else //if (distanceToWin < 10)
			motivationalModifier = 1.2;
		return motivationalModifier;
	}



	private static void calculatePointsToUpTarget(Distances distances, RankingRow rr, ArrayList<Integer> importantPositions, ArrayList<RankingRow> ranking, Integer targetProximity, ChampEnum champ) {
		// chi insegue deve essere ottimista e ipotizzare che chi sta davanti perderà tutte le partite da recuperare e che lei stessa vincerà tutte le partite da recuperare 
		
		Integer pointsFromUp = null;
		Integer teamsFromUpTarget = null;
		Integer pointOfDisadvantageFromUpTarget = null;
		Integer teamPosition = rr.getPosition();
		for (int i = 0; i < importantPositions.size(); i++) {
			Integer targetPosition = importantPositions.get(i);
			if (teamPosition <= targetPosition){
				if (i != 0) {
					if ( i-1-targetProximity < 0 ){
						return;
					}
					Integer positionOfUpTarget = importantPositions.get(i - 1 - targetProximity); //positionOfUpTarget = index nell'array + 1  
					pointsFromUp = ranking.get(positionOfUpTarget - 1).getAllPoints() - rr.getAllPoints();
					teamsFromUpTarget = teamPosition - positionOfUpTarget;
					pointOfDisadvantageFromUpTarget = getPointOfDisadvantageFromUpTarget(teamPosition, positionOfUpTarget, ranking );
				}
				break;
			}
//			else if (rr.getPosition() == targetPosition) {
//				if (i != 0){
//					Integer positionOfUpTarget = importantPositions.get(i-1); //positionOfUpTarget = index nell'array + 1  
//					pointsFromUp = ranking.get(positionOfUpTarget - 1).getAllPoints() - rr.getAllPoints();
//				}
//				break;
//				
//			}
			else {  //if (teamPosition > pos) {
				continue;
			}
		}
		
		distances.setPointOfDisadvantageFromUpTarget(pointOfDisadvantageFromUpTarget);
		distances.setTeamsFromUpTarget(teamsFromUpTarget);
		distances.setDistanceFromUpTarget(pointsFromUp);
		
	}

	private static Integer getPointOfDisadvantageFromUpTarget(Integer teamPosition, Integer targetPosition,	ArrayList<RankingRow> ranking) {
		Integer pointOfDisadvantageFromUpTarget = 0;
		Integer arrayTeamPosition = teamPosition-1;
		Integer arrayTargetPosition = targetPosition-1;
		Integer teamPoints = ranking.get(arrayTeamPosition).getAllPoints();
		
		RankingRow rr;
		for (int i = arrayTargetPosition; i < arrayTeamPosition; i++){// il + 1 è per fargli saltare la prima iterazione che è sempre 0
			rr = ranking.get(i);
			pointOfDisadvantageFromUpTarget +=  rr.getAllPoints() - teamPoints ;
		}
		return pointOfDisadvantageFromUpTarget;
	}



	private static void calculatePointsToDownTarget(Distances distances, RankingRow rr, ArrayList<Integer> importantPositions, ArrayList<RankingRow> ranking, Integer targetProximity) {
		Integer pointsFromDown = null;
		Integer teamsFromDownTarget = null;
		Integer pointOfAdvantageFromDownTarget = null;
		Integer teamPosition = rr.getPosition();
		for (int i = 0; i < importantPositions.size(); i++) {
			if ( i + targetProximity >= importantPositions.size() ){
				return;
			}
			Integer targetPosition = importantPositions.get(i);
			if (teamPosition <= targetPosition){
				targetPosition = importantPositions.get(i + targetProximity);
				if (targetPosition != ranking.size()){
					pointsFromDown = rr.getAllPoints() - ranking.get(targetPosition).getAllPoints(); //targetPosition vale position -1 
					teamsFromDownTarget = targetPosition - teamPosition + 1;
					pointOfAdvantageFromDownTarget = getPointOfAdvantageFromDownTarget(teamPosition, targetPosition, ranking );
				}
				break;
			}
			
//			else if (rr.getPosition() == targetPosition) {
//				if (targetPosition != ranking.size())
//					pointsFromDown = rr.getAllPoints() - ranking.get(targetPosition).getAllPoints();
//				break;
//				
//			}
			else {  //if (teamPosition > pos) {
				continue;
			}
		}
		distances.setPointOfAdvantageFromDownTarget(pointOfAdvantageFromDownTarget);
		distances.setTeamsFromDownTarget(teamsFromDownTarget);
		distances.setDistanceFromDownTarget(pointsFromDown);
	}
	

	private static Integer getPointOfAdvantageFromDownTarget(Integer teamPosition, Integer targetPosition, ArrayList<RankingRow> ranking) {
		Integer pointOfAdvantageFromDownTarget = 0;
		Integer arrayTeamPosition = teamPosition-1;
		Integer arrayTargetPosition = targetPosition-1;
		Integer teamPoints = ranking.get(arrayTeamPosition).getAllPoints();
		
		for (int i = arrayTeamPosition; i <= arrayTargetPosition + 1; i++){
			RankingRow rr = ranking.get(i);
			pointOfAdvantageFromDownTarget += teamPoints - rr.getAllPoints();
		}
		return pointOfAdvantageFromDownTarget;
	}



	private static void calculateAvgs(RankingRow rr) {
		rr.setHomeAvgScoredGoals(new Double(rr.getHomeScoredGoals()) / new Double(rr.getHomePlayedMatches()));
		rr.setHomeAvgTakenGoals(new Double(rr.getHomeTakenGoals()) / new Double(rr.getHomePlayedMatches()));
		rr.setAwayAvgScoredGoals(new Double(rr.getAwayScoredGoals()) / new Double(rr.getAwayPlayedMatches()));
		rr.setAwayAvgTakenGoals(new Double(rr.getAwayTakenGoals()) / new Double(rr.getAwayPlayedMatches()));
		rr.setAllAvgScoredGoals(new Double(rr.getAllScoredGoals()) / new Double(rr.getAllPlayedMatches()));
		rr.setAllAvgTakenGoals(new Double(rr.getAllTakenGoals()) / new Double(rr.getAllPlayedMatches()));
		
	}



	private static void updateRankingRow(RankingRow rr, String teamName, MatchResult m) {
		
		Boolean isHomeMatch = teamName.equals(m.getHomeTeam());
		
		Integer homeGoals = m.getFTHG();
		Integer awayGoals = m.getFTAG();
		Integer scoredGoals;
		Integer takenGoals;
		if (isHomeMatch){
			scoredGoals = homeGoals;
			takenGoals = awayGoals;
		}
		else {
			scoredGoals = awayGoals;
			takenGoals = homeGoals;
		}
		Integer points;
		if (scoredGoals > takenGoals)
			points = 3;
		else if (scoredGoals < takenGoals)
			points = 0;
		else
			points = 1;
		
		//HOME
		if (isHomeMatch){
			rr.setHomePoints(rr.getHomePoints() + points);
			rr.setHomePlayedMatches(rr.getHomePlayedMatches() + 1);
			if (points == 3)
				rr.setHomeWin(rr.getHomeWin()+1);
			else if (points == 0)
				rr.setHomeLose(rr.getHomeLose()+1);
			else
				rr.setHomeDraw(rr.getHomeDraw()+1);
			rr.setHomePoints(rr.getHomePoints()+points);
			rr.setHomeScoredGoals(rr.getHomeScoredGoals() + scoredGoals);
			rr.setHomeTakenGoals(rr.getHomeTakenGoals() +takenGoals);
			
		}
		//AWAY
		else {
			rr.setAwayPoints(rr.getAwayPoints() + points);
			rr.setAwayPlayedMatches(rr.getAwayPlayedMatches() + 1);
			if (points == 3)
				rr.setAwayWin(rr.getAwayWin()+1);
			else if (points == 0)
				rr.setAwayLose(rr.getAwayLose()+1);
			else
				rr.setAwayDraw(rr.getAwayDraw()+1);
			rr.setAwayPoints(rr.getAwayPoints()+points);
			rr.setAwayScoredGoals(rr.getAwayScoredGoals() + scoredGoals);
			rr.setAwayTakenGoals(rr.getAwayTakenGoals() +takenGoals);
		}
		//ALL
		rr.setAllPlayedMatches(rr.getAllPlayedMatches() + 1);
		if (points == 3)
			rr.setAllWin(rr.getAllWin()+1);
		else if (points == 0)
			rr.setAllLose(rr.getAllLose()+1);
		else
			rr.setAllDraw(rr.getAllDraw()+1);
		rr.setAllPoints(rr.getAllPoints()+points);
		rr.setAllScoredGoals(rr.getAllScoredGoals() + scoredGoals);
		rr.setAllTakenGoals(rr.getAllTakenGoals() +takenGoals);
		
	}


	private static void applyCriteria(final ChampEnum champ, ArrayList<RankingRow> ranking) {
		
		
		Collections.sort(ranking, new Comparator<RankingRow>() {
			public int compare(RankingRow o1, RankingRow o2) {
				int compare = 0;
				for (RankCritEnum crit : champ.getRankCriteria()){
					if ( compare != 0 )
						return compare;
					else {
						switch (crit) {
						case POINTS:
							compare = o2.getAllPoints().compareTo(o1.getAllPoints());
							break;
							
						case GOALS_DIFFERENCE:
							compare = (o2.getAllScoredGoals() - o2.getAllTakenGoals()) - (o1.getAllScoredGoals() - o1.getAllTakenGoals());
							break;
							
						case GOALS_SCORED:
							compare = o2.getAllScoredGoals() - o1.getAllScoredGoals();
							break;
							
						case HEAD_TO_HEAD_POINTS:
							ArrayList<MatchResult> matchesResults;
							matchesResults = getMatchesResult(o1.getTeamName(), o2.getTeamName());
							compare = getHeadToHeadPointsCompare(o1, o2, matchesResults);
							break;
							
						case HEAD_TO_HEAD_GOALS_DIFFERENCE:
							matchesResults = getMatchesResult(o1.getTeamName(), o2.getTeamName());
							compare = getHeadToHeadGoalsDifferenceCompare(o1, o2, matchesResults);
							break;
							
						case HEAD_TO_HEAD_GOALS_SCORED_AWAY:
							matchesResults = getMatchesResult(o1.getTeamName(), o2.getTeamName());
							compare = getHeadToHeadGoalsScoredAwayCompare(o1, o2, matchesResults);
							break;
							
						case GOALS_SCORED_AWAY:
							compare = o2.getAwayScoredGoals() - o1.getAwayScoredGoals();
							break;
	
						case GOALS_TAKEN:
							compare = o1.getAllTakenGoals() - o2.getAllTakenGoals();
							break;
						
						case MATCHES_WON:
							compare = o2.getAllWin() - o1.getAllWin();
							break;
							
						case MATCHES_WON_AWAY:
							compare = o2.getAwayWin() - o1.getAwayWin();
							break;
	
						default:
							compare = 0;
							break;
						}
					}	
				}
				return compare;
				
			}

			private int getHeadToHeadGoalsScoredAwayCompare(RankingRow o1,	RankingRow o2, ArrayList<MatchResult> matchesResults) {
				int compare;
				int o1GoalsAway = 0;
				int o2GoalsAway = 0;
				for (MatchResult m : matchesResults){
					if (m.getHomeTeam().equals(o1.getTeamName())){
						o2GoalsAway += m.getFTAG();
					}
					else if (m.getHomeTeam().equals(o2.getTeamName())){
						o1GoalsAway += m.getFTAG();
					}
							
				}
				compare = o2GoalsAway - o1GoalsAway;
				return compare;
			}

			private int getHeadToHeadGoalsDifferenceCompare(RankingRow o1,	RankingRow o2, ArrayList<MatchResult> matchesResults) {
				int compare;
				int o1Goals = 0;
				int o2Goals = 0;
				for (MatchResult m : matchesResults){
					if (m.getHomeTeam().equals(o1.getTeamName())){
						o1Goals += m.getFTHG();
						o2Goals += m.getFTAG();
					}
					else if (m.getHomeTeam().equals(o2.getTeamName())){
						o1Goals += m.getFTAG();
						o2Goals += m.getFTHG();
					}
							
				}
				compare = o2Goals - o1Goals;
				return compare;
			}

			private int getHeadToHeadPointsCompare(RankingRow o1, RankingRow o2, ArrayList<MatchResult> matchesResults) {
				int compare;
				int o1Points = 0;
				int o2Points = 0;
				for (MatchResult m : matchesResults){
					if (m.getHomeTeam().equals(o1.getTeamName())){
						if (m.getFTHG() > m.getFTAG()){
							o1Points += 3;
							o2Points += 0;
						}
						if (m.getFTHG() < m.getFTAG()){
							o1Points += 0;
							o2Points += 3;
						}
						if (m.getFTHG() == m.getFTAG()){
							o1Points += 1;
							o2Points += 1;
						}
					}
					else if (m.getHomeTeam().equals(o2.getTeamName())){
						if (m.getFTHG() > m.getFTAG()){
							o1Points += 0;
							o2Points += 3;
						}
						if (m.getFTHG() < m.getFTAG()){
							o1Points += 3;
							o2Points += 0;
						}
						if (m.getFTHG() == m.getFTAG()){
							o1Points += 1;
							o2Points += 1;
						}
					}
							
				}
				compare = o2Points - o1Points;
				return compare;
			}

			private ArrayList<MatchResult> getMatchesResult(String teamName1, String teamName2) {
				ArrayList<MatchResult> matches = new ArrayList<MatchResult>();
				for (MatchResult matchResult : teamsToMatchesAll.get(champ).get(teamName1)){
					if (	matchResult.getHomeTeam().equals(teamName1)  && matchResult.getAwayTeam().equals(teamName2) ||
							matchResult.getHomeTeam().equals(teamName2)  && matchResult.getAwayTeam().equals(teamName1)
						){
						matches.add(matchResult);
					}
				}
				
				return matches;
			}

		});
	}
	
	
	public void printRanking(List<RankingRow> ranking, ChampEnum champ) {
		 String header = 
				 "\n" + champ.getRankCriteria().get(1) +

			// ------------------   BASE INFO   -----------------------	
				 
				 "\nPos"  + "\t" + Utils.redimString("Team", 17) + "\t" + "Points"  + "\t" + "Match" + "\t|" +
						 
				 
				 
			// ------------------   MOTIVATION   -----------------------	
				 "fin" + "\t| " + 
				 "DOP" + "\t" + 
				 "DOPu" + "\t" + 
				 "DOPd" + "\t| " + 
				 "DO" + "\t" + 
				 "DOu" + "\t" + 
				 "DOd" + "\t| " + 
				 "D" + "\t" + 
				 "Du" + "\t" + 
				 "Dd" + "\t| " + 
				 
				 
				
				// ------------------   W - D - L   -----------------------				 

				

//				 "W" + "\t" + "D" + "\t" + "L" + "\t| " + 
//				 "GF" + "\t" + "GS" + "\t"  +  "DR" + "\t| " + 
//
//				 
//				 "WH" + "\t" + "DH" + "\t" + "LH" + "\t| " + 
////				 "GFH" + "\t" + "GSH" + "\t"  +  "DRH" + "\t| " + 
//				 
//
//				 "WA" + "\t" + "DA" + "\t" + "LA" + "\t| " + 
////				 "GFA" + "\t" + "GSA" + "\t"  +  "DRA" + "\t| " +
				 

				
				// ------------------   DISTANCES   -----------------------
				
				
				 "uTP" + "\t" + "uP" + "\t" + "dTP" + "\t" + "dP" + "\t| " + 
				 "2uTP" + "\t" + "2uP" + "\t" + "2dTP" + "\t" + "2dP" + "\t| " + 
//				 "3uTP" + "\t" + "3uP" + "\t" + "3dTP" + "\t" + "3dP" +
		 		"\n";
		 
		System.out.println(header);
		for (int j = 0; j < ranking.size(); j++) {
			RankingRow rr = ranking.get(j);
			for ( Integer pos: champ.getImportantPositions()){
				if (j == pos){
					System.out.println("----------------");
					break;
				}
			}
			System.out.print(rr);
		}
		
	}
	
}
