package com.example.bookingjmsrestjpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	List<HotelBookingSender> bookings = new ArrayList<>();
	
	private BookingReceiverRepository bookingReceiverRepository;
	
	@Autowired
    public Receiver(BookingReceiverRepository bookingReceiverRepository){ // ??
        this.bookingReceiverRepository = bookingReceiverRepository;
    }
	
	@JmsListener(destination = "mailbox", containerFactory = "myFactory")
	public void receiveMessage(HotelBookingSender hotelBookingSender) {		
        bookings.add(hotelBookingSender);        
		bookingReceiverRepository.saveAll(bookings);		
		System.out.println("Received <" + hotelBookingSender + ">");
	}
	
	@JmsListener(destination = "mailbox2", containerFactory = "myFactory")
	public void receiveAllMessage() {
		System.out.println("Printing all bookings");
		for(HotelBookingSender hotelBookingSender : bookings) {
            System.out.println(hotelBookingSender.toString());
        }
	}
	
	@JmsListener(destination = "mailbox3", containerFactory = "myFactory") 
	public void receiveDeleteMessage(Long id) {
		bookingReceiverRepository.deleteById(id);
		System.out.println("Row with id number " + id + " was deleted"); 
	}
	
	@JmsListener(destination = "mailbox4", containerFactory = "myFactory") 
	public void receiveAffordableMessage(double price) {
		List<HotelBookingSender> affordable = new ArrayList<>();		
		System.out.println("Listing bookings below " + price + " dollars"); 
		affordable = bookingReceiverRepository.findByPricePerNightLessThan(price);		
		for(HotelBookingSender hotelBookingSender : affordable) {
            System.out.println(hotelBookingSender.toString());
        }
	}	 
}