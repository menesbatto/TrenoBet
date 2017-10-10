package app.dao.tabelle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.GoalsStats;
import app.dao.tabelle.entities.Team;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;
import app.logic._2_matchResultAnalyzer.model.GoalsStatsBean;
import app.logic._2_matchResultAnalyzer.model.UoThresholdStats;
import app.utils.ChampEnum;
import ma.glasnost.orika.MapperFacade;

@Service
public class GoalsStatsDao {

	@Autowired
	private GoalsStatsRepo goalsStatsRepo;

	@Autowired
	private UoThresholdTypeDao uoThresholdTypeDao;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private ChampDao champDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private MapperFacade mapper;
	
	public List<GoalsStatsBean> findByChamp(ChampEnum champEnum) {
		Champ champ = champDao.findByChampEnum(champEnum);
		List<GoalsStats> ents = goalsStatsRepo.findByTeamChampOrderByTeam(champ);
		List<GoalsStatsBean> beans = new ArrayList<GoalsStatsBean>();
		GoalsStatsBean bean;
		for (GoalsStats ent : ents) {
			bean = getBeanFromList(ent.getTeamName(), ent.getPlayingField(), ent.getTimeType(), beans);
			if (bean == null) {
				bean = new GoalsStatsBean();
				mapper.map(ent, bean);
				bean.setTeamName(ent.getTeam().getName());
				TimeTypeEnum timeTypeBean = timeTypeDao.findBeanByEnt(ent.getTimeType());
				bean.setTimeTypeBean(timeTypeBean);
				bean.setPlayingField(ent.getPlayingField());
				beans.add(bean);
			}
			
			UoThresholdEnum thrBean = uoThresholdTypeDao.findBeanByEnt(ent.getThreshold());
			UoThresholdStats thrStatsBean = new UoThresholdStats();
			thrStatsBean.setOverHit(ent.getOverHit());
			thrStatsBean.setOverPerc(ent.getOverPerc());
			thrStatsBean.setUnderHit(ent.getUnderHit());
			thrStatsBean.setUnderPerc(ent.getUnderPerc());
			bean.getThresholdMap().put(thrBean, thrStatsBean);
			
		}
		return beans;
		
	}
	
//	public GoalsStatsBean findByTeamNameAndChampAndTimeTypeAndPlayingField(String teamName, ChampEnum champEnum,	TimeTypeEnum timeTypeEnum, String playingField) {
//	
//		Champ champ = champDao.findByChampEnum(champEnum);
//		Team team = teamDao.findByNameAndChamp(teamName, champ);
//		TimeType timeType = timeTypeDao.findByValue(timeTypeEnum.name());
//		List<GoalsStats> listEnt = goalsStatsRepo.findByTeamAndTimeTypeAndPlayingField(team, timeType, playingField);
//		if (listEnt.isEmpty()){
//			listEnt = initGoalsStatsForTeam(team, timeType, playingField);
//		}
//		
//		
//		GoalsStatsBean bean = new GoalsStatsBean();
//		for (GoalsStats ent : listEnt) {
//			if (bean.getTeamName() == null) {	//Lo mappo solo la prima volta
//				mapper.map(ent, bean);
//				bean.setTeamName(ent.getTeam().getName());
//			}
//			else {
//				UoThresholdEnum findBeanByEnt = uoThresholdTypeDao.findBeanByEnt(ent.getThreshold());
//				UoThresholdStats uoThresholdStats = bean.getThresholdMap().get(findBeanByEnt);
//				uoThresholdStats.setOverHit(ent.getOverHit());
//				uoThresholdStats.setOverPerc(ent.getOverPerc());
//				uoThresholdStats.setUnderHit(ent.getUnderHit());
//				uoThresholdStats.setUnderPerc(ent.getUnderPerc());
//			}
//			
//			
//		}
//
//		return bean;
//	}
//	public ArrayList<GoalsStatsBean> findByTeamNameAndChampAndTimeTypeAndPlayingField(String teamName, ChampEnum champEnum,	TimeTypeEnum timeTypeEnum, String playingField) {
//		
//		Champ champ = champDao.findByChampEnum(champEnum);
//		Team team = teamDao.findByNameAndChamp(teamName, champ);
//		TimeType timeType = timeTypeDao.findByValue(timeTypeEnum.name());
//		List<GoalsStats> listEnt = goalsStatsRepo.findByTeamAndTimeTypeAndPlayingField(team, timeType, playingField);
//		if (listEnt.isEmpty()){
//			listEnt = initGoalsStatsForTeam(team, timeType, playingField);
//		}
//		
//		
//		ArrayList<GoalsStatsBean> listBean = new ArrayList<GoalsStatsBean>();
//		for (GoalsStats ent : listEnt) {
//			GoalsStatsBean bean = new GoalsStatsBean();
//			if (ent.getTotalMatches() != null)	// Per non sovrascrivere gli 0 con i null provenienti da DB, in caso metti diretto gli 0 a DB
//				mapper.map(ent, bean);
//			
//			
//			bean.setThreshold(ent.getThreshold().getValueNum());
//			bean.setTeamName(ent.getTeam().getName());
//			
//			listBean.add(bean);
//		}
//		
//		return listBean;
//	}
	

