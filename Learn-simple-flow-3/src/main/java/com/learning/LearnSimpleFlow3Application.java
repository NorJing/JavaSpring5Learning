package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class LearnSimpleFlow3Application {

	public static void main(String[] args) {
		SpringApplication.run(LearnSimpleFlow3Application.class, args);
	}

	@Bean
	public CommandLineRunner writeData(FileWriterGateway gateway, Environment env) {
		System.out.println("In the CommandLineRunner writeData 1");
		
	  return args -> {
		  System.out.println("In the CommandLineRunner writeData 2");
	    
		  String[] activeProfiles = env.getActiveProfiles();
		  
	    if (activeProfiles.length > 0) {
	      String profile = activeProfiles[0];
	      System.out.println("In the CommandLineRunner writeData 3");
	      System.out.println("profile 4=" + profile);
	      gateway.writeToFile("simple2.txt", "Hello 5, Spring Integration! (" + profile + ")");
	    } else {
	      System.out.println("No active profile set. Should set active profile to one of xmlconfig, javaconfig, or javadsl.");
	    }
	  };
	}
	

}
