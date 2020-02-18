package com.learning;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;

// @Service
@Component
public class Sender {

	/*@Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(String message) {
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }*/
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${dy.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${dy.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(String message) {
		rabbitTemplate.convertAndSend(exchange, routingkey, message);
		System.out.println("Send msg=" + message); 
	}
    
}
