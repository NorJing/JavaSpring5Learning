package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.domain.Ingredient;

// @CrossOrigin(origins="*")
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
	
	/*Iterable<Ingredient> findAll();
	
	Ingredient findById(String id);
	
	Ingredient save(Ingredient ingredient);*/

}
