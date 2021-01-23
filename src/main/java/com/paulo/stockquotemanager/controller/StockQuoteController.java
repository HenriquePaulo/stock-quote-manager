package com.paulo.stockquotemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paulo.stockquotemanager.exception.ObjectNotFoundException;
import com.paulo.stockquotemanager.json.ResponseMessage;
import com.paulo.stockquotemanager.json.StockManagerJson;
import com.paulo.stockquotemanager.service.StockQuoteService;

@RestController
public class StockQuoteController {
	
	@Autowired
	private StockQuoteService StockQuoteService;
	
	@PostMapping("/")
	public ResponseEntity<ResponseMessage> create(@RequestBody StockManagerJson stockManagerJson) throws Exception {
		return ResponseEntity.ok(StockQuoteService.create(stockManagerJson));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<StockManagerJson>> findAll() throws Exception {
		return ResponseEntity.ok(StockQuoteService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StockManagerJson> findById(@PathVariable String id) throws ObjectNotFoundException, Exception {
		return ResponseEntity.ok(StockQuoteService.findById(id));
	}

}
