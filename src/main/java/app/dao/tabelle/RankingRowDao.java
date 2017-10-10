package app.dao.tabelle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.RankingRowEnt;
import app.dao.tabelle.entities.Team;
import app.logic._3_rankingCalculator.model.RankingRow;
import app.utils.ChampEnum;
import ma.glasnost.orika.MapperFacade;

@Service
public class RankingRowDao {

	@Autowired
	private RankingRowRepo rankingRowRepo;

	@Autowired
	private ChampDao champDao;
	
	private HashMap<Champ, HashMap<String, Team>> cacheMap;

	@Autowired
	private MapperFacade mapper;
	
//	public Team findByTeamBean(TeamBean team) {
//		List<Team> list = teamRepo.findByName(team.getName());
//		Team first = list.get(0);
//		return first;
//		
//	}

	public List<RankingRow> findByChamp(ChampEnum champEnum) {
		Champ champ = champDao.findByChampEnum(champEnum);
		List<RankingRowEnt> rankingRowsEnt = rankingRowRepo.findByChamp(champ);
		List<RankingRow> rankingRowBean = new ArrayList<RankingRow>();
		for (RankingRowEnt ent : rankingRowsEnt) {
			RankingRow bean = new RankingRow();
			mapper.map(ent, bean);
			rankingRowBean.add(bean);
		}
		return rankingRowBean;
	}
	
	
//	public Team findByNameAndChamp(String name, Champ champ) {
//		Team first = findInCache(name, champ);
//		if (first == null) {
//			//List<Team> list = teamRepo.findByNameAndChamp(name, champ);
//			List<Team> list = teamRepo.findByName(name);
//			if (list.isEmpty())
//				first = saveTeam(name, champ);
//			else 
//				first = list.get(0);
//		}
//		return first;
//	}
	
	public List<RankingRow> saveRanking(ChampEnum champEnum, List<RankingRow> rankingBean) {
		Champ champ = champDao.findByChampEnum(champEnum);
		List<RankingRowEnt> rankingEnt = rankingRowRepo.findByChamp(champ);
		for (RankingRowEnt ent : rankingEnt) {
			for (RankingRow bean : rankingBean) {
				if (ent.getTeam().getName().equals(bean.getTeamName())) {
					mapper.map(bean, ent);
				}
			}
		}
		rankingRowRepo.save(rankingEnt);
		
		return rankingBean;
		
	}

	
	
	
	
	
	
	

	
	
	
}
