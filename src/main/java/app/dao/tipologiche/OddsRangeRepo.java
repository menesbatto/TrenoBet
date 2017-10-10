package app.dao.tipologiche;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.OddsRange;

@RepositoryRestResource
public interface OddsRangeRepo extends PagingAndSortingRepository<OddsRange, Long> {

//	List<BetHouse> findAll();
//
//	List<BetHouse> findByValue(@Param("value") String string);
	
	
//	List<Person> findByLastName(@Param("name") String name);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
