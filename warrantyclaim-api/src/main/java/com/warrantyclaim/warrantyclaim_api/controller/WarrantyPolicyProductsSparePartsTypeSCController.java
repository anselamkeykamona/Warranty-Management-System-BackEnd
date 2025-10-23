package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyProductsSparePartsTypeSC;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyProductsSparePartsTypeSCId;
import com.warrantyclaim.warrantyclaim_api.service.WarrantyPolicyProductsSparePartsTypeSCService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warranty-policy-part-types")
@CrossOrigin(origins = "*")
public class WarrantyPolicyProductsSparePartsTypeSCController {

    private final WarrantyPolicyProductsSparePartsTypeSCService service;

    public WarrantyPolicyProductsSparePartsTypeSCController(WarrantyPolicyProductsSparePartsTypeSCService service) {
        this.service = service;
    }

    @GetMapping
    public List<WarrantyPolicyProductsSparePartsTypeSC> getAll() {
        return service.getAll();
    }

    @GetMapping("/{warrantyPolicyId}/{partTypeId}")
    public Optional<WarrantyPolicyProductsSparePartsTypeSC> getById(
            @PathVariable String warrantyPolicyId,
            @PathVariable String partTypeId) {
        return service.getById(new WarrantyPolicyProductsSparePartsTypeSCId(warrantyPolicyId, partTypeId));
    }

    @PostMapping
    public WarrantyPolicyProductsSparePartsTypeSC create(@RequestBody WarrantyPolicyProductsSparePartsTypeSC entity) {
        return service.save(entity);
    }

    @PutMapping
    public WarrantyPolicyProductsSparePartsTypeSC update(@RequestBody WarrantyPolicyProductsSparePartsTypeSC entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{warrantyPolicyId}/{partTypeId}")
    public void delete(@PathVariable String warrantyPolicyId, @PathVariable String partTypeId) {
        service.deleteById(new WarrantyPolicyProductsSparePartsTypeSCId(warrantyPolicyId, partTypeId));
    }
}
