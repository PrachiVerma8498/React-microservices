package com.learnms.limitservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties("limit-service")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LimitConfig {
	
	private int maximum;
	private int minimum;
	
	
	
}
