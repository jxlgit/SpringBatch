package com.jxl.jpa.datasources;
  
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;  

/**
 * 
 * ClassName: SecondaryConfig
 * date: 2018年5月12日 下午6:18:53
 * 
 * @author jiangxiaolong 
 * @version  
 * @since JDK 1.8 
 *
 * @describe 数据源二
 *
 */
@Configuration  
@EnableTransactionManagement  
@EnableJpaRepositories(  
        entityManagerFactoryRef="entityManagerFactorySecondary",  
        transactionManagerRef="transactionManagerSecondary",  
        basePackages= { "com.jxl.jpa.dao2" }) //设置Repository所在位置  
public class SecondaryConfig {  
  
    @Autowired  
    @Qualifier("secondaryDataSource")  
    private DataSource secondaryDataSource;  
    
    @Bean(name = "entityManagerSecondary")  
    public EntityManagerFactory entityManager(EntityManagerFactoryBuilder builder) {  
        return entityManagerFactorySecondary(builder).getObject();  
    }  
    
    @Bean(name = "entityManagerFactorySecondary")  
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {  
        return builder  
                .dataSource(secondaryDataSource)  
                .packages("com.jxl.jpa.bean") //设置实体类所在位置  
                .persistenceUnit("secondaryPersistenceUnit")  
                .build();  
    } 
    
    @Bean(name = "transactionManagerSecondary")  
    public PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {  
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());  
    }  
  
}  

