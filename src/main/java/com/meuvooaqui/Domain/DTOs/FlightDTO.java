package com.meuvooaqui.Domain.DTOs;

import java.time.LocalDateTime;

import com.meuvooaqui.Domain.models.Flight;

public class FlightDTO {
    
    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String status;
    private LocalDateTime scheduleDeparture;
    private LocalDateTime scheduleArrival;

    public FlightDTO(Flight flight) {
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNumber();
        this.origin = flight.getOrigin();
        this.destination = flight.getDestination();
        this.status = flight.getStatus();
        this.scheduleDeparture = flight.getScheduleDeparture();
        this.scheduleArrival = flight.getScheduleArrival();
    }

    public FlightDTO() {}

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
        return "FlightDTO [id=" + id + ", flightNumber=" + flightNumber + ", origin=" + origin + ", destination="
                + destination + ", status=" + status + ", scheduleDeparture=" + scheduleDeparture + ", scheduleArrival="
                + scheduleArrival + "]";
    }
}
