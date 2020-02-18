package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.domain.PaymentMethod;

import reactor.core.publisher.Mono;

public interface PaymentMethodRepository extends ReactiveCrudRepository<PaymentMethod, UUID> {

  Mono<PaymentMethod> findByUserUsername(String username);

}
