package com.meuvooaqui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuvooaqui.models.UserFlight;

@Repository
public interface UserFlightRepository extends JpaRepository<UserFlight, Long>{
    
}
