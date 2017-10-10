package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UoFull implements Serializable{
	
	private static final long serialVersionUID = -4116365496171461580L;

	private UoLeaf avgUoOdds;
	
	private HashMap<BetHouseEnum, UoLeaf> betHouseToUoOdds;
	
	public UoFull() {
		this.betHouseToUoOdds = new HashMap<BetHouseEnum, UoLeaf>();
	}

	public Map<BetHouseEnum, UoLeaf> getBetHouseToUoOdds() {
		return betHouseToUoOdds;
	}

	public void setBetHouseToUoOdds(HashMap<BetHouseEnum, UoLeaf> betHouseToUoOdds) {
		this.betHouseToUoOdds = betHouseToUoOdds;
	}

	public UoLeaf getAvgUoOdds() {
		return avgUoOdds;
	}

	public void setAvgUoOdds(UoLeaf avgUoOdds) {
		this.avgUoOdds = avgUoOdds;
	}

	@Override
	public String toString() {
		return "avg: " + avgUoOdds + "\n" + betHouseToUoOdds + "\n";
	}
	
	
	
	
	
}
