package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.domain.Ingredient;

@CrossOrigin(origins="*")
public interface IngredientApiRepository extends PagingAndSortingRepository<Ingredient, String> {

}
