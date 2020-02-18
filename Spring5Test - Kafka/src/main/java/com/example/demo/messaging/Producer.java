package com.example.demo.messaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Order;

@Service
public class Producer {
	
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	
	private static final String TOPIC = "tacos";
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	public void sendOrder(Order order){
		logger.info(String.format("Send order=%s", order));
		this.kafkaTemplate.send(TOPIC, order);
	}
}