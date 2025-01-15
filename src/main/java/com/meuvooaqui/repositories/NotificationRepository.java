package com.meuvooaqui.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuvooaqui.models.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

    List<Notification> findByUserFlight_UserId(Long userId);
}
