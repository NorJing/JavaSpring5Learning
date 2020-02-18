package com.learning;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/orders/fromEmail")
public class ProcessOrderFromEmail {
	
	@PostMapping
	public void processOrder(Order order) {
		System.out.println("Receive order=" + order.toString());
	}
}
