package app.dao.tipologiche.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HomeVariationType {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="value", unique=true)
	private String value;

	public HomeVariationType() {
	}

	public HomeVariationType(String value) {
		this.value = value;
	}
	public HomeVariationType(int id, String value) {
		this.id = id;
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
	public String toString() {
		return "" + value + "";
	} 
	
	
	//	m9, m8, m7, m6, m5, m4, m3, m2, m1, p1, p2, p3, p4, p5, p6, p7, p8, p9 

	
	
}
