package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricVehicleTypeRepository extends JpaRepository<ElectricVehicle, String> {

}
