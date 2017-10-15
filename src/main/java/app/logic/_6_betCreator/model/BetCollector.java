package app.logic._6_betCreator.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import app.logic._1_matchesDownlaoder.model.SingleBetBean;

public class BetCollector implements Serializable{

	private static final long serialVersionUID = 6253921183288635672L;
	
	private List<SingleBetBean> singleBet;
	
	private Date startDate;
	
	private Date endDate;
	
	private Double betAmount;
	
	private Double winOrLostAmount;
	
	private Double winOrLostPerc;
		
	private Double hitPerc;
	
	private Double losePerc;
	
	private SingleBetBean bestProfitableSingleBet;
	
	
	
	
	
	
	

}
