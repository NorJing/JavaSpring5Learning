package com.example.demo.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.example.demo.domain.Order;

public class Sender {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String message) {
		LOGGER.info("1 sending message='{}'", message);
	    jmsTemplate.convertAndSend("tacoqueue1", message);
	}
	
	public void sendOrder(Order order) {
		LOGGER.info("Sending order='{}'", order);
	    jmsTemplate.convertAndSend("tacoqueue1", order);
	}
	
}
