package app.dao.tabelle;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Matcho;
import app.dao.tabelle.entities.Team;

@RepositoryRestResource
public interface MatchoRepo extends JpaRepository<Matcho, Long> {

	List<Matcho> findAll();

	List<Matcho> findByChampAndAndMatchDateBetween(Champ champ, Date startDate, Date endDate);
	
	List<Matcho> findByChampAndMatchDateBeforeOrderByMatchDateDesc(Champ champ, Date limitDate);

	
	
	

	List<Matcho> findByHomeTeamNameAndAwayTeamNameAndChamp(String homeTeam, String awayTeam, Champ champ);
	
	void deleteByChampAndFullTimeResultIsNull(Champ champ);

	void deleteById(Integer idMatch);

	Matcho findById(Integer id);

	Matcho findByHomeTeamNameAndAwayTeamName(String homeTeam, String awayTeam);
	
	void deleteByChampId(Integer champId);
	
//	###########################################################################################
	@Deprecated
	List<Matcho> findByChampAndHomeTeamAndFullTimeResultIsNotNullOrderByMatchDate(Champ champ, Team team);
	
	@Deprecated
	List<Matcho> findByChampAndAwayTeamAndFullTimeResultIsNotNullOrderByMatchDate(Champ champ, Team team);
	
	@Deprecated
	List<Matcho> findByChampAndFullTimeResultIsNotNullOrderByMatchDateDesc(Champ champ);
	
	@Deprecated
	List<Matcho> findByChampAndFullTimeResultIsNullAndMatchDateBetween(Champ champ, Date startDate, Date endDate);

	void deleteByChampIsNull();
	
//	@Query(value = "SELECT TOP ?1, * FROM MATCH WHERE CHAMP_ID = ?1", nativeQuery = true)
//	List<Matcho> findByChampAndAwayTeamAndFullTimeResultIsNotNullTopN(Champ champ, Team team);
//	List<Match> findByNameAndStartYearAndNation(String name, int startYear, String nation);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);

	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
