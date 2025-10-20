package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.service.ServiceCampaignsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Configure as needed
@RequestMapping("/api/ServiceCampaigns")
public class ServiceCampaignsController {

    private final ServiceCampaignsService serviceCampaignsService;

    @PostMapping
    public ResponseEntity<ServiceCampaignsResponseDTO> createServiceCampaigns(@RequestBody ServiceCampaignsRequestDTO requestDTO) {
        ServiceCampaignsResponseDTO responseDTO = serviceCampaignsService.createServiceCampaigns(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}/dates")
    public ResponseEntity<ServiceCampaignsResponseDTO> updateCampaignDate(
            @PathVariable String id,
            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        ServiceCampaignsResponseDTO response = serviceCampaignsService.updateDate(id, startDate, endDate);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCampaignsResponseDTO> getCampaignById(@PathVariable String id) {
        ServiceCampaignsResponseDTO response = serviceCampaignsService.getCampaignById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ServiceCampaignsListDTO>> getAllCampaigns(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "startDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ServiceCampaignsListDTO> response = serviceCampaignsService.getAllCampaigns(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceCampaignsResponseDTO> updateCampaign(
            @PathVariable String id,
            @Valid @RequestBody ServiceCampaignsUpdateDTO request) {
        ServiceCampaignsResponseDTO response = serviceCampaignsService.updateCampaign(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable String id) {
        serviceCampaignsService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/vehicle-types")
    public ResponseEntity<ServiceCampaignsResponseDTO> assignVehicleTypes(
            @PathVariable String id,
            @Valid @RequestBody AssignVehicleTypesDTO request) {
        ServiceCampaignsResponseDTO response = serviceCampaignsService.assignVehicleTypes(id, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/vehicle-types/{vehicleTypeId}")
    public ResponseEntity<ServiceCampaignsResponseDTO> addVehicleType(
            @PathVariable String id,
            @PathVariable String vehicleTypeId) {
        ServiceCampaignsResponseDTO response = serviceCampaignsService.addVehicleType(id, vehicleTypeId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/vehicle-types/{vehicleTypeId}")
    public ResponseEntity<ServiceCampaignsResponseDTO> removeVehicleType(
            @PathVariable String id,
            @PathVariable String vehicleTypeId) {
        ServiceCampaignsResponseDTO response = serviceCampaignsService.removeVehicleType(id, vehicleTypeId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/technicians")
    public ResponseEntity<ServiceCampaignsResponseDTO> assignTechnicians(
            @PathVariable String id,
            @Valid @RequestBody AssignTechniciansDTO request) {
        ServiceCampaignsResponseDTO response = serviceCampaignsService.assignTechnicians(id, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/technicians/{technicianId}")
    public ResponseEntity<ServiceCampaignsResponseDTO> addTechnician(
            @PathVariable String id,
            @PathVariable String technicianId) {
        ServiceCampaignsResponseDTO response = serviceCampaignsService.addTechnician(id, technicianId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/technicians/{technicianId}")
    public ResponseEntity<ServiceCampaignsResponseDTO> removeTechnician(
            @PathVariable String id,
            @PathVariable String technicianId) {
        ServiceCampaignsResponseDTO response = serviceCampaignsService.removeTechnician(id, technicianId);
        return ResponseEntity.ok(response);
    }


}
