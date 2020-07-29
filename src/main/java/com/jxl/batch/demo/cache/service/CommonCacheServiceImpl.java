package com.jxl.batch.demo.cache.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * ClassName: CommonCacheServiceImpl
 * date: 2018年4月29日 下午10:03:40
 * 
 * @author jiangxiaolong 
 * @version  
 * @since JDK 1.8 
 *
 * @describe 
 *
 * @Cacheable(value = "jxl", key = "#p0")
 * value、cacheNames：两个等同的参数（cacheNames为Spring 4新增，作为value的别名），用于指定缓存存储的集合名(ehcache-config.xml中的cache name)。
 * 由于Spring 4中新增了@CacheConfig，因此在Spring 3中原本必须有的value属性，也成为非必需项了。
 * key：缓存对象存储在Map集合中的key值，非必需，缺省按照函数的所有参数组合作为key值，若自己配置需使用SpEL表达式，比如：@Cacheable(key = "#p0")：使用函数第一个参数作为缓存的key值。
 * 
 */
@Service
@CacheConfig(cacheNames = "listMap")
public class CommonCacheServiceImpl implements CommonCacheService {

	private static final Logger log = LoggerFactory.getLogger(CommonCacheServiceImpl.class);
	
	@Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;
	
	@Autowired
    @Qualifier("secondaryJdbcTemplate")
	private JdbcTemplate secondaryJdbcTemplate;
	
	@Override
    @CacheEvict
    public void remove(CacheKey cacheKey) {
		log.info("删除了缓存数据:" + cacheKey);
        //这里不做实际删除操作
    }

    @Override
    @Cacheable(key = "#p0")
    public List<Map<String, Object>> find(CacheKey cacheKey) {
    	List<Map<String, Object>> cacheDatas = new ArrayList<Map<String, Object>>();
    	log.info("获取缓存数据条数:" + cacheDatas.size());
    	switch (cacheKey) {
		case USER:
			cacheDatas = primaryJdbcTemplate.queryForList("select * from people");
			log.info("获取查询数据条数:" + cacheDatas.size());
			break;
		case PEOPLE:
			cacheDatas = secondaryJdbcTemplate.queryForList("select * from people2");
			log.info("获取查询数据条数:" + cacheDatas.size());
			break;
		default:
			break;
		}
        return cacheDatas;
    }
	
}
