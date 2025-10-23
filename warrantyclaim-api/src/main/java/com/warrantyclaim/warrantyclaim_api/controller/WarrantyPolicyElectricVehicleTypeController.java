package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyElectricVehicleType;
import com.warrantyclaim.warrantyclaim_api.service.WarrantyPolicyElectricVehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warranty-policy-electric-vehicle-type")
@CrossOrigin(origins = "*")
public class WarrantyPolicyElectricVehicleTypeController {

    private final WarrantyPolicyElectricVehicleTypeService service;

    @Autowired
    public WarrantyPolicyElectricVehicleTypeController(WarrantyPolicyElectricVehicleTypeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<WarrantyPolicyElectricVehicleType>> getAll() {
        List<WarrantyPolicyElectricVehicleType> entities = service.getAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{warrantyPolicyId}/{vehicleTypeId}")
    public ResponseEntity<WarrantyPolicyElectricVehicleType> getById(
            @PathVariable String warrantyPolicyId,
            @PathVariable String vehicleTypeId) {
        return service.getById(warrantyPolicyId, vehicleTypeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{warrantyPolicyId}/{vehicleTypeId}")
    public ResponseEntity<WarrantyPolicyElectricVehicleType> create(
            @PathVariable String warrantyPolicyId,
            @PathVariable String vehicleTypeId,
            @RequestBody WarrantyPolicyElectricVehicleType entity) {

        // Set IDs from path parameters
        entity.setWarrantyPolicyId(warrantyPolicyId);
        entity.setVehicleTypeId(vehicleTypeId);

        WarrantyPolicyElectricVehicleType created = service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{warrantyPolicyId}/{vehicleTypeId}")
    public ResponseEntity<WarrantyPolicyElectricVehicleType> update(
            @PathVariable String warrantyPolicyId,
            @PathVariable String vehicleTypeId,
            @RequestBody WarrantyPolicyElectricVehicleType entity) {

        WarrantyPolicyElectricVehicleType updated = service.update(warrantyPolicyId, vehicleTypeId, entity);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{warrantyPolicyId}/{vehicleTypeId}")
    public ResponseEntity<Void> delete(
            @PathVariable String warrantyPolicyId,
            @PathVariable String vehicleTypeId) {

        boolean deleted = service.delete(warrantyPolicyId, vehicleTypeId);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}