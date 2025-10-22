package com.warrantyclaim.warrantyclaim_api.mapper;

import com.warrantyclaim.warrantyclaim_api.dto.RecallCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.RecallResponseDTO;
import com.warrantyclaim.warrantyclaim_api.entity.Recall;

public class RecallMapper {
    public static Recall toEntity(RecallCreateDTO dto) {
        Recall recall = new Recall();
        recall.setId(dto.getId());
        recall.setName(dto.getName());
        recall.setIssueDescription(dto.getIssueDescription());
        recall.setStartDate(dto.getStartDate());
        recall.setRequiredAction(dto.getRequiredAction());
        recall.setPartsRequired(dto.getPartsRequired());
        recall.setStatus(dto.getStatus());
        recall.setNotificationSent(dto.getNotificationSent());
        recall.setEvmApprovalStatus(dto.getEvmApprovalStatus());
        return recall;
    }

    public static RecallResponseDTO toDTO(Recall recall) {
        return new RecallResponseDTO(
                recall.getId(),
                recall.getName(),
                recall.getIssueDescription(),
                recall.getStartDate(),
                recall.getRequiredAction(),
                recall.getPartsRequired(),
                recall.getStatus(),
                recall.getNotificationSent(),
                recall.getEvmApprovalStatus()
        );
    }
}

