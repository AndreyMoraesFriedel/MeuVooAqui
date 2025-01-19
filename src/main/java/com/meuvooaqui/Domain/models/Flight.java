package com.meuvooaqui.Domain.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = Flight.NAME_TABLE)
public class Flight {
    public static final String NAME_TABLE = "flight";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String origin;
    private String destination;
    private String status;

    private LocalDateTime scheduleDeparture;
    private LocalDateTime scheduleArrival;

    public Flight() {
    }

    public Flight(String flightNumber, String origin, String destination, String status, LocalDateTime scheduleDeparture,
    LocalDateTime scheduleArrival) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
        this.scheduleDeparture = scheduleDeparture;
        this.scheduleArrival = scheduleArrival;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getScheduleDeparture() {
        return scheduleDeparture;
    }

    public void setScheduleDeparture(LocalDateTime scheduleDeparture) {
        this.scheduleDeparture = scheduleDeparture;
    }

    public LocalDateTime getScheduleArrival() {
        return scheduleArrival;
    }

    public void setScheduleArrival(LocalDateTime scheduleArrival) {
        this.scheduleArrival = scheduleArrival;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", status='" + status + '\'' +
                ", scheduleDeparture=" + scheduleDeparture + 
                ", scheduleArrival=" + scheduleArrival +
                '}';
    }
}
