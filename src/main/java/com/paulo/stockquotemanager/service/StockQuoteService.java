package com.paulo.stockquotemanager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.stockquotemanager.Model.Quote;
import com.paulo.stockquotemanager.exception.ObjectNotFoundException;
import com.paulo.stockquotemanager.json.ResponseMessage;
import com.paulo.stockquotemanager.json.StockManagerJson;
import com.paulo.stockquotemanager.repository.QuoteRepository;
import com.paulo.stockquotemanager.util.Constant;
import com.paulo.stockquotemanager.validate.StockQuoteServiceValidate;

@Service
public class StockQuoteService {
	
	@Autowired
	private QuoteRepository quoteRepository;
	
	@Autowired
	private StockManagerService stockManagerService;
	
	@Autowired
	private StockQuoteServiceValidate stockQuoteServiceValidate;
	
	
	public ResponseMessage create(StockManagerJson stockManagerJson) throws Exception {
		stockQuoteServiceValidate.validate(stockManagerJson);
		stockManagerService.callPost(stockManagerJson);		
		stockManagerJson.getQuotes().entrySet().forEach(p -> quoteRepository.save(new Quote(p, stockManagerJson)));
		return new ResponseMessage(Constant.CREATED_SUCCESS);	
	}

	public List<StockManagerJson> findAll() throws Exception {
		List<StockManagerJson> stockList = stockManagerService.callGet();
		stockList.forEach(p -> p.setQuotes(parseQuoteToMap(quoteRepository.findByStockId(p.getId()))));
		return stockList;
	}

	public StockManagerJson findById(String id) throws ObjectNotFoundException, Exception {
		StockManagerJson stockManagerJson;
		try {
			stockQuoteServiceValidate.validateId(id);
			stockManagerJson = stockManagerService.findById(id);
			stockManagerJson.setQuotes(parseQuoteToMap(quoteRepository.findByStockId(id)));
			return stockManagerJson;
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException(String.format(Constant.NOT_FOUND, Constant.STOCK));
		}
	}
	
	private Map<String, String> parseQuoteToMap(List<Quote> listQuote) {
		Map<String, String> mapQuote = new HashMap<>();
		if (null != listQuote) {
			listQuote.forEach(p -> mapQuote.put(p.getQuoteDate(), p.getPrice().toString()));			
		}
		return mapQuote;
	}
}
