package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import com.warrantyclaim.warrantyclaim_api.entity.SCTechnician;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyClaim;
import com.warrantyclaim.warrantyclaim_api.enums.WarrantyClaimStatus;
import com.warrantyclaim.warrantyclaim_api.exception.ResourceNotFoundException;
import com.warrantyclaim.warrantyclaim_api.mapper.WarrantyClaimMapper;
import com.warrantyclaim.warrantyclaim_api.repository.ElectricVehicleRepository;
import com.warrantyclaim.warrantyclaim_api.repository.ScTechnicianRepository;
import com.warrantyclaim.warrantyclaim_api.repository.WarrantyClaimRepository;
import com.warrantyclaim.warrantyclaim_api.service.WarrantyClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarrantyClaimServiceImp implements WarrantyClaimService {

    private final WarrantyClaimRepository warrantyClaimRepository;
    private final ElectricVehicleRepository electricVehicleRepository;
    private final ScTechnicianRepository scTechnicianRepository;
    private final WarrantyClaimMapper warrantyClaimMapper;

    @Override
    public WarrantyClaimResponseDTO createWarrantyClaim(WarrantyClaimCreateRequestDTO warrantyClaimRequest) {

        ElectricVehicle electricVehicle = electricVehicleRepository.findById(warrantyClaimRequest.getVehicleId()) // for testing exception
                .orElseThrow(() -> new ResourceNotFoundException("There is no Electric Vehicle with this ID!!!"));

        // Validate staff if provided
//        ScStaff staff = null;
//        if (warrantyClaimRequest.getScStaffId() != null && !warrantyClaimRequest.getScStaffId().isEmpty()) {
//            staff = scStaffRepository.findById(warrantyClaimRequest.getScStaffId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + warrantyClaimRequest.getScStaffId()));
//        }
        WarrantyClaim warrantyClaim = warrantyClaimMapper.toEntityWarrantyClaim(warrantyClaimRequest);
        warrantyClaim.setId(generateClaimId());
        warrantyClaim.setVehicle(electricVehicle);
        warrantyClaim.setStatus(WarrantyClaimStatus.PENDING);
        warrantyClaimRepository.save(warrantyClaim);

        return warrantyClaimMapper.toResponseWarrantyClaim(warrantyClaim);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WarrantyClaimListResponseDTO> getAllClaims(Pageable pageable) {
        Page<WarrantyClaim> claims = warrantyClaimRepository.findAll(pageable);
        return claims.map(warrantyClaimMapper::toListResponse);
    }

    @Override
    public WarrantyClaimResponseDTO updateClaim(String claimId, WarrantyClaimUpdateRequestDTO request) {
        WarrantyClaim claim = warrantyClaimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Warranty claim not found with ID: " + claimId));
        ElectricVehicle electricVehicle = electricVehicleRepository.findById(request.getElectricVehicleId()) // for testing exception
                .orElseThrow(() -> new ResourceNotFoundException("There is no Electric Vehicle with this ID!!!"));


        // Update staff if provided
//        if (request.getScStaffId() != null && !request.getScStaffId().isEmpty()) {
//            SCStaff staff = scStaffRepository.findById(request.getScStaffId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + request.getScStaffId()));
//            claim.setScStaff(staff);
//        }

        // Update other fields
        warrantyClaimMapper.updateEntity(claim, request);
        claim.setVehicle(electricVehicle);
        WarrantyClaim updatedClaim = warrantyClaimRepository.save(claim);
        return warrantyClaimMapper.toResponseWarrantyClaim(updatedClaim);
    }

    private String generateClaimId() {
        return "WC-" + LocalDate.now().getYear() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    public WarrantyClaimDetailResponseDTO getClaimById(String claimId) {

        WarrantyClaim claim = warrantyClaimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Warranty claim not found with ID: " + claimId));

        return warrantyClaimMapper.toDetailResponse(claim);
    }

    @Override
    public WarrantyClaimResponseDTO updateClaimStatus(String claimId, WarrantyClaimStatus status) {
        WarrantyClaim claim = warrantyClaimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Warranty claim not found with ID: " + claimId));

        claim.setStatus(status);
        WarrantyClaim updatedClaim = warrantyClaimRepository.save(claim);

        return warrantyClaimMapper.toResponseWarrantyClaim(updatedClaim);
    }

    @Override
    public WarrantyClaimResponseDTO updateRequiredPart(String claimId, String requiredPart) {
        WarrantyClaim claim = warrantyClaimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Warranty claim not found with ID: " + claimId));

        claim.setRequiredParts(requiredPart);
        WarrantyClaim updatedClaim = warrantyClaimRepository.save(claim);

        return warrantyClaimMapper.toResponseWarrantyClaim(updatedClaim);
    }

    @Override
    public WarrantyClaimResponseDTO assignScTech(String claimId, String scTechId) {
        WarrantyClaim claim = warrantyClaimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Warranty claim not found with ID: " + claimId));

        SCTechnician scTechnician = scTechnicianRepository.findById(scTechId)
                .orElseThrow(() -> new ResourceNotFoundException("SC Tech not found with ID: " + scTechId));

        claim.setTechnician(scTechnician);
        WarrantyClaim updatedWarrantyClaim = warrantyClaimRepository.save(claim);
        return warrantyClaimMapper.toResponseWarrantyClaim(claim);
    }
}
