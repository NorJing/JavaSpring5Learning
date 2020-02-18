package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

	// Order save(Order order);
	
	List<Order> findByDeliveryZip(String deliveryZip);
	
	// List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
}
