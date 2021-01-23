package com.paulo.stockquotemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paulo.stockquotemanager.Model.Quote;


@Repository
public interface QuoteRepository extends CrudRepository<Quote, String>{
	@Query("SELECT p FROM Quote p WHERE p.stockId = ?1")
	List<Quote> findByStockId(String stockId);
	
}
