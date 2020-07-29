package com.jxl.batch.demo.cache.service;

import java.util.List;
import java.util.Map;

public interface CommonCacheService {
	
	public void remove(CacheKey cacheKey);
	public List<Map<String, Object>> find(CacheKey cacheKey);

}
