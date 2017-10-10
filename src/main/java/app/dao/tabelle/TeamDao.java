package app.dao.tabelle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Team;
import app.utils.ChampEnum;

@Service
public class TeamDao {

	@Autowired
	private TeamRepo teamRepo;

	@Autowired
	private ChampDao champDao;
	
	private HashMap<Champ, HashMap<String, Team>> cacheMap;

	
	
	public ArrayList<String> findByChamp(ChampEnum champEnum) {
		Champ champ = champDao.findByChampEnum(champEnum);
		List<Team> teams = teamRepo.findByChamp(champ);
		ArrayList<String> teamsNames = new ArrayList<String>();
		for (Team team : teams) {
			teamsNames.add(team.getName());
		}
		return teamsNames;
	}
	
	public Team findByNameAndChamp(String name, Champ champ) {
		Team first = findInCache(name, champ);
		if (first == null) {
			//List<Team> list = teamRepo.findByNameAndChamp(name, champ);
			List<Team> list = teamRepo.findByName(name);
			if (list.isEmpty())
				first = saveTeam(name, champ);
			else 
				first = list.get(0);
		}
		return first;
	}
	
	private Team saveTeam(String name, Champ champ) {
		Team team = new Team();
		team.setName(name);
		team.setChamp(champ);
		teamRepo.save(team);
		return team;
		
	}

	private Team findInCache(String teamName, Champ champ) {
		if (cacheMap == null) {
			cacheMap = new HashMap<Champ, HashMap<String, Team>>();
		}
		HashMap<String, Team> teamMap = cacheMap.get(champ);
		if (teamMap == null)
			teamMap = new HashMap<String, Team>();
		
		return teamMap.get(teamName);
	}
	
	public void removeTeamById(Long id) {
		teamRepo.delete(id);
		return;
	}
	
	
}
