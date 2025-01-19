package com.meuvooaqui.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuvooaqui.Domain.models.UserFlight;

@Repository
public interface UserFlightRepository extends JpaRepository<UserFlight, Long>{
    
    List<UserFlight> findByUserId(Long userId);

    Optional<UserFlight> findByUserIdAndFlightId(Long userId, Long flightId);
}
