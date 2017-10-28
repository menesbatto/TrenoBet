package app.dao.tabelle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Matcho;
import app.dao.tabelle.entities.SingleBet;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchesDownlaoder.model.BetType;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.SingleBetBean;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.utils.ChampEnum;

@Service
public class SingleBetDao {

	@Autowired
	private SingleBetRepo singleBetRepo;

	@Autowired
	private TimeTypeDao timeTypeDao;
	
	@Autowired
	private MatchoDao matchoDao;

	@Autowired
	private ChampDao champDao;
	
	
	
	
	
	private List<SingleBetBean> mapEntToBean(List<SingleBet> betEntList) {
		List<SingleBetBean> betBeanList = new ArrayList<SingleBetBean>();
		SingleBetBean betBean;
		for (SingleBet betEnt : betEntList)	{
			BetType betTypeEnum = BetType.valueOf(betEnt.getBetType());
			MatchResultEnum matchResultEnum = MatchResultEnum.valueOf(betEnt.getMatchResultForecast());
			TimeTypeEnum timeTypeEnum = timeTypeDao.findBeanByEnt(betEnt.getTimeType());
			ChampEnum champ = champDao.findChampEnumByChamp(betEnt.getChamp());

			betBean= new SingleBetBean(betTypeEnum,  matchResultEnum , betEnt.getWinOdds(), timeTypeEnum, champ);
			
			MatchResult matchResultBean = matchoDao.mapMatchoToMatchResult(betEnt.getMatch(), true);
			betBean.setMatch(matchResultBean);
			
			betBean.setSeasonDay(betEnt.getSeasonDay());
			
			
			betBean.setMatchId(betEnt.getMatch().getId());
	
			betBeanList.add(betBean);
			
		}
		return betBeanList;
	}
	
	@Transactional
	public void saveBetResult(List<SingleBetBean> singleBetBeanList, ChampEnum champEnum) {
		
		if (singleBetBeanList.isEmpty()) {
			return;
		}
	
		List<SingleBet> singleBetEntList = new ArrayList<SingleBet>();
		SingleBet sbEnt;
		for (SingleBetBean sbBean : singleBetBeanList) {
			sbEnt = new SingleBet();
			sbEnt.setBetType(sbBean.getBetType().name());
			sbEnt.setMatchResultForecast(sbBean.getMatchResultForecast().name());
			sbEnt.setWinOdds(sbBean.getWinOdds());
			sbEnt.setSeasonDay(sbBean.getSeasonDay());
			Champ champ = champDao.findByChampEnum(champEnum);
			sbEnt.setChamp(champ);
			
			Matcho matcho = matchoDao.findById(sbBean.getMatchId());
			sbEnt.setMatch(matcho);
			
			TimeType timeType = timeTypeDao.findByValue(sbBean.getTimeTypeEnum().name());
			sbEnt.setTimeType(timeType);
			sbEnt.setSeasonDay(sbBean.getSeasonDay());
			sbEnt.setWin(sbBean.getWin());
			singleBetEntList.add(sbEnt);
			
		}
		singleBetRepo.save(singleBetEntList);
	}

	@Transactional
	public void deleteBetResultByChampAndSeasonDay(ChampEnum champEnum, Integer seasonDay) {
		Champ champ = champDao.findByChampEnum(champEnum);
		singleBetRepo.deleteByMatchChampAndSeasonDay(champ, seasonDay);
		
	}


	public List<SingleBetBean> retrieveSingleBetsToCheckInDateRange(ChampEnum champEnum, Integer seasonDay) {
		Champ champ = champDao.findByChampEnum(champEnum);
		
		List<SingleBet> singleBetEntList = singleBetRepo.findByMatchChampAndSeasonDay(champ, seasonDay);
		
		
		List<SingleBetBean> singleBetBeanList = mapEntToBean(singleBetEntList);
		return singleBetBeanList;
	}

	
	//###############################################################################################################################################

	public List<SingleBetBean> retrieveSingleBetsToCheckInDateRange(ChampEnum champEnum){
		
		List<SingleBetBean> betBeanList = new ArrayList<SingleBetBean>();
		
		
		return betBeanList; 
	}
	
	public List<SingleBetBean> retrieveSingleBetsToCheck(ChampEnum champEnum){
		
		Champ champ = champDao.findByChampEnum(champEnum);
		List<SingleBet> betEntList = singleBetRepo.findByMatchChampAndWinIsNull(champ);
		List<SingleBetBean> betBeanList = mapEntToBean(betEntList);
		
		return betBeanList; 				
	}

	public void removeByMatchId(Integer idMatch) {
		singleBetRepo.deleteByMatchId(idMatch);
		
	}

	
	
}
