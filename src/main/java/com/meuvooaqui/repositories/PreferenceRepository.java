package com.meuvooaqui.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuvooaqui.models.Preference;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long>{

    List<Preference> findByUserId(Long userId);

    Optional<Preference> findByUserIdAndAirportCode(Long userId, String airportCode);
}