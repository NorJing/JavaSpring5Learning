package com.example.demo.messaging;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
*/
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;

import com.example.demo.domain.Order;

// @Service
@Component
public class Sender {

	// private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	/*@Autowired
	private RabbitTemplate template;

	//@Value("${jms.queue.destination}")
    //String destinationQueue;
	
	String routingKey = "tacocloud.order";
	
	public void sendOrder(Order order) {
		LOGGER.info("Sending order='{}'", order);
		
		MessageConverter converter = template.getMessageConverter();
		MessageProperties props = new MessageProperties();
		Message message = converter.toMessage(order, props);
		template.send(routingKey, message);
	}*/
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${dy.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${dy.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void sendOrder(Order order) {
		rabbitTemplate.convertAndSend(exchange, routingkey, order);
		System.out.println("Send order=" + order); 
	}
	
}
