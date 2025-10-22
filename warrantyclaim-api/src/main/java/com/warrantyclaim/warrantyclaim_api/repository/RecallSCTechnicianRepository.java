package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.RecallSCTechnician;
import com.warrantyclaim.warrantyclaim_api.entity.RecallSCTechnicianId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecallSCTechnicianRepository extends JpaRepository<RecallSCTechnician, RecallSCTechnicianId> {
    List<RecallSCTechnician> findByRecallId(String recallId);
}
