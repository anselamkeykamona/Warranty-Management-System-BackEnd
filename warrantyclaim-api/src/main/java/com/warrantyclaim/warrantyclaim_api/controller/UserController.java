package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.dto.ChangePasswordRequest;
import com.warrantyclaim.warrantyclaim_api.dto.UpdateUserRequest;
import com.warrantyclaim.warrantyclaim_api.dto.UserResponse;
import com.warrantyclaim.warrantyclaim_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @Valid @RequestBody UpdateUserRequest req,
            @RequestHeader("Authorization") String token
    ) {
        UserResponse res = userService.updateUser(req, token);
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", res));
    }

    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<String>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(ApiResponse.success("Đổi mật khẩu thành công", null));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUserById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        userService.deleteUserByIdWithRoleCheck(userDetails.getUsername(), id);
        return ResponseEntity.ok(ApiResponse.success("Xóa tài khoản thành công", null));
    }



}
