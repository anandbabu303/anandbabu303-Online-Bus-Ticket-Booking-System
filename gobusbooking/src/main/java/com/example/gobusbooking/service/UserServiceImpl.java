package com.example.gobusbooking.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gobusbooking.entity.Ticket;
import com.example.gobusbooking.entity.User;
import com.example.gobusbooking.repository.TicketRepository;
import com.example.gobusbooking.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	
	 private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	    @Autowired
	    UserRepository userRepository;
	    
	    @Autowired
	   TicketRepository ticketRepository;
	    
	    

	   

	    public User registerOrUpdateUser(User user) {
	        logger.info("Registering or updating user: {}", user.getUserId());
	        return userRepository.registerOrUpdateUser(user);
	    }



	    public boolean loginUser(int userId, String password) {
	        logger.info("Logging in user with ID: {}", userId);
	        return userRepository.loginUser(userId, password);
	    }

	    public boolean changePassword(int userId, String password) {
	        logger.info("Changing password for user with ID: {}", userId);
	        return userRepository.changePassword(userId, password);
	    }



	    public List<User> viewAllRegsiteredCustomers() {
	        logger.info("Viewing all registered customers");
	        return userRepository.viewAllRegsiteredCustomers();
	    }

	    public List<User> viewCustomerWhoRegisteredButwithNoBooking() {
	        logger.info("Viewing customers who registered but have no bookings");
	        return userRepository.viewCustomerWhoRegisteredButwithNoBooking();
	    }

	    public User rechargeWallet(int userId, int rechargeAmount) {
	        logger.info("Recharging wallet for user with ID: {} with amount: {}", userId, rechargeAmount);
	        return userRepository.rechargeWallet(userId, rechargeAmount);
	    }



	    public boolean payThroughWallet(int userId, double amount) {
	        logger.info("Paying through wallet for user with ID: {} with amount: {}", userId, amount);
	        return userRepository.payThroughWallet(userId, amount);
	    }



	    public List<Ticket> viewTicketBookedByUserId(int userId) {
	        logger.info("Viewing tickets booked by user with ID: {}", userId);
	        return ticketRepository.viewTicketBookedByUserId(userId);
	    }

	    @Override
	    public User findUser(int userId) {
	        logger.info("Finding user with ID: {}", userId);
	        return userRepository.findUser(userId);
	    }



	    public User forgotPassword(int userId, String email) {
	        logger.info("Initiating forgot password process for user with ID: {}", userId);
	        return userRepository.forgotPassword(userId, email);
	    }


}
