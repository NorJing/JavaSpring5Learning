package com.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path="/orders/fromEmail")
public class ProcessOrderFromEmail {
	
	//@Autowired
	//private RestTemplate restTemplate;
	
	/*public ProcessOrderFromEmail(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}*/
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void processTaco(@RequestBody Taco taco) throws Exception {
		System.out.println("Receive taco=" + taco.toString());
	}
	
}
