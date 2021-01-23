package com.paulo.stockquotemanager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.paulo.stockquotemanager.exception.ObjectNotFoundException;
import com.paulo.stockquotemanager.json.StockManagerJson;


@SpringBootTest
@AutoConfigureMockMvc
public class StockQuoteServiceTest {

	@Autowired
	private StockQuoteService stockQuoteService;
	
	@Test
	public void createErrorTest() throws Exception {
		Map<String, String> quotes = new HashMap<>();
		quotes.put("2019-01-01", "10");
		quotes.put("2019-01-02", "11");
		StockManagerJson json = new StockManagerJson("jfdh23", "Petrobras PN", quotes);
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			stockQuoteService.create(json).getMessage();
		  });
	}

	@Test
	public void findByIdTest() throws Exception {
		String id = "vale5";
		assertThat(stockQuoteService.findById(id)).isNotNull();
	}
	
	@Test
	public void findByIdErrorTest() throws Exception {
		String id = "ka3";
		Assertions.assertThrows(ObjectNotFoundException.class, () -> {
			stockQuoteService.findById(id);
		  });
	}
}
