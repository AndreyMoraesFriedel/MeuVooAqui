package com.meuvooaqui.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meuvooaqui.Domain.DTOs.NotificationDTO;
import com.meuvooaqui.Domain.models.Notification;
import com.meuvooaqui.Domain.models.UserFlight;
import com.meuvooaqui.repositories.NotificationRepository;
import com.meuvooaqui.repositories.UserFlightRepository;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserFlightRepository userFlightRepository;

    public NotificationService(NotificationRepository notificationRepository,
            UserFlightRepository userFlightRepository) {
        this.notificationRepository = notificationRepository;
        this.userFlightRepository = userFlightRepository;
    }

    @Transactional
    public NotificationDTO addFlightNotification(String message, LocalDateTime timestamp, Long userFlightId) {
        UserFlight userFlight = userFlightRepository.findById(userFlightId)
                            .orElseThrow(() -> new RuntimeException("UserFlight Not Found"));
        Notification notification = new Notification(message, timestamp, userFlight);
        notification = notificationRepository.save(notification);
        return new NotificationDTO(notification);
    }

    @Transactional
    public List<NotificationDTO> getNotificationsByUser(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserFlight_UserId(userId);
        return notifications.stream()
                .map(NotificationDTO::new)
                .toList();
    }

    @Transactional
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
