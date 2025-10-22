package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.service.RecallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recalls")
public class RecallController {

    @Autowired
    private RecallService recallService;

    @PostMapping
    public ResponseEntity<RecallResponseDTO> createRecall(@RequestBody RecallCreateDTO dto) {
        return ResponseEntity.ok(recallService.createRecall(dto));
    }

    @GetMapping("/{id}/vehicles")
    public ResponseEntity<List<VehicleRecallDTO>> getAffectedVehicles(@PathVariable String id) {
        return ResponseEntity.ok(recallService.getAffectedVehicles(id));
    }

    @PostMapping("/assign-technician")
    public ResponseEntity<?> assignTechnician(@RequestBody TechnicianAssignDTO dto) {
        recallService.assignTechnician(dto);
        return ResponseEntity.ok("Technician assigned");
    }

    @PutMapping("/update-status")
    public ResponseEntity<?> updateStatus(@RequestBody RecallStatusUpdateDTO dto) {
        recallService.updateStatus(dto);
        return ResponseEntity.ok("Status updated");
    }
}
