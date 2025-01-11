package com.meuvooaqui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuvooaqui.models.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

    
}
