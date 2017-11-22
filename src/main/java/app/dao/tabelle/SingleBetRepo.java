package app.dao.tabelle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.SingleBet;

@RepositoryRestResource
public interface SingleBetRepo extends JpaRepository<SingleBet, Long> {

	List<SingleBet> findByMatchChampAndWinIsNull(Champ champ);


	List<SingleBet> findByMatchChampAndSeasonDay(Champ champ, Integer seasonDay);
	
	void deleteByMatchChampAndSeasonDay(Champ champ, Integer seasonDay);


	void deleteByMatchId(Integer idMatch);


	void deleteByChampId(Integer champId);
	
	
	
//	List<SingleBet> findByMatchoChampAndMatchoMatchDateBetween(Champ champ, Date startDate, Date endDate);
//	MoreThanEqualAndMatchoMatchDateLessThanEqual
//	List<SingleBet> findByMatchoChampAndMatchoFullTimeResultIsNotNull(Champ champ);
//	List<Champ> findByNameAndStartYearAndNation(String name, int startYear, String nation);
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
