package com.learning;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSubmitMessageHandler implements GenericHandler<Order> {

  private RestTemplate rest;
  private ApiProperties apiProps;
  //private ProcessOrderFromEmail processOrderFromEmail;
  
  @Autowired
  public OrderSubmitMessageHandler(ApiProperties apiProps, RestTemplate rest){ // ProcessOrderFromEmail processOrderFromEmail) {
    this.apiProps = apiProps;
    this.rest = rest;
    //this.processOrderFromEmail = processOrderFromEmail;
  }

  /*@Override
  public Object handle(Order order, Map<String, Object> headers) {
    rest.postForObject(apiProps.getUrl(), order, String.class);
    return null;
  }*/

  @Override
	public Object handle(Order order, MessageHeaders headers) {
		
		System.out.println("In the OrderSubmitMessageHandler to process Order");
		
		String returnResult = rest.postForObject(apiProps.getUrl(), order, String.class);
		// processOrderFromEmail.processOrder(order);
		System.out.println("After process, returnResult2=" + returnResult); // null
		return null;
	}

}
