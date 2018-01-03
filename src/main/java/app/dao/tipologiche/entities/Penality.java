package app.dao.tipologiche.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Team;

@Entity
public class Penality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Team team;

	@ManyToOne
	private Champ champ;
	
	private Integer points;
	
	public Penality() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Champ getChamp() {
		return champ;
	}

	public void setChamp(Champ champ) {
		this.champ = champ;
	}


	
	
	
	

	


}