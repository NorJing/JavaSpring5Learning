package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Ingredient;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.domain.Ingredient.Type;
import com.example.demo.domain.DeliveryBox;
import com.example.demo.domain.DeliveryBox.DType;
import com.example.demo.repository.DeliveryBoxRepository;

@SpringBootApplication
public class Spring5TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5TestApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo, DeliveryBoxRepository deliveryBoxRepo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
		    	repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
		        repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
		        repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
		        repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
		        repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
		        repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
		        repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
		        repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
		        repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
		        repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		        
		        deliveryBoxRepo.save(new DeliveryBox("Red small box", "Red small box", DType.small));
		        deliveryBoxRepo.save(new DeliveryBox("Green small box", "Green small box", DType.small));
		        deliveryBoxRepo.save(new DeliveryBox("Red medium box", "Red medium box", DType.medium));
		        deliveryBoxRepo.save(new DeliveryBox("Green medium box", "Green medium box", DType.medium));
		        deliveryBoxRepo.save(new DeliveryBox("Red big box", "Red big box", DType.big));
		        deliveryBoxRepo.save(new DeliveryBox("Green big box", "Green big box", DType.big));
			}
		};
	 }

}
