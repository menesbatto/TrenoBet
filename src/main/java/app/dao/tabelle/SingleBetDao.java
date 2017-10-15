package app.dao.tabelle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.EventOdds;
import app.dao.tabelle.entities.Matcho;
import app.dao.tabelle.entities.SingleBet;
import app.dao.tipologiche.RankingCriteriaDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.entities.RankingCriteria;
import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchesDownlaoder.model.BetType;
import app.logic._1_matchesDownlaoder.model.EventOddsBean;
import app.logic._1_matchesDownlaoder.model.MatchResult;
import app.logic._1_matchesDownlaoder.model.MatchResultEnum;
import app.logic._1_matchesDownlaoder.model.SingleBetBean;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.utils.ChampEnum;
import app.utils.RankCritEnum;

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
	
	
	public List<SingleBetBean> retrieveSingleBetsToCheck(ChampEnum champEnum){
		
		Champ champ = champDao.findByChampEnum(champEnum);
		List<SingleBet> betEntList = singleBetRepo.findByMatchoChamp(champ);
		
		List<SingleBetBean> betBeanList = new ArrayList<SingleBetBean>();
		SingleBetBean betBean;
		for (SingleBet betEnt : betEntList)	{
			BetType betTypeEnum = BetType.valueOf(betEnt.getBetType());
			MatchResultEnum matchResultEnum = MatchResultEnum.valueOf(betEnt.getMatchResultForecast());
			TimeTypeEnum timeTypeEnum = timeTypeDao.findBeanByEnt(betEnt.getTimeType());

			
			
			
			betBean= new SingleBetBean(betTypeEnum,  matchResultEnum , betEnt.getWinOdds(), timeTypeEnum);
			
			MatchResult matchResultBean = matchoDao.mapMatchoToMatchResult(betEnt.getMatcho(), true);
			betBean.setMatch(matchResultBean);
			
			betBeanList.add(betBean);
			
		}
		
		return betBeanList; 				
	}
	
	
	public void saveBetResult(List<SingleBetBean> singleBetBeanList, ChampEnum champ) {
		List<SingleBet> singleBetEntList = new ArrayList<SingleBet>();
		SingleBet sbEnt;
		for (SingleBetBean sbBean : singleBetBeanList) {
			sbEnt = new SingleBet();
			sbEnt.setBetType(sbBean.getBetType().name());
			sbEnt.setMatchResultForecast(sbBean.getMatchResultEnum().name());
			sbEnt.setWinOdds(sbBean.getWinOdds());
			
			Matcho matcho = matchoDao.findById(sbBean.getMatchId());
			sbEnt.setMatcho(matcho);
			
			TimeType timeType = timeTypeDao.findByValue(sbBean.getTimeTypeEnum().name());
			sbEnt.setTimeType(timeType);
			
			
			singleBetEntList.add(sbEnt);
			
		}
		
		singleBetRepo.save(singleBetEntList);
	}
	
	
}
