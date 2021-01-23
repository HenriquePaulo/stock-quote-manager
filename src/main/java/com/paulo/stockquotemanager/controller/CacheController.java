package com.paulo.stockquotemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulo.stockquotemanager.json.ResponseMessage;
import com.paulo.stockquotemanager.service.CacheService;
import com.paulo.stockquotemanager.util.Constant;

@RestController
public class CacheController {

	@Autowired
	private CacheService cacheService;
	
	@DeleteMapping("/stockcache")
	public ResponseEntity<ResponseMessage> clearStockCache() {
		cacheService.clearCache();
		
		return ResponseEntity.ok(new ResponseMessage(Constant.CLEAN));
	}
}
