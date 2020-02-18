package com.example.demo.controller;

import java.time.Duration;
//import java.security.Principal;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Order;
import com.example.demo.domain.Taco;
import com.example.demo.domain.TacoUDT;
//import com.example.demo.domain.User;
import com.example.demo.domain.Ingredient.Type;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.TacoRepository;
import com.example.demo.repository.UserRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;
	private TacoRepository designTacoRepo;
	private UserRepository userRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designTacoRepo, UserRepository userRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designTacoRepo = designTacoRepo;
		this.userRepo = userRepo;
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		Order order = new Order();
		return order;
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
    public String showDesignForm(Model model) { // Principal principal
		//List<Ingredient> ingredients = new ArrayList<>();
		
		Flux<Ingredient> fluxIngredients = ingredientRepo.findAll();
		
		//ingredients = fluxIngredients.collectList().block();
		//ingredientRepo.findAll().doOnEach(i -> ingredients.add((Ingredient) i));
		
		// ingredients.forEach(System.out::println); // no output ! 
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			log.info("type=" + type.toString().toLowerCase() + " filterByType=" + filterByType1(fluxIngredients, type));
			model.addAttribute(type.toString().toLowerCase(), filterByType1(fluxIngredients, type)); // wrap, FLTO
		}
		
		//String username = principal.getName();
		//log.info("showDesignForm username=" +  username);
		//User normalUser = new User(username, "password", "Percy", "123 North Street", "Oslo", "CA", "76227", "123-123-1234", "my@g.com");
		//Mono<User> user = userRepo.findByUsername(username);
		//model.addAttribute("user", user);
		
	    return "design";
	}
	
	// convert flux to list?
	private List<Ingredient> filterByType1(Flux<Ingredient> ingredients, Type type) {
		List<Ingredient> list = ingredients.collectList(); // ?
		return list;
		// Flux.fromIterable(movie).delayElements(Duration.ofSeconds(2));
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
	
	@PostMapping
	public String processDesign(@Valid Taco designTaco, Errors errors, @ModelAttribute Order order) {
		log.info("Going to process Taco in the processDesign...");
		log.info("errors=" + errors.toString());
		
		if (errors.hasErrors()) {
			return "design";
		}
		
		Mono<@Valid Taco> savedTaco = designTacoRepo.save(designTaco);
		TacoUDT tacoUDT = new TacoUDT(designTaco.getName(), designTaco.getIngredients());
		order.addDesign(tacoUDT);
		log.info("Processing designTaco=" + designTaco);
		log.info("Processing tacoUDT=" + tacoUDT);
		return "redirect:/orders/current";
	}

}
