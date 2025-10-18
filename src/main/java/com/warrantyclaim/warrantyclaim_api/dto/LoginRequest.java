package com.warrantyclaim.warrantyclaim_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message = "Email format is invalid")
        @NotBlank(message = "Email is not null")
        String email,

        @NotBlank(message = "Password is required")
        String password
){}
