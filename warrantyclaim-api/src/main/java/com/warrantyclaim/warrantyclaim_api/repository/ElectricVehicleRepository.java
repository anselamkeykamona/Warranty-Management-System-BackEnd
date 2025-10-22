package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricVehicleRepository extends JpaRepository<ElectricVehicle, String> {
    List<ElectricVehicle> findByVehicleType_Id(String vehicleTypeId);
}