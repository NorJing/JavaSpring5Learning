package com.example.demo.messaging;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.example.demo.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderDeserializer implements Deserializer<Order> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper(); 
		Order order = null; 
		try { 
			order = mapper.readValue(data, Order.class); 
		} catch (Exception e) {  
			e.printStackTrace(); 
		} 
		return order; 
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
