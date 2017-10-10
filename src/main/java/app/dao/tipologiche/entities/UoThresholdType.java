package app.dao.tipologiche.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UoThresholdType {
	@Id
	private int id;
	
	@Column(name="value", unique=true)
	private String value;

	@Column
	private Double valueNum;

	public UoThresholdType() {
	}

	public UoThresholdType(String value) {
		this.value = value;
	}
	public UoThresholdType(int id, String value, Double valueNum) {
		this.id = id;
		this.value = value;
		this.valueNum = valueNum;
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

	public Double getValueNum() {
		return valueNum;
	}

	public void setValueNum(Double valueInt) {
		this.valueNum = valueInt;
	}

	@Override
	public String toString() {
		return "" + value + "";
	} 
	
	//	_0_5, _1_5,  _2_5, _3_5, _4_5, _5_5, _6_5, _7_5, _8_5, _9_5, _10_5

	
	
	
}
