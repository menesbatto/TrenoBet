package app.logic._1_matchesDownlaoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextMatchesDownloader {

	@Autowired
	private MatchesDownloader matchesDownloader;
	
	public void execute(){
		matchesDownloader.execute("Next");
	
	}
	
}
