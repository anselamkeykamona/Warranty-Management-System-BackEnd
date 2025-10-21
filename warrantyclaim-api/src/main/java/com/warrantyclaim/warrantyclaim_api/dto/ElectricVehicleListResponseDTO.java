package com.warrantyclaim.warrantyclaim_api.dto;

import com.warrantyclaim.warrantyclaim_api.enums.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectricVehicleListResponseDTO {
    private String id;  // VIN
    private String name;
    private Float totalKm;
    private String owner;
    private String phoneNumber;
    private VehicleStatus status;

    // Simplified vehicle type info
    private String vehicleTypeId;
    private String modelName;
}
