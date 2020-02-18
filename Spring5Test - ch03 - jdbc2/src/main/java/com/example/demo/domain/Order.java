package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class Order {

	private Long id;
	
	private Date placedAt;
	
	@NotBlank(message="Delivery Name is required")
	private String deliveryName;
	
	@NotBlank(message="Delivery Street is required")
	private String deliveryStreet;
	
	@NotBlank(message="Delivery City is required")
	private String deliveryCity;
	
	@NotBlank(message="Delivery State is required")
	private String deliveryState;
	
	@NotBlank(message="Delivery Zip code is required")
	private String deliveryZip;
	
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}

	private String fastDelivery; // yes or no

}
