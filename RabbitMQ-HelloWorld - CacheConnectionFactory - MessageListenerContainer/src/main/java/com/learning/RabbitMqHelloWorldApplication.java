package com.learning;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqHelloWorldApplication {

	// private static final Logger log = LoggerFactory.getLogger(RabbitMqHelloWorldApplication.class);
	
	/*@Bean
    public CommandLineRunner usage() {
        return args -> {
            System.out.println("This app uses Spring Profiles to control its behavior.\n");
            System.out.println("Sample usage: java -jar rabbit-tutorials.jar --spring.profiles.active=hello-world,sender");
        };
    }*/
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitMqHelloWorldApplication.class, args);
	}
	
	/*@Bean
	CommandLineRunner start(RabbitTemplate template) {
		return args -> {
			log.info("Sending> ...");
			Sender sender = new Sender();
			sender.send();
			// template.convertAndSend("taco", "Hello World from Spring Boot via Embedded Armetis!");
		};
	}*/

}
