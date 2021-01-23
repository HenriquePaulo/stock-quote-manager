package com.paulo.stockquotemanager.Model;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.paulo.stockquotemanager.json.StockManagerJson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Quote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String quoteDate;
	private Double price;
	private String stockId;
	
	public Quote(Map.Entry<String, String> quotes, StockManagerJson stockManagerJson) {
		this.quoteDate = quotes.getKey();
		this.price = Double.valueOf(quotes.getValue());
		this.stockId = stockManagerJson.getId();
		
	}

}
