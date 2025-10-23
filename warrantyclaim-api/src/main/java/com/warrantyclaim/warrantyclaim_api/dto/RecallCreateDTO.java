package com.warrantyclaim.warrantyclaim_api.dto;

import com.warrantyclaim.warrantyclaim_api.enums.EvmApprovalStatus;
import com.warrantyclaim.warrantyclaim_api.enums.RecallStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Needed name for recall!!!")
    private String name;

    @NotBlank(message = "Need to describe issue to solve!!!")
    private String issueDescription;

    @NotNull(message = "Start date is required for recall!!!")
    private LocalDate startDate;

    @NotBlank(message = "What need to be done for fixing!!!")
    private String requiredAction;

    @NotBlank(message = "Part needed to fix the issue!!!")
    private String partsRequired;


    private RecallStatus status;

    private Boolean notificationSent;

    private EvmApprovalStatus evmApprovalStatus;


    private List<String> vehicleTypeIds;
    private List<String> technicianIds;
    private List<String> vehicleId;// Gắn loại xe vào chiến dịch
}

