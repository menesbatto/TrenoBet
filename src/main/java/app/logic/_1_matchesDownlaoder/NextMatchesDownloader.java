package app.logic._1_matchesDownlaoder;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.utils.ChampEnum;

@Service
public class NextMatchesDownloader{

//	@Autowired
//	private MatchesDownloader matchesDownloader;
	
	public void execute(){
		ChampEnum[] allChamps = ChampEnum.values();
		execute(allChamps);
	}

//	public void execute(ChampEnum[] champs) {
//		matchesDownloader.execute("Next", champs);
//	}
	
	public void execute(ChampEnum[] champs) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		for (ChampEnum champ : champs) {
			MatchesDownloader downloader = new MatchesDownloader();
			downloader.setChamp(champ);
			downloader.setType("Next");
			executor.submit(downloader);
		}
		executor.shutdown();
	}
	
}
