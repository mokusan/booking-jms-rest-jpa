package com.example.bookingjmsrestjpa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/bookings")

public class BookingController {
	
	@Autowired private JmsTemplate jmsTemplate;
	
	@RequestMapping(value = "/send", method = RequestMethod.POST) 
		public void send(@RequestBody HotelBookingSender hotelBooking) {
		    System.out.println("Sending a transaction.");
		    // Post message to the message queue named "OrderTransactionQueue"
		    jmsTemplate.convertAndSend("mailbox", hotelBooking);		  
	}

}

