package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsTypeEVMWarrantyPolicy;
import com.warrantyclaim.warrantyclaim_api.service.ProductsSparePartsTypeEVMWarrantyPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products-evm-warranty-policy")
public class ProductsSparePartsTypeEVMWarrantyPolicyController {

    @Autowired
    private ProductsSparePartsTypeEVMWarrantyPolicyService service;

    @GetMapping
    public List<ProductsSparePartsTypeEVMWarrantyPolicy> getAll() {
        return service.getAll();
    }

    @GetMapping("/{partTypeId}/{warrantyPolicyId}")
    public ResponseEntity<ProductsSparePartsTypeEVMWarrantyPolicy> getById(
            @PathVariable String partTypeId,
            @PathVariable String warrantyPolicyId) {
        Optional<ProductsSparePartsTypeEVMWarrantyPolicy> entity = service.getById(partTypeId, warrantyPolicyId);
        return entity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductsSparePartsTypeEVMWarrantyPolicy create(@RequestBody ProductsSparePartsTypeEVMWarrantyPolicy entity) {
        return service.create(entity);
    }

    @PutMapping("/{partTypeId}/{warrantyPolicyId}")
    public ResponseEntity<ProductsSparePartsTypeEVMWarrantyPolicy> update(
            @PathVariable String partTypeId,
            @PathVariable String warrantyPolicyId,
            @RequestBody ProductsSparePartsTypeEVMWarrantyPolicy entity) {
        ProductsSparePartsTypeEVMWarrantyPolicy updated = service.update(partTypeId, warrantyPolicyId, entity);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{partTypeId}/{warrantyPolicyId}")
    public ResponseEntity<Void> delete(
            @PathVariable String partTypeId,
            @PathVariable String warrantyPolicyId) {
        boolean deleted = service.delete(partTypeId, warrantyPolicyId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
