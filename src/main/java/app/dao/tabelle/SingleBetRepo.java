package app.dao.tabelle;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Matcho;
import app.dao.tabelle.entities.SingleBet;
import app.utils.ChampEnum;

@RepositoryRestResource
public interface SingleBetRepo extends JpaRepository<SingleBet, Long> {

	List<SingleBet> findByMatchoChampAndWinIsNull(Champ champ);


	List<SingleBet> findByMatchoChampAndSeasonDay(Champ champ, Integer seasonDay);
	
	void deleteByMatchoChampAndSeasonDay(Champ champ, Integer seasonDay);


	
	
	
	
//	List<SingleBet> findByMatchoChampAndMatchoMatchDateBetween(Champ champ, Date startDate, Date endDate);
//	MoreThanEqualAndMatchoMatchDateLessThanEqual
//	List<SingleBet> findByMatchoChampAndMatchoFullTimeResultIsNotNull(Champ champ);
//	List<Champ> findByNameAndStartYearAndNation(String name, int startYear, String nation);
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
