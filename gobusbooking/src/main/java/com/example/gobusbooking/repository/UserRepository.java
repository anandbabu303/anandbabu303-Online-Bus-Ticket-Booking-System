package com.example.gobusbooking.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.gobusbooking.entity.User;
@Repository
public interface UserRepository {

	User registerOrUpdateUser(User user);

	boolean loginUser(int userId, String password);

	boolean changePassword(int userId, String password);

	List<User> viewAllRegsiteredCustomers();

	List<User> viewCustomerWhoRegisteredButwithNoBooking();

	User rechargeWallet(int userId, int rechargeAmount);

	boolean payThroughWallet(int userId, double amount);

	User findUser(int userId);

	User forgotPassword(int userId, String email);

	



}
