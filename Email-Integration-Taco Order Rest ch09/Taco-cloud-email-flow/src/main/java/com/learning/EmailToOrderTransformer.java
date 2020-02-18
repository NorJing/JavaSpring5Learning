package com.learning;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * <p>Handles email content as taco orders where...</p>
 *  <li> The order's email is the sender's email</li>
 *  <li> The email subject line *must* be "TACO ORDER" or else it will be ignored</li>
 *  <li> Each line of the email starts with the name of a taco design, followed by a colon,
 *    followed by one or more ingredient names in a comma-separated list.</li>
 *    
 * <p>The ingredient names are matched against a known set of ingredients using a LevenshteinDistance
 * algorithm. As an example "beef" will match "GROUND BEEF" and be mapped to "GRBF"; "corn" will
 * match "Corn Tortilla" and be mapped to "COTO".</p>
 * 
 * <p>An example email body might look like this:</p>
 * 
 * <code>
 * Corn Carnitas: corn, carnitas, lettuce, tomatoes, cheddar<br/>
 * Veggielicious: flour, tomatoes, lettuce, salsa
 * </code>
 * 
 * <p>This will result in an order with two tacos where the names are "Corn Carnitas" and "Veggielicious".
 * The ingredients will be {COTO, CARN, LETC, TMTO, CHED} and {FLTO,TMTO,LETC,SLSA}.</p>
 */

@Component
public class EmailToOrderTransformer extends AbstractMailMessageTransformer<Order> {
  
  private static final String SUBJECT_KEYWORDS = "TACO ORDER";

  @Override
  protected AbstractIntegrationMessageBuilder<Order> doTransform(Message mailMessage) throws Exception {
    Order tacoOrder = processPayload(mailMessage); // tacoOrder has Taco
    System.out.println("In the doTransform Order=" + tacoOrder.toString());
    return MessageBuilder.withPayload(tacoOrder);
  }
  
  private Order processPayload(Message mailMessage) {
    try {
    	
      String subject = mailMessage.getSubject();
      if (subject.toUpperCase().contains(SUBJECT_KEYWORDS)) {
        String email = ((InternetAddress) mailMessage.getFrom()[0]).getAddress();
        
        // getContent() works when email type is text/plain
        // it does not work if email type is multipart
        String content = mailMessage.getContent().toString();
        String mailType = mailMessage.getContentType().toString();
        System.out.println("mailType=" + mailType);
        
        if (mailType.contains("text/plain")) {
        	System.out.println("In the processPayload content=" + content);
            return parseEmailToOrder(email, content);
        }else if (mailMessage.isMimeType("multipart/*")) {
        	// In the processPayload content=javax.mail.internet.MimeMultipart@24e81cf6
        	
        	// mailType=multipart/alternative; boundary="000000000000165ab305988fde64"
        			
        	// get null point exception
        	System.out.println("mailType=MimeMultipart?");
        	MimeMultipart mimeMultipart = (MimeMultipart) mailMessage.getContent();
            String result = getTextFromMimeMultipart(mimeMultipart);
            return parseEmailToOrder(email, result);
        }
        
        /*String content = "";
        int partCount = mimeMultipart.getCount();
        for (int i = 0; i < partCount; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                // result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
                result = html;
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }*/
        
      }
    } catch (MessagingException e) {} 
      catch (IOException e) {}
      return null;
  }
  
  private String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}

  private Order parseEmailToOrder(String email, String content) {
	  Order order = new Order(email);
	  String[] lines = content.split("\\r?\\n");
	  for (String line : lines) {
		  if (line.trim().length() > 0 && line.contains(":")) {
			  System.out.println("In the parseEmailToOrder line=" + line);
			  String[] lineSplit = line.split(":");
			  String tacoName = lineSplit[0].trim();
			  String ingredients = lineSplit[1].trim();
			  String[] ingredientsSplit = ingredients.split(",");
			  List<String> ingredientCodes = new ArrayList<>();
			  for (String ingredientName : ingredientsSplit) {
				  String code = lookupIngredientCode(ingredientName.trim());
				  if (code != null) {
					  ingredientCodes.add(code);
				  }
			  }
        
			  Taco taco = new Taco(tacoName);
			  taco.setIngredients(ingredientCodes);
			  System.out.println("In the parseEmailToOrder 1");
			  order.addTaco(taco);
		  }
	  }
	  return order;
  }
  
  private String lookupIngredientCode(String ingredientName) {
	  /*
	   In the lookupIngredientCode ingredientName=flour
		In the lookupIngredientCode ingredientName=tomatoes
		In the lookupIngredientCode ingredientName=lettuce
		In the lookupIngredientCode ingredientName=salsa
	   */
	  System.out.println("In the lookupIngredientCode ingredientName=" + ingredientName);
	  for (Ingredient ingredient : ALL_INGREDIENTS) {
		  String ucIngredientName = ingredientName.toUpperCase();
		  System.out.println("In the lookupIngredientCode ucIngredientName=" + ucIngredientName);
		  if (LevenshteinDistance.getDefaultInstance().apply(ucIngredientName, ingredient.getName()) < 3 ||
	          ucIngredientName.contains(ingredient.getName()) ||
	          ingredient.getName().contains(ucIngredientName)) {
	    	  return ingredient.getCode();
		  }
	  }
	  return null;
  }
  
  private static Ingredient[] ALL_INGREDIENTS = new Ingredient[] {
      new Ingredient("FLTO", "FLOUR TORTILLA"), // code, name
      new Ingredient("COTO", "CORN TORTILLA"),
      new Ingredient("GRBF", "GROUND BEEF"),
      new Ingredient("CARN", "CARNITAS"),
      new Ingredient("TMTO", "TOMATOES"),
      new Ingredient("LETC", "LETTUCE"),
      new Ingredient("CHED", "CHEDDAR"),
      new Ingredient("JACK", "MONTERREY JACK"),
      new Ingredient("SLSA", "SALSA"),
      new Ingredient("SRCR", "SOUR CREAM")
  };
}
