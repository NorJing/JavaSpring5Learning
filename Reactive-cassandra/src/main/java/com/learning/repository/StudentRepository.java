package com.learning.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import com.learning.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveCassandraRepository<Student, Integer> {

	@AllowFiltering
	Flux<Student> findByAgeGreaterThan(int age);
	
	@AllowFiltering
	Mono<Student> findById(int id);

	// Mono<Student> save(Mono<Student> monoStudent);
	
	@AllowFiltering
	Flux<Student> findByName(String name);
}
