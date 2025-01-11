package com.meuvooaqui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuvooaqui.models.Preference;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long>{

}