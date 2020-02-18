package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.DeliveryBox;

public interface DeliveryBoxRepository extends CrudRepository<DeliveryBox, String> {

	/*Iterable<DeliveryBox> findAll();
	
	DeliveryBox findById(String id);*/
	
}
