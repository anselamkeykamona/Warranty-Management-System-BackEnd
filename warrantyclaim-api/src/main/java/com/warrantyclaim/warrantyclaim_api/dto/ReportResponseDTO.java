package com.warrantyclaim.warrantyclaim_api.dto;

import com.warrantyclaim.warrantyclaim_api.enums.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {
    private String id;
    private String reportName;
    private String description;
    private String error;
    private String image;
    private ReportStatus status;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private String campaignId;
    private String campaignName;
    private String recallId;
    private String recallTitle;
    private String warrantyClaimId;
    private String staffId;
    private String staffName;
    private String evmStaffId;
    private String evmStaffName;
}