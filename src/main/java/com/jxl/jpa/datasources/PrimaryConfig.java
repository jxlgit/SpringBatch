package com.jxl.jpa.datasources;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;  
  
/**
 * 
 * ClassName: PrimaryConfig
 * date: 2018年5月12日 下午6:18:34
 * 
 * @author jiangxiaolong 
 * @version  
 * @since JDK 1.8 
 *
 * @describe 数据源一 
 *
 */
@Configuration  
@Configurable(preConstruction = true)
@EnableTransactionManagement  
@EnableJpaRepositories(  
        entityManagerFactoryRef="entityManagerFactoryPrimary",  
        transactionManagerRef="transactionManagerPrimary",  
        basePackages= { "com.jxl.jpa.dao" }) //设置Repository所在位置  
public class PrimaryConfig {  
  
	
    @Autowired  
    @Qualifier("primaryDataSource")  
    private DataSource primaryDataSource;  
    
    @Primary  
    @Bean(name = "entityManagerPrimary")  
    public EntityManagerFactory entityManager(EntityManagerFactoryBuilder builder) {  
        return entityManagerFactoryPrimary(builder).getObject();  
    }  
    
    @Primary  
    @Bean(name = "entityManagerFactoryPrimary")  
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {  

    	return builder  
                .dataSource(primaryDataSource)  
                .packages("com.jxl.jpa.bean") //设置实体类所在位置  
                .persistenceUnit("primaryPersistenceUnit")
                .build();  
    }  
    
    @Primary  
    @Bean(name = "transactionManagerPrimary")  
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {  
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());  
    }  
    
}  

