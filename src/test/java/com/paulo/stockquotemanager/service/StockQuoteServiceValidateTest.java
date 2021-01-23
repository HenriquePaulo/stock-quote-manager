package com.paulo.stockquotemanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paulo.stockquotemanager.json.StockManagerJson;
import com.paulo.stockquotemanager.util.Constant;
import com.paulo.stockquotemanager.validate.StockQuoteServiceValidate;

@SpringBootTest
public class StockQuoteServiceValidateTest {

	@Autowired
	private StockQuoteServiceValidate stockQuoteServiceValidate;

	@Test
	public void validIdTest() throws Exception {
		stockQuoteServiceValidate.validateId("val5");
	}
	
	@Test
	public void invalidIdTest() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			stockQuoteServiceValidate.validate(new StockManagerJson());
		});
		assertEquals(exception.getMessage(), String.format(Constant.NULL_FIELD_ERROR, Constant.ID));
	}

}
