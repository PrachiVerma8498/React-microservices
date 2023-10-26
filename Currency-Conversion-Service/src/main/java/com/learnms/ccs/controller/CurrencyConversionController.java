package com.learnms.ccs.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learnms.ccs.bean.CurrencyConversion;
import com.learnms.ccs.bean.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		HashMap<String, String> uriVariables=new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().
				getForEntity("http://localhost:4234/currencyExchange/from/{from}/to/{to}", CurrencyConversion.class,uriVariables);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		BigDecimal calculatedAmount= currencyConversion.getConversionMultiple().multiply(quantity);
		return new CurrencyConversion(currencyConversion.getId(),from,to, quantity
				, currencyConversion.getConversionMultiple(), calculatedAmount	,currencyConversion.getEnvironment());
		
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		
		CurrencyConversion currencyConversion =proxy.retrieveExchangeValue(from, to);
		BigDecimal calculatedAmount= currencyConversion.getConversionMultiple().multiply(quantity);
		return new CurrencyConversion(currencyConversion.getId(),from,to, quantity
				, currencyConversion.getConversionMultiple(), calculatedAmount	,currencyConversion.getEnvironment()+" - feign");
		
	}

}
