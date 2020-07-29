package com.jxl.batch.demo.map.onetable;

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
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@Configuration
@EnableBatchProcessing
public class MapBatchConfiguration {
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
	    
	    //需要使用jobParameters时必须要加上注解@StepScope
	    @StepScope
	    @Bean
	    public JdbcCursorItemReader<Map<String, Object>> readerJDBCToMap(@Value("#{jobParameters['name']}") String name) {
	            return new JdbcCursorItemReaderBuilder<Map<String, Object>>()
	                            .dataSource(primaryDataSource)
	                            .name("personReader")
	                            .sql("select person_id, first_name, last_name from people where first_name = ?")
	                            .queryArguments(new Object[] {name})
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
	                            .build();

	    }
	    
	    @Bean
	    public MapItemProcessor processorToMap() {
	        return new MapItemProcessor();
	    }
	    
	    ////注意这里的@Bean注解不能省略，即使下面step是通过方法调用。
	    @Bean
	    public JdbcBatchItemWriter<Map<String, Object>> writerToMap() {
	        return new JdbcBatchItemWriterBuilder<Map<String, Object>>()
	            .itemSqlParameterSourceProvider(new ItemSqlParameterSourceProvider<Map<String,Object>>() {
					
					@Override
					public SqlParameterSource createSqlParameterSource(Map<String, Object> item) {
						// TODO Auto-generated method stub
						MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
						mapSqlParameterSource.addValues(item);
						return mapSqlParameterSource;
					}
				})
	            .sql("INSERT INTO people2 (first_name, last_name) VALUES (:first_name, :last_name)")
	            .dataSource(secondaryDataSource)
	            .build();
	    }
	    
	    // end::readerwriterprocessor[]

	    // tag::jobstep[]
	    @Bean
	    public Job importUserJobMap(MapJobListener mapJobListener, Step stepMap) {
	    	//jobBuilderFactory.get("importUserJobMap") 创建步骤构建器并初始化其作业存储库和事务管理器。 请注意，如果构建器用于创建@Bean定义，则步骤名称和bean名称可能会有所不同。
	        return jobBuilderFactory.get("importUserJobMap")
	            .incrementer(new RunIdIncrementer())
	            .listener(mapJobListener)
	            .flow(stepMap)
	            .end()
	            .build();
	    }
	    
	    @Bean
	    public Step stepMap(MapStepListener mapStepListener, JdbcBatchItemWriter<Map<String, Object>> writer) {
	        return stepBuilderFactory.get("stepMap")
	        	.listener(mapStepListener)
	            .<Map<String, Object>, Map<String, Object>> chunk(5)
	            .reader(readerJDBCToMap(null))
	            .processor(processorToMap())
	            .writer(writer)
	            .build();
	    }
	    
	    // end::jobstep[]
	}