package com.warrantyclaim.warrantyclaim_api.config;

import com.warrantyclaim.warrantyclaim_api.entity.Role;
import com.warrantyclaim.warrantyclaim_api.entity.User;
import com.warrantyclaim.warrantyclaim_api.enums.Role;
import com.warrantyclaim.warrantyclaim_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepo) {
        return args -> {
            String email = "evmadmin@example.com";
            if (!userRepo.existsByEmail(email)) {
                User admin = new User();
                admin.setUsername("EVM Admin");
                admin.setEmail(email);
                admin.setPassword(BCrypt.hashpw("evmadmin123", BCrypt.gensalt()));
                admin.setRoles(Set.of(Role.EVM_ADMIN));
                userRepo.save(admin);
                System.out.println("EVM_ADMIN account created");
            } else {
                System.out.println("EVM_ADMIN account already exists");
            }
        };
    }
}
