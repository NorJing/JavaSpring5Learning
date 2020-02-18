package com.example.demo.domain;

// import java.io.Serializable;

//import javax.persistence.Entity;
//import javax.persistence.Id;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
// @Entity
@Table("ingredients")
public class Ingredient { // implements Serializable
	
	// private static final long serialVersionUID = 1L;
	
	//@Id
	@PrimaryKey
	private final String id;	
	
	private final String name;
	
	private final Type type;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

}
