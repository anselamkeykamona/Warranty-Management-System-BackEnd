package com.warrantyclaim.warrantyclaim_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecallCreateDTO {
    private String id;
    private String name;
    private String issueDescription;
    private LocalDate startDate;
    private String requiredAction;
    private String partsRequired;
    private String status;
    private Boolean notificationSent;
    private String evmApprovalStatus;
    private List<String> vehicleTypeIds; // Gắn loại xe vào chiến dịch
}

