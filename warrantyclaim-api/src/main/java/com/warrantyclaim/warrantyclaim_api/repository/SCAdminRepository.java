package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.SCAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SCAdminRepository extends JpaRepository<SCAdmin, String> {
    Optional<SCAdmin> findByEmail(String email);
    void deleteByEmail(String email);

}

