package com.learning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// import javax.persistence.Entity;

import lombok.Data;

@Data
// @Entity
public class Order implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	  private final String email;
	  private List<Taco> tacos = new ArrayList<>();
	
	  public void addTaco(Taco taco) {
	    this.tacos.add(taco);
	  }
  
}
