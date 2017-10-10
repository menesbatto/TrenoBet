package app.dao.tabelle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tipologiche.RankingCriteriaDao;
import app.dao.tipologiche.entities.RankingCriteria;
import app.utils.ChampEnum;
import app.utils.RankCritEnum;

@Service
public class ChampDao {

	@Autowired
	private ChampRepo champRepo;

	@Autowired
	private RankingCriteriaDao rankingCriteriaDao;
	
	private HashMap<ChampEnum, Champ> cacheMap;

	public Champ findByChampEnum(ChampEnum champEnum) {
		Champ first = findInCache(champEnum);
		if (first == null) {
			String name = champEnum.getName();
			int startYear = champEnum.getStartYear();
			String nation = champEnum.getNation();
			List<Champ> list = champRepo.findByNameAndStartYearAndNation(name, startYear, nation);
			first = list.get(0);
			
			cacheMap.put(champEnum, first);
		}
		return first;
	}

	

	private Champ findInCache(ChampEnum champEnum) {
		if (cacheMap == null) {
			cacheMap = new HashMap<ChampEnum, Champ>();
		}
		return cacheMap.get(champEnum);
	}
	
	

	
	public void initTable() {
		ChampEnum champEnum = ChampEnum.ENG_PREMIER;
		Champ champEnt = saveChamp(champEnum);
//		ChampImpPos champImpPosEnt =  saveChampImpPos(champEnum, champEnt);
		List<RankingCriteria> rankingCriteriaEnts =  getRankingCriteria(champEnum, champEnt);
		
//		champEnt.setImpPos(champImpPosEnt);
		champEnt.setRankingCriteria(rankingCriteriaEnts);
		champRepo.save(champEnt);
		//...
		System.out.println("");
		
	}
	
	private List<RankingCriteria> getRankingCriteria(ChampEnum champEnum, Champ champEnt) {
		List<RankingCriteria> criteriaEnt = new ArrayList<RankingCriteria>();
		RankingCriteria criteriumEnt;
		for (RankCritEnum criterium : champEnum.getRankCriteria()) {
			criteriumEnt = rankingCriteriaDao.findByValue(criterium.name());
			criteriaEnt.add(criteriumEnt);
		}
		
		return criteriaEnt;
	}



	private Champ saveChamp(ChampEnum champEnum) {
		Champ champEnt = new Champ(	champEnum.getName(),		champEnum.getStartYear(), 	champEnum.getNation(), 
									champEnum.getResultsUrl(), 	champEnum.getNextMatchesUrl());
		champRepo.save(champEnt);
		return champEnt;
	}
	
	
	
	
	
}
