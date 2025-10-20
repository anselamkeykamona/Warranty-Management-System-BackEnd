package com.warrantyclaim.warrantyclaim_api.dto;

import com.warrantyclaim.warrantyclaim_api.enums.ServiceCampaignsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCampaignsListDTO {

    private String campaignsId;
    private String campaignsTypeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private ServiceCampaignsStatus status;
    private Boolean notificationSent;

    // Count of relationships
    private Integer vehicleTypeCount;
    private Integer technicianCount;
}
