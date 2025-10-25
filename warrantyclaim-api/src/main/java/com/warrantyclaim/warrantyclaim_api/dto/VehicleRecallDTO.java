package com.warrantyclaim.warrantyclaim_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRecallDTO {
    private String vehicleId;
    private String vehicleName;
    private String owner;
    private String phoneNumber;
    private String email;
}
