package com.meuvooaqui.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuvooaqui.Domain.DTOs.FlightDTO;
import com.meuvooaqui.services.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {
    
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightDTO>> getAllFlights() {
        List<FlightDTO> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable Long id) {
        Optional<FlightDTO> flightDTO = flightService.findFlightById(id);
        return flightDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FlightDTO> addFlight(@RequestBody FlightDTO flightDTO) {
        FlightDTO createdFlight = flightService.addFlight(flightDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDTO> updateFlightStatus(@PathVariable Long id, @RequestBody String status) {
        FlightDTO updatedFlight = flightService.updateFlightStatus(id, status);
        return ResponseEntity.ok(updatedFlight);
    }

    @GetMapping("/airport/{airportCode}")
    public ResponseEntity<List<FlightDTO>> getFlightsByAirport(@PathVariable String airportCode) {
        List<FlightDTO> flights = flightService.findFlightsByAirport(airportCode);
        return ResponseEntity.ok(flights);
    }
}

