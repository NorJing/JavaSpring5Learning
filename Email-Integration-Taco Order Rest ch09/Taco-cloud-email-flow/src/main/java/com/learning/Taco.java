package com.learning;


import java.io.Serializable;
import java.util.List;

// import javax.persistence.Entity;

import lombok.Data;

@Data
// @Entity
public class Taco implements Serializable {

	private static final long serialVersionUID = 1L;
	
  private final String name;
  private List<String> ingredients;
  
}
