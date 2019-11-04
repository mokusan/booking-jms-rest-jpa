package com.example.bookingjmsrestjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private BookingReceiverRepository bookingReceiverRepository;
	
	@Autowired
    public Receiver(BookingReceiverRepository bookingReceiverRepository){ // ??
        this.bookingReceiverRepository = bookingReceiverRepository;
    }
	
	@JmsListener(destination = "mailbox", containerFactory = "myFactory")
	public void receiveMessage(HotelBookingSender hotelBookingSender) {
		bookingReceiverRepository.save(hotelBookingSender);
		System.out.println("Received <" + hotelBookingSender + ">");
	}

}