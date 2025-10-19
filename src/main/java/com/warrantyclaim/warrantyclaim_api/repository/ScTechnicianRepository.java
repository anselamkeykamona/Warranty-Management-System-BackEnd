package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.SCTechnician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScTechnicianRepository extends JpaRepository<SCTechnician, String> {
}
