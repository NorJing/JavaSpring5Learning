package com.example.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.Taco;
import com.example.demo.domain.TacoResource;
import com.example.demo.domain.TacoResourceAssembler;
import com.example.demo.repository.TacoApiRepository;
import com.example.demo.repository.TacoRepository;

@RepositoryRestController
public class RecentTacosController {

  private TacoApiRepository tacoRepo;

  public RecentTacosController(TacoApiRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }

  // http://localhost:8080/api/tacos/recent
  @GetMapping(path="/tacos/recent", produces="application/hal+json")
  public ResponseEntity<Resources<TacoResource>> recentTacos() {
	  System.out.println("in the tacos recent.");
	  PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
	  List<Taco> tacos = tacoRepo.findAll(page).getContent();

	  List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
	  Resources<TacoResource> recentResources = new Resources<TacoResource>(tacoResources);
    
	  recentResources.add(linkTo(methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
	  return new ResponseEntity<>(recentResources, HttpStatus.OK);
  }

  @Bean
  public ResourceProcessor<PagedResources<Resource<Taco>>> tacoProcessor(EntityLinks links) {
	  return new ResourceProcessor<PagedResources<Resource<Taco>>>() {
		  @Override
		  public PagedResources<Resource<Taco>> process(PagedResources<Resource<Taco>> resource) {
			  resource.add(links.linkFor(Taco.class).slash("recent").withRel("recents"));
			  return resource;
		  }
	  };
  }
	
}