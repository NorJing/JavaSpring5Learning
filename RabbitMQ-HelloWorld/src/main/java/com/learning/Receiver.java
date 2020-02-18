package com.learning;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
// import org.springframework.amqp.rabbit.annotation.RabbitListener;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Component
@Service
public class Receiver implements MessageListener {

	/*@RabbitListener(queues = "${dy.rabbitmq.queue}")
	public void recievedMessage(String msg) {
		System.out.println("Recieved Message From RabbitMQ=" + msg);
	}*/
	
	public void onMessage(Message message) {
		System.out.println("Consuming Message from RabbitMQ=" + new String(message.getBody()));
	}
	
}
