package com.meuvooaqui.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meuvooaqui.models.Flight;
import com.meuvooaqui.models.User;
import com.meuvooaqui.models.UserFlight;
import com.meuvooaqui.repositories.UserFlightRepository;

@Service
public class UserFlightService {
    private final UserFlightRepository userFlightRepository;
    
    public UserFlightService(UserFlightRepository userFlightRepository) {
        this.userFlightRepository = userFlightRepository;
    }

    @Transactional
    public UserFlight addFlightToUser(User user, Flight flight){
        UserFlight userFlight = new UserFlight(user, flight);
        return userFlightRepository.save(userFlight);
    }

    @Transactional
    public List<UserFlight> getFlightsByUser(Long userId){
        return userFlightRepository.findByUserId(userId);
    }

    @Transactional
    public void removeFlightFromUser(Long userId, Long flightId){
        UserFlight userflight = userFlightRepository.findByUserIdAndFlightId(userId, flightId)
        .orElseThrow(() -> new RuntimeException("UserFlight Not Found"));
        userFlightRepository.delete(userflight);
    }
}
