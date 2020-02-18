package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.domain.Taco;

public interface TacoApiRepository extends PagingAndSortingRepository<Taco, Long> {

}
