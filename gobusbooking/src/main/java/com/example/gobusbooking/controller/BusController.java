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
public class BusController {
	
	
	
	 private static final Logger logger = LoggerFactory.getLogger(BusController.class);

	    @Autowired
	    BusService busService;

	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;

	   @ApiOperation(value = "Add or update a bus")
	    @PostMapping(value = "/addorupdatebus")
	    public Bus addBus(@RequestBody Bus bus) {
	        logger.info("Adding or updating bus: {}", bus.getbusId());
	        Bus busPersisted = busService.addBus(bus);
	        logger.info("Bus added or updated: {}", bus.getbusId());
	        return busPersisted;
	    }

	   @ApiOperation(value = "Update a bus")
	   @PutMapping(value = "/updateBus")
	   public String updateBus(
	           @ApiParam(value = "Bus ID", required = true) @RequestParam("busId") int busId,
	           @ApiParam(value = "Source", required = true) @RequestParam("source") String source,
	           @ApiParam(value = "Destination", required = true) @RequestParam("destination") String destination,
	           @ApiParam(value = "Fare", required = true) @RequestParam("fare") double fare) {
	       logger.info("Updating bus: {}", busId);
	       String res = busService.updateBus(busId, source, destination, fare);
	       logger.info("Bus updated: {}", busId);
	       return res;
	   }
	       

	        @ApiOperation(value = "Search for buses")
	        @GetMapping(value = "/searchbus")
	        public List<Bus> searchBus(
	                @ApiParam(value = "Source", required = true) @RequestParam("source") String source,
	                @ApiParam(value = "Destination", required = true) @RequestParam("destination") String destination) {
	            logger.info("Searching for buses from {} to {}", source, destination);
	            return busService.searchBus(source, destination);
	        }

	        @ApiOperation(value = "Get bus details by ID")
	        @GetMapping(value = "/getbusbyid")
	        public Bus chooseBus(
	                @ApiParam(value = "Bus ID", required = true) @RequestParam("busId") int busId) {
	            logger.info("Getting bus details for bus ID: {}", busId);
	            return busService.chooseBus(busId);
	        }

	        @ApiOperation(value = "Fetch booked seats for a specific bus and date")
	        @GetMapping(value = "/fetchbookedseats")
	        public List<String> fetchBookedSeats(
	                @ApiParam(value = "Travel Date", required = true) @RequestParam("travelDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate travelDate,
	                @ApiParam(value = "Bus ID", required = true) @RequestParam("busId") int busId) {
	            logger.info("Fetching booked seats for bus ID: {} on travel date: {}", busId, travelDate);
	            return busService.fetchBookedSeats(travelDate, busId);
	        }

	        @ApiOperation(value = "Get frequently traveled routes")
	        @GetMapping(value = "/frequentlytravelledroute")
	        public List<Object[]> frequentlyTravelledRoute() {
	            logger.info("Getting frequently traveled routes");
	            return busService.frequentlyTravelledRoute();
	        }

	        @ApiOperation(value = "View all buses")
	        @GetMapping(value = "/viewallbuses")
	        public List<Bus> viewAllBuses() {
	            logger.info("Viewing all buses");
	            return busService.viewAllBuses();
	        }

	   


	        @ApiOperation(value = "Get the most preferred buses")
	        @GetMapping(value = "/mostpreferredbus")
	        public List<Integer> mostPreferredBus() {
	            logger.info("Getting the most preferred buses");
	            return busService.mostPreferredBus();
	        }

	       


	      

	        @ApiOperation(value = "Get bus details by ticket ID")
	        @GetMapping(value = "/getBusByTicketId")
	        public Bus getBusByTicketId(
	                @ApiParam(value = "Ticket ID", required = true) @RequestParam("ticketId") int ticketId) {
	            logger.info("Getting bus details by ticket ID: {}", ticketId);
	            return busService.getBus(ticketId);
	        }

	      




}
