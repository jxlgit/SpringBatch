package com.jxl.batch.demo.bean.moretable;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jxl.batch.demo.cache.service.CacheKey;
import com.jxl.batch.demo.cache.service.CommonCacheServiceImpl;

@Component
public class AdinJobListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(AdinJobListener.class);
	
	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CommonCacheServiceImpl commonCacheServiceImpl;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
		super.beforeJob(jobExecution);
		log.info("===== JOB执行开始 =====");
		
		log.info("在这里可以执行查询数据库中数据至缓存中用于数据传递");
		List<Map<String, Object>> dataList = commonCacheServiceImpl.find(CacheKey.USER);
		log.info("初始化数据：" + dataList.size());
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			
			List<Map<String, Object>> dataList1 = commonCacheServiceImpl.find(CacheKey.USER);
			log.info("查询缓存数据：" + dataList1.size());
			
			//移除缓存
			commonCacheServiceImpl.remove(CacheKey.USER);
			
			List<Map<String, Object>> dataList2 = commonCacheServiceImpl.find(CacheKey.USER);
			log.info("查询缓存数据：" + dataList2.size());
			
			log.info("===== JOB执行完成 =====");
		}
	}
	
}
