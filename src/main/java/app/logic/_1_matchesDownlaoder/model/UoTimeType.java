package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
import java.util.HashMap;

public class UoTimeType implements Serializable{

	private static final long serialVersionUID = -6037988318871290610L;

	private HashMap<UoThresholdEnum, UoFull> map;
	
	
	
	public UoTimeType() {

		this.map = new HashMap<UoThresholdEnum, UoFull>();
		for (UoThresholdEnum val:  UoThresholdEnum.values()) {
			UoFull oddsFull = new UoFull();
			this.map.put(val, oddsFull);
		}
	
	}



	public HashMap<UoThresholdEnum, UoFull> getMap() {
		return map;
	}



	public void setMap(HashMap<UoThresholdEnum, UoFull> map) {
		this.map = map;
	}
	
	
	
	
//	@Override
//	public String toString() {
//		return 	"uo0_5\n" + uo0_5 + 
//				"uo1_5\n" + uo1_5 + 
//				"uo2_5\n" + uo2_5 + 
//				"uo3_5\n" + uo3_5 +
//				"uo4_5\n" + uo4_5 +
//				"uo5_5\n" + uo5_5 + 
//				"uo6_5\n" + uo6_5 +
//				"uo7_5\n" + uo7_5 +
//				"uo8_5\n" + uo8_5 +
//				"uo9_5\n" + uo9_5 +
//				"uo10_5\n" + uo10_5 + "\n";
//	}
//

	
	
	
}
