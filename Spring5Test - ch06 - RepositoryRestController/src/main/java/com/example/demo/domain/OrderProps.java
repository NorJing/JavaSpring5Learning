package com.example.demo.domain;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "taco.order")
@Valid
public class OrderProps {

	@Min(value = 5, message="value must be between 5 and 20")
	@Max(value = 20, message="value must be between 5 and 20")
	private int pageSize;
	
}
