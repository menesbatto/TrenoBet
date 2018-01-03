package app.dao.tipologiche;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import app.dao.tabelle.ChampDao;
import app.dao.tabelle.TeamDao;
import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Team;
import app.dao.tipologiche.entities.Penality;
import app.utils.ChampEnum;

@Service
@EnableCaching
public class PenalityDao {

	@Autowired
	private PenalityRepo penalityRepo;
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private ChampDao champDao;
	
	@Cacheable("penality")
	public Map<String, Integer> findByChamp(ChampEnum champEnum) {
		Champ champ = champDao.findByChampEnum(champEnum);
		List<Penality> entities = penalityRepo.findByTeamChamp(champ);
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Penality ent : entities) {
			map.put(ent.getTeam().getName(), ent.getPoints());
		}
		
		return map;
	}
	

	public void insertPenality(ChampEnum champEnum, String teamName, Integer points) {
		Champ champ  = champDao.findByChampEnum(champEnum);
		Penality p = new Penality();
		Team team = teamDao.findByNameAndChamp(teamName, champ);
		p.setTeam(team);
		p.setChamp(champ);
		p.setPoints(points);
		if (team == null)
			System.out.println("Non è stato trovato nessun team");
		penalityRepo.save(p);
	}

}
