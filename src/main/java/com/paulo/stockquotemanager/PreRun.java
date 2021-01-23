package com.paulo.stockquotemanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.paulo.stockquotemanager.json.StockManagerJson;
import com.paulo.stockquotemanager.service.StockManagerService;

@Component
public class PreRun implements CommandLineRunner {

	private final StockManagerService stockManagerService;
	
	@Value("${server.port}")
	private Integer port;
	
	@Value("${server.host.url}")
	private String host;

	public PreRun(StockManagerService stockManagerService) {
		this.stockManagerService = stockManagerService;
	}

	@Override
	public void run(String... args) throws Exception {
		stockManagerService.notifyQuoteManager();
		List<StockManagerJson> stocks = stockManagerService.callGet();
		for(StockManagerJson stockJson : stocks) {
			stockManagerService.findById(stockJson.getId());
		}
	}
}
