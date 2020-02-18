package com.learning;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.GenericTransformer;

import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
public class FileWriterIntegrationConfig {

  /*@Profile("xmlconfig")
  @Configuration
  @ImportResource("classpath:/filewriter-config.xml")
  public static class XmlConfiguration {}*/
  
  /*@Profile("javaconfig")
  @Bean
  @Transformer(inputChannel="textInChannel1", outputChannel="fileWriterChannel1")
  public GenericTransformer<String, String> upperCaseTransformer() {
	  System.out.println("In the GenericTransformer 1 config");
	  return text -> text.toUpperCase();
  }

  @Profile("javaconfig")
  @Bean
  @ServiceActivator(inputChannel="fileWriterChannel1")
  public FileWritingMessageHandler fileWriter() {
	  System.out.println("In the FileWritingMessageHandler 1 config");
	  
	  FileWritingMessageHandler handler = new FileWritingMessageHandler(
			  new File("C:\\Users\\doyan\\Documents\\java learning\\workspace-sts-3.9.9.RELEASE\\Learn-simple-flow-3\\output"));
	  
	  handler.setExpectReply(false);
	  handler.setFileExistsMode(FileExistsMode.APPEND);
	  handler.setAppendNewLine(true);
	  return handler;
  }*/
  
  //
  // DSL Configuration
  //
  @Profile("javadsl")
  @Bean
  public IntegrationFlow fileWriterFlow() {
	  String des = "C:\\\\Users\\\\doyan\\\\Documents\\\\java learning\\\\workspace-sts-3.9.9.RELEASE\\\\Learn-simple-flow-3\\\\output";
	  return IntegrationFlows
		        .from(MessageChannels.direct("textInChannel1"))
		        .<String, String>transform(t -> t.toUpperCase())
		        .handle(Files.outboundAdapter(new File(des))
		            .fileExistsMode(FileExistsMode.APPEND)
		            .appendNewLine(true))
		        .get(); 
  }

}
