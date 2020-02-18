package com.example.demo.repository;

import com.example.demo.domain.Taco;

public interface TacoRepository {

	Taco save(Taco design);
	
	// String findByPriority(String priority);
	
}
