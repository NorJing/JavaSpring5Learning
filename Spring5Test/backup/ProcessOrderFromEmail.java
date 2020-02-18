package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Order2;

@RestController
@RequestMapping(path="/orders/fromEmail")
public class ProcessOrderFromEmail {
	
	@PostMapping
	public void processOrder(Order2 order) {
		System.out.println("Receive order2=" + order.toString());
	}
}
