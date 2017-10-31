package app.dao.tipologiche;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.OddsRange;

@Service
@EnableCaching

public class OddsRangeDao {

	@Autowired
	private OddsRangeRepo oddsRangeRepo;
	
	

	@Cacheable("oddsRangeEnt")
	public OddsRange findByValue(Double value) {
		OddsRange entity = oddsRangeRepo.findByValueUp(value);
		return entity ;
	}
	

	@Cacheable("oddsRangeEnts")
	public List<OddsRange> findAll() {
		List<OddsRange> list = oddsRangeRepo.findAll();
		return list; 
	}
	
	
	public void initTable() {
		
		//public static List<Double> RANGE_EDGES = Arrays.asList(1.0, 1.10,  1.25, 1.43, 1.66, 2.0, 2.5, 3.3, 5.0, 10.0, 1000.0);
		OddsRange edge1 = new OddsRange(1.0, 1.1);
		oddsRangeRepo.save(edge1);
		OddsRange edge2 = new OddsRange(1.1, 1.25);
		oddsRangeRepo.save(edge2);
		OddsRange edge3 = new OddsRange(1.25, 1.43);
		oddsRangeRepo.save(edge3);
		OddsRange edge4 = new OddsRange(1.43, 1.66);
		oddsRangeRepo.save(edge4);
		OddsRange edge5 = new OddsRange(1.66, 2.0);
		oddsRangeRepo.save(edge5);
		OddsRange edge6 = new OddsRange(2.0, 2.5);
		oddsRangeRepo.save(edge6);
		OddsRange edge7 = new OddsRange(2.5, 3.3);
		oddsRangeRepo.save(edge7);
		OddsRange edge8 = new OddsRange(3.3, 5.0);
		oddsRangeRepo.save(edge8);
		OddsRange edge9 = new OddsRange(5.0, 10.0);
		oddsRangeRepo.save(edge9);
		OddsRange edge10 = new OddsRange(10.0, 1000.0);
		oddsRangeRepo.save(edge10);


	}
	
}
