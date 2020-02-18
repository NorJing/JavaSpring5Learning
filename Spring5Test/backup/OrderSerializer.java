package com.example.demo.messaging;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.example.demo.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderSerializer implements Serializer<Order> {

	@Override
	public void configure(Map configs, boolean isKey) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	// sender object -> kafka byte[]
	@Override
	public byte[] serialize(String topic, Order data) {
		byte[] retVal = null; 
		ObjectMapper objectMapper = new ObjectMapper(); 
		try { 
			retVal = objectMapper.writeValueAsString(data).getBytes(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return retVal; 
	} 
	
}
