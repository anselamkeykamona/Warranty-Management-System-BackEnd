package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.dto.ServiceCampaignsRequestDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ServiceCampaignsResponseDTO;
import com.warrantyclaim.warrantyclaim_api.service.ServiceCampaignsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Configure as needed
@RequestMapping("/api/ServiceCampaigns")
public class ServiceCampaignsController {

    private final ServiceCampaignsService service;

    @PostMapping
    public ResponseEntity<ServiceCampaignsResponseDTO> createServiceCampaigns(@RequestBody ServiceCampaignsRequestDTO requestDTO) {
        ServiceCampaignsResponseDTO responseDTO = service.createServiceCampaigns(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
