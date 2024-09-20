package com.example.gobusbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.gobusbooking.entity.Bus;
import com.example.gobusbooking.entity.Passenger;
import com.example.gobusbooking.entity.Ticket;
@Repository
public interface TicketRepository {

	Ticket bookATicket(Ticket ticket);

	Ticket ticketDetails(int ticketId);

	boolean cancelTicket(int ticketId);

	List<Ticket> viewTicketBookedByUserId(int userId);

	List<Passenger> getPassenger(int ticketId);

	Bus getBus(int ticketId);

	List<Ticket> bookingsBasedOnPeriod(int busId, LocalDate travelDate);

	Ticket rescheduleTicket(int ticketId, LocalDate travelDate, List<String> seats);

	Ticket setTicketForUser(Ticket ticket);

}
