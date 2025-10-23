package com.warrantyclaim.warrantyclaim_api.dto;

import java.time.LocalDate;
import java.util.Set;

public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String branchOffice;
    private LocalDate dateOfBirth;
    private Set<String> roles;

    public UserResponse(Long id, String username, String email,
                        String phoneNumber, String branchOffice,
                        LocalDate dateOfBirth, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.branchOffice = branchOffice;
        this.dateOfBirth = dateOfBirth;
        this.roles = roles;
    }

    // Getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getBranchOffice() { return branchOffice; }
    public void setBranchOffice(String branchOffice) { this.branchOffice = branchOffice; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}
