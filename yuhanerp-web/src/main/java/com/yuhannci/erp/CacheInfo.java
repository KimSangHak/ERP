package com.yuhannci.erp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CacheInfo implements CommandLineRunner {

	@Autowired CacheManager cacheManager; 


	@Override
	public void run(String... args) throws Exception {
		
		log.info("cache manager is " + cacheManager.getClass().getName() );		
	}

}
