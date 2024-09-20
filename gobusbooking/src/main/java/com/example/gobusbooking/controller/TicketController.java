package com.example.gobusbooking.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gobusbooking.entity.BookTicket;
import com.example.gobusbooking.entity.Bus;
import com.example.gobusbooking.entity.Passenger;
import com.example.gobusbooking.entity.Ticket;
import com.example.gobusbooking.entity.User;
import com.example.gobusbooking.service.BusService;
import com.example.gobusbooking.service.TicketService;
import com.example.gobusbooking.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*")
@RestController
public class TicketController {
	
	
	
	 private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	    @Autowired
	    TicketService ticketService;


	    @Autowired
	    UserService userService;
	    
	    @Autowired
	    BusService busService;



	    @ApiOperation(value = "Book a ticket")
	    @PostMapping(value = "/bookaticket")
	    public Ticket bookATicket(@RequestBody BookTicket bookTicket, @RequestParam("userId") int userId,
	                              @RequestParam("busId") int busId) {
	        Ticket ticket = bookTicket.getTicket();
	        Bus bus = busService.chooseBus(busId);
	        ticket.setBus(bus);
	        User user = userService.findUser(userId);
	        ticket.setUser(user);
	        List<Passenger> passengers = bookTicket.getPassengers();
	        ticket.setPassengers(passengers);
	        for (int i = 0; i < passengers.size(); i++) {
	            passengers.get(i).setTicket(ticket);
	        }
	        logger.info("Booking a ticket for user ID: {}", userId);
	        return ticketService.bookATicket(ticket);
	    }

	    @ApiOperation(value = "Add a ticket to a user")
	    @GetMapping(value = "/addtickettouser")
	    public Ticket addTicketToUserId(@RequestParam("ticketId") int ticketId, @RequestParam("userId") int userId) {
	        Ticket ticket1 = ticketService.ticketDetails(ticketId);
	        User user1 = userService.findUser(userId);
	        ticket1.setUser(user1);
	        Ticket ticketPersisted = ticketService.setTicketForUser(ticket1);
	        logger.info("Adding a ticket to user ID: {}", userId);
	        return ticketPersisted;
	    }

	   



	        @ApiOperation(value = "Get ticket details")
	        @GetMapping(value = "/ticketDetails")
	        public Ticket ticketDetails(
	                @ApiParam(value = "Ticket ID", required = true) @RequestParam("ticketId") int ticketId) {
	            logger.info("Getting ticket details for ticket ID: {}", ticketId);
	            return ticketService.ticketDetails(ticketId);
	        }

	 

	        @ApiOperation(value = "Cancel a ticket")
	        @DeleteMapping(value = "/cancelticket")
	        public boolean cancelTicket(
	                @ApiParam(value = "Ticket ID", required = true) @RequestParam("ticketId") int ticketId) {
	            logger.info("Cancelling ticket with ID: {}", ticketId);
	            return ticketService.cancelTicket(ticketId);
	        }

	        @ApiOperation(value = "View tickets booked by a user")
	        @GetMapping(value = "/viewticketbookedbyuserid")
	        public List<Ticket> viewTicketBookedByUserId(
	                @ApiParam(value = "User ID", required = true) @RequestParam("userId") int userId) {
	            logger.info("Viewing tickets booked by user with ID: {}", userId);
	            return ticketService.viewTicketBookedByUserId(userId);
	        }

	   


	        @ApiOperation(value = "Find bookings based on period")
	        @GetMapping(value = "bookingsbasedonperiod")
	        public List<Ticket> findBookingBasedOnPeriod(
	                @ApiParam(value = "Bus ID", required = true) @RequestParam("busId") int busId,
	                @ApiParam(value = "Travel Date", required = true) @RequestParam("travelDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate travelDate) {
	            logger.info("Finding bookings based on period for bus ID: {} and travel date: {}", busId, travelDate);
	            return ticketService.bookingsBasedOnPeriod(busId, travelDate);
	        }

	      

	        @ApiOperation(value = "Reschedule a ticket")
	        @PutMapping(value = "reschedule")
	        public Ticket reschedule(
	                @ApiParam(value = "Ticket ID", required = true) @RequestParam("ticketId") int ticketId,
	                @ApiParam(value = "Travel Date", required = true) @RequestParam("travelDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate travelDate,
	                @RequestBody List<String> seats) {
	            logger.info("Rescheduling ticket with ID: {} to travel date: {}", ticketId, travelDate);
	            return ticketService.rescheduleTicket(ticketId, travelDate, seats);
	        }

}
