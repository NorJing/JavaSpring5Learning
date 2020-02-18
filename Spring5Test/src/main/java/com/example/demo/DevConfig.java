package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
// import org.springframework.security.crypto.password.PasswordEncoder;

/*import com.example.demo.domain.DeliveryBox;
import com.example.demo.domain.DeliveryBox.DType;*/

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.User;

import com.example.demo.domain.Ingredient.Type;
//import com.example.demo.repository.DeliveryBoxRepository;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.UserRepository;

//import reactor.core.publisher.Mono;

@Profile(value = { "dev" })
@Configuration
public class DevConfig {

	@Bean 
	public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepo) { // DeliveryBoxRepository deliveryBoxRepo
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				System.out.println("Ingredient pre data insert");
				
				List<Ingredient> ingredient = new ArrayList<>();
				ingredient.add(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
				ingredient.add(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
				ingredient.add(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
				ingredient.add(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
				ingredient.add(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
				ingredient.add(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
				ingredient.add(new Ingredient("CHED", "Cheddar", Type.CHEESE));
				ingredient.add(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
				ingredient.add(new Ingredient("SLSA", "Salsa", Type.SAUCE));
				ingredient.add(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));		        
		        repo.saveAll(ingredient).subscribe();
		        
		        //System.out.println("DeliveryBox repo insert");
		        /*deliveryBoxRepo.save(new DeliveryBox("Red small box", "Red small box", DType.small));
		        deliveryBoxRepo.save(new DeliveryBox("Green small box", "Green small box", DType.small));
		        deliveryBoxRepo.save(new DeliveryBox("Red medium box", "Red medium box", DType.medium));
		        deliveryBoxRepo.save(new DeliveryBox("Green medium box", "Green medium box", DType.medium));
		        deliveryBoxRepo.save(new DeliveryBox("Red big box", "Red big box", DType.big));
		        deliveryBoxRepo.save(new DeliveryBox("Green big box", "Green big box", DType.big));*/
		        
		        userRepo.save(new User("percy", "password", "Percy", "123 North Street", "Oslo", "CA", "76227", "123-123-1234", "my@g.com")).subscribe();
			}
		};
	 }
	
}
