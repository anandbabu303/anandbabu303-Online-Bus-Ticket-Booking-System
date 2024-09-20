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
import com.example.gobusbooking.entity.ChangePasswordDto;
import com.example.gobusbooking.entity.LoginDto;
import com.example.gobusbooking.entity.LoginForgetDto;
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
public class UserController {
	
	
	 private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	    @Autowired
	    UserService userService;
	    
	    @Autowired
	    BusService busService;
	    
	    @Autowired
	    TicketService ticketService;

	    @ApiOperation(value = "Register or update a user")
	    @PostMapping(value = "/registerorupdateuser")
	    public User registerOrUpdateUser(@RequestBody User user) {
	        logger.info("Registering or updating user: {}", user.getUserId());
	        User userPersisted = userService.registerOrUpdateUser(user);
	        logger.info("User registered or updated: {}", user.getUserId());
	        return userPersisted;
	    }




	    @ApiOperation(value = "User login")
	    @PostMapping(value = "/login")
	    public boolean login(@RequestBody LoginDto dto) {
	        logger.info("User login request for ID: {}", dto.getId());
	        boolean userPersisted =userService.loginUser(dto.getId(), dto.getPassword());
	        logger.info("User login result for ID {}: {}", dto.getId(), userPersisted);
	        return userPersisted;
	    }
	    


	    @ApiOperation(value = "Change password")
	    @PutMapping(value = "/changepassword")
	    public boolean changePassword(@RequestBody ChangePasswordDto dto) {
	        logger.info("Changing password for user ID: {}", dto.getUserId());
	        boolean result = userService.changePassword(dto.getUserId(), dto.getPassword());
	        logger.info("Password changed for user ID: {}", dto.getUserId());
	        return result;
	    }

	 




	        @ApiOperation(value = "View all registered customers")
	        @GetMapping(value = "/viewallregsiteredcustomers")
	        public List<User> viewAllRegsiteredCustomers() {
	            logger.info("Viewing all registered customers");
	            return userService.viewAllRegsiteredCustomers();
	        }

	        @ApiOperation(value = "View customers who registered but have no bookings")
	        @GetMapping(value = "/viewcustomerwhoregisteredbutwithnobooking")
	        public List<User> viewCustomerWhoRegisteredButwithNoBooking() {
	            logger.info("Viewing customers who registered but have no bookings");
	            return userService.viewCustomerWhoRegisteredButwithNoBooking();
	        }

	        @ApiOperation(value = "Recharge a user's wallet")
	        @GetMapping(value = "/rechargeWallet")
	        public User rechargeWallet(
	                @ApiParam(value = "User ID", required = true) @RequestParam("userId") int userId,
	                @ApiParam(value = "Recharge Amount", required = true) @RequestParam("rechargeAmount") int rechargeAmount) {
	            logger.info("Recharging wallet for user ID: {} with amount: {}", userId, rechargeAmount);
	            return userService.rechargeWallet(userId, rechargeAmount);
	        }


	        @ApiOperation(value = "Pay through wallet")
	        @GetMapping(value = "/paythroughwallet")
	        public boolean payThroughWallet(
	                @ApiParam(value = "User ID", required = true) @RequestParam("userId") int userId,
	                @ApiParam(value = "Amount", required = true) @RequestParam("amount") double amount) {
	            logger.info("Paying through wallet for user ID: {} with amount: {}", userId, amount);
	            return userService.payThroughWallet(userId, amount);
	        }



	     



	        @ApiOperation(value = "Find user by ID")
	        @GetMapping(value = "/finduserbyid")
	        public User findUser(
	                @ApiParam(value = "User ID", required = true) @RequestParam int userId) {
	            logger.info("Finding user by ID: {}", userId);
	            return userService.findUser(userId);
	        }


}
