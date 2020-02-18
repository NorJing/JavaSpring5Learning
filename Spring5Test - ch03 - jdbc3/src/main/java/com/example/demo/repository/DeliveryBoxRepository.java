package com.example.demo.repository;

import com.example.demo.domain.DeliveryBox;

public interface DeliveryBoxRepository {

	Iterable<DeliveryBox> findAll();
	
	DeliveryBox findById(String id);
	
}
