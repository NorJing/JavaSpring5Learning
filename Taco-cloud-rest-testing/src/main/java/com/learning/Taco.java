package com.learning;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
// @JsonCreator
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Taco implements Serializable{

	private static final long serialVersionUID = 1L;
	
  private final String name;
  private List<String> ingredients;
  
}
