package app.dao.tabelle;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.GoalsStats;
import app.dao.tabelle.entities.Team;
import app.dao.tabelle.entities.WinRangeStats;
import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.TimeType;

@RepositoryRestResource
public interface GoalsStatsRepo extends JpaRepository<GoalsStats, Long> {

//	List<Team> findByName(String name);
//
//	List<BetHouse> findByValue(@Param("value") String string);

	List<GoalsStats> findByTeamAndPlayingField(Team team, String playingField);

	int countByTeam(Team team);

	GoalsStats findFirstByTeamAndPlayingField(Team teamEnt, String playingField);
	
	List<GoalsStats>  findByTeamChampOrderByTeam(Champ champ);
	
//	List<Person> findByLastName(@Param("name") String name);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
