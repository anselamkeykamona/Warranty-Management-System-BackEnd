package com.warrantyclaim.warrantyclaim_api.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.warrantyclaim.warrantyclaim_api.enums.Role;

import java.util.Set;

public class RegisterRequest {


    @NotBlank(message = " Username is required")
    private String username;

    @Size(min = 8, message = "Pass word must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 1, message = "At least one role is required")// user ko gui null/set rong
    private Set<Role> roles; // de ko trung role

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String createdByEmail;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String email, Set<Role> roles, String createdByEmail) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.createdByEmail = createdByEmail;
    }

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        this.createdByEmail = createdByEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
