package com.meuvooaqui.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meuvooaqui.Domain.models.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.origin = :airportCode OR f.destination = :airportCode")
    List<Flight> findByAirportCode(@Param("airportCode") String airportCode);
}
