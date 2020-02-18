package com.learning;

import org.springframework.beans.DirectFieldAccessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.common.LiteralExpression;

//import org.springframework.integration.support.MessageBuilder;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.PollableChannel;

//import org.springframework.beans.DirectFieldAccessor;
//import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;

@SpringBootApplication
public class LearnSimpleFlowApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(LearnSimpleFlowApplication.class, args);
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("fileCopyDemo-text.xml");
		File inDir = (File) new DirectFieldAccessor(context.getBean(FileReadingMessageSource.class)).getPropertyValue("directory");
		LiteralExpression expression = (LiteralExpression) new DirectFieldAccessor(context.getBean(FileWritingMessageHandler.class)).getPropertyValue("destinationDirectoryExpression");
		File outDir = new File(expression.getValue());
		System.out.println("Input directory is: " + inDir.getAbsolutePath());
		System.out.println("Output directory is: " + outDir.getAbsolutePath());
		System.out.println("===================================================");
		
		context.close();
		
	    /*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("transformer-context.xml");
        MessageChannel input = context.getBean("input", MessageChannel.class);
        PollableChannel output = context.getBean("output", PollableChannel.class);
 
        // send ticket map to an input channel, transformer create ticket, output channel print the ticket
        Map ticketMap = new HashMap();
        ticketMap.put("ticketId", "1001");
        ticketMap.put("description", "First ticket");
        System.out.println("Sent: " + ticketMap);
         
        input.send(MessageBuilder.withPayload(ticketMap).build());
        System.out.println("received " + output.receive().getPayload());
         
        context.close();*/
        
		/*AbstractApplicationContext context 
	      = new AnnotationConfigApplicationContext(BasicIntegrationConfig.class);
	    context.registerShutdownHook();
	     
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Please enter q and press <enter> to exit the program: ");
	     
	    while (true) {
	       String input = scanner.nextLine();
	       if("q".equals(input.trim())) {
	          break;
	      }
	    }
	    System.exit(0);*/
	}

	/*@Bean
	public CommandLineRunner writeData(FileWriterGateway gateway, Environment env) {
	  return args -> {
	    String[] activeProfiles = env.getActiveProfiles();
	    System.out.println("1 activeProfiles=");
	    gateway.writeToFile("file1.txt", "dy");
	    
	    if (activeProfiles.length > 0) {
	      String profile = activeProfiles[0];
	      gateway.writeToFile("simple.txt", "Hello, Spring Integration! (" + profile + ")");
	    } else {
	      System.out.println("No active profile set. Should set active profile to one of xmlconfig, javaconfig, or javadsl.");
	    }
	  };
	}*/

}
