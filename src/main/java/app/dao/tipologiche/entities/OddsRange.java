package app.dao.tipologiche.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OddsRange {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	

    @Column(name="valueDown", unique=true)
	private Double valueDown;

    @Column(name="valueUp", unique=true)
    private Double valueUp;

    public OddsRange() {
	}


    public OddsRange(Double valueDown, Double valueUp) {
		this.valueDown = valueDown;
		this.valueUp = valueUp;
	}

    
    
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}


	public Double getValueDown() {
		return valueDown;
	}


	public void setValueDown(Double valueDown) {
		this.valueDown = valueDown;
	}


	public Double getValueUp() {
		return valueUp;
	}


	public void setValueUp(Double valueUp) {
		this.valueUp = valueUp;
	}
	
	
	//1.22, 1.45, 1.8, 2.1, 2.7...ecc
}
