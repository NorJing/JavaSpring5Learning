package com.example.demo.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
// import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.domain.Ingredient;

import reactor.core.publisher.Mono;

// import reactor.core.publisher.Mono;

@CrossOrigin(origins="*")
public interface IngredientRepository extends ReactiveCassandraRepository<Ingredient, String> {
	
	/*Iterable<Ingredient> findAll();
	
	Ingredient findById(String id);*/
	
	// Mono<Ingredient> save(Ingredient ingredient);
	
	@AllowFiltering
	Mono<Ingredient> findById(String id);

}
