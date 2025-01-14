package com.meuvooaqui.models;

import jakarta.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "user_flight_id", nullable = false)
    private UserFlight userFlight;

    public Notification() {}

    public Notification(String message, String timestamp, UserFlight userFlight) {
        this.message = message;
        this.timestamp = timestamp;
        this.userFlight = userFlight;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public UserFlight getUserFlight() {
        return userFlight;
    }

    public void setUserFlight(UserFlight userFlight) {
        this.userFlight = userFlight;
    }

    @Override
    public String toString() {
        return "Notification [id=" + id + ", message=" + message + ", timestamp=" + timestamp + ", userFlight="
                + userFlight + "]";
    }
}
