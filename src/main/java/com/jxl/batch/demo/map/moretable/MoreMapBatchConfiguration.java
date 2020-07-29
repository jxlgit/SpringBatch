package com.jxl.batch.demo.map.moretable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Configuration
@EnableBatchProcessing
public class MoreMapBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("primaryDataSource")
	public DataSource primaryDataSource;

	@Autowired
	@Qualifier("secondaryDataSource")
	public DataSource secondaryDataSource;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;

	// tag::readerwriterprocessor[]

	@Bean
	public MyJdbcPagingItemReader<Map<String, Object>> readerJDBCToMoreMap() {
		
		Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("first_name", "111");
        
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("person_id", Order.DESCENDING);
        
		return new MyJdbcPagingItemReaderBuilder<Map<String, Object>>()
		        .name("creditReader")
		        .dataSource(primaryDataSource)
		        
		        .selectClause("select person_id, first_name")
		        .fromClause("from people")
		        .whereClause("where first_name=:first_name")
		        .sortKeys(sortKeys)
		        
		        
		        .parameterValues(parameterValues)
		        .rowMapper(new RowMapper<Map<String,Object>>() {
					
					@Override
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Map<String, Object> map = new HashMap<String, Object>();
						for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
							map.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
						}
						return map;
					}
				})
		        .pageSize(10)
		        .build();

	}

	@Bean
	public MoreMapItemProcessor processorToMoreMap() {
		return new MoreMapItemProcessor();
	}

	//注意这里的@Bean注解不能省略，即使下面step是通过方法调用。
	@Bean
	public ItemWriter<Map<String, Object>> writerToMoreMap() {
		return new MyItemWriter();
	}

	// end::readerwriterprocessor[]

	// tag::jobstep[]
	@Bean
	public Job importUserJobMoreMap() {
		// jobBuilderFactory.get("importUserJobMap") 创建步骤构建器并初始化其作业存储库和事务管理器。
		// 请注意，如果构建器用于创建@Bean定义，则步骤名称和bean名称可能会有所不同。
		return jobBuilderFactory.get("importUserJobMoreMap")
				.incrementer(new RunIdIncrementer())
				.start(stepMoreMap())
				.build();
	}

	@Bean
	public Step stepMoreMap() {
		return stepBuilderFactory.get("stepMoreMap")
				.<Map<String, Object>, Map<String, Object>>chunk(5)
			    .reader(readerJDBCToMoreMap())
			   .processor(processorToMoreMap())
			   .writer(writerToMoreMap())
			   .build();
	}
	

	// end::jobstep[]
}
