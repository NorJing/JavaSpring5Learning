package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
	
	/*Iterable<Ingredient> findAll();
	
	Ingredient findById(String id);
	
	Ingredient save(Ingredient ingredient);*/

}
