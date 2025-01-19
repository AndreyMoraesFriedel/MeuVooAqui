package com.meuvooaqui.Domain.DTOs;

import com.meuvooaqui.Domain.models.UserFlight;

public class UserFlightDTO {
    private Long id;
    private Long userId;  
    private Long flightId;  

    public UserFlightDTO() {}

    public UserFlightDTO(UserFlight userFlight) {
        this.id = userFlight.getId();
        this.userId = userFlight.getUser().getId();
        this.flightId = userFlight.getFlight().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "UserFlightDTO [id=" + id + ", userId=" + userId + ", flightId=" + flightId + "]";
    }
}
