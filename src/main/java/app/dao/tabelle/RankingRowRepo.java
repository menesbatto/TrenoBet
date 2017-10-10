package app.dao.tabelle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.RankingRowEnt;

@RepositoryRestResource
public interface RankingRowRepo extends JpaRepository<RankingRowEnt, Long> {

	List<RankingRowEnt> findByChamp(Champ champ);
//
//	List<BetHouse> findByValue(@Param("value") String string);

//	List<Team> findByNameAndChamp(String name, Champ champ);
//
//	List<Team> findByChamp(Champ champ);
	
	
//	List<Person> findByLastName(@Param("name") String name);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
