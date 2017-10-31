package app.dao.tipologiche;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tipologiche.entities.UoThresholdType;

@RepositoryRestResource
public interface UoThresholdTypeRepo extends JpaRepository<UoThresholdType, Long> {

	UoThresholdType findByValue(String value);

//	List<Person> findByLastName(@Param("name") String name);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
