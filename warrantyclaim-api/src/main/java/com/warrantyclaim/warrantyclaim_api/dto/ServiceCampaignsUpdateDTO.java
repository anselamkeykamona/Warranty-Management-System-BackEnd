package com.warrantyclaim.warrantyclaim_api.dto;

import com.warrantyclaim.warrantyclaim_api.enums.ServiceCampaignsStatus;

import java.time.LocalDate;
import java.util.List;

public class ServiceCampaignsUpdateDTO {

    private String campaignsTypeName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String requiredParts;

    private String description;

    private ServiceCampaignsStatus status;

    private Boolean notificationSent;

    // IDs for relationships
    private List<String> vehicleTypeIds;
    private List<String> technicianIds;
}
