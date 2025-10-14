package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarrantyClaimService {

    public WarrantyClaimResponseDTO createWarrantyClaim(WarrantyClaimCreateRequestDTO warrantyClaimRequest);

    public Page<WarrantyClaimListResponseDTO> getAllClaims(Pageable pageable);

    public WarrantyClaimResponseDTO updateClaim(String claimId, WarrantyClaimUpdateRequestDTO request);

    public WarrantyClaimDetailResponseDTO getClaimById(String claimId);
}
