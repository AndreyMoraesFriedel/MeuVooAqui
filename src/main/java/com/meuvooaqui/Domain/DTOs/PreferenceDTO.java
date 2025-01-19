package com.meuvooaqui.Domain.DTOs;

import com.meuvooaqui.Domain.models.Preference;

public class PreferenceDTO {
    
    private Long id;
    private String airportCode;
    private Long userId;

    public PreferenceDTO(Preference preference) {
        this.id = preference.getId();
        this.airportCode = preference.getAirportCode();
        this.userId = preference.getUser().getId();
    }

    public PreferenceDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PreferenceDTO [id=" + id + ", airportCode=" + airportCode + ", userId=" + userId + "]";
    }
}

