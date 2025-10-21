package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductsSparePartsSCService {

    /**
     * Create a new spare part SC
     */
    ProductsSparePartsSCResponseDTO createSparePart(ProductsSparePartsSCCreateDTO createDTO);

    /**
     * Get spare part by ID
     */
    ProductsSparePartsSCResponseDTO getSparePartById(String id);

    /**
     * Get all spare parts with pagination
     */
    Page<ProductsSparePartsSCResponseDTO> getAllSpareParts(Pageable pageable);

    /**
     * Get spare parts by part type ID
     */
    List<ProductsSparePartsSCResponseDTO> getSparePartsByType(String partTypeId);

    /**
     * Get spare parts by claim ID
     */
    List<ProductsSparePartsSCResponseDTO> getSparePartsByClaim(String claimId);

    /**
     * Update spare part
     */
    ProductsSparePartsSCResponseDTO updateSparePart(String id, ProductsSparePartsSCUpdateDTO updateDTO);

    /**
     * Delete spare part
     */
    void deleteSparePart(String id);
}
