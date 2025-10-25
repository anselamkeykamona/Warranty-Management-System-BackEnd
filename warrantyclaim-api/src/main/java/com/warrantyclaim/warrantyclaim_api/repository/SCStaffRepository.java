package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.SCStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SCStaffRepository extends JpaRepository<SCStaff, String> {
    Optional<SCStaff> findByEmail(String email);
    void deleteByEmail(String email);

}