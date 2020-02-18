package com.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;

@Configuration
public class TacoOrderEmailIntegrationConfig {
  
  @Bean
  public IntegrationFlow tacoOrderEmailFlow(
		  EmailProperties emailProps,
		  EmailToOrderTransformer emailToOrderTransformer, // The order has taco
		  OrderSubmitMessageHandler orderSubmitHandler) 
  {
	  System.out.println("In the TacoOrderEmailIntegrationConfig 1");
    
    return IntegrationFlows
        .from(Mail.imapInboundAdapter(emailProps.getImapUrl()), // inbound adapter
            e -> e.poller(
                Pollers.fixedDelay(emailProps.getPollRate())))
        .transform(emailToOrderTransformer) // transformer
        .handle(orderSubmitHandler) // outbound adapter
        .get();
  }
  
}