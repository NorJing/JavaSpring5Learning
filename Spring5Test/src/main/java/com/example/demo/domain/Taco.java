package com.example.demo.domain;

//import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//import javax.persistence.Entity;
/*import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;*/
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
//import org.springframework.data.rest.core.annotation.RestResource;

import com.datastax.driver.core.utils.UUIDs;

// import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;

@Data
//@Entity
//@RestResource(rel="tacos", path="tacos")
@Table("tacos")
public class Taco {	 // implements Serializable
	
	//private static final long serialVersionUID = 1L;
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//private Long id; // auto

	@PrimaryKeyColumn(type=PrimaryKeyType.PARTITIONED)
	private UUID id = UUIDs.timeBased();
	
	@NotNull
	@Size(min=3, message="Name must be at least 3 characters long")
	private String name;
	
	@PrimaryKeyColumn(type=PrimaryKeyType.CLUSTERED,ordering=Ordering.DESCENDING)
	private Date createdAt = new Date();
	
	//@ManyToMany(targetEntity=Ingredient.class)
	@Size(min=1, message="You must choose at least 1 ingredient")
	@Column("ingredients")
	private List<IngredientUDT> ingredients;
	
	/*@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}*/
	
}
