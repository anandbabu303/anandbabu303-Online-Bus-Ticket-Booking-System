package com.example.gobusbooking.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.gobusbooking.entity.Bus;
import com.example.gobusbooking.entity.Passenger;
import com.example.gobusbooking.entity.Ticket;


@Service
public interface TicketService {
	
	
	
	

	 Logger logger = LoggerFactory.getLogger(TicketService.class);


	    public Ticket bookATicket(Ticket ticket);



	    public Ticket ticketDetails(int ticketId);



	    public boolean cancelTicket(int ticketId);

	    public List<Ticket> viewTicketBookedByUserId(int userId);

	  

	    public List<Passenger> getPassenger(int ticketId);

	    public Bus getBus(int ticketId);


	    public List<Ticket> bookingsBasedOnPeriod(int busId, LocalDate travelDate);

	

	    public Ticket setTicketForUser(Ticket ticket);

	    public Ticket rescheduleTicket(int ticketId, LocalDate travelDate, List<String> seats);
	}

