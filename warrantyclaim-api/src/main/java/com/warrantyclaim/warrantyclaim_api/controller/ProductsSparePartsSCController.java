package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCUpdateDTO;
import com.warrantyclaim.warrantyclaim_api.service.ProductsSparePartsSCService;
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
@RequestMapping("/api/spare-parts-sc")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductsSparePartsSCController {

    private final ProductsSparePartsSCService sparePartsService;

    /**
     * Create a new spare part SC
     * POST /api/spare-parts-sc
     */
    @PostMapping
    public ResponseEntity<ProductsSparePartsSCResponseDTO> createSparePart(
            @Valid @RequestBody ProductsSparePartsSCCreateDTO createDTO) {
        ProductsSparePartsSCResponseDTO response = sparePartsService.createSparePart(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get spare part by ID
     * GET /api/spare-parts-sc/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductsSparePartsSCResponseDTO> getSparePartById(@PathVariable String id) {
        ProductsSparePartsSCResponseDTO response = sparePartsService.getSparePartById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all spare parts with pagination
     * GET /api/spare-parts-sc?page=0&size=10&sortBy=name&sortDir=asc
     */
    @GetMapping
    public ResponseEntity<Page<ProductsSparePartsSCResponseDTO>> getAllSpareParts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductsSparePartsSCResponseDTO> response = sparePartsService.getAllSpareParts(pageable);
        return ResponseEntity.ok(response);
    }

    /**
     * Get spare parts by part type ID
     * GET /api/spare-parts-sc/type/{partTypeId}
     */
    @GetMapping("/type/{partTypeId}")
    public ResponseEntity<List<ProductsSparePartsSCResponseDTO>> getSparePartsByType(
            @PathVariable String partTypeId) {
        List<ProductsSparePartsSCResponseDTO> response = sparePartsService.getSparePartsByType(partTypeId);
        return ResponseEntity.ok(response);
    }

    /**
     * Get spare parts by claim ID
     * GET /api/spare-parts-sc/claim/{claimId}
     */
    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<ProductsSparePartsSCResponseDTO>> getSparePartsByClaim(
            @PathVariable String claimId) {
        List<ProductsSparePartsSCResponseDTO> response = sparePartsService.getSparePartsByClaim(claimId);
        return ResponseEntity.ok(response);
    }

    /**
     * Update spare part
     * PUT /api/spare-parts-sc/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductsSparePartsSCResponseDTO> updateSparePart(
            @PathVariable String id,
            @Valid @RequestBody ProductsSparePartsSCUpdateDTO updateDTO) {
        ProductsSparePartsSCResponseDTO response = sparePartsService.updateSparePart(id, updateDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete spare part
     * DELETE /api/spare-parts-sc/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSparePart(@PathVariable String id) {
        sparePartsService.deleteSparePart(id);
        return ResponseEntity.noContent().build();
    }
}
