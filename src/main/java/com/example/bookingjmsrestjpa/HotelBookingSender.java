package com.example.bookingjmsrestjpa;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class HotelBookingSender {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // identity when auto_increment is set
    private long id;
	
	private String hotelName;
    private double pricePerNight;
    private int nbOfNights;
    
    public HotelBookingSender() {
    }
    
    public HotelBookingSender(String hotelName, double pricePerNight, int nbOfNights) {
    	this.hotelName = hotelName;
    	this.pricePerNight = pricePerNight;
    	this.nbOfNights = nbOfNights;    	
    }
    
    public String getHotelName() {
    	return hotelName;
    }
    
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
    
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    
    public int getNbOfNights() {
        return nbOfNights;
    }
    
    public void setgetNbOfNights(int nbOfNights) {
        this.nbOfNights = nbOfNights;
    }
    
    public double getTotalPrice() {
        return pricePerNight * nbOfNights;
    }
    
    public long getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return String.format("HotelBookingSender{hotelName=%s, pricePerNight=%.2f, nbOfNights=%d, totalPrice=%.2f}", getHotelName(), getPricePerNight(), 
        		getNbOfNights(), getTotalPrice());
    }
}
