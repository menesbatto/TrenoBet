package app.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.ChampDao;
import app.dao.tabelle.GoalsStatsRepo;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.MatchoRepo;
import app.dao.tabelle.RankingRowRepo;
import app.dao.tabelle.TeamDao;
import app.dao.tabelle.WinRangeStatsRepo;
import app.dao.tabelle.entities.Champ;
import app.dao.tipologiche.BetHouseDao;
import app.dao.tipologiche.HomeVariationTypeDao;
import app.dao.tipologiche.OddsRangeDao;
import app.dao.tipologiche.RankingCriteriaDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.utils.ChampEnum;

@Service
public class UtilityModel {



	@Autowired
	private ChampDao champDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private RankingCriteriaDao rankingCriteriaDao;

	@Autowired
	private BetHouseDao betHouseDao;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private UoThresholdTypeDao uoThresholdTypeDao;
	
	@Autowired
	private HomeVariationTypeDao homeVariationTypeDao;
	
	@Autowired
	private OddsRangeDao oddsRangeDao;

	@Autowired
	private WinRangeStatsRepo winRangeStatsRepo;

//	@Autowired
//	private WinEhRangeStatsRepo winEhRangeStatsRepo;
	
	@Autowired	
	private GoalsStatsRepo goalsStatsRepo;

	@Autowired
	private MatchoRepo matchoRepo;
	
	@Autowired
	private RankingRowRepo rankingRowRepo;
	
	
	
	public void deleteAllRankings() {
		rankingRowRepo.deleteAll();
	}
	
	public void deleteAllMatches() {
		matchoRepo.deleteAll();
	}
	
	public void deleteAllWinRangeStats() {
		winRangeStatsRepo.deleteAll();
	}		
	public void deleteAllGoalsStats() {
		goalsStatsRepo.deleteAll();
	}		

	public void deleteWinRangeStatsBySeasonDay(Integer seasonDay) {
		winRangeStatsRepo.deleteBySeasonDay(seasonDay);
	}

	public void deleteGoalsStatsBySeasonDay(Integer seasonDay) {
		goalsStatsRepo.deleteBySeasonDay(seasonDay);
	}
	
	

	public void execute() {
		betHouseDao.initTable();
		timeTypeDao.initTable();
		rankingCriteriaDao.initTable();
		uoThresholdTypeDao.initTable();
		homeVariationTypeDao.initTable();
		oddsRangeDao.initTable();
		
	}
	
	
	public void initChampsTable() {
		champDao.initTable();
	}

	
	public Champ saveChamp(ChampEnum champEnum) {
		Champ saveChamp = champDao.saveChamp(champEnum);
		return saveChamp;
	}



	
}
