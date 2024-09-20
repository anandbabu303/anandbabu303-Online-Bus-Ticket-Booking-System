package com.example.gobusbooking.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.gobusbooking.entity.Ticket;
import com.example.gobusbooking.entity.User;


@Service
public interface UserService {
	 Logger logger = LoggerFactory.getLogger(UserService.class);

	    public User registerOrUpdateUser(User user);


	    public User forgotPassword(int userId, String email);



	    public boolean loginUser(int userId, String password);

	    public boolean changePassword(int userId, String password);

	   
	    public List<User> viewAllRegsiteredCustomers();

	    public List<User> viewCustomerWhoRegisteredButwithNoBooking();

	    public User rechargeWallet(int userId, int rechargeAmount);

	

	    public boolean payThroughWallet(int userId, double amount);


	    public List<Ticket> viewTicketBookedByUserId(int userId);

	    public User findUser(int userId);


}
