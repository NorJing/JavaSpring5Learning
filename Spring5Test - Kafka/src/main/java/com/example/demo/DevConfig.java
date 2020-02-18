package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
// import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.DeliveryBox;
import com.example.demo.domain.Ingredient;
import com.example.demo.domain.User;
import com.example.demo.domain.DeliveryBox.DType;
import com.example.demo.domain.Ingredient.Type;
import com.example.demo.repository.DeliveryBoxRepository;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.UserRepository;

@Profile(value = { "dev", "qa", "prod" })
@Configuration
public class DevConfig {

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo, DeliveryBoxRepository deliveryBoxRepo, UserRepository userRepo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				System.out.println("Ingredient repo insert");
				System.out.println("Type.WRAP=" +  Type.WRAP.ordinal());
				
				repo.save(new Ingredient("FLOR", "White Flour", Type.WRAP));
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
		        
		        System.out.println("DeliveryBox repo insert");
		        deliveryBoxRepo.save(new DeliveryBox("Red small box", "Red small box", DType.small));
		        deliveryBoxRepo.save(new DeliveryBox("Green small box", "Green small box", DType.small));
		        deliveryBoxRepo.save(new DeliveryBox("Red medium box", "Red medium box", DType.medium));
		        deliveryBoxRepo.save(new DeliveryBox("Green medium box", "Green medium box", DType.medium));
		        deliveryBoxRepo.save(new DeliveryBox("Red big box", "Red big box", DType.big));
		        deliveryBoxRepo.save(new DeliveryBox("Green big box", "Green big box", DType.big));
		        
		        userRepo.save(new User("percy", "percy", 
		               "Percy", "123 North Street", "Cross Roads", "CA", 
		                "76227", "123-123-1234"));
			}
		};
	 }
	
}
