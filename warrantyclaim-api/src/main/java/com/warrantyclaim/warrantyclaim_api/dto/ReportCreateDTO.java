package com.warrantyclaim.warrantyclaim_api.dto;

import com.warrantyclaim.warrantyclaim_api.enums.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportCreateDTO {
    private String reportName;
    private String description;
    private String error;
    private String image;
    private ReportStatus status;
    private String staffId;
    private String evmStaffId;
}