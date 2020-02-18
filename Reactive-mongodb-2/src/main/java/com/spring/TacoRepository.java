package com.spring;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface TacoRepository extends ReactiveCrudRepository<Taco, String> {

}

/*public interface TacoRepository extends ReactiveMongoRepository<Taco, String> {
	Flux<Taco> findByOrderByCreatedAtDesc();
}*/