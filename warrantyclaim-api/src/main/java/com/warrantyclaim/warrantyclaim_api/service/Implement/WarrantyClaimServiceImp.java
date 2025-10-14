package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyClaim;
import com.warrantyclaim.warrantyclaim_api.exception.ResourceNotFoundException;
import com.warrantyclaim.warrantyclaim_api.mapper.WarrantyClaimMapper;
import com.warrantyclaim.warrantyclaim_api.repository.ElectricVehicleRepository;
import com.warrantyclaim.warrantyclaim_api.repository.WarrantyClaimRepository;
import com.warrantyclaim.warrantyclaim_api.service.WarrantyClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class WarrantyClaimServiceImp implements WarrantyClaimService {

    private final WarrantyClaimRepository warrantyClaimRepository;
    private final ElectricVehicleRepository electricVehicleRepository;
//    private final WorkAssignRepository workAssignRepository;
//    private final ScTechnician scTechnician;
//    private final ScStaffRepository scStaffRepository;
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
        warrantyClaim.setVehicle(electricVehicle);
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

    @Override
    public WarrantyClaimDetailResponseDTO getClaimById(String claimId) {
        return null;
    }
}
