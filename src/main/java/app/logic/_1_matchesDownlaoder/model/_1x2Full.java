package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
import java.util.HashMap;

public class _1x2Full implements Serializable{

	private static final long serialVersionUID = -6037988318871333610L;

	private _1x2Leaf avg1x2Odds;
	
	private HashMap<BetHouseEnum, _1x2Leaf> betHouseTo1x2Odds;

	
	
	
	public _1x2Full() {
		this.betHouseTo1x2Odds = new HashMap<BetHouseEnum, _1x2Leaf>();
	}

	public _1x2Leaf getAvg1x2Odds() {
		return avg1x2Odds;
	}

	public void setAvg1x2Odds(_1x2Leaf avg1x2Odds) {
		this.avg1x2Odds = avg1x2Odds;
	}

	public HashMap<BetHouseEnum, _1x2Leaf> getBetHouseTo1x2Odds() {
		return betHouseTo1x2Odds;
	}

	public void setBetHouseTo1x2Odds(HashMap<BetHouseEnum, _1x2Leaf> betHouseTo1x2Odds) {
		this.betHouseTo1x2Odds = betHouseTo1x2Odds;
	}
	
	@Override
	public String toString() {
		return "avg: " + avg1x2Odds + "\n" + betHouseTo1x2Odds + "\n";
	}
	
	
	
	
}
