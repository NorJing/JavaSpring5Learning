package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.domain.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {
	
	// extends CrudRepository<User, Long> 
	@AllowFiltering
	Mono<User> findByUsername(String username);
	
	@AllowFiltering
	Mono<User> findByEmail(String email);

	Mono<User> save(User user);
}