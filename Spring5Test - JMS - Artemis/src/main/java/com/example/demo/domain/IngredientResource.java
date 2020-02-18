package com.example.demo.domain;

import lombok.Getter;

import org.springframework.hateoas.ResourceSupport;

import com.example.demo.domain.Ingredient.Type;

public class IngredientResource extends ResourceSupport {

	@Getter
	private String name;
	
	@Getter
	private Type type;
	
	public IngredientResource(Ingredient ingredient) {
		this.name = ingredient.getName();
		this.type = ingredient.getType();
	}
	
}
