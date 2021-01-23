package com.paulo.stockquotemanager.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.paulo.stockquotemanager.json.NotifyJson;
import com.paulo.stockquotemanager.json.ResponseMessage;
import com.paulo.stockquotemanager.json.StockManagerJson;
import com.paulo.stockquotemanager.util.Constant;

@Service
public class StockManagerService {
	
	RestTemplate restTemplate;
	
	@Value("${stock.manager.link}")
	private String link;
	
	@Value("${stock.manager.link.notify}")
	private String linkNotify;

	@Value("${server.port}")
	private Integer port;

	@Value("${server.host.url}")
	private String host;

	@Cacheable(value = "cache", key = "#id", condition = "#id != null")
	public StockManagerJson findById(String id) throws Exception {
		List<StockManagerJson> ListResponse = callGet();
		
		return ListResponse.stream()
			.filter(p -> p.getId().equals(id))
			.findFirst()
			.get();
	}
	
	public StockManagerService() {
		if (null == this.restTemplate) {
			this.restTemplate = new RestTemplate();
		}
	}
	
	public List<StockManagerJson> callGet() throws Exception {
			StockManagerJson[] result = this.restTemplate.getForObject(link, StockManagerJson[].class);
			return Arrays.asList(result);
	}

	public ResponseMessage callPost(StockManagerJson stockManagerJson) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<StockManagerJson> entity = new HttpEntity<StockManagerJson>(stockManagerJson, headers);
		this.restTemplate.exchange(link, HttpMethod.POST, entity, StockManagerJson.class);
		return new ResponseMessage(Constant.CREATED_SUCCESS);
	}

	public void notifyQuoteManager() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		NotifyJson notificationJson = new NotifyJson(host, port);
		HttpEntity<NotifyJson> entity = new HttpEntity<NotifyJson>(notificationJson, headers);
		try {
			restTemplate.exchange(linkNotify, HttpMethod.POST, entity, NotifyJson[].class);
		} catch (HttpClientErrorException ex) {
			throw ex;
		} catch (RuntimeException ex) {
			throw ex;
		}
		
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
