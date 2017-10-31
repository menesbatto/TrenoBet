package app.dao.tipologiche;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.UoThresholdType;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;

@Service
@EnableCaching
public class UoThresholdTypeDao {

	@Autowired
	private UoThresholdTypeRepo uoThresholdTypeRepo;
	
	@Cacheable("uoThresholdTypes")
	public List<UoThresholdType> findAll() {
		List<UoThresholdType> list = uoThresholdTypeRepo.findAll();
		return list;		
		
	}

	@Cacheable("uoThresholdType")
	public UoThresholdType findByValue(String value) {
		UoThresholdType entity = uoThresholdTypeRepo.findByValue(value);
		return entity;
	}
	
	@Cacheable("uoThresholdEnum")
	public UoThresholdEnum findBeanByEnt(UoThresholdType ent) {
		UoThresholdEnum bean = UoThresholdEnum.valueOf(ent.getValue());
		return bean;
	}

	public void initTable() {
		UoThresholdType _0_5 = new UoThresholdType(0, "_0_5", 0.5);
		uoThresholdTypeRepo.save(_0_5);
		UoThresholdType _1_5 = new UoThresholdType(1, "_1_5", 1.5);
		uoThresholdTypeRepo.save(_1_5);
		UoThresholdType _2_5 = new UoThresholdType(2, "_2_5", 2.5);
		uoThresholdTypeRepo.save(_2_5);
		UoThresholdType _3_5 = new UoThresholdType(3, "_3_5", 3.5);
		uoThresholdTypeRepo.save(_3_5);
		UoThresholdType _4_5 = new UoThresholdType(4, "_4_5", 4.5);
		uoThresholdTypeRepo.save(_4_5);
		UoThresholdType _5_5 = new UoThresholdType(5, "_5_5", 5.5);
		uoThresholdTypeRepo.save(_5_5);
		UoThresholdType _6_5 = new UoThresholdType(6, "_6_5", 6.5);
		uoThresholdTypeRepo.save(_6_5);
		UoThresholdType _7_5 = new UoThresholdType(7, "_7_5", 7.5);
		uoThresholdTypeRepo.save(_7_5);
		UoThresholdType _8_5 = new UoThresholdType(8, "_8_5", 8.5);
		uoThresholdTypeRepo.save(_8_5);
		UoThresholdType _9_5 = new UoThresholdType(9, "_9_5", 9.5);
		uoThresholdTypeRepo.save(_9_5);
		UoThresholdType _10_5 = new UoThresholdType(10, "_10_5", 10.5);
		uoThresholdTypeRepo.save(_10_5);
	}
	
}
