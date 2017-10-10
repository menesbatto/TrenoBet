package app.dao.tipologiche;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchesDownlaoder.model.BetHouseEnum;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;

@Service
public class BetHouseDao {

	@Autowired
	private BetHouseRepo betHouseRepo;
	
	private HashMap<String, BetHouse> cacheMap;
	
	private HashMap<String, BetHouseEnum> cacheMapBean;

	
	public BetHouse findByValue(String value) {
		if (cacheMap == null || cacheMap.isEmpty())
			initCacheMap();
			
		BetHouse entity = cacheMap.get(value);
		
		return entity;
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
	
	

	private void initCacheMap() {
		cacheMap = new HashMap<String, BetHouse>();
		Iterable<BetHouse> findAll = betHouseRepo.findAll();
		for (Iterator<BetHouse> iter = findAll.iterator(); iter.hasNext(); ) {
			BetHouse element = iter.next();
			cacheMap.put(element.getValue(), element);
		}	
	}

	public BetHouseEnum findBeanByEnt(BetHouse ent) {
		if (cacheMapBean == null || cacheMapBean.isEmpty())
			initCacheMapBean();
		
		BetHouseEnum bean = cacheMapBean.get(ent.getValue());
		
		return bean;
	}
	
	
	
	private void initCacheMapBean() {
		cacheMapBean = new HashMap<String, BetHouseEnum>();
		Iterable<BetHouse> findAll = betHouseRepo.findAll();
		for (Iterator<BetHouse> iter = findAll.iterator(); iter.hasNext(); ) {
			BetHouse element = iter.next();
			BetHouseEnum bean = BetHouseEnum.valueOf(element.getValue());
			cacheMapBean.put(element.getValue(), bean);
		}
	}

}
