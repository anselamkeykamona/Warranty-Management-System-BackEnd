package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicleTypeRecall;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicleTypeRecallId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectricVehicleTypeRecallRepository extends JpaRepository<ElectricVehicleTypeRecall, ElectricVehicleTypeRecallId> {
    List<ElectricVehicleTypeRecall> findByRecallId(String recallId);
}
