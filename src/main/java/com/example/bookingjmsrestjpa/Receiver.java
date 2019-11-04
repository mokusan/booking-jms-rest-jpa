package com.example.bookingjmsrestjpa;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(HotelBookingSender hotelBookingSender) {
        System.out.println("Received <" + hotelBookingSender + ">");
    }

}