package com.example.demo.messaging;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;*/

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Order;

@Component
public class Receiver {

  /*private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private RabbitTemplate rabbit;
  private MessageConverter converter;
  
  @Autowired
  public Receiver(RabbitTemplate rabbit) {
	  this.rabbit = rabbit;
	  this.converter = rabbit.getMessageConverter();
  }
  
  public Order receiveOrder() {
	  Message message = rabbit.receive("tacocloud.orders");
	  // Order order = (Boolean) null ?  (Order) converter.fromMessage(message) : null;
	  LOGGER.info("receive order=" + message);
	  return message != null ? (Order) converter.fromMessage(message) : null;
  }*/
  
	@RabbitListener(queues = "${dy.rabbitmq.queue}")
	public void recievedMessage(Order order) {
		System.out.println("Recieved order From RabbitMQ=" + order);
	}
  
}