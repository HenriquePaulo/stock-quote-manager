package com.paulo.stockquotemanager.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paulo.stockquotemanager.Model.Quote;

@SpringBootTest
public class QuoteRepositoryTest {
	@Autowired
	private QuoteRepository quoteRepository;

	@Test
	public void saveQuote() {
		Quote quote = new Quote();
		quote.setStockId("unit-test-paulo");
		quote.setQuoteDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		quote.setPrice(10.0);
		assertThat(quoteRepository.save(quote)).isNotNull();
	}
}
