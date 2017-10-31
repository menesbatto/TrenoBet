package app.dao.tipologiche;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tipologiche.entities.HomeVariationType;

@RepositoryRestResource
public interface HomeVariationTypeRepo extends JpaRepository<HomeVariationType, Long> {

	HomeVariationType findByValue(String value);

//	List<Person> findByLastName(@Param("name") String name);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
