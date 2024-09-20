package com.example.gobusbooking.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gobusbooking.entity.Passenger;
import com.example.gobusbooking.repository.PassengerRepository;
import com.example.gobusbooking.repository.TicketRepository;
import com.example.gobusbooking.repository.UserRepository;



@Service
public class PassengerServiceImpl  implements PassengerService{
	
	
	 private static final Logger logger = LoggerFactory.getLogger(PassengerServiceImpl.class);

	    @Autowired
	    UserRepository userRepository;
	    
	    @Autowired
	   TicketRepository ticketRepository;
	    
	    
	    @Autowired
		   PassengerRepository passengerRepository;
	
	
    @Override
    public List<Passenger> getPassenger(int ticketId) {
        logger.info("Getting passenger list for ticket with ID: {}", ticketId);
        return passengerRepository.getPassenger(ticketId);
    }

}
