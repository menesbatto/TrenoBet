package app.dao.tipologiche;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.HomeVariationType;
import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;

@Service
@EnableCaching
public class HomeVariationTypeDao {

	@Autowired
	private HomeVariationTypeRepo homeVariationTypeRepo;
	
	
	@Cacheable("homeVariationEnt")
	public HomeVariationType findByValue(String value) {
		HomeVariationType entity = homeVariationTypeRepo.findByValue(value);
		return entity ;
	}
	
	@Cacheable("homeVariationEnum")
	public HomeVariationEnum findBeanByEnt(HomeVariationType ent) {
		String name = ent.getValue();
		HomeVariationEnum bean = HomeVariationEnum.valueOf(name);
		return bean;
	}

	@Cacheable("homeVariationEnts")
	public List<HomeVariationType> findAll() {
		List<HomeVariationType> list = homeVariationTypeRepo.findAll();
		return list; 
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
	
}
