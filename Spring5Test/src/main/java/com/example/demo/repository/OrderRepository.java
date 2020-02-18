package com.example.demo.repository;

//import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;

import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCassandraRepository<Order, UUID> {

	//  extends CrudRepository<Order, Long> 
	
	// Order save(Order order);
	
	// List<Order> findByDeliveryZip(String deliveryZip);
	
	//List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
	@AllowFiltering
	Flux<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
}