	private GoalsStatsBean getBeanFromList(String teamName, String playingField, TimeType timeType, List<GoalsStatsBean> beans) {
		for (GoalsStatsBean bean : beans) {
			if (bean.getTeamName().equals(teamName)) {
				if (bean.getPlayingField().equals(playingField)) {
					TimeTypeEnum timeTypeEnum = timeTypeDao.findBeanByEnt(timeType);
					if (bean.getTimeTypeBean().equals(timeTypeEnum)) {
						return bean;
					}
				}
			}
		}
		return null;
	}

	
	public List<GoalsStatsBean> saveGoalsStats(List<GoalsStatsBean> beans, ChampEnum champEnum) {
		Champ champ = champDao.findByChampEnum(champEnum);
		
		
		List<GoalsStats> beanGoalsStats = new ArrayList<GoalsStats>();

		
		for (GoalsStatsBean bean : beans) {
			
			Team teamEnt = teamDao.findByNameAndChamp(bean.getTeamName(), champ);
			TimeType beanTimeType = timeTypeDao.findByValue(bean.getTimeTypeBean().name());
			
			for (Entry<UoThresholdEnum, UoThresholdStats> entry : bean.getThresholdMap().entrySet()) {
			
				UoThresholdEnum uoThresEnum = entry.getKey();
				UoThresholdStats tresStatsBean = entry.getValue();
				UoThresholdType uoThr = uoThresholdTypeDao.findByValue(uoThresEnum.name());
				
				GoalsStats ent = new GoalsStats(uoThr, teamEnt);
				mapper.map(bean, ent);
			
				ent.setPlayingField(bean.getPlayingField());
				ent.setTimeType(beanTimeType);
				
				ent.setOverHit(tresStatsBean.getOverHit());
				ent.setOverPerc(tresStatsBean.getOverPerc());
				ent.setUnderHit(tresStatsBean.getUnderHit());
				ent.setUnderPerc(tresStatsBean.getUnderPerc());
			
			
				beanGoalsStats.add(ent);
			}
				
			
		}

		goalsStatsRepo.save(beanGoalsStats);
		
		return beans;
	}



	public void calculateGoalsStatsNoPlayingField(String teamName, ChampEnum champEnum, List<GoalsStatsBean> homeStatses, List<GoalsStatsBean> awayStatses) {
		
		ArrayList<GoalsStatsBean> totalStatses = new ArrayList<GoalsStatsBean>();
//		if (homeStatses == null)
//			homeStatses = initGoalsStatsForTeam(team, timeType, playingField)
//		else if (awayStatses == null)
//			awayStatses = new ArrayList<GoalsStatsBean>();
			
//		for (TimeTypeEnum timeTypeEnum : timeTypeDao.findAllTimeTypeEnum()) {
		for (int i = 0; i < 3; i++) {

//			GoalsStatsBean homeStats = findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champEnum, timeTypeEnum, "A");
//			GoalsStatsBean awayStats = findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champEnum, timeTypeEnum, "H");
			GoalsStatsBean homeStats = !homeStatses.isEmpty() ? homeStatses.get(i) : createEmptyOne(awayStatses.get(i));
			GoalsStatsBean awayStats = !awayStatses.isEmpty() ? awayStatses.get(i) : createEmptyOne(homeStatses.get(i));

			GoalsStatsBean totalStats = new GoalsStatsBean();
			
			for (Entry<UoThresholdEnum, UoThresholdStats> entryH : homeStats.getThresholdMap().entrySet()) {
				if (totalStats.getTeamName() == null) {
					totalStats.setTeamName(homeStats.getTeamName());
					totalStats.setTimeTypeBean(homeStats.getTimeTypeBean());
					totalStats.setTotalMatches(homeStats.getTotalMatches() + awayStats.getTotalMatches());
					totalStats.setTotalGoals(homeStats.getTotalGoals() +  awayStats.getTotalGoals());
					totalStats.setTakenGoalsTotal(homeStats.getTakenGoalsTotal() + awayStats.getTakenGoalsTotal());
					totalStats.setStrikedGoalsTotal(homeStats.getStrikedGoalsTotal() + awayStats.getStrikedGoalsTotal());
				}
				UoThresholdEnum keyH = entryH.getKey();
				for (Entry<UoThresholdEnum, UoThresholdStats> entryA : awayStats.getThresholdMap().entrySet()) {
					UoThresholdEnum keyA = entryA.getKey();
					if (keyH.equals(keyA)) {
						UoThresholdStats valueH = entryH.getValue();
						UoThresholdStats valueA = entryA.getValue();
						Integer totalMatches = homeStats.getTotalMatches() + awayStats.getTotalMatches();
						
						Double underHit = valueH.getUnderHit() + valueA.getUnderHit();
						Double overHit = valueH.getOverHit() + valueA.getOverHit();
						
						Double underPerc = 0.0;
						Double overPerc = 0.0;
						if (totalMatches != 0) {
							underPerc = underHit / totalMatches.doubleValue();
							overPerc = overHit / totalMatches.doubleValue();
						}
						UoThresholdStats valueF = new UoThresholdStats(underHit, underPerc, overHit, overPerc); 
						totalStats.getThresholdMap().put(keyA, valueF);
					}
				}
			}
			totalStatses.add(totalStats);
		}
		
//		saveGoalsStats(totalStatses, teamName, champEnum, "T");

	}


	private GoalsStatsBean createEmptyOne(GoalsStatsBean goalsStatsBean) {
		GoalsStatsBean newOne = new GoalsStatsBean();
		
		newOne.setTimeTypeBean(goalsStatsBean.getTimeTypeBean());
		newOne.setTeamName(goalsStatsBean.getTeamName());
		return newOne;
	}



	public int getLastSeasonDayOddsAndPlayingField(String teamName, ChampEnum champEnum, String playingField) {
		Champ champ = champDao.findByChampEnum(champEnum);
		
		Team teamEnt = teamDao.findByNameAndChamp(teamName, champ);

		GoalsStats goalsStats = goalsStatsRepo.findFirstByTeamAndPlayingField(teamEnt, playingField);
		Integer totalMatches = 0;
		if (goalsStats!= null)
			totalMatches = goalsStats.getTotalMatches();
		
		return totalMatches;
	}
	
	
	
	

}
