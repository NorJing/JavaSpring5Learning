package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.demo.domain.Ingredient.Type;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class DeliveryBox {
	
	@Id
	private final String id;
	private final String color;
	private final DType size;
	
	public static enum DType {
		small, medium, big, large, huge
	}
	
}
