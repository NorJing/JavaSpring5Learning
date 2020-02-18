package com.spring;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	public IngredientController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	
	@GetMapping("/all") // /ingredient/all
	public Flux<Ingredient> getAllIngredients(){
		return ingredientRepository.findAll();
	}
	
	@GetMapping("/{id}") // /ingredient/COTO
	public Mono<Ingredient> getById(@PathVariable String id){
		return ingredientRepository.findById(id);
	}
	
	// map -> function
	@GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<IngredientEvent> generateIngredientEvent(@PathVariable String id){
		return ingredientRepository.findById(id)
				.flatMapMany(ingre -> {
			
			Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
			
			Flux<IngredientEvent> ingredientEvent = Flux.fromStream(
					Stream.generate(() -> new IngredientEvent(ingre, new Date())
							));
			
			//return Flux.zip(interval, ingredientEvent).map(object -> { return object.getT2();
				//	});
			return Flux.zip(interval, ingredientEvent).map(Tuple2::getT2);
		});
	}
	
	@GetMapping("/param") // /ingredient/param?id=COTO&name=now
	@ResponseBody
	public Mono<Ingredient> getById2(@RequestParam("id") String id, @RequestParam("name") String name){
		System.out.println("IN THE GETBYID2 NAME=" + name);
		return ingredientRepository.findById(id);
	}
	
}
