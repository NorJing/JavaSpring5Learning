package com.example.demo.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Order;

@Component
public class Sender {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${jms.queue.destination}")
    String destinationQueue;
	
	public void sendOrder(Order order) {
		LOGGER.info("Sending order='{}'", order);
	    jmsTemplate.convertAndSend(destinationQueue, order);
	}
	
}
