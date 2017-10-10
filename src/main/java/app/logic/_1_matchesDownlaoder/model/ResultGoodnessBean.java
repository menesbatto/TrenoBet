package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultGoodnessBean implements Serializable{
	
	private static final long serialVersionUID = -8220296671620254113L;

	private ResultGoodnessWDLBean winFinal;
	
	private ResultGoodnessWDLBean winClean;
	
	private ResultGoodnessWDLBean winTrend;
	
	private ResultGoodnessWDLBean winMotivation;
	
	private Map<UoThresholdEnum, ResultGoodnessUoBean> uoGoodness;

	private Map<HomeVariationEnum, ResultGoodnessWDLBean> ehGoodness;
	
	
	public ResultGoodnessBean() {
		uoGoodness = new HashMap<UoThresholdEnum, ResultGoodnessUoBean>();
		ehGoodness = new HashMap<HomeVariationEnum, ResultGoodnessWDLBean>();
	}

	public Map<UoThresholdEnum, ResultGoodnessUoBean> getUoGoodness() {
		return uoGoodness;
	}
	public void setUoGoodness(Map<UoThresholdEnum, ResultGoodnessUoBean> uoGoodness) {
		this.uoGoodness = uoGoodness;
	}
	public Map<HomeVariationEnum, ResultGoodnessWDLBean> getEhGoodness() {
		return ehGoodness;
	}
	public void setEhGoodness(Map<HomeVariationEnum, ResultGoodnessWDLBean> ehGoodness) {
		this.ehGoodness = ehGoodness;
	}
	public ResultGoodnessWDLBean getWinFinal() {
		return winFinal;
	}
	public void setWinFinal(ResultGoodnessWDLBean winFinal) {
		this.winFinal = winFinal;
	}
	public ResultGoodnessWDLBean getWinClean() {
		return winClean;
	}
	public void setWinClean(ResultGoodnessWDLBean winClean) {
		this.winClean = winClean;
	}
	public ResultGoodnessWDLBean getWinTrend() {
		return winTrend;
	}
	public void setWinTrend(ResultGoodnessWDLBean winTrend) {
		this.winTrend = winTrend;
	}
	public ResultGoodnessWDLBean getWinMotivation() {
		return winMotivation;
	}
	public void setWinMotivation(ResultGoodnessWDLBean winMotivation) {
		this.winMotivation = winMotivation;
	}
	
}
