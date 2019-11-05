package com.example.bookingjmsrestjpa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/bookings")

public class BookingController {
	
	@Autowired private JmsTemplate jmsTemplate;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST) 
		public void send(@RequestBody HotelBookingSender hotelBooking) {
		    System.out.println("Sending Hotel booking data...");
		    // Post message to the message queue named "OrderTransactionQueue"
		    jmsTemplate.convertAndSend("mailbox", hotelBooking);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll(){
		System.out.println("Sending request...");
        jmsTemplate.convertAndSend("mailbox2", "printAll");
        
        return ("<h1>Request sended<h1>");
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable long id){        
        jmsTemplate.convertAndSend("mailbox3", id);
        System.out.println("Sending Delete request...");
        return ("<h1>Delete request sended<h1>");
    }
	
	@RequestMapping(value = "/affordable/{price}", method = RequestMethod.GET)
    public String getAffordable(@PathVariable double price){
		jmsTemplate.convertAndSend("mailbox4", price);
		System.out.println("Sending Affordable request...");
		return ("<h1>Request for affordable booking sended<h1>");
    }

}

