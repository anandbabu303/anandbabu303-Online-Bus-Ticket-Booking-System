package com.example.gobusbooking.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.gobusbooking.entity.Passenger;
@Repository
public interface PassengerRepository {

	List<Passenger> getPassenger(int ticketId);

}
