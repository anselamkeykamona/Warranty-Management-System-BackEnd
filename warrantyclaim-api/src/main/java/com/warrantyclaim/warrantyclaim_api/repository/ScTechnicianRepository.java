package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.SCTechnician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SCTechnicianRepository extends JpaRepository<SCTechnician, String> {
    Optional<SCTechnician> findByEmail(String email);
}
