package com.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @Autowired
    Sender sender;

    @RequestMapping(value="/send")
    public String produce(@RequestParam("msg") String msg){
    	sender.send(msg);
        return "Done";
    }
    
}