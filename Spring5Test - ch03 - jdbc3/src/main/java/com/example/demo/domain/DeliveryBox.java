package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class DeliveryBox {
	
	private final String id;
	private final String color;
	private final Type size;
	
	public static enum Type {
		small, medium, big, large, huge
	}
	
}
