package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

	// Order save(Order order);
	
}
