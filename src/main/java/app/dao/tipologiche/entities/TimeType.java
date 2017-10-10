package app.dao.tipologiche.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimeType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="value", unique=true)
	private String value;

	public TimeType() {
	}

	public TimeType(String value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	} 
	
	
	@Override
	public boolean equals(Object obj) {
		TimeType tt = (TimeType)obj;
		return this.id == tt.getId();
	}
	
	
	//bet365, Betclic,  bwin, PaddyPower, Tipico, Unibet, WilliamHill
}
