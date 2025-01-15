package com.meuvooaqui.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meuvooaqui.models.Flight;
import com.meuvooaqui.repositories.FlightRepository;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional
    public Flight addFlight(String flightNumber, String origin, String destination, String status, LocalDateTime scheduleDeparture,
    LocalDateTime scheduleArrival){
        Flight flight = new Flight(flightNumber, origin, destination, status, scheduleDeparture, scheduleArrival);
        return flightRepository.save(flight);
    }

    @Transactional
    public Flight updateFlightStatus(Long fligthId, String status){
        Flight flight = flightRepository.findById(fligthId).orElseThrow(() -> new RuntimeException("Flight Not Found"));
        flight.setStatus(status);
        return flightRepository.save(flight);
    }

    @Transactional
    public List<Flight> findFlightsByAirport(String airportCode){
        return flightRepository.findByAirportCode(airportCode);
    }

    @Transactional
    public Optional<Flight> findFlightById(Long flightId){
        return flightRepository.findById(flightId);
    }
}
