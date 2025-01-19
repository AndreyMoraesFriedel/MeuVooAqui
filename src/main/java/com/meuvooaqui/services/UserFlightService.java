package com.meuvooaqui.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meuvooaqui.Domain.DTOs.UserFlightDTO;
import com.meuvooaqui.Domain.models.Flight;
import com.meuvooaqui.Domain.models.User;
import com.meuvooaqui.Domain.models.UserFlight;
import com.meuvooaqui.repositories.FlightRepository;
import com.meuvooaqui.repositories.UserFlightRepository;
import com.meuvooaqui.repositories.UserRepository;

@Service
public class UserFlightService {
    private final UserFlightRepository userFlightRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    public UserFlightService(UserFlightRepository userFlightRepository, UserRepository userRepository,
            FlightRepository flightRepository) {
        this.userFlightRepository = userFlightRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }

    @Transactional
    public UserFlightDTO addFlightToUser(Long userId, Long flightId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight Not Found"));
        UserFlight userFlight = new UserFlight(user, flight);
        userFlight = userFlightRepository.save(userFlight);
        return new UserFlightDTO(userFlight);
    }

    @Transactional
    public List<UserFlightDTO> getFlightsByUser(Long userId) {
        List<UserFlight> userFlights = userFlightRepository.findByUserId(userId);
        return userFlights.stream()
                .map(UserFlightDTO::new)
                .toList();
    }

    @Transactional
    public void removeFlightFromUser(Long userId, Long flightId) {
        UserFlight userFlight = userFlightRepository.findByUserIdAndFlightId(userId, flightId)
                .orElseThrow(() -> new RuntimeException("UserFlight Not Found"));
        userFlightRepository.delete(userFlight);
    }
}
