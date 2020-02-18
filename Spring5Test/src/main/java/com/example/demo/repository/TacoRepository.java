package com.example.demo.repository;

import java.util.UUID;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.domain.Taco;

public interface TacoRepository extends ReactiveCrudRepository<Taco, UUID>{

	// extends CrudRepository<Taco, Long>
	
	// Taco save(Taco design);
	
	// String findByPriority(String priority);
	
}
