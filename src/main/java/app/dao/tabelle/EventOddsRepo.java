package app.dao.tabelle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.EventOdds;

@RepositoryRestResource
public interface EventOddsRepo extends JpaRepository<EventOdds, Long> {

//	List<BetHouse> findByValue(@Param("value") String string);


	void deleteByMatchChamp(Champ champ);
	
	EventOdds findById(Integer id);

	List<EventOdds> findByMatchChampAndSeasonDay(Champ champ, Integer seasonDay);

	void deleteByMatchChampAndSeasonDay(Champ champ, Integer seasonDay);

	EventOdds findFirstByMatchChampAndSeasonDay(Champ champ, Integer seasonDay);

	void deleteByMatchChampIsNull();

	void deleteBySeasonDay(Integer seasonDay);

	
	void deleteByMatchId(Integer idMatch);
	
//	List<GoalsStats> findByTeamAndPlayingField(Team team, String playingField);
//
//	int countByTeam(Team team);
//
//	GoalsStats findFirstByTeamAndPlayingField(Team teamEnt, String playingField);
//	
//	List<GoalsStats>  findByTeamChampOrderByTeam(Champ champ);
//	
//	List<Person> findByLastName(@Param("name") String name);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
