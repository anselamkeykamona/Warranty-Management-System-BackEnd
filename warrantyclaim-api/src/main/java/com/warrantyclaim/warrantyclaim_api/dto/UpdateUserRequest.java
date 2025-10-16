package com.warrantyclaim.warrantyclaim_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class UpdateUserRequest {

    @NotBlank(message = "Username must not be blank")
    private String username;

    @Email(message = "Invalid email format")
    private String email;

    // Getters & Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
