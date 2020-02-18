package com.learning;

import java.util.Map;
import org.springframework.stereotype.Component;
 
@Component
public class MapToObject {
 
    public Ticket map(Map message) {
    	
        Ticket ticket = new Ticket();
        ticket.setTicketId(message.get("ticketId").toString());
        ticket.setDescription(message.get("description").toString());
 
        return ticket;
    }
    
}