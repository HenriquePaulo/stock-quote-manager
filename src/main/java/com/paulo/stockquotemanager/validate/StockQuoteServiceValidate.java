package com.paulo.stockquotemanager.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.stockquotemanager.exception.ValidateException;
import com.paulo.stockquotemanager.json.StockManagerJson;
import com.paulo.stockquotemanager.service.StockManagerService;
import com.paulo.stockquotemanager.util.Constant;

@Service
public class StockQuoteServiceValidate {

	@Autowired
	private StockManagerService stockManagerService;
	
	

	public void validate(StockManagerJson stockManagerJson) throws Exception {
		validateId(stockManagerJson.getId());		
		stockIsRegistered(stockManagerJson.getId());
	}

	public void validateId(String id) throws ValidateException {
		if (null == id) {
			throw new ValidateException(String.format(Constant.NULL_FIELD_ERROR, Constant.ID));
		}
	}
	
	public void stockIsRegistered(String id) throws Exception {
		
		stockManagerService.findById(id);
	}

}
