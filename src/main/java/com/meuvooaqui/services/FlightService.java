package com.meuvooaqui.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meuvooaqui.Domain.DTOs.FlightDTO;
import com.meuvooaqui.Domain.models.Flight;
import com.meuvooaqui.repositories.FlightRepository;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional
    public FlightDTO addFlight(FlightDTO flightDTO) {
        Flight flight = new Flight(
            flightDTO.getFlightNumber(),
            flightDTO.getOrigin(),
            flightDTO.getDestination(),
            flightDTO.getStatus(),
            flightDTO.getScheduleDeparture(),
            flightDTO.getScheduleArrival()
        );
        flightRepository.save(flight);
        return new FlightDTO(flight);
    }

    @Transactional
    public List<FlightDTO> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream()
                    .map(FlightDTO::new)
                    .toList();
    }

    @Transactional
    public FlightDTO updateFlightStatus(Long flightId, String status) {
        Flight flight = flightRepository.findById(flightId)
                            .orElseThrow(() -> new RuntimeException("Flight Not Found"));
        flight.setStatus(status);
        flightRepository.save(flight);
        return new FlightDTO(flight);
    }

    @Transactional
    public List<FlightDTO> findFlightsByAirport(String airportCode) {
        List<Flight> flights = flightRepository.findByAirportCode(airportCode);
        return flights.stream()
                    .map(FlightDTO::new)
                    .toList();
    }

    @Transactional
    public Optional<FlightDTO> findFlightById(Long flightId) {
        return flightRepository.findById(flightId)
                .map(FlightDTO::new); 
    }
}

