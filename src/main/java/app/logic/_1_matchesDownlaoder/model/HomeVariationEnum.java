package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
import java.util.EnumSet;
 
public enum HomeVariationEnum implements Serializable{
	m9(-9), m8(-8), m7(-7), m6(-6), m5(-5), m4(-4), m3(-3), m2(-2), m1(-1), 
	
	p1(1), p2(2), p3(3), p4(4), p5(5), p6(6), p7(7), p8(8), p9(9);
	

	private static final EnumSet<HomeVariationEnum> subSet = EnumSet.of( m3, m2, m1, p1, p2, p3);
	private Integer valueNum;

	private HomeVariationEnum(Integer valueNum) {
		this.valueNum = valueNum;
	}


	public Integer getValueNum() {
		return valueNum;
	}

	public void setValueNum(Integer valueNum) {
		this.valueNum = valueNum;
	}
	
	public static EnumSet<HomeVariationEnum> getSubSet() {
        return subSet; // return Red and Green
    }

	
}
