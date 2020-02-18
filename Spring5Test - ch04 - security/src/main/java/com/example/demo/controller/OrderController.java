package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.domain.DeliveryBox;
import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Order;
import com.example.demo.domain.Taco;
import com.example.demo.domain.User;
import com.example.demo.domain.DeliveryBox.DType;
import com.example.demo.repository.DeliveryBoxRepository;
import com.example.demo.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private OrderRepository orderRepo;
	private DeliveryBoxRepository deliveryBoxRepo;
	
	@Autowired
	public OrderController(OrderRepository orderRepo, DeliveryBoxRepository deliveryBoxRepo) {
		this.orderRepo = orderRepo;
		this.deliveryBoxRepo = deliveryBoxRepo;
	}
	
	@GetMapping("/current")
	public String orderForm(Model model, @AuthenticationPrincipal User user, @ModelAttribute Order order) {
		List<DeliveryBox> deliveryBox = new ArrayList<>();
		deliveryBoxRepo.findAll().forEach(i -> deliveryBox.add(i));
		DType[] types = DeliveryBox.DType.values();
		for (DType type : types) {
			log.info("type=" + type.toString().toLowerCase() + " filterByType=" + filterByType(deliveryBox, type));
			model.addAttribute(type.toString().toLowerCase(), filterByType(deliveryBox, type)); 
		}
		
		if (order.getDeliveryName() == null) {
			order.setDeliveryName(user.getFullname());
		}
		if (order.getDeliveryStreet() == null) {
			order.setDeliveryStreet(user.getStreet());
		}
		if (order.getDeliveryCity() == null) {
			order.setDeliveryCity(user.getCity());
		}
		if (order.getDeliveryState() == null) {
			order.setDeliveryState(user.getState());
		}
		if (order.getDeliveryZip() == null) {
			order.setDeliveryZip(user.getZip());
		}    

		return "orderForm";
	}
	
	private List<DeliveryBox> filterByType(List<DeliveryBox> boxes, DType type) {
		return boxes.stream().filter(x -> x.getSize().equals(type)).collect(Collectors.toList());
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		log.info("controller fastDelivery=" + order.getFastDelivery());
		log.info("controller deliveryBox=" + order.getDeliveryBox());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		order.setUser(user);
		
		orderRepo.save(order);
		sessionStatus.setComplete();
		log.info("Order submitted: " + order);
		
		return "redirect:/";
	}
}
