package com.example.gobusbooking.repository;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.gobusbooking.entity.Bus;
import com.example.gobusbooking.entity.Ticket;
import com.example.gobusbooking.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


@Repository
public class BusRepositoryImpl implements BusRepository {

    Logger logger = LoggerFactory.getLogger(BusRepositoryImpl.class);

    @PersistenceContext
    EntityManager em;

    @Transactional
    public User registerOrUpdateUser(User user) {
        User u = em.merge(user);
        logger.info("Registered or Updated user with ID: {}", u.getUserId());
        return u;
    }

    @Transactional
    public Bus addBus(Bus bus) {
        Bus busPersisted = em.merge(bus);
        logger.info("Added or Updated Bus with ID: {}", busPersisted.getbusId());
        return busPersisted;
    }



  


    public List<Bus> searchBus(String source, String destination) {
        String jpql = "select b from Bus b where b.source=:s and b.destination=:d";
        TypedQuery<Bus> query = em.createQuery(jpql, Bus.class);
        query.setParameter("s", source);
        query.setParameter("d", destination);
        List<Bus> bus = query.getResultList();
        return bus;
    }

    public Bus chooseBus(int busId) {
        String jpql = "select b from Bus b where b.busId=:bid";
        TypedQuery<Bus> query = em.createQuery(jpql, Bus.class);
        query.setParameter("bid", busId);
        Bus bus = query.getSingleResult();
        return bus;
    }

    public List<String> fetchBookedSeats(LocalDate travelDate, int busId) {
        String jpql = "select p.seatNo from Passenger p where p.ticket.travelDate=:tvlDate and p.ticket.bus.busId=:bId";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        query.setParameter("tvlDate", travelDate);
        query.setParameter("bId", busId);
        List<String> seatNo = query.getResultList();
        return seatNo;
    }

    public List<Bus> viewAllBuses() {
        String jpql = "select b from Bus b";
        TypedQuery<Bus> query = em.createQuery(jpql, Bus.class);
        List<Bus> buses = query.getResultList();
        return buses;
    }



    public List<Object[]> frequentlyTravelledRoute() {
        String jpql = "select count(*),t.bus.source,t.bus.destination from Ticket t group by t.bus.source,t.bus.destination order by count(*) desc";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        List<Object[]> routes = query.getResultList();
        return routes;
    }

  

  


    public List<Integer> mostPreferredBus() {
        String jpql = "select t.bus.busId from Ticket t group by t.bus.busId order by count(*) desc";
        TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
        List<Integer> tickets = query.getResultList();
        return tickets;
    }

  


 

  

   


    @Override
    public Bus getBus(int ticketId) {
        String jpql = "select b from Bus b where b.busId =(select t.bus.busId from Ticket t where t.ticketId=:tid)";
        TypedQuery<Bus> query = em.createQuery(jpql, Bus.class);
        query.setParameter("tid", ticketId);
        Bus getBus = query.getSingleResult();
        return getBus;
    }

    @Transactional
    public String updateBus(int busId, String source, String destination, double fare) {
        String jpql = "update Bus b set b.source=:s, b.destination=:d, b.fare=:f where b.busId=:bid";
        Query query = em.createQuery(jpql);
        query.setParameter("bid", busId);
        query.setParameter("s", source);
        query.setParameter("d", destination);
        query.setParameter("f", fare);

        int updatedCount = query.executeUpdate();

        if (updatedCount > 0) {
            return "Bus updated successfully";
        } else {
            return "No bus found with the given  bus Id";
        }
    }


    @Override
    public List<Ticket> bookingsBasedOnPeriod(int busId, LocalDate travelDate) {
        String jpql = "select t from Ticket t where t.bus.busId=:bid and t.travelDate=:td";
        TypedQuery<Ticket> query = em.createQuery(jpql, Ticket.class);
        query.setParameter("bid", busId);
        query.setParameter("td", travelDate);
        List<Ticket> tickets = query.getResultList();
        return tickets;
    }


}