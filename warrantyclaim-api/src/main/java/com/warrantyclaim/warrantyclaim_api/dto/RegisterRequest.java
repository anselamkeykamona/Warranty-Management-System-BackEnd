package com.warrantyclaim.warrantyclaim_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.warrantyclaim.warrantyclaim_api.enums.Role;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username must not exceed 100 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotNull(message = "At least one role is required")
    @Size(min = 1, message = "At least one role is required")
    private Set<Role> roles;

    @NotBlank(message = "Creator email is required")
    @Email(message = "Invalid creator email format")
    @Size(max = 100, message = "Creator email must not exceed 100 characters")
    private String createdByEmail;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Branch office is required")
    @Size(max = 150, message = "Branch office must not exceed 150 characters")
    private String branchOffice;

    @Size(max = 100, message = "Specialty must not exceed 100 characters")
    private String specialty;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

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

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        this.createdByEmail = createdByEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(String branchOffice) {
        this.branchOffice = branchOffice;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
