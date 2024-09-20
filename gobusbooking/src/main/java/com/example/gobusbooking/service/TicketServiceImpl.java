package com.example.gobusbooking.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gobusbooking.entity.Bus;
import com.example.gobusbooking.entity.Passenger;
import com.example.gobusbooking.entity.Ticket;
import com.example.gobusbooking.repository.BusRepository;
import com.example.gobusbooking.repository.TicketRepository;


@Service
public class TicketServiceImpl  implements TicketService{

	
	
	
	 private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	    @Autowired
	    TicketRepository ticketRepository;

	    @Autowired
	    UserService userservice;
	    
	    @Autowired
	    BusRepository busRepository;
	    

	  


	

	    public Ticket bookATicket(Ticket ticket) {
	        logger.info("Booking a ticket for user with ID: {}", ticket.getUser().getUserId());
	        return ticketRepository.bookATicket(ticket);
	    }

	 






	    public Ticket ticketDetails(int ticketId) {
	        logger.info("Getting ticket details for ticket ID: {}", ticketId);
	        return ticketRepository.ticketDetails(ticketId);
	    }

	

	    public boolean cancelTicket(int ticketId) {
	        logger.info("Canceling ticket with ID: {}", ticketId);
	        return ticketRepository.cancelTicket(ticketId);
	    }

	    public List<Ticket> viewTicketBookedByUserId(int userId) {
	        logger.info("Viewing tickets booked by user with ID: {}", userId);
	        return ticketRepository.viewTicketBookedByUserId(userId);
	    }





	    @Override
	    public List<Passenger> getPassenger(int ticketId) {
	        logger.info("Getting passenger list for ticket with ID: {}", ticketId);
	        return ticketRepository.getPassenger(ticketId);
	    }

	    @Override
	    public Bus getBus(int ticketId) {
	        logger.info("Getting bus details for ticket with ID: {}", ticketId);
	        return busRepository.getBus(ticketId);
	    }

	 

	    @Override
	    public List<Ticket> bookingsBasedOnPeriod(int busId, LocalDate travelDate) {
	        logger.info("Finding bookings for bus ID: {} on travel date: {}", busId, travelDate);
	        return ticketRepository.bookingsBasedOnPeriod(busId, travelDate);
	    }

	  




	    @Override
	    public Ticket rescheduleTicket(int ticketId, LocalDate travelDate, List<String> seats) {
	        logger.info("Rescheduling ticket with ID: {} for new travel date: {} and seats: {}", ticketId, travelDate, seats);
	        return ticketRepository.rescheduleTicket(ticketId, travelDate, seats);
	    }

	    @Override
	    public Ticket setTicketForUser(Ticket ticket) {
	        logger.info("Setting ticket for user with ID: {}", ticket.getUser().getUserId());
	        return ticketRepository.setTicketForUser(ticket);
	    }
}
