package app.logic._1_matchesDownlaoder.model; 	 // 

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import app.dao.tabelle.entities.Champ;
import app.utils.ChampEnum;

public class MatchResult implements Serializable{
	
	private static final long serialVersionUID = 5857347462773566556L;

	// Key to results data:
	
	private String Div;			// League division
	private ChampEnum champ;		// League division
	private String Date; 	 	// Match Date (dd/mm/yy)
	private Date matchDate;
	private String HomeTeam; 	// Home Team
	private String AwayTeam; 	// Away Team
	private Integer FTHG; 	 	// Full Time Home Team Goals
	private Integer FTAG; 	 	// Full Time Away Team Goals
	private String FTR; 	 	// Full Time Result (H=Home Win, D=Draw, A=Away Win)
	private Integer HTHG; 	 	// Half Time Home Team Goals
	private Integer HTAG; 	 	// Half Time Away Team Goals
	private String HTR; 	 	// Half Time Result (H=Home Win, D=Draw, A=Away Win)
	
	private Double PSCH;		// Quota 1		
	private Double PSCD;		// Quota X
	private Double PSCA;		// Quota 2
	
	// Odds Statistics (where available)
	
	private HashMap<TimeTypeEnum, _1x2Full> _1x2;
	
	private HashMap<TimeTypeEnum, UoTimeType> uo;

	private HashMap<TimeTypeEnum, EhTimeType> eh;
	
	
	


	
	
	public MatchResult() {
		_1x2 = new HashMap<TimeTypeEnum, _1x2Full>();
		_1x2.put(TimeTypeEnum._final, new _1x2Full());
		_1x2.put(TimeTypeEnum._1, new _1x2Full());
		_1x2.put(TimeTypeEnum._2, new _1x2Full());

		uo = new HashMap<TimeTypeEnum, UoTimeType>();
		uo.put(TimeTypeEnum._final, new UoTimeType());
		uo.put(TimeTypeEnum._1, new UoTimeType());
		uo.put(TimeTypeEnum._2, new UoTimeType());

		eh = new HashMap<TimeTypeEnum, EhTimeType>();
		eh.put(TimeTypeEnum._final, new EhTimeType());
		eh.put(TimeTypeEnum._1, new EhTimeType());
		eh.put(TimeTypeEnum._2, new EhTimeType());
	}



