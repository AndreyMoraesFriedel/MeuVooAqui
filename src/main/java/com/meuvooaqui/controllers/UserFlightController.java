package com.meuvooaqui.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meuvooaqui.Domain.DTOs.UserFlightDTO;
import com.meuvooaqui.services.UserFlightService;

@RestController
@RequestMapping("/user-flights")
public class UserFlightController {

    private final UserFlightService userFlightService;

    public UserFlightController(UserFlightService userFlightService) {
        this.userFlightService = userFlightService;
    }

    @PostMapping
    public ResponseEntity<UserFlightDTO> addFlightToUser(@RequestBody UserFlightDTO userFlightDTO) {
        UserFlightDTO createdUserFlight = userFlightService.addFlightToUser(userFlightDTO.getUserId(), userFlightDTO.getFlightId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserFlight);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserFlightDTO>> getFlightsByUser(@PathVariable Long userId) {
        List<UserFlightDTO> userFlights = userFlightService.getFlightsByUser(userId);
        return ResponseEntity.ok(userFlights);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeFlightFromUser(@RequestParam Long userId, @RequestParam Long flightId) {
        userFlightService.removeFlightFromUser(userId, flightId);
        return ResponseEntity.noContent().build();
    }
}
