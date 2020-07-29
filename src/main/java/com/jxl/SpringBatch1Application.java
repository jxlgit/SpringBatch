package com.jxl;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching//开启缓存，需要显示的指定
public class SpringBatch1Application {

    public static void main(String[] args) throws Exception {
 	    
    	ConfigurableApplicationContext ctx = SpringApplication.run(SpringBatch1Application.class, args);
        
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);  
        JobParameters jobParameters = new JobParametersBuilder()
        		.addString("tableID", "32010601")
        		.addString("name", "111")
                .addDate("date", new Date())  
                .toJobParameters();  
        jobLauncher.run(ctx.getBean("importJobCustom", Job.class), jobParameters); 
  
        System.exit(0); 
        
    }
}