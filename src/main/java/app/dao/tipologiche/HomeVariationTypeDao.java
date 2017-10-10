package app.dao.tipologiche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.HomeVariationType;
import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;

@Service
public class HomeVariationTypeDao {

	@Autowired
	private HomeVariationTypeRepo homeVariationTypeRepo;
	
	private HashMap<String, HomeVariationEnum> cacheMapBean;
	
	private HashMap<String, HomeVariationType> cacheMap;
	
	public HomeVariationType findByValue(String value) {
		if (cacheMap == null || cacheMap.isEmpty())
			initCacheMap();
			
		HomeVariationType entity = cacheMap.get(value);
		
		return entity;
	}
	
	public HomeVariationEnum findBeanByEnt(HomeVariationType ent) {
		if (cacheMapBean == null || cacheMapBean.isEmpty())
			initCacheMapBean();
		
		HomeVariationEnum bean = cacheMapBean.get(ent.getValue());
		
		return bean;
	}

	public void initTable() {
		HomeVariationType p9 = new HomeVariationType(9, "p9");
		homeVariationTypeRepo.save(p9);
		HomeVariationType p8 = new HomeVariationType(8, "p8");
		homeVariationTypeRepo.save(p8);
		HomeVariationType p7 = new HomeVariationType(7, "p7");
		homeVariationTypeRepo.save(p7);
		HomeVariationType p6 = new HomeVariationType(6, "p6");
		homeVariationTypeRepo.save(p6);
		HomeVariationType p5 = new HomeVariationType(5, "p5");
		homeVariationTypeRepo.save(p5);
		HomeVariationType p4 = new HomeVariationType(4, "p4");
		homeVariationTypeRepo.save(p4);
		HomeVariationType p3 = new HomeVariationType(3, "p3");
		homeVariationTypeRepo.save(p3);
		HomeVariationType p2 = new HomeVariationType(2, "p2");
		homeVariationTypeRepo.save(p2);
		HomeVariationType p1 = new HomeVariationType(1, "p1");
		homeVariationTypeRepo.save(p1);
		
		HomeVariationType m9 = new HomeVariationType(-9, "m9");
		homeVariationTypeRepo.save(m9);
		HomeVariationType m8 = new HomeVariationType(-8, "m8");
		homeVariationTypeRepo.save(m8);
		HomeVariationType m7 = new HomeVariationType(-7, "m7");
		homeVariationTypeRepo.save(m7);
		HomeVariationType m6 = new HomeVariationType(-6, "m6");
		homeVariationTypeRepo.save(m6);
		HomeVariationType m5 = new HomeVariationType(-5, "m5");
		homeVariationTypeRepo.save(m5);
		HomeVariationType m4 = new HomeVariationType(-4, "m4");
		homeVariationTypeRepo.save(m4);
		HomeVariationType m3 = new HomeVariationType(-3, "m3");
		homeVariationTypeRepo.save(m3);
		HomeVariationType m2 = new HomeVariationType(-2, "m2");
		homeVariationTypeRepo.save(m2);
		HomeVariationType m1 = new HomeVariationType(-1, "m1");
		homeVariationTypeRepo.save(m1);

	
	}
	
	private void initCacheMap() {
		cacheMap = new HashMap<String, HomeVariationType>();
		Iterable<HomeVariationType> findAll = homeVariationTypeRepo.findAll();
		for (Iterator<HomeVariationType> iter = findAll.iterator(); iter.hasNext(); ) {
			HomeVariationType element = iter.next();
			cacheMap.put(element.getValue(), element);
		}	
	}
	
	private void initCacheMapBean() {
		cacheMapBean = new HashMap<String, HomeVariationEnum>();
		Iterable<HomeVariationType> findAll = homeVariationTypeRepo.findAll();
		for (Iterator<HomeVariationType> iter = findAll.iterator(); iter.hasNext(); ) {
			HomeVariationType element = iter.next();
			HomeVariationEnum bean = HomeVariationEnum.valueOf(element.getValue());
			cacheMapBean.put(element.getValue(), bean);
		}
	}

	public List<HomeVariationType> findAll() {
		if (cacheMap == null || cacheMap.isEmpty())
			initCacheMap();
		List<HomeVariationType> list = new ArrayList<HomeVariationType>();
		list.addAll(cacheMap.values());
		return list; 
	}
	

	
}
