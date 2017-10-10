package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;

import javax.persistence.Column;
 
public enum UoThresholdEnum implements Serializable{
	_0_5(0.5), _1_5(1.5),  _2_5(2.5), _3_5(3.5), _4_5(4.5), _5_5(5.5), _6_5(6.5), _7_5(7.5), _8_5(8.5), _9_5(9.5), _10_5(10.5);
	
	

	private Double valueNum;

	private UoThresholdEnum(Double valueNum) {
		this.valueNum = valueNum;
	}


	public Double getValueNum() {
		return valueNum;
	}

	public void setValueNum(Double valueNum) {
		this.valueNum = valueNum;
	}
	
	

	
	
}
