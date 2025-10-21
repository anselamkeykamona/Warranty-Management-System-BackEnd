package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMUpdateDTO;
import com.warrantyclaim.warrantyclaim_api.service.ProductsSparePartsEVMService;
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
@RequestMapping("/api/spare-parts-evm")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductsSparePartsEVMController {

    private final ProductsSparePartsEVMService sparePartsService;

    /**
     * Create a new spare part EVM
     * POST /api/spare-parts-evm
     */
    @PostMapping
    public ResponseEntity<ProductsSparePartsEVMResponseDTO> createSparePart(
            @Valid @RequestBody ProductsSparePartsEVMCreateDTO createDTO) {
        ProductsSparePartsEVMResponseDTO response = sparePartsService.createSparePart(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get spare part by ID
     * GET /api/spare-parts-evm/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductsSparePartsEVMResponseDTO> getSparePartById(@PathVariable String id) {
        ProductsSparePartsEVMResponseDTO response = sparePartsService.getSparePartById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all spare parts with pagination
     * GET /api/spare-parts-evm?page=0&size=10&sortBy=name&sortDir=asc
     */
    @GetMapping
    public ResponseEntity<Page<ProductsSparePartsEVMResponseDTO>> getAllSpareParts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductsSparePartsEVMResponseDTO> response = sparePartsService.getAllSpareParts(pageable);
        return ResponseEntity.ok(response);
    }

    /**
     * Get spare parts by part type ID
     * GET /api/spare-parts-evm/type/{partTypeId}
     */
    @GetMapping("/type/{partTypeId}")
    public ResponseEntity<List<ProductsSparePartsEVMResponseDTO>> getSparePartsByType(
            @PathVariable String partTypeId) {
        List<ProductsSparePartsEVMResponseDTO> response = sparePartsService.getSparePartsByType(partTypeId);
        return ResponseEntity.ok(response);
    }

    /**
     * Update spare part
     * PUT /api/spare-parts-evm/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductsSparePartsEVMResponseDTO> updateSparePart(
            @PathVariable String id,
            @Valid @RequestBody ProductsSparePartsEVMUpdateDTO updateDTO) {
        ProductsSparePartsEVMResponseDTO response = sparePartsService.updateSparePart(id, updateDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete spare part
     * DELETE /api/spare-parts-evm/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSparePart(@PathVariable String id) {
        sparePartsService.deleteSparePart(id);
        return ResponseEntity.noContent().build();
    }
}
