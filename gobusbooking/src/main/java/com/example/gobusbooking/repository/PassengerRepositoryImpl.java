package com.example.gobusbooking.repository;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.gobusbooking.entity.Passenger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
@Repository
public class PassengerRepositoryImpl  implements PassengerRepository{
	
	
	

    Logger logger = LoggerFactory.getLogger(PassengerRepositoryImpl.class);

    @PersistenceContext
    EntityManager em;


    @Override
    public List<Passenger> getPassenger(int ticketId) {
        String jpql = "select  p from Passenger p where p.ticket.ticketId=:tid";
        TypedQuery<Passenger> query = em.createQuery(jpql, Passenger.class);
        query.setParameter("tid", ticketId);
        List<Passenger> passengerList = query.getResultList();
        return passengerList;
    }

}
