package app.dao.tabelle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.Champ;

@RepositoryRestResource
public interface ChampRepo extends JpaRepository<Champ, Long> {

	Champ findByNameAndStartYearAndNation(String name, int startYear, String nation);
	
	Champ findById(Integer id);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
