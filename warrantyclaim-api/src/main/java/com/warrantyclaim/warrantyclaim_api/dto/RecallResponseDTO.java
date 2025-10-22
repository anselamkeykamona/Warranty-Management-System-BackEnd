package com.warrantyclaim.warrantyclaim_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecallResponseDTO {
    private String id;
    private String name;
    private String issueDescription;
    private LocalDate startDate;
    private String requiredAction;
    private String partsRequired;
    private String status;
    private Boolean notificationSent;
    private String evmApprovalStatus;
}
