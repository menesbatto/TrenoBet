package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
import java.util.HashMap;

public class EhTimeType implements Serializable{

	private static final long serialVersionUID = -6037988318871290610L;

	private HashMap<HomeVariationEnum, _1x2Full> map;
	
//	private _1x2Full m6;
//	private _1x2Full m5;
//	private _1x2Full m4;
//	private _1x2Full m3;
//	private _1x2Full m2;
//	private _1x2Full m1;
//	private _1x2Full p1;
//	private _1x2Full p2;
//	private _1x2Full p3;
//	private _1x2Full p4;
//	private _1x2Full p5;
//	private _1x2Full p6;
	
	
	
	
	public EhTimeType() {
		this.map = new HashMap<HomeVariationEnum, _1x2Full>();
		for (HomeVariationEnum val:  HomeVariationEnum.values()) {
			_1x2Full oddsFull = new _1x2Full();
			this.map.put(val, oddsFull);
		}
//		m1 = new _1x2Full();
//		m2 = new _1x2Full();
//		m3 = new _1x2Full();
//		m4 = new _1x2Full();
//		m5 = new _1x2Full();
//		m6 = new _1x2Full();
//		
//		p1 = new _1x2Full();
//		p2 = new _1x2Full();
//		p3 = new _1x2Full();
//		p4 = new _1x2Full();
//		p5 = new _1x2Full();
//		p6 = new _1x2Full();
	}
//	public _1x2Full getM6() {
//		return m6;
//	}
//	public void setM6(_1x2Full m6) {
//		this.m6 = m6;
//	}
//	public _1x2Full getM5() {
//		return m5;
//	}
//	public void setM5(_1x2Full m5) {
//		this.m5 = m5;
//	}
//	public _1x2Full getM4() {
//		return m4;
//	}
//	public void setM4(_1x2Full m4) {
//		this.m4 = m4;
//	}
//	public _1x2Full getM3() {
//		return m3;
//	}
//	public void setM3(_1x2Full m3) {
//		this.m3 = m3;
//	}
//	public _1x2Full getM2() {
//		return m2;
//	}
//	public void setM2(_1x2Full m2) {
//		this.m2 = m2;
//	}
//	public _1x2Full getM1() {
//		return m1;
//	}
//	public void setM1(_1x2Full m1) {
//		this.m1 = m1;
//	}
//	public _1x2Full getP1() {
//		return p1;
//	}
//	public void setP1(_1x2Full p1) {
//		this.p1 = p1;
//	}
//	public _1x2Full getP2() {
//		return p2;
//	}
//	public void setP2(_1x2Full p2) {
//		this.p2 = p2;
//	}
//	public _1x2Full getP3() {
//		return p3;
//	}
//	public void setP3(_1x2Full p3) {
//		this.p3 = p3;
//	}
//	public _1x2Full getP4() {
//		return p4;
//	}
//	public void setP4(_1x2Full p4) {
//		this.p4 = p4;
//	}
//	public _1x2Full getP5() {
//		return p5;
//	}
//	public void setP5(_1x2Full p5) {
//		this.p5 = p5;
//	}
//	public _1x2Full getP6() {
//		return p6;
//	}
//	public void setP6(_1x2Full p6) {
//		this.p6 = p6;
//	}
//	@Override
//	public String toString() {
//		return "EhTimeType [m6=" + m6 + ", m5=" + m5 + ", m4=" + m4 + ", m3=" + m3 + ", m2=" + m2 + ", m1=" + m1
//				+ ", p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4 + ", p5=" + p5 + ", p6=" + p6 + "]";
//	}

	public HashMap<HomeVariationEnum, _1x2Full> getMap() {
		return map;
	}

	public void setMap(HashMap<HomeVariationEnum, _1x2Full> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "map=" + map + "\n";
	}


	
	
	
}
