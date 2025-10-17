package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.dto.UpdateUserRequest;
import com.warrantyclaim.warrantyclaim_api.dto.UserResponse;
import com.warrantyclaim.warrantyclaim_api.entity.User;
import com.warrantyclaim.warrantyclaim_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final JwtService jwtService;

    public UserService(UserRepository userRepo, JwtService jwtService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    @Transactional
    public UserResponse updateUser(UpdateUserRequest req, String token) {
        String emailFromToken = jwtService.extractUserName(token.replace("Bearer ", ""));

        User user = userRepo.findByEmail(emailFromToken)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());

        User updatedUser = userRepo.save(user);

        Set<String> roleNames = updatedUser.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getUsernameDisplay(),
                updatedUser.getEmail(),
                roleNames
        );
    }

    @Transactional
    public void deleteUserByIdWithRoleCheck(String requesterEmail, Long targetUserId) {
        User requester = userRepo.findByEmail(requesterEmail)
                .orElseThrow(() -> new IllegalArgumentException("Người gọi không tồn tại"));

        User target = userRepo.findById(targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("Người bị xóa không tồn tại"));

        Set<String> requesterRoles = requester.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());

        Set<String> targetRoles = target.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());


        if (requesterRoles.contains("EVM_ADMIN")) {
            if (targetRoles.contains("EVM_ADMIN")) {
                throw new RuntimeException("Không thể xóa EVM_ADMIN khác");
            }
        } else if (requesterRoles.contains("SC_ADMIN")) {
            for (String role : targetRoles) {
                if (!role.equals("SC_STAFF") && !role.equals("SC_TECHNICAL")) {
                    throw new RuntimeException("SC_ADMIN chỉ được xóa SC_STAFF hoặc SC_TECHNICAL");
                }
            }
        } else {
            throw new RuntimeException("Bạn không có quyền xóa tài khoản");
        }

        userRepo.deleteById(targetUserId);
    }


}
