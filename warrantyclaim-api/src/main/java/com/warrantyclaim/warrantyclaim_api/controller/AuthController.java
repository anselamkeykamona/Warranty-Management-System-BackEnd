package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.dto.ApiResponse;
import com.warrantyclaim.warrantyclaim_api.dto.RegisterRequest;
import com.warrantyclaim.warrantyclaim_api.dto.RegisterResponse;
import com.warrantyclaim.warrantyclaim_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
            @Valid @RequestBody RegisterRequest req
    ) {


        RegisterResponse res = service.register(req);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Registration successful",
                        res,
                        null
                ));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        service.deleteUserById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully", null, null));
    }

}
