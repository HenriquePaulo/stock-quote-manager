package com.paulo.stockquotemanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

@SpringBootTest
@AutoConfigureMockMvc
public class CacheServiceTest {
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Test
    public void ClearCacheTest() {
        cacheService.clearCache();
        assertEquals(cacheManager.getCache("cache").invalidate(), false);
    }
}
