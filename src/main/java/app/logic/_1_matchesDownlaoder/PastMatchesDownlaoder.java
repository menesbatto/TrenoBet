package app.logic._1_matchesDownlaoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.utils.ChampEnum;


@Service
public class PastMatchesDownlaoder {

	@Autowired
	private MatchesDownloader matchesDownloader;
	
	public void execute(){
		ChampEnum[] allChamps = ChampEnum.values();
		execute(allChamps);
	}

	
	public void execute(ChampEnum[] champs){
		matchesDownloader.execute("Past", champs);
	}

}
