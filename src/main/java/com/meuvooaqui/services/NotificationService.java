package com.meuvooaqui.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meuvooaqui.models.Notification;
import com.meuvooaqui.models.UserFlight;
import com.meuvooaqui.repositories.NotificationRepository;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public Notification addFlightNotification(String message, LocalDateTime timestamp, UserFlight userFlight){
        Notification notification = new Notification(message, timestamp, userFlight);
        return notificationRepository.save(notification);
    }

    @Transactional
    public List<Notification> getNotificationsByUser(Long userId) {
        return notificationRepository.findByUserFlight_UserId(userId);
    }

    @Transactional
    public void deleteNotification(Long id){
        notificationRepository.deleteById(id);
    }
}
