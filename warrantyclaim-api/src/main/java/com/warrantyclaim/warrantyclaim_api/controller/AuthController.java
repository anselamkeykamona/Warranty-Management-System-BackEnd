package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;


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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest req) {
        LoginResponse res = service.login(req);
        return ResponseEntity.ok(ApiResponse.success("Login successful", res));
    }



//    @PostMapping("/logout")
//    public ResponseEntity<ApiResponse<Void>> logout() {
//        return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
//    }

//    @PutMapping("/change-password")
//    public ResponseEntity<ApiResponse<String>> changePassword(
//            @Valid @RequestBody ChangePasswordRequest request,
//            @AuthenticationPrincipal UserDetails userDetails
//    ) {
//        service.changePassword(userDetails.getUsername(), request);
//        return ResponseEntity.ok(ApiResponse.success("Đổi mật khẩu thành công", null));
//    }
//




}
