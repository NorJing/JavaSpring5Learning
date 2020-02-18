package com.example.demo.domain;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.example.demo.controller.DesignTacoApiController;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource> {
	
	public TacoResourceAssembler() {
		super(DesignTacoApiController.class, TacoResource.class);
	}
	
	@Override
	protected TacoResource instantiateResource(Taco taco) {
		return new TacoResource(taco);
	}
	
	@Override
	public TacoResource toResource(Taco taco) {
		return createResourceWithId(taco.getId(), taco);
	}
	
}