package app.dao.tipologiche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.OddsRange;

@Service
public class OddsRangeDao {

	@Autowired
	private OddsRangeRepo oddsEdgeRepo;
	
	private List<OddsRange> cacheList;

	private HashMap<Double, OddsRange> cacheMap;
	
	public List<OddsRange> findAll() {
		if (cacheList == null || cacheList.isEmpty())
			initCacheList();
		
		return cacheList;
	}
	
	

	public OddsRange findByValue(Double value) {
		if (cacheMap == null || cacheMap.isEmpty())
			initCacheMap();
			
		OddsRange entity = cacheMap.get(value);
		
		return entity;
	}
	
	public void initTable() {
		
		//public static List<Double> RANGE_EDGES = Arrays.asList(1.0, 1.10,  1.25, 1.43, 1.66, 2.0, 2.5, 3.3, 5.0, 10.0, 1000.0);
		OddsRange edge1 = new OddsRange(1.0, 1.1);
		oddsEdgeRepo.save(edge1);
		OddsRange edge2 = new OddsRange(1.1, 1.25);
		oddsEdgeRepo.save(edge2);
		OddsRange edge3 = new OddsRange(1.25, 1.43);
		oddsEdgeRepo.save(edge3);
		OddsRange edge4 = new OddsRange(1.43, 1.66);
		oddsEdgeRepo.save(edge4);
		OddsRange edge5 = new OddsRange(1.66, 2.0);
		oddsEdgeRepo.save(edge5);
		OddsRange edge6 = new OddsRange(2.0, 2.5);
		oddsEdgeRepo.save(edge6);
		OddsRange edge7 = new OddsRange(2.5, 3.3);
		oddsEdgeRepo.save(edge7);
		OddsRange edge8 = new OddsRange(3.3, 5.0);
		oddsEdgeRepo.save(edge8);
		OddsRange edge9 = new OddsRange(5.0, 10.0);
		oddsEdgeRepo.save(edge9);
		OddsRange edge10 = new OddsRange(10.0, 1000.0);
		oddsEdgeRepo.save(edge10);


	}
	
	

	private void initCacheMap() {
		cacheMap = new HashMap<Double, OddsRange>();
		Iterable<OddsRange> findAll = oddsEdgeRepo.findAll();
		for (Iterator<OddsRange> iter = findAll.iterator(); iter.hasNext(); ) {
			OddsRange element = iter.next();
			cacheMap.put(element.getValueUp(), element);
		}	
	}
	
	private void initCacheList() {
		cacheList = new ArrayList<OddsRange>();
		Iterable<OddsRange> findAll = oddsEdgeRepo.findAll();
		for (Iterator<OddsRange> iter = findAll.iterator(); iter.hasNext(); ) {
			OddsRange element = iter.next();
			cacheList.add(element);
		}
		
	}
	
}
