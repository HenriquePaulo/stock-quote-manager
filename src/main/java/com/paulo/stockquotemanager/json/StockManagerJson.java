package com.paulo.stockquotemanager.json;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockManagerJson {

	private String id;
	private String description;
	private Map<String, String> quotes;
	
	public StockManagerJson(String id, String description) {
		this.id = id;
		this.description = description;
	}
}
