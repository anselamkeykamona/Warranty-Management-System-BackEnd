package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.dto.*;

import java.util.List;

public interface RecallService {
    RecallResponseDTO createRecall(RecallCreateDTO dto);
    List<VehicleRecallDTO> getAffectedVehicles(String recallId);
    void assignTechnician(TechnicianAssignDTO dto);
    void updateStatus(RecallStatusUpdateDTO dto);
}

