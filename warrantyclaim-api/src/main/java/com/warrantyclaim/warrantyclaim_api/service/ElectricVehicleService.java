package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ElectricVehicleService {
    public VehicleDetailInfo addElectricVehicle(VehicleCreateDTO vehicleCreateDTO);

    public ElectricVehicleResponseDTO updateVehicle(String id, ElectricVehicleUpdateRequestDTO request);

    public ElectricVehicleResponseDTO getVehicleById(String id);

    public void deleteVehicle(String id);

    public Page<ElectricVehicleListResponseDTO> getAllVehicles(Pageable pageable);
}
