package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCUpdateDTO;
import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsSC;
import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsTypeSC;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyClaim;
import com.warrantyclaim.warrantyclaim_api.exception.ResourceNotFoundException;
import com.warrantyclaim.warrantyclaim_api.mapper.ProductsSparePartsSCMapper;
import com.warrantyclaim.warrantyclaim_api.repository.ProductsSparePartsSCRepository;
import com.warrantyclaim.warrantyclaim_api.repository.ProductsSparePartsTypeSCRepository;
import com.warrantyclaim.warrantyclaim_api.repository.WarrantyClaimRepository;
import com.warrantyclaim.warrantyclaim_api.service.ProductsSparePartsSCService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductsSparePartsSCServiceImpl implements ProductsSparePartsSCService {

    private final ProductsSparePartsSCRepository sparePartsRepository;
    private final ProductsSparePartsTypeSCRepository partTypeRepository;
    private final WarrantyClaimRepository warrantyClaimRepository;
    private final ProductsSparePartsSCMapper mapper;

    @Override
    public ProductsSparePartsSCResponseDTO createSparePart(ProductsSparePartsSCCreateDTO createDTO) {
        // Validate part type exists
        ProductsSparePartsTypeSC partType = partTypeRepository.findById(createDTO.getPartTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Part type not found with ID: " + createDTO.getPartTypeId()));

        // Validate claim if provided
        WarrantyClaim claim = null;
        if (createDTO.getClaimId() != null && !createDTO.getClaimId().isEmpty()) {
            claim = warrantyClaimRepository.findById(createDTO.getClaimId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Warranty claim not found with ID: " + createDTO.getClaimId()));
        }

        // Create entity
        ProductsSparePartsSC entity = mapper.toEntity(createDTO, partType, claim);

        // Save to database
        ProductsSparePartsSC savedEntity = sparePartsRepository.save(entity);

        // Return response DTO
        return mapper.toResponseDTO(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductsSparePartsSCResponseDTO getSparePartById(String id) {
        ProductsSparePartsSC entity = sparePartsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Spare part not found with ID: " + id));

        return mapper.toResponseDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductsSparePartsSCResponseDTO> getAllSpareParts(Pageable pageable) {
        Page<ProductsSparePartsSC> entities = sparePartsRepository.findAll(pageable);
        return entities.map(mapper::toResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductsSparePartsSCResponseDTO> getSparePartsByType(String partTypeId) {
        List<ProductsSparePartsSC> entities = sparePartsRepository.findByPartTypeId(partTypeId);
        return entities.stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductsSparePartsSCResponseDTO> getSparePartsByClaim(String claimId) {
        List<ProductsSparePartsSC> entities = sparePartsRepository.findByClaimClaimId(claimId);
        return entities.stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductsSparePartsSCResponseDTO updateSparePart(String id, ProductsSparePartsSCUpdateDTO updateDTO) {
        // Find existing entity
        ProductsSparePartsSC entity = sparePartsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Spare part not found with ID: " + id));

        // Validate part type if changed
        ProductsSparePartsTypeSC partType = null;
        if (updateDTO.getPartTypeId() != null) {
            partType = partTypeRepository.findById(updateDTO.getPartTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Part type not found with ID: " + updateDTO.getPartTypeId()));
        }

        // Validate claim if changed
        WarrantyClaim claim = null;
        if (updateDTO.getClaimId() != null && !updateDTO.getClaimId().isEmpty()) {
            claim = warrantyClaimRepository.findById(updateDTO.getClaimId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Warranty claim not found with ID: " + updateDTO.getClaimId()));
        }

        // Update entity
        mapper.updateEntityFromDTO(entity, updateDTO, partType, claim);

        // Save changes
        ProductsSparePartsSC updatedEntity = sparePartsRepository.save(entity);

        // Return response DTO
        return mapper.toResponseDTO(updatedEntity);
    }

    @Override
    public void deleteSparePart(String id) {
        // Check if exists
        if (!sparePartsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Spare part not found with ID: " + id);
        }

        // Delete
        sparePartsRepository.deleteById(id);
    }
}
