package com.warrantyclaim.warrantyclaim_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.warrantyclaim.warrantyclaim_api.enums.WarrantyClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarrantyClaimListResponseDTO {
    private String claimId;
    private String customerName;
    private String customerPhone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate claimDate;

    private WarrantyClaimStatus status;
    private String vehicleName;
}
