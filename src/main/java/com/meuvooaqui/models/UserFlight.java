package com.meuvooaqui.models;

import jakarta.persistence.*;

@Entity
@Table(name = UserFlight.NAME_TABLE)
public class UserFlight {
    public static final String NAME_TABLE = "user_flight";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    public UserFlight(){}

    public UserFlight(User user, Flight flight) {
        this.user = user;
        this.flight = flight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "User_Flight [id=" + id + ", user=" + user + ", flight=" + flight + "]";
    }   
}
