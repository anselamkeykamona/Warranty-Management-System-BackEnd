package com.warrantyclaim.warrantyclaim_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeInfoDTO {
    private String id;
    private String description;
    private String modelName;
    private Integer yearModelYear;
    private String batteryType;
    private Float price;
}
