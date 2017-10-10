package app.dao.tipologiche;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.RankingCriteria;
import app.dao.tipologiche.entities.TimeType;

@Service
public class RankingCriteriaDao {

	@Autowired
	private RankingCriteriaRepo rankingCriteriaRepo;
	
	private HashMap<String, RankingCriteria> cacheMap;
	
	public RankingCriteria findByValue(String value) {
		if (cacheMap == null || cacheMap.isEmpty())
			initCacheMap();
			
		RankingCriteria entity = cacheMap.get(value);
		
		return entity;
	}
	
	public void initTable() {
		//	POINTS,			HEAD_TO_HEAD_POINTS,	HEAD_TO_HEAD_GOALS_DIFFERENCE,	HEAD_TO_HEAD_GOALS_SCORED_AWAY,		MATCHES_WON,
		//	GOALS_SCORED,	GOALS_TAKEN,			GOALS_DIFFERENCE,				GOALS_SCORED_AWAY,					MATCHES_WON_AWAY,
		RankingCriteria POINTS = new RankingCriteria("POINTS");
		rankingCriteriaRepo.save(POINTS);
		RankingCriteria HEAD_TO_HEAD_POINTS = new RankingCriteria("HEAD_TO_HEAD_POINTS");
		rankingCriteriaRepo.save(HEAD_TO_HEAD_POINTS);
		RankingCriteria HEAD_TO_HEAD_GOALS_DIFFERENCE = new RankingCriteria("HEAD_TO_HEAD_GOALS_DIFFERENCE");
		rankingCriteriaRepo.save(HEAD_TO_HEAD_GOALS_DIFFERENCE);
		RankingCriteria HEAD_TO_HEAD_GOALS_SCORED_AWAY = new RankingCriteria("HEAD_TO_HEAD_GOALS_SCORED_AWAY");
		rankingCriteriaRepo.save(HEAD_TO_HEAD_GOALS_SCORED_AWAY);
		RankingCriteria MATCHES_WON = new RankingCriteria("MATCHES_WON");
		rankingCriteriaRepo.save(MATCHES_WON);
		RankingCriteria GOALS_SCORED = new RankingCriteria("GOALS_SCORED");
		rankingCriteriaRepo.save(GOALS_SCORED);
		RankingCriteria GOALS_TAKEN = new RankingCriteria("GOALS_TAKEN");
		rankingCriteriaRepo.save(GOALS_TAKEN);
		RankingCriteria GOALS_DIFFERENCE = new RankingCriteria("GOALS_DIFFERENCE");
		rankingCriteriaRepo.save(GOALS_DIFFERENCE);
		RankingCriteria GOALS_SCORED_AWAY = new RankingCriteria("GOALS_SCORED_AWAY");
		rankingCriteriaRepo.save(GOALS_SCORED_AWAY);
		RankingCriteria MATCHES_WON_AWAY = new RankingCriteria("MATCHES_WON_AWAY");
		rankingCriteriaRepo.save(MATCHES_WON_AWAY);


	}
	
	

	private void initCacheMap() {
		cacheMap = new HashMap<String, RankingCriteria>();
		Iterable<RankingCriteria> findAll = rankingCriteriaRepo.findAll();
		for (Iterator<RankingCriteria> iter = findAll.iterator(); iter.hasNext(); ) {
			RankingCriteria element = iter.next();
			cacheMap.put(element.getValue(), element);
		}	
	}
	

}
