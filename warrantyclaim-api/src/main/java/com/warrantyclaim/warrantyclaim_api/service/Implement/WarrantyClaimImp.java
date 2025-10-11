package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.dto.WarrantyClaimCreateRequestDTO;
import com.warrantyclaim.warrantyclaim_api.dto.WarrantyClaimResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.WarrantyClaimUpdateRequestDTO;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import com.warrantyclaim.warrantyclaim_api.entity.ScStaff;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyClaim;
import com.warrantyclaim.warrantyclaim_api.exception.ResourceNotFoundException;
import com.warrantyclaim.warrantyclaim_api.repository.ElectricVehicleRepository;
import com.warrantyclaim.warrantyclaim_api.service.WarrantyClaimService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class WarrantyClaimImp implements WarrantyClaimService {

    private final ElectricVehicleRepository electricVehicleRepository;
    private final 

    @Override
    public WarrantyClaimCreateRequestDTO createWarrantyClaim(WarrantyClaimCreateRequestDTO warrantyClaimRequest) {
        ElectricVehicle vehicle = electricVehicleRepository.findById(warrantyClaimRequest.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + warrantyClaimRequest.getVehicleId()));

//        // Validate staff if provided
//        ScStaff staff = null;
//        if (warrantyClaimRequest.getScStaffId() != null && !warrantyClaimRequest.getScStaffId().isEmpty()) {
//            staff = scStaffRepository.findById(request.getScStaffId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + request.getScStaffId()));
//        }

        // Create claim entity
        WarrantyClaim claim = mapper.toEntity(request);
        claim.setClaimId(generateClaimId());
        claim.setElectricVehicle(vehicle);
        claim.setScStaff(staff);
        claim.setStatus("PENDING");

        // Save claim
        WarrantyClaim savedClaim = warrantyClaimRepository.save(claim);

        return mapper.toResponse(savedClaim);
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
        claim.setElectricVehicle(electricVehicle);
        WarrantyClaim updatedClaim = warrantyClaimRepository.save(claim);
        return warrantyClaimMapper.toResponseWarrantyClaim(updatedClaim);
    }
}
}
