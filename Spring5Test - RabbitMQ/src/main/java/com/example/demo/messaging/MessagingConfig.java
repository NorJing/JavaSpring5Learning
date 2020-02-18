package com.example.demo.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Order;

@Configuration
public class MessagingConfig {

  /*@Bean
  public MappingJackson2MessageConverter messageConverter() {
	  
    MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    // messageConverter.setTargetType(MessageType.TEXT);
    messageConverter.setTypeIdPropertyName("_typeId");
    
    Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
    typeIdMappings.put("order", Order.class);
    messageConverter.setTypeIdMappings(typeIdMappings);
    
    return messageConverter;
  }*/
  
	// this is for amqp sending message
	// using RabbitTemplate
  //@Bean
  //public MessageConverter messageConverter() {
	//return new Jackson2JsonMessageConverter();
  //}
  
  /*@Bean
  public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory, 
  		DefaultJmsListenerContainerFactoryConfigurer configurer) {
  	
      DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
      // This provides all boot's default to this factory, including the message converter
      configurer.configure(factory, connectionFactory);
      // You could still override some of Boot's default if necessary.
      return factory;
  }*/
	

	@Value("${dy.rabbitmq.queue}")
	String queueName;

	@Value("${dy.rabbitmq.exchange}")
	String exchange;

	@Value("${dy.rabbitmq.routingkey}")
	private String routingkey;

	
	@Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}
	
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
