package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.dto.WarrantyClaimCreateRequestDTO;
import com.warrantyclaim.warrantyclaim_api.dto.WarrantyClaimListResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.WarrantyClaimResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.WarrantyClaimUpdateRequestDTO;
import com.warrantyclaim.warrantyclaim_api.enums.WarrantyClaimStatus;
import com.warrantyclaim.warrantyclaim_api.service.WarrantyClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Configure as needed
@RequestMapping("/api/WarrantyClaim")
public class WarrantyClaimController {

    private final WarrantyClaimService warrantyClaimService;

    @PostMapping
    public ResponseEntity<WarrantyClaimResponseDTO> createWarrantyClaim(@RequestBody WarrantyClaimCreateRequestDTO warrantyClaimCreateRequestDTO) {
        System.out.println("✅ Controller reached: ");
        WarrantyClaimResponseDTO saveWarrantyClaim = warrantyClaimService.createWarrantyClaim(warrantyClaimCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveWarrantyClaim);
    }

    @GetMapping
    public ResponseEntity<Page<WarrantyClaimListResponseDTO>> getAllClaims(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "claimDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<WarrantyClaimListResponseDTO> response = warrantyClaimService.getAllClaims(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{claimId}")
    public ResponseEntity<WarrantyClaimResponseDTO> updateClaim(
            @PathVariable String claimId,
            @RequestBody WarrantyClaimUpdateRequestDTO request) {
        WarrantyClaimResponseDTO response = warrantyClaimService.updateClaim(claimId, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{claimId}/status")
    public ResponseEntity<WarrantyClaimResponseDTO> updateClaimStatus(
            @PathVariable String claimId,
            @RequestParam WarrantyClaimStatus status) {
        WarrantyClaimResponseDTO response = warrantyClaimService.updateClaimStatus(claimId, status);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{claimId}/required_part")
    public ResponseEntity<WarrantyClaimResponseDTO> updateClaimRequiredPart(
            @PathVariable String claimId,
            @RequestParam String requiredPart) {
        WarrantyClaimResponseDTO response = warrantyClaimService.updateRequiredPart(claimId, requiredPart);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{claimId}/assign-tech")
    public ResponseEntity<WarrantyClaimResponseDTO> assignStaffToClaim(
            @PathVariable String claimId,
            @RequestParam String scTechId) {
        WarrantyClaimResponseDTO response = warrantyClaimService.assignScTech(claimId, scTechId);
        return ResponseEntity.ok(response);
    }
}
