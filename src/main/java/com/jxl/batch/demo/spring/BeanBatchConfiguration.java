package com.jxl.batch.demo.spring;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableBatchProcessing
public class BeanBatchConfiguration {

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
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
            .name("personItemReader")
            .resource(new ClassPathResource("sample-data.csv"))
            .delimited()
            .names(new String[]{"firstName", "lastName"})
            .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }})
            .build();
    }
    
    @Bean
    public JdbcCursorItemReader<Person> readerJDBC() {
            return new JdbcCursorItemReaderBuilder<Person>()
                            .dataSource(primaryDataSource)
                            .name("personReader")
                            .sql("select person_id, first_name, last_name from people")
                            .rowMapper(new CustomerPersonRowMapper())
                            .build();

    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    //注意这里的@Bean注解不能省略，即使下面step是通过方法调用。
    @Bean
    public JdbcBatchItemWriter<Person> writer() {
        return new JdbcBatchItemWriterBuilder<Person>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO people2 (first_name, last_name) VALUES (:firstName, :lastName)")
            .dataSource(secondaryDataSource)
            .build();
    }
    
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
    	////jobBuilderFactory.get("importUserJobMap") 创建步骤构建器并初始化其作业存储库和事务管理器。 请注意，如果构建器用于创建@Bean定义，则步骤名称和bean名称可能会有所不同。
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
//            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Person> writer) {
        return stepBuilderFactory.get("step1")
            .<Person, Person> chunk(5)
            .reader(readerJDBC())
            .processor(processor())
            .writer(writer)
            .build();
    }
    
    // end::jobstep[]
}