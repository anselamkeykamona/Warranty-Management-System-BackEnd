package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.RecallElectricVehicle;
import com.warrantyclaim.warrantyclaim_api.entity.RecallElectricVehicleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecallElectricVehicleRepository extends JpaRepository<RecallElectricVehicle, RecallElectricVehicleId> {
    List<RecallElectricVehicle> findByRecallId(String recallId);
}
