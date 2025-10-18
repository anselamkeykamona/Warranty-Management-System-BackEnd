package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.dto.ElectricVehicleResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ElectricVehicleUpdateRequestDTO;
import com.warrantyclaim.warrantyclaim_api.dto.VehicleCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.VehicleDetailInfo;

public interface ElectricVehicleService {
    public VehicleDetailInfo addElectricVehicle(VehicleCreateDTO vehicleCreateDTO);

    public ElectricVehicleResponseDTO updateVehicle(String id, ElectricVehicleUpdateRequestDTO request);

    public ElectricVehicleResponseDTO getVehicleById(String id);

    public void deleteVehicle(String id);
}
