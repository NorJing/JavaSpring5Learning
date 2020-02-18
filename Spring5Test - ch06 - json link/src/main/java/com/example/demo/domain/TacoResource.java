package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import lombok.Getter;

@Relation(value="taco", collectionRelation="tacos2")
public class TacoResource extends ResourceSupport {
	
	private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();
	
	@Getter
	private final String name;
	
	@Getter
	private final Date createdAt;
	
	@Getter
	private final List<IngredientResource> ingredients;
	// private final List<Ingredient> ingredients;
	
	public TacoResource(Taco taco) {
		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
		// this.ingredients = taco.getIngredients();
		this.ingredients = ingredientAssembler.toResources(taco.getIngredients());
	}
	
}