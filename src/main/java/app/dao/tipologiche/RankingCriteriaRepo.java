package app.dao.tipologiche;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tipologiche.entities.RankingCriteria;

@RepositoryRestResource
public interface RankingCriteriaRepo extends JpaRepository<RankingCriteria, Long> {

	void findByValue(String name);

//	List<BetHouse> findAll();
//
//	List<BetHouse> findByValue(@Param("value") String string);
	
	
//	List<Person> findByLastName(@Param("name") String name);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
