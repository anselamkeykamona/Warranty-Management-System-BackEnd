package com.warrantyclaim.warrantyclaim_api.dto;

import com.warrantyclaim.warrantyclaim_api.enums.Role;

import java.util.Set;

public record LoginResponse(
        String token,
        String username,
        Set<Role> roles
) {}
