package com.spring;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//@Data
//@RequiredArgsConstructor
//@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
//@AllArgsConstructor // lombok does not work here

@Document(collection="ingredients")
public class Ingredient {
  
  @Id
  private String id; // if it is null, Mongodb assign a value automatically. no final
  private String name; // no final
  private Type type; // no final
  
  
  public Ingredient(String id, String name, Type type) {
	super();
	this.id = id;
	this.name = name;
	this.type = type;
  }

  public String getId() {
	return id;
  }

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}		

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + this.getId() + ", name=" + this.getName() + ", type=" + this.getType() + "]";
	}
	
	

}
