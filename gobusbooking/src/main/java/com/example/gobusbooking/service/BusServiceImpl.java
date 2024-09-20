package com.example.gobusbooking.service;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gobusbooking.entity.Bus;
import com.example.gobusbooking.entity.Ticket;
import com.example.gobusbooking.repository.BusRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class BusServiceImpl implements BusService {

    private static final Logger logger = LoggerFactory.getLogger(BusServiceImpl.class);

    @Autowired
    BusRepository busRepository;

    

  

    public Bus addBus(Bus bus) {
        logger.info("Adding a new bus: {}", bus.getbusId());
        return busRepository.addBus(bus);
    }

 
    public List<Bus> searchBus(String source, String destination) {
        logger.info("Searching for buses from {} to {}", source, destination);
        return busRepository.searchBus(source, destination);
    }

    public Bus chooseBus(int busId) {
        logger.info("Choosing bus with ID: {}", busId);
        return busRepository.chooseBus(busId);
    }

    public List<String> fetchBookedSeats(LocalDate travelDate, int busId) {
        logger.info("Fetching booked seats for bus ID: {} on travel date: {}", busId, travelDate);
        return busRepository.fetchBookedSeats(travelDate, busId);
    }

    public List<Object[]> frequentlyTravelledRoute() {
        logger.info("Getting frequently traveled routes");
        return busRepository.frequentlyTravelledRoute();
    }

    public List<Bus> viewAllBuses() {
        logger.info("Viewing all buses");
        return busRepository.viewAllBuses();
    }

   


    

    public List<Integer> mostPreferredBus() {
          logger.info("Getting the most preferred buses");
          return busRepository.mostPreferredBus();
      }
  

    @Override
    public Bus getBus(int ticketId) {
        logger.info("Getting bus details for ticket with ID: {}", ticketId);
        return busRepository.getBus(ticketId);
    }

    @Override
    public String updateBus(int busId, String source, String destination, double fare) {
        logger.info("Updating bus with ID: {}, source: {}, destination: {}, and fare: {}", busId, source, destination, fare);
        return busRepository.updateBus(busId, source, destination, fare);
    }

    @Override
    public List<Ticket> bookingsBasedOnPeriod(int busId, LocalDate travelDate) {
        logger.info("Finding bookings for bus ID: {} on travel date: {}", busId, travelDate);
        return busRepository.bookingsBasedOnPeriod(busId, travelDate);
    }


}
