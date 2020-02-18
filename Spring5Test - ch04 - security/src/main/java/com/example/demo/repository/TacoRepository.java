package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

	// Taco save(Taco design);
	
	// String findByPriority(String priority);
	
}
