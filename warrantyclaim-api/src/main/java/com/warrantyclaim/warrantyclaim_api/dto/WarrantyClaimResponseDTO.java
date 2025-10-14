package com.warrantyclaim.warrantyclaim_api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarrantyClaimResponseDTO {
    private String claimId;
    private String customerName;
    private String customerPhone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate claimDate;

    private String issueDescription;
    private String status;
    private String email;

    // Nested objects for related entities
    private VehicleBasicInfoDTO vehicle;
//    private StaffBasicInfoDTO assignedStaff;
}