package com.warrantyclaim.warrantyclaim_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class VehicleDetailInfo {
    private String vehicleId;
    private String vehicleName;
    private String vin;
    private Float totalKm;
    private String owner;
    private String phoneNumber;
    private String email;
    private String status;
    private String vehicleTypeName;
    private String modelName;
}
