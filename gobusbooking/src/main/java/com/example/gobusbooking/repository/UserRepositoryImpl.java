package com.example.gobusbooking.repository;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.gobusbooking.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	
	Logger logger=LoggerFactory.getLogger(UserRepositoryImpl.class);
	@PersistenceContext
	EntityManager em;


    @Transactional
    public User registerOrUpdateUser(User user) {
        User u = em.merge(user);
        logger.info("Registered or Updated user with ID: {}", u.getUserId());
        return u;
    }

    public boolean loginUser(int userId, String password) {
        User user = em.find(User.class, userId);
        if (user != null && user.getPassword().equals(password)) {
            logger.info("User with ID: {} logged in successfully", userId);
            return true;
        } else {
            logger.info("Login failed for user with ID: {}", userId);
            return false;
        }
    }

    @Transactional
    public boolean changePassword(int userId, String password) {
        User user = em.find(User.class, userId);
        User u = null;
        if (user != null) {
            user.setPassword(password);
            u = em.merge(user);
        }
        if (u != null) {
            logger.info("Password changed for user with ID: {}", userId);
            return true;
        } else {
            logger.info("Password change failed for user with ID: {}", userId);
            return false;
        }
    }

    public List<User> viewAllRegsiteredCustomers() {
        String jpql = "select u from User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> users = query.getResultList();
        return users;
    }

    public List<User> viewCustomerWhoRegisteredButwithNoBooking() {
        String jpql = "select u from User u where u.userId not in (select nvl2(t.user.userId,t.user.userId,0) from Ticket t)";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> user = query.getResultList();
        return user;
    }

    @Transactional
    public User rechargeWallet(int userId, int rechargeAmount) {
        User user = em.find(User.class, userId);
        user.setWallet(user.getWallet() + rechargeAmount);
        User user1 = em.merge(user);
        return user1;
    }

    @Transactional
    public boolean payThroughWallet(int userId, double amount) {
        User user = em.find(User.class, userId);
        if (user.getWallet() < amount) {
            return false;
        } else {
            user.setWallet(user.getWallet() - amount);
            em.merge(user);
            return true;
        }
    }


    @Transactional
    public User findUser(int userId) {
        User user = em.find(User.class, userId);
        if (Objects.isNull(user)) {
            return user;
        }
        return user;
    }
    @Transactional
    public User forgotPassword(int userId, String email) {
        String jpql = "select u from User u where u.userId=:id and u.email=:Email";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("id", userId);
        query.setParameter("Email", email);
        User user = query.getSingleResult();
        return user;
    }

}
