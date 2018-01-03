package app.dao.tipologiche;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.Champ;
import app.dao.tipologiche.entities.Penality;

@RepositoryRestResource
public interface PenalityRepo extends JpaRepository<Penality, Long> {


	List<Penality> findByTeamChamp(Champ champ);


}
