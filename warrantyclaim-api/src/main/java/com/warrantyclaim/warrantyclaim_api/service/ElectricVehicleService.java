package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.dto.VehicleCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.VehicleDetailInfo;

public interface ElectricVehicleService {
    public VehicleDetailInfo addElectricVehicle(VehicleCreateDTO vehicleCreateDTO);

}
