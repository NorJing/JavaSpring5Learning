package com.spring;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.Ingredient.Type;

@SpringBootApplication
public class ReactiveMongodb2Application {

	@Bean
	CommandLineRunner loadIngredients(IngredientRepository ingredientRepository) {
		return args -> {
			ingredientRepository.deleteAll().subscribe(null, null, () -> { // consumer, errorConsumer, completeConsumer
				Stream.of(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
						new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
						new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
						new Ingredient("CARN", "Carnitas", Type.PROTEIN)
						).forEach(ingredient -> {
							System.out.println("IN THE LOAD INGREDIENTS" + ingredient);
							ingredientRepository.save(ingredient).subscribe(ingre -> System.out.println(ingre));
				});
			});
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongodb2Application.class, args);
	}

}
