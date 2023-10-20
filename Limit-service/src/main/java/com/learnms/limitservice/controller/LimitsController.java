package com.learnms.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnms.limitservice.bean.Limits;
import com.learnms.limitservice.configuration.LimitConfig;

@RestController
public class LimitsController {

	@Autowired
	private LimitConfig configuration;
	
@GetMapping(value =   "/limits")
	public Limits retrieveLimits() {
		System.out.println(new Limits(1,1000));
		return new Limits(configuration.getMinimum(),configuration.getMaximum());
	}
}
