package com.example.demo.domain;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.example.demo.controller.IngredientApiController;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource>{

	public IngredientResourceAssembler() {
		super(IngredientApiController.class, IngredientResource.class);
	}
	
	@Override
	public IngredientResource toResource(Ingredient ingredient) {
		return createResourceWithId(ingredient.getId(), ingredient);
	}
		
	@Override
	protected IngredientResource instantiateResource(Ingredient ingredient) {
		return new IngredientResource(ingredient);
	}
	
}
