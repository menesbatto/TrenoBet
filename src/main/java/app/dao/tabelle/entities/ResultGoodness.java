package app.dao.tabelle.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ResultGoodness {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private EventOdds eventOdds;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ResultGoodnessWdl winFinal;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ResultGoodnessWdl winClean;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ResultGoodnessWdl winTrend;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ResultGoodnessWdl winMotivation;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ResultGoodnessUo> uo;

	@OneToMany(cascade = CascadeType.ALL)
	private List<ResultGoodnessWdl> eh;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	
	public List<ResultGoodnessUo> getUo() {
		return uo;
	}


	public void setUo(List<ResultGoodnessUo> uo) {
		this.uo = uo;
	}


	public List<ResultGoodnessWdl> getEh() {
		return eh;
	}


	public void setEh(List<ResultGoodnessWdl> eh) {
		this.eh = eh;
	}


	public EventOdds getEventOdds() {
		return eventOdds;
	}


	public void setEventOdds(EventOdds eventOdds) {
		this.eventOdds = eventOdds;
	}


	public ResultGoodnessWdl getWinFinal() {
		return winFinal;
	}


	public void setWinFinal(ResultGoodnessWdl winFinal) {
		this.winFinal = winFinal;
	}


	public ResultGoodnessWdl getWinClean() {
		return winClean;
	}


	public void setWinClean(ResultGoodnessWdl winClean) {
		this.winClean = winClean;
	}


	public ResultGoodnessWdl getWinTrend() {
		return winTrend;
	}


	public void setWinTrend(ResultGoodnessWdl winTrend) {
		this.winTrend = winTrend;
	}


	public ResultGoodnessWdl getWinMotivation() {
		return winMotivation;
	}


	public void setWinMotivation(ResultGoodnessWdl winMotivation) {
		this.winMotivation = winMotivation;
	}


	@Override
	public String toString() {
		return "id=" + id + ", eventOdds=" + eventOdds + "\n, winFinal=" + winFinal + ", winClean="
				+ winClean + ", winTrend=" + winTrend + ", winMotivation=" + winMotivation + "\n uo=" + uo + "\n eh=" + eh
				+ "\n";
	}


	
}
