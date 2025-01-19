package com.meuvooaqui.Domain.DTOs;

import java.time.LocalDateTime;

import com.meuvooaqui.Domain.models.Notification;

public class NotificationDTO {

    private Long id;
    private String message;
    private LocalDateTime timestamp;
    private Long userFlightId;

    public NotificationDTO() {}

    public NotificationDTO(Notification notification) {
        this.id = notification.getId();
        this.message = notification.getMessage();
        this.timestamp = notification.getTimestamp();
        this.userFlightId = notification.getUserFlight().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getUserFlightId() {
        return userFlightId;
    }

    public void setUserFlightId(Long userFlightId) {
        this.userFlightId = userFlightId;
    }

    @Override
    public String toString() {
        return "NotificationDTO [id=" + id + ", message=" + message + ", timestamp=" + timestamp + ", userFlightId="
                + userFlightId + "]";
    }
}
