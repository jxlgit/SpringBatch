package com.jxl.batch.demo.bean.moretable;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jxl.jpa.bean.People;

@Configuration
@EnableBatchProcessing
public class PeopleBatchConfiguration {

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
    
    @Autowired
    @Qualifier("entityManagerPrimary")
    protected EntityManagerFactory factoryBean;
    
    
    // tag::readerwriterprocessor[]
    
    public JpaPagingItemReader<People> readerJDBC2() {
            return new JpaPagingItemReaderBuilder<People>()
            		        .entityManagerFactory(factoryBean)
                            .name("PeopleReader")
                            .queryString("select p from People p")
                            .build();

    }

    public PeopleItemProcessor processor() {
        return new PeopleItemProcessor();
    }

    //注意这里的@Bean注解不能省略，即使下面step是通过方法调用。
    @Bean
    public JdbcBatchItemWriter<People> writer() {
        return new JdbcBatchItemWriterBuilder<People>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO people2 (first_name, last_name) VALUES (:firstName, :lastName)")
            .dataSource(secondaryDataSource)
            .build();
    }
    
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importPeopleJob1(Step step1) {
    	//jobBuilderFactory.get("importUserJobMap") 
    	//创建步骤构建器并初始化其作业存储库和事务管理器。 请注意，如果构建器用于创建@Bean定义，则步骤名称和bean名称可能会有所不同。
        return jobBuilderFactory.get("importPeopleJob1")
            .incrementer(new RunIdIncrementer())
            .flow(step1())
            .end()
            .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
            .<People, People> chunk(5)
            .reader(readerJDBC2())
            .processor(processor())
            .writer(writer())
            .build();
    }
    
    // end::jobstep[]
}