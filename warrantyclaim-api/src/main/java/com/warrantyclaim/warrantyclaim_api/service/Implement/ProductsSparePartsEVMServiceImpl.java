package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMUpdateDTO;
import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsEVM;
import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsTypeEVM;
import com.warrantyclaim.warrantyclaim_api.exception.ResourceNotFoundException;
import com.warrantyclaim.warrantyclaim_api.mapper.ProductsSparePartsEVMMapper;
import com.warrantyclaim.warrantyclaim_api.repository.ProductsSparePartsEVMRepository;
import com.warrantyclaim.warrantyclaim_api.repository.ProductsSparePartsTypeEVMRepository;
import com.warrantyclaim.warrantyclaim_api.service.ProductsSparePartsEVMService;
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
public class ProductsSparePartsEVMServiceImpl implements ProductsSparePartsEVMService {

    private final ProductsSparePartsEVMRepository sparePartsRepository;
    private final ProductsSparePartsTypeEVMRepository partTypeRepository;
    private final ProductsSparePartsEVMMapper mapper;

    @Override
    public ProductsSparePartsEVMResponseDTO createSparePart(ProductsSparePartsEVMCreateDTO createDTO) {
        // Validate part type exists
        ProductsSparePartsTypeEVM partType = partTypeRepository.findById(createDTO.getPartTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Part type not found with ID: " + createDTO.getPartTypeId()));

        // Create entity
        ProductsSparePartsEVM entity = mapper.toEntity(createDTO, partType);

        // Save to database
        ProductsSparePartsEVM savedEntity = sparePartsRepository.save(entity);

        // Return response DTO
        return mapper.toResponseDTO(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductsSparePartsEVMResponseDTO getSparePartById(String id) {
        ProductsSparePartsEVM entity = sparePartsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Spare part not found with ID: " + id));

        return mapper.toResponseDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductsSparePartsEVMResponseDTO> getAllSpareParts(Pageable pageable) {
        Page<ProductsSparePartsEVM> entities = sparePartsRepository.findAll(pageable);
        return entities.map(mapper::toResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductsSparePartsEVMResponseDTO> getSparePartsByType(String partTypeId) {
        List<ProductsSparePartsEVM> entities = sparePartsRepository.findByPartTypeId(partTypeId);
        return entities.stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductsSparePartsEVMResponseDTO updateSparePart(String id, ProductsSparePartsEVMUpdateDTO updateDTO) {
        // Find existing entity
        ProductsSparePartsEVM entity = sparePartsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Spare part not found with ID: " + id));

        // Validate part type if changed
        ProductsSparePartsTypeEVM partType = null;
        if (updateDTO.getPartTypeId() != null) {
            partType = partTypeRepository.findById(updateDTO.getPartTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Part type not found with ID: " + updateDTO.getPartTypeId()));
        }

        // Update entity
        mapper.updateEntityFromDTO(entity, updateDTO, partType);

        // Save changes
        ProductsSparePartsEVM updatedEntity = sparePartsRepository.save(entity);

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
