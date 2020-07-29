package com.jxl.batch.demo.bean.custom;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.jxl.jpa.bean.ADInfomation;

public class MyJdbcBatchItemWriter<T> implements ItemWriter<T>, InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(MyJdbcBatchItemWriter.class);
	
	@Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(List<? extends T> items) throws Exception {
		// TODO Auto-generated method stub
        String sql = "INSERT INTO people2 (first_name, last_name) VALUES (?, ?)";
        
//        List<T> items1 = (List<T>)items;
//		int[][] batchCount = jdbcTemplate2.batchUpdate(sql, items1, 10, new ParameterizedPreparedStatementSetter<T>() {
//
//			@Override
//			public void setValues(PreparedStatement ps, T object) throws SQLException {
//				try {
//					ADInfomation adInfomation = (ADInfomation)object;
//					ps.setObject(1, adInfomation.getEncounter());
//					ps.setObject(2, adInfomation.getAdmissionSource());
//				} catch (Exception e) {
//					e.printStackTrace();
//					log.error("批量插入表：people2出错！", e);
//					throw new RuntimeException(e);
//				}
//
//			}
//		});
		
		
		int[] updateCounts = jdbcTemplate2.execute(sql, new PreparedStatementCallback<int[]>() {
			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				log.info("处理大小：" + items.size());
				for (T item : items) {
					ADInfomation adInfomation = (ADInfomation)item;
					ps.setObject(1, adInfomation.getEncounter());
					ps.setObject(2, adInfomation.getAdmissionSource());
					ps.addBatch();
				}
				return ps.executeBatch();
			}
		});
		
		log.info("处理条数：" + Arrays.toString(updateCounts));
		
	}

}
