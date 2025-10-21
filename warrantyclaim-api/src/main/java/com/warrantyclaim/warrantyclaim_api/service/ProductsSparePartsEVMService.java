package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductsSparePartsEVMService {

    /**
     * Create a new spare part EVM
     */
    ProductsSparePartsEVMResponseDTO createSparePart(ProductsSparePartsEVMCreateDTO createDTO);

    /**
     * Get spare part by ID
     */
    ProductsSparePartsEVMResponseDTO getSparePartById(String id);

    /**
     * Get all spare parts with pagination
     */
    Page<ProductsSparePartsEVMResponseDTO> getAllSpareParts(Pageable pageable);

    /**
     * Get spare parts by part type ID
     */
    List<ProductsSparePartsEVMResponseDTO> getSparePartsByType(String partTypeId);

    /**
     * Update spare part
     */
    ProductsSparePartsEVMResponseDTO updateSparePart(String id, ProductsSparePartsEVMUpdateDTO updateDTO);

    /**
     * Delete spare part
     */
    void deleteSparePart(String id);
}
