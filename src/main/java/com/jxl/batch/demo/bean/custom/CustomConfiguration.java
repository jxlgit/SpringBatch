package com.jxl.batch.demo.bean.custom;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.jxl.batch.demo.bean.moretable.AdinItemProcessor;
import com.jxl.batch.demo.bean.moretable.AdinJobListener;
import com.jxl.batch.demo.bean.moretable.AdinStepListener;
import com.jxl.jpa.bean.ADInfomation;
import com.jxl.jpa.bean.Charges;
import com.jxl.jpa.bean.Diagnosis;
import com.jxl.jpa.bean.Procedure;
import com.jxl.utils.AnnotationSetComm;

@Configuration
@EnableBatchProcessing
public class CustomConfiguration {

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
    @Qualifier("entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary;
    
    // tag::readerwriterprocessor[]
    //需要使用jobParameters时必须要加上注解@StepScope,@Bean
    @StepScope
    @Bean
    public JpaPagingItemReader<ADInfomation> readerCustom(@Value("#{jobParameters['tableID']}") String tableID) {
    	
    	AnnotationSetComm.modifyTableClassAnnotation(ADInfomation.class, "name", "hdt_adinfomation" + tableID);
  	    AnnotationSetComm.modifyTableClassAnnotation(Charges.class, "name", "hdt_charges" + tableID);
  	    AnnotationSetComm.modifyTableClassAnnotation(Diagnosis.class, "name", "hdt_diagnosis" + tableID);
  	    AnnotationSetComm.modifyTableClassAnnotation(Procedure.class, "name", "hdt_procedure" + tableID);
  	  
  	    //afterPropertiesSet方法，初始化bean的时候执行，可以针对某个具体的bean进行配置。afterPropertiesSet 必须实现 InitializingBean接口,具体参看bean生命周期
  	    //由于spring容器在初始化Bean时就会调用afterPropertiesSet，所以要刷新配置需要重新调用此方法
  	  	entityManagerFactoryPrimary.afterPropertiesSet();
    	
    	return new JpaPagingItemReaderBuilder<ADInfomation>()
            		        .entityManagerFactory(entityManagerFactoryPrimary.getObject())
                            .name("readerAdin")
                            .pageSize(5000)
//                            .queryString("select a from ADInfomation a ")
                            .queryString("select a from ADInfomation a where a.BatchSign = '11' ")
                            .build();

    }

    public AdinItemProcessor processor() {
        return new AdinItemProcessor();
    }

    //注意这里的@Bean注解不能省略，即使下面step是通过方法调用。
    @Bean
    public ItemWriter<ADInfomation> writerCustom() {
//        return new JdbcBatchItemWriterBuilder<ADInfomation>()
//            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//            .sql("INSERT INTO people2 (first_name, last_name) VALUES (:Encounter, :OriginalID)")
//            .dataSource(secondaryDataSource)
//            .build();
        
        MyJdbcBatchItemWriter<ADInfomation> batchItemWriter = new MyJdbcBatchItemWriter<ADInfomation>();
        return batchItemWriter;
    }
    
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Step stepCustom(JpaPagingItemReader<ADInfomation> readerAdin, AdinStepListener adinStepListener) {
        return stepBuilderFactory.get("stepCustom")
        	.listener(adinStepListener)
            .<ADInfomation, ADInfomation> chunk(10000)
            .reader(readerAdin)
            .processor(processor())
            .writer(writerCustom())
            .build();
    }
    
    @Bean
    public Job importJobCustom(AdinJobListener adinJobListener, Step stepCustom) {
    	//jobBuilderFactory.get("importUserJobMap") 
    	//创建步骤构建器并初始化其作业存储库和事务管理器。 请注意，如果构建器用于创建@Bean定义，则步骤名称和bean名称可能会有所不同。
        return jobBuilderFactory.get("importJobCustom")
        	.listener(adinJobListener)
            .incrementer(new RunIdIncrementer())
            .flow(stepCustom)
            .end()
            .build();
    }
    // end::jobstep[]
}
