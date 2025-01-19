package com.meuvooaqui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuvooaqui.Domain.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

} 
