package com.example.demo;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TacoCloudClient {

  //@Autowired
  private RestTemplate rest;
  
  //@Autowired
  private Traverson traverson;

  public TacoCloudClient(RestTemplate rest, Traverson traverson) {
    this.rest = rest;
    this.traverson = traverson;
  }

  /*public TacoCloudClient(RestTemplate rest) {
	  this.rest = rest;
  }*/
  
  //
  // GET examples
  //

  /*
   * Specify parameter as varargs argument
   */
  
  // Ingredient:  Ingredient(id=null, name=Cheddar, type=CHEESE)
  public Ingredient getIngredientById(String ingredientId) {
	  // Map<String, String> urlVariables = new HashMap<>();
	  log.info("Get ingredient id=" + ingredientId);
	  // System.out.println("Get ingredient id=" + ingredientId);
	  // urlVariables.put("id", ingredientId);
	  // return rest.getForObject("http://localhost:8080/api/ingredients/{id}", Ingredient.class, urlVariables);
	  return rest.getForObject("http://localhost:8080/api/ingredients/{id}", Ingredient.class, ingredientId);
  }

  /*public Ingredient getIngredientById(String ingredientId) {
	    return rest.getForObject("http://localhost:8080/ingredients/{id}",
	                             Ingredient.class, ingredientId);
	  }*/
  
  /*
   * Alternate implementations...
   * The next three methods are alternative implementations of
   * getIngredientById() as shown in chapter 6. If you'd like to try
   * any of them out, comment out the previous method and uncomment
   * the variant you want to use.
   */

  /*
   * Specify parameters with a map
   */
  // public Ingredient getIngredientById(String ingredientId) {
  //   Map<String, String> urlVariables = new HashMap<>();
  //   urlVariables.put("id", ingredientId);
  //   return rest.getForObject("http://localhost:8080/ingredients/{id}",
  //       Ingredient.class, urlVariables);
  // }

  /*
   * Request with URI instead of String
   */
  // public Ingredient getIngredientById(String ingredientId) {
  //   Map<String, String> urlVariables = new HashMap<>();
  //   urlVariables.put("id", ingredientId);
  //   URI url = UriComponentsBuilder
  //             .fromHttpUrl("http://localhost:8080/ingredients/{id}")
  //             .build(urlVariables);
  //   return rest.getForObject(url, Ingredient.class);
  // }

  /*
   * Use getForEntity() instead of getForObject()
   */
  // public Ingredient getIngredientById(String ingredientId) {
  //   ResponseEntity<Ingredient> responseEntity =
  //       rest.getForEntity("http://localhost:8080/ingredients/{id}",
  //           Ingredient.class, ingredientId);
  //   log.info("Fetched time: " +
  //           responseEntity.getHeaders().getDate());
  //   return responseEntity.getBody();
  // }

  public List<Ingredient> getAllIngredients() { // it does not work
	  // ObjectMapper mapper = new ObjectMapper();
	  // mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	  // Collection<Ingredient> readValues = new ObjectMapper().readValue(jsonAsString, new TypeReference<Collection<Ingredient>>() { } );
	  
	  ObjectMapper objectMapper = new ObjectMapper();
	  objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
	  
	  return rest.exchange("http://localhost:8080/api/ingredients", HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {}).getBody();
  }

  //
  // PUT examples
  //

  public void updateIngredient(Ingredient ingredient) {
	  Map<String, String> urlVariables = new HashMap<>();
	  urlVariables.put("id", ingredient.getId());
	  // rest.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
	  rest.put("http://localhost:8080/api/ingredients/{id}", ingredient, urlVariables);
 }

  //
  // POST examples
  //
  public Ingredient createIngredient(Ingredient ingredient) {
    return rest.postForObject("http://localhost:8080/api/ingredients", ingredient, Ingredient.class);
  }

  /*
   * Alternate implementations...
   * The next two methods are alternative implementations of
   * createIngredient() as shown in chapter 6. If you'd like to try
   * any of them out, comment out the previous method and uncomment
   * the variant you want to use.
   */

  public URI createIngredient2(Ingredient ingredient) {
	  return rest.postForLocation("http://localhost:8080/api/ingredients", ingredient, Ingredient.class);
  }

  // public Ingredient createIngredient(Ingredient ingredient) {
  //   ResponseEntity<Ingredient> responseEntity =
  //          rest.postForEntity("http://localhost:8080/ingredients",
  //                             ingredient,
  //                             Ingredient.class);
  //   log.info("New resource created at " +
  //            responseEntity.getHeaders().getLocation());
  //   return responseEntity.getBody();
  // }

  //
  // DELETE examples
  //

  public void deleteIngredient(Ingredient ingredient) {
	  Map<String, String> urlVariables = new HashMap<>();
	  log.info("delete ingredient id=" + ingredient.getId());
	  urlVariables.put("id", ingredient.getId());
	  rest.delete("http://localhost:8080/api/ingredients/{id}", urlVariables);
  }

  public void deleteIngredient2(Ingredient ingredient) {
	  rest.delete("http://localhost:8080/api/ingredients/{id}", ingredient.getId());
  }
  
  //
  // Traverson with RestTemplate examples
  //

  public Iterable<Ingredient> getAllIngredientsWithTraverson() {
    ParameterizedTypeReference<Resources<Ingredient>> ingredientType = new ParameterizedTypeReference<Resources<Ingredient>>() {};

    Resources<Ingredient> ingredientRes = traverson.follow("ingredients").toObject(ingredientType);
    
    Collection<Ingredient> ingredients = ingredientRes.getContent();
          
    return ingredients;
  }

  public Ingredient addIngredient(Ingredient ingredient) {
	  // Traverson can read href. RestTemplate can write and delete.
	  String ingredientsUrl = traverson.follow("ingredients").asLink().getHref();
	  return rest.postForObject(ingredientsUrl, ingredient, Ingredient.class);
  }

  public Iterable<Taco> getRecentTacosWithTraverson() {
    ParameterizedTypeReference<Resources<Taco>> tacoType = new ParameterizedTypeReference<Resources<Taco>>() {};

    // object + link = resource
    Resources<Taco> tacoResource = traverson.follow("tacos").follow("recents").toObject(tacoType);

      // Alternatively, list the two paths in the same call to follow()
//    Resources<Taco> tacoRes =
//        traverson
//          .follow("tacos", "recents")
//          .toObject(tacoType);

    return tacoResource.getContent();
  }

}
