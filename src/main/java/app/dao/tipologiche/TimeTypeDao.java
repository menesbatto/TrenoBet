package app.dao.tipologiche;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;

@Service
@EnableCaching
public class TimeTypeDao {

	@Autowired
	private TimeTypeRepo timeTypeRepo;
	

	@Cacheable("timeTypeEnts")
	public List<TimeType> findAll(){
		return timeTypeRepo.findAll();
	}
	

	@Cacheable("timeTypeEnums")
	public List<TimeTypeEnum> findAllTimeTypeEnum(){
		List<TimeTypeEnum> list = new ArrayList<TimeTypeEnum>();
		list.add(TimeTypeEnum._final);
		list.add(TimeTypeEnum._1);
		list.add(TimeTypeEnum._2);
		return list;
	}
	
	@Cacheable("timeTypeEnt")	
	public TimeType findByValue(String value) {
		TimeType entity = timeTypeRepo.findByValue(value);
		
		return entity;
	}

	@Cacheable("timeTypeEnum")	
	public TimeTypeEnum findBeanByEnt(TimeType ent) {
		TimeTypeEnum bean = TimeTypeEnum.valueOf(ent.getValue());
		return bean;
	}

	public void initTable() {
		TimeType _final = new TimeType("_final");
		timeTypeRepo.save(_final);
		TimeType _1 = new TimeType("_1");
		timeTypeRepo.save(_1);
		TimeType _2 = new TimeType("_2");
		timeTypeRepo.save(_2);
		
	}
	
}
