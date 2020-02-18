package com.example.demo.messaging;

import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import com.example.demo.domain.Order;

public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @JmsListener(destination = "helloworld.q")
  public void receive(String message) {
    LOGGER.info("2 received message='{}'", message);
    latch.countDown();
  }
  
  @JmsListener(destination = "tacoqueue1")
  public void receiveOrder(Order order) {
    LOGGER.info("Received message='{}'", order);
  }
  
}