package com.example.gobusbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gobusbooking.entity.Passenger;



@Service
public interface PassengerService {
	
	
 public List<Passenger> getPassenger(int ticketId);
}
