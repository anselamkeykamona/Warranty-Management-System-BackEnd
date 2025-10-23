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
public class RecallResponseDTO {
    private String id;
    private String name;
    private String issueDescription;
    private LocalDate startDate;

    private String requiredAction;

    private String partsRequired;
    private RecallStatus status;
    private Boolean notificationSent;
    private EvmApprovalStatus evmApprovalStatus;

    private List<VehicleTypeInfoDTO> vehicleTypeInfoDTOS;
    private List<VehicleBasicInfoDTO> vehicleBasicInfoDTOS;
    private List<TechnicianBasicDTO> technicianBasicDTOS;
}
