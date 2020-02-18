package com.learning;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(path="/orders/fromEmail")
public class ProcessOrderFromEmail {
	
	// @RequestMapping(value = "/orders/fromEmail", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	/*@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> processOrder(@RequestBody Order order) throws Exception {
		
		// Receive order2=Order(email=null, tacos=[])
		System.out.println("Receive order2=" + order.toString());
		return new ResponseEntity(HttpStatus.CREATED);
	}*/
	
	@RequestMapping(value = "/orders/fromEmail", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	// @PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void processOrder(@RequestBody Order order) throws Exception {		
		// Receive order2=Order(email=null, tacos=[])
		System.out.println("Receive order2=" + order.toString());
	}
	
}
