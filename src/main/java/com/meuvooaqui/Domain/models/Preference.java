package com.meuvooaqui.Domain.models;

import jakarta.persistence.*;

@Entity
@Table(name = Preference.NAME_TABLE)
public class Preference {
    public static final String NAME_TABLE = "preference";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airportCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Preference() {
    }

    public Preference(String airportCode, User user) {
        this.airportCode = airportCode;
        this.user = user;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Preference{" +
                "id=" + id +
                ", airportCode='" + airportCode + '\'' +
                ", user=" + user.getUsername() +
                '}';
    }
}
