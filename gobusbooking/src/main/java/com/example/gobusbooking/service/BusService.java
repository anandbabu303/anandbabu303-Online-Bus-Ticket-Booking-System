package com.example.gobusbooking.service;



import java.time.LocalDate;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.gobusbooking.entity.Bus;
import com.example.gobusbooking.entity.Ticket;

@Service
public interface BusService {

    Logger logger = LoggerFactory.getLogger(BusService.class);

 
    public Bus addBus(Bus bus);

  

    public List<Bus> searchBus(String source, String destination);

    public Bus chooseBus(int busId);

    public List<String> fetchBookedSeats(LocalDate travelDate, int busId);

    public List<Object[]> frequentlyTravelledRoute();

    public List<Bus> viewAllBuses();

   

    public List<Integer> mostPreferredBus();


    public Bus getBus(int ticketId);

    public String updateBus(int busId, String source, String destination, double fare);

    public List<Ticket> bookingsBasedOnPeriod(int busId, LocalDate travelDate);

}