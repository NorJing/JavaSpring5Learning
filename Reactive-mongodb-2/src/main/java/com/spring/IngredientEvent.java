package com.spring;

import java.util.Date;

public class IngredientEvent {

	private Ingredient ingredient;
	private Date date;
	
	
	public IngredientEvent(Ingredient ingredient, Date date) {
		super();
		this.ingredient = ingredient;
		this.date = date;
	}
	
	public Ingredient getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "IngredientEvent [ingredient=" + this.getIngredient() + ", date=" + this.getDate() + "]";
	}
	
	
}
