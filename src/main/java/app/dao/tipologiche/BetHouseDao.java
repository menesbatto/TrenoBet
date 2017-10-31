package app.dao.tipologiche;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.BetHouse;
import app.logic._1_matchesDownlaoder.model.BetHouseEnum;

@Service
@EnableCaching
public class BetHouseDao {

	@Autowired
	private BetHouseRepo betHouseRepo;
	

	@Cacheable("betHouseEnt")
	public BetHouse findByValue(String value) {
		BetHouse ent = betHouseRepo.findByValue(value);
		return ent;
	}
	
	
	@Cacheable("betHouseEnum")
	public BetHouseEnum findBeanByEnt(BetHouse ent) {
		String name = ent.getValue();
		BetHouseEnum bean = BetHouseEnum.valueOf(name);
		return bean;
	}
	
	
	
	public void initTable() {
		//bet365, Betclic,  bwin, PaddyPower, Tipico, Unibet, WilliamHill

		BetHouse bet365 = new BetHouse("bet365");
		betHouseRepo.save(bet365);
		BetHouse Betclic = new BetHouse("Betclic");
		betHouseRepo.save(Betclic);
		BetHouse bwin = new BetHouse("bwin");
		betHouseRepo.save(bwin);
		BetHouse PaddyPower = new BetHouse("PaddyPower");
		betHouseRepo.save(PaddyPower);
		BetHouse Tipico = new BetHouse("Tipico");
		betHouseRepo.save(Tipico);
		BetHouse Unibet = new BetHouse("Unibet");
		betHouseRepo.save(Unibet);
		BetHouse WilliamHill = new BetHouse("WilliamHill");
		betHouseRepo.save(WilliamHill);


	}
	
	



	
	
	


}
