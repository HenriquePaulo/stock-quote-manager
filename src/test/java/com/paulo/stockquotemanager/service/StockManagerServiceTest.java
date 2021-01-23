package com.paulo.stockquotemanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.paulo.stockquotemanager.json.StockManagerJson;
import com.paulo.stockquotemanager.util.Constant;

@SpringBootTest
@AutoConfigureMockMvc
public class StockManagerServiceTest {

	@Autowired
	private StockManagerService stockManagerService;
	
	@Autowired
	private CacheService cacheService;

	@Value("${stock.manager.link}")
	private String link;
	
	@Test
	public void createNewManagerStockTest() throws Exception {
		RestTemplate restTemplate = mock(RestTemplate.class);
		
		stockManagerService.setRestTemplate(restTemplate);
		
		StockManagerJson stockManagerJsonMock = new StockManagerJson("ciel3", "test");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<StockManagerJson> entity = new HttpEntity<StockManagerJson>(stockManagerJsonMock, headers);

		when(restTemplate.exchange(link, HttpMethod.POST, entity, StockManagerJson.class))
		.thenReturn(new ResponseEntity<StockManagerJson>(stockManagerJsonMock, HttpStatus.OK));
		
		assertEquals(stockManagerService.callPost(stockManagerJsonMock).getMessage(),
				Constant.CREATED_SUCCESS);
	}

	@Test
	public void getReturnTwoOrMoreTest() throws Exception {
		stockManagerService.setRestTemplate(new RestTemplate());
		assertTrue(stockManagerService.callGet().size() >= 2);
	}

	@Test
	public void checkDuplicationCallsTest() throws Exception {
		RestTemplate restTemplate = mock(RestTemplate.class);
		StockManagerJson[] stockManagerJsonMock = { new StockManagerJson("ciel3", "test") };
		stockManagerService.setRestTemplate(restTemplate);
		when(restTemplate.getForObject(link, StockManagerJson[].class)).thenReturn(stockManagerJsonMock);
		cacheService.clearCache();
		stockManagerService.findById("ciel3");
		stockManagerService.findById("ciel3");
		verify(restTemplate, times(1)).getForObject(link, StockManagerJson[].class);
	}

}