	@Override
	public String toString() {
		return 	"ESITO\n"
					+ "\tDiv=" + Div + ",	 Date=" + Date + ",	 HomeTeam=" + HomeTeam + ",	 AwayTeam=" + AwayTeam
					+ ",	 FTHG=" + FTHG + ",	 FTAG=" + FTAG + ",	 FTR=" + FTR + ",	 HTHG=" + HTHG + ",	 HTAG=" + HTAG + ",HTR="
					+ HTR + ",\n"
				
				+ "STATISTICHE\n"
//					+ "\tHS=" + HS + ",	 AS=" + AS + ",	 HST=" + HST + ",	 AST=" + AST + ",	 HF=" + HF + ",	 AF=" + AF
//					+ ",	 HC=" + HC + ",	 AC=" + AC + ",	 HY=" + HY + ",	 AY=" + AY + ",	 HR=" + HR + ",	 AR=" + AR + ",\n"

//				+ "QUOTE\n"
//					+ "\tB365H=" + B365H + ",	 B365D=" + B365D + ",	 B365A=" + B365A + ",	 \n\tBWH=" + BWH + ",	 BWD=" + BWD + ",	 BWA=" + BWA
//					+ ",	 \n\tIWH=" + IWH + ",	 IWD=" + IWD + ",	 IWA=" + IWA + ",	 \n\tLBH=" + LBH + ",	 LBD=" + LBD + ",	 LBA=" + LBA
//					+ ",	 \n\tPSH=" + PSH + ",	 PSD=" + PSD + ",	 PSA=" + PSA + ",	 \n\tWHH=" + WHH + ",	 WHD=" + WHD + ",	 WHA=" + WHA
//					+ ",	 \n\tVCH=" + VCH + ",	 VCD=" + VCD + ",	 VCA=" + VCA + ",\n"

				+ "QUOTE\n"
					+ "\tB365H=" + "\t" + _1x2.get(TimeTypeEnum._final).getAvg1x2Odds().getOdd1()  + "\t" + _1x2.get(TimeTypeEnum._final).getAvg1x2Odds().getOddX() + "\t" + _1x2.get(TimeTypeEnum._final).getAvg1x2Odds().getOdd2() 
					//+ "\n\tBWH=" + "\t" + BWH + "\t" + BWD + "\t" + BWA
					//+ "\n\tIWH=\t" + IWH + "\t" + IWD + "\t" + IWA + "\n\tLBH=\t" + LBH + "\t" + LBD + "\t" + LBA
					//+ "\n\tPSH=\t" + PSH + "\t" + PSD + "\t" + PSA + "\n\tWHH=\t" + WHH + "\t" + WHD + "\t" + WHA
					//+ "\n\tVCH=\t" + VCH + "\t" + VCD + "\t" + VCA 
					+ "\n"
				
					
				+ "ESITO\n"
//					+ "\tBb1X2=" + Bb1X2 + ",	 BbMxH=" + BbMxH + ",	 BbAvH=" + BbAvH + ",	 BbMxD=" + BbMxD + ","	+ " BbAvD=" + BbAvD + ",	 BbMxA=" + BbMxA + ",	 BbAvA="
//					+ BbAvA + ",	 BbOU=" + BbOU + ",	\n"
				
				+ "U/O\n"
//					+ "\tBbMxO2_5=" + BbMxO2_5 + ",	 BbAvO2_5=" + BbAvO2_5 + ",	 BbMxU2_5="	+ BbMxU2_5 + ",	 BbAvU2_5=" + BbAvU2_5 + ",\n"
				
				+ "HANDICAP\n"
//					+ "\tBbAH=" + BbAH + ",	 BbAHh=" + BbAHh + ",	 BbMxAHH=" + BbMxAHH + ",	 BbAvAHH=" + BbAvAHH + ",	 BbMxAHA=" + BbMxAHA + ",	 BbAvAHA=" + BbAvAHA + ",\n"
				
				+ "LAST ODDS\n"
//					+ "\tPSCH=" + PSCH + ",	 PSCD=" + PSCD + ",	 PSCA=" + PSCA + "\n"
				+ "\n";
	}



	public String getDiv() {
		return Div;
	}

	public void setDiv(String div) {
		Div = div;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getHomeTeam() {
		return HomeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		HomeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return AwayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		AwayTeam = awayTeam;
	}

	public Integer getFTHG() {
		return FTHG;
	}

	public void setFTHG(Integer fTHG) {
		FTHG = fTHG;
	}

	public Integer getFTAG() {
		return FTAG;
	}

	public void setFTAG(Integer fTAG) {
		FTAG = fTAG;
	}

	public String getFTR() {
		return FTR;
	}

	public void setFTR(String fTR) {
		FTR = fTR;
	}

	public Integer getHTHG() {
		return HTHG;
	}

	public void setHTHG(Integer hTHG) {
		HTHG = hTHG;
	}

	public Integer getHTAG() {
		return HTAG;
	}

	public void setHTAG(Integer hTAG) {
		HTAG = hTAG;
	}

	public String getHTR() {
		return HTR;
	}

	public void setHTR(String hTR) {
		HTR = hTR;
	}

	public ChampEnum getChamp() {
		return champ;
	}

	public void setChamp(ChampEnum champ) {
		this.champ = champ;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public HashMap<TimeTypeEnum, _1x2Full> get_1x2() {
		return _1x2;
	}

	public void set_1x2(HashMap<TimeTypeEnum, _1x2Full> _1x2) {
		this._1x2 = _1x2;
	}

	public HashMap<TimeTypeEnum, UoTimeType> getUo() {
		return uo;
	}

	public void setUo(HashMap<TimeTypeEnum, UoTimeType> uo) {
		this.uo = uo;
	}

	public HashMap<TimeTypeEnum, EhTimeType> getEh() {
		return eh;
	}

	public void setEh(HashMap<TimeTypeEnum, EhTimeType> eh) {
		this.eh = eh;
	}



	public Double getPSCA() {
		return PSCA;
	}



	public void setPSCA(Double pSCA) {
		PSCA = pSCA;
	}



	public Double getPSCH() {
		return PSCH;
	}



	public void setPSCH(Double pSCH) {
		PSCH = pSCH;
	}



	public Double getPSCD() {
		return PSCD;
	}



	public void setPSCD(Double pSCD) {
		PSCD = pSCD;
	}

}