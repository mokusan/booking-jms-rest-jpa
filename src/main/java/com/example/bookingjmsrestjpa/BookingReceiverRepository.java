package com.example.bookingjmsrestjpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // marks a class as a Data Access Object(an interface that provides access to DB or any persistence storage)
public interface BookingReceiverRepository extends JpaRepository<HotelBookingSender, Long> {
	List<HotelBookingSender> findByPricePerNightLessThan(double price);
}