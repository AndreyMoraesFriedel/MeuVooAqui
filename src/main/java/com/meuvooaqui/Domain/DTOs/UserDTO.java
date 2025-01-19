package com.meuvooaqui.Domain.DTOs;

import com.meuvooaqui.Domain.models.User;

public class UserDTO {
    
    private Long id;
    private String email;
    private String username;  

    public UserDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }

    public UserDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", email=" + email + ", username=" + username + "]";
    }
}
