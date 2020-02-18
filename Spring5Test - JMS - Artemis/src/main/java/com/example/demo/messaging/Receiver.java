package com.example.demo.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Order;

@Component
public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  @JmsListener(destination = "${jms.queue.destination}")
  public void receiveOrder(Order order) {
    LOGGER.info("Received message from Artemis='{}'", order);
  }
  
}