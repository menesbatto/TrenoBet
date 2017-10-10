package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
 
public enum TimeTypeEnum implements Serializable{
	 _final("?r=1#1X2;2", "?r=1#over-under;2", "?r=1#eh;2"),
	 _1("?r=1#1X2;3", "?r=1#over-under;3", "?r=1#eh;3"),
	 _2("?r=1#1X2;4", "?r=1#over-under;4", "?r=1#eh;4");
	 //_2("?r=1#1X2;3", "?r=1#over-under;4", "?r=1#eh;4");

	private String uoUrlSuffix;
	private String _1x2urlSuffix;
	private String ehUrlSuffix;

	private TimeTypeEnum(String _1x2urlSuffix, String uourlSuffix, String ehUrlSuffix){
		this._1x2urlSuffix = _1x2urlSuffix;
		this.uoUrlSuffix = uourlSuffix;
		this.ehUrlSuffix = ehUrlSuffix;
    }

	public String getUoUrlSuffix() {
		return uoUrlSuffix;
	}

	public void setUrlSuffix(String urlSuffix) {
		this.uoUrlSuffix = urlSuffix;
	}

	public String get_1x2urlSuffix() {
		return _1x2urlSuffix;
	}

	public void set_1x2urlSuffix(String _1x2urlSuffix) {
		this._1x2urlSuffix = _1x2urlSuffix;
	}

	public String getEhUrlSuffix() {
		return ehUrlSuffix;
	}

	public void setEhUrlSuffix(String ehUrlSuffix) {
		this.ehUrlSuffix = ehUrlSuffix;
	}
	
	


}
