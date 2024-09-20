package com.example.gobusbooking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gobusbooking.entity.BookTicket;
import com.example.gobusbooking.entity.Bus;
import com.example.gobusbooking.entity.ChangePasswordDto;
import com.example.gobusbooking.entity.LoginDto;
import com.example.gobusbooking.entity.Passenger;
import com.example.gobusbooking.entity.Ticket;
import com.example.gobusbooking.entity.User;
import com.example.gobusbooking.service.BusService;
import com.example.gobusbooking.service.PassengerService;
import com.example.gobusbooking.service.TicketService;
import com.example.gobusbooking.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@CrossOrigin(origins = "*")
@RestController
public class PassengerController {
	
	
	
	
	

	 private static final Logger logger = LoggerFactory.getLogger(PassengerController.class);

	    @Autowired
	    UserService userService;
	    
	    @Autowired
	    BusService busService;
	    
	    @Autowired
	    TicketService ticketService;
	    
@Autowired
PassengerService passengerService;

	
      @ApiOperation(value = "Get passenger list for a ticket")
	        @GetMapping(value = "/getPassengerList")
	        public List<Passenger> getPassengerList(
	                @ApiParam(value = "Ticket ID", required = true) @RequestParam("ticketId") int ticketId) {
	            logger.info("Getting passenger list for ticket ID: {}", ticketId);
	            return passengerService.getPassenger(ticketId);
	        }



	 

	
	
	

}
