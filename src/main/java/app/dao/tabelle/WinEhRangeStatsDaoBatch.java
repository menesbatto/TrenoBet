//package app.dao.tabelle;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import java.sql.PreparedStatement;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BatchPreparedStatementSetter;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.SingleConnectionDataSource;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import app.dao.tabelle.entities.WinEhRangeStats;
//
//@Repository
//public class WinEhRangeStatsDaoBatch {
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	private Integer id = 1;
//
//	
//	public void saveBatch ( List<WinEhRangeStats> winEhRangeStatsList) {
//		if (id == null)
//			initId();
//		try {
//			
//			Connection connection = jdbcTemplate.getDataSource().getConnection();
//		
//			Statement stmt = connection.createStatement();
//			
//			connection.setAutoCommit(false);
//
//			int homeVariation_id,  timeType_id, team_id, range_id;
//			Double winPerc, losePerc, drawPerc;
//			Integer homeHits, homeMisses, drawHits, drawMisses, awayHits, awayMisses, total;
//
//			for(WinEhRangeStats ent : winEhRangeStatsList){
//				homeVariation_id = ent.getHomeVariation().getId();
//				timeType_id = ent.getTimeType().getId();
//				team_id = ent.getTeam().getId();
//				range_id = ent.getRange().getId();
//
//				homeHits = ent.getHomeHits();
//				homeMisses = ent.getHomeMisses();
//
//				drawHits = ent.getDrawHits();
//				drawMisses = ent.getDrawMisses();
//
//				awayHits = ent.getAwayHits();
//				awayMisses = ent.getAwayMisses();
//
//				winPerc = ent.getWinPerc();
//				drawPerc = ent.getDrawPerc();
//				losePerc = ent.getLosePerc();
//
//				total = ent.getTotal();
//
//				String string = "insert into WinEhRangeStats (		ID				, homeVariation_id,			team_id,			timeType_id,			range_id,			homeHits,			homeMisses,			drawHits,			drawMisses,			awayHits,			awayMisses,			winPerc,			drawPerc,			losePerc,			total	) "
//									+ 				"VALUES (" 		+ id++ 			+ "," +  homeVariation_id 	+ "," + team_id		+ "," + timeType_id		+ "," + range_id	+ "," + homeHits	+ "," + homeMisses	+ "," + drawHits	+ "," + drawMisses	+ "," + awayHits	+ "," + awayMisses	+ "," + winPerc		+ "," + drawPerc	+ "," + losePerc+ 	"," + total 		+")" ;
//				stmt.addBatch(string);	
//			}
//			
//			int[] result = stmt.executeBatch();
////			System.out.println("The number of rows affected: "+ result.length);
//			connection.commit();
//			if(stmt!=null)
//				stmt.close();
//			if(connection!=null)
//				connection.close();
//		
//	
//		
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	private void initId() {
//		
//		String query = "SELECT id FROM WinEhRangeStats ORDER BY id DESC LIMIT 1";
//		
//	    Integer idFound= jdbcTemplate.queryForObject(query, Integer.class);
//		
//		this.id= idFound;
//			
//	
//		
//		
//	}
//	
//}