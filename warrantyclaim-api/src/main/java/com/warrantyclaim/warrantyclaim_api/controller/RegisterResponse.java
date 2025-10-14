package com.warrantyclaim.warrantyclaim_api.controller;
import java.util.Set;
public class RegisterResponse {

    private Long id;
    private String username;
    private String email;
    private Set<String> roles;

    public RegisterResponse() {
    }

    public RegisterResponse(Long id, String username, String emai, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.email = emai;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emai) {
        this.email = emai;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
