package com.jxl.batch.demo.bean.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomStepListener extends StepExecutionListenerSupport {
	private static final Logger log = LoggerFactory.getLogger(CustomStepListener.class);

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
		log.info("===== Step执行开始 =====");
		
		super.beforeStep(stepExecution);
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
		log.info("===== Step执行完成 =====");
		
		return super.afterStep(stepExecution);
	}
	
}
