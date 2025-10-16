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

}
