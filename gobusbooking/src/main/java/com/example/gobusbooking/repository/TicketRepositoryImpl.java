package com.example.gobusbooking.repository;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Query;

import jakarta.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.gobusbooking.entity.Bus;
import com.example.gobusbooking.entity.Passenger;
import com.example.gobusbooking.entity.Status;
import com.example.gobusbooking.entity.Ticket;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class TicketRepositoryImpl implements TicketRepository {
	
	Logger logger=LoggerFactory.getLogger(TicketRepositoryImpl.class);
	
	@PersistenceContext
	EntityManager em;

	   @Transactional
	    public Ticket bookATicket(Ticket ticket) {
	        Ticket persistedTicket = em.merge(ticket);
	        return persistedTicket;
	    }

	   public Ticket ticketDetails(int ticketId) {
	        String jpql = "select t from Ticket t where t.ticketId=:tid";
	        TypedQuery<Ticket> query = em.createQuery(jpql, Ticket.class);
	        query.setParameter("tid", ticketId);
	        Ticket ticketdetails = query.getSingleResult();
	        return ticketdetails;
	    }

	   @Transactional
	    public boolean cancelTicket(int ticketId) {
	        Ticket ticket = em.find(Ticket.class, ticketId);
	        if (ticket.getTravelDate().isBefore(LocalDate.now())) {
	            return false;
	        } else {
	            ticket.setStatus(Status.CANCELLED);
	            double refund = ticket.getTotalAmount();
	            ticket.getUser().setWallet(ticket.getUser().getWallet() + refund);
	            em.merge(ticket);
	            String jpql = "delete from Passenger p where p.ticket.ticketId=:tid";
	            Query query = em.createQuery(jpql);
	            query.setParameter("tid", ticketId);
	            query.executeUpdate();
	            return true;
	        }
	    }

	   public List<Ticket> viewTicketBookedByUserId(int userId) {
	        String jpql = "select t from Ticket t where t.user.userId=:uid order by t.ticketId DESC";
	        TypedQuery<Ticket> query = em.createQuery(jpql, Ticket.class);
	        query.setParameter("uid", userId);
	        List<Ticket> tickets = query.getResultList();
	        return tickets;
	    }

	    @Override
	    public List<Passenger> getPassenger(int ticketId) {
	        String jpql = "select  p from Passenger p where p.ticket.ticketId=:tid";
	        TypedQuery<Passenger> query = em.createQuery(jpql, Passenger.class);
	        query.setParameter("tid", ticketId);
	        List<Passenger> passengerList = query.getResultList();
	        return passengerList;
	    }

	    @Override
	    public Bus getBus(int ticketId) {
	        String jpql = "select b from Bus b where b.busId =(select t.bus.busId from Ticket t where t.ticketId=:tid)";
	        TypedQuery<Bus> query = em.createQuery(jpql, Bus.class);
	        query.setParameter("tid", ticketId);
	        Bus getBus = query.getSingleResult();
	        return getBus;
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
	    @Transactional
	    public Ticket rescheduleTicket(int ticketId, LocalDate travelDate, List<String> seats) {
	        Ticket ticket = em.find(Ticket.class, ticketId);
	        ticket.setTravelDate(travelDate);
	        List<Passenger> passengerList = ticket.getPassengers();
	        for (int i = 0; i < ticket.getNoOfPassengers(); i++) {
	            passengerList.get(i).setSeatNo(seats.get(i));
	        }
	        em.merge(ticket);
	        for (int i = 0; i < ticket.getNoOfPassengers(); i++) {
	            em.merge(passengerList.get(i));
	        }
	        return ticket;
	    }
	    @Transactional
	    public Ticket setTicketForUser(Ticket ticket) {
	        Ticket ticketPersisted = em.merge(ticket);
	        return ticketPersisted;
	    }

}
