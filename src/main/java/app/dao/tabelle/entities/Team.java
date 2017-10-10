package app.dao.tabelle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	
	@ManyToOne
//	@JoinColumn(name = "champ_id")
	private Champ champ;

	
	
	public Team() {
	}
	public Team(String name, Champ champ) {
		this.name = name;
		this.setChamp(champ);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Champ getChamp() {
		return champ;
	}
	public void setChamp(Champ champ) {
		this.champ = champ;
	}
	

	

}
