package com.example.gobusbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gobusbooking.entity.Bus;
import com.example.gobusbooking.entity.Ticket;
@Repository
public interface BusRepository{

	Bus addBus(Bus bus);

	List<Bus> searchBus(String source, String destination);

	Bus chooseBus(int busId);

	List<String> fetchBookedSeats(LocalDate travelDate, int busId);

	List<Object[]> frequentlyTravelledRoute();

	List<Bus> viewAllBuses();

	List<Integer> mostPreferredBus();

	Bus getBus(int ticketId);

	String updateBus(int busId, String source, String destination, double fare);

	List<Ticket> bookingsBasedOnPeriod(int busId, LocalDate travelDate);

}
