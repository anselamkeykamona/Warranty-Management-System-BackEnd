package com.warrantyclaim.warrantyclaim_api.controller;


import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import com.warrantyclaim.warrantyclaim_api.service.ElectricVehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Configure as needed
@RequestMapping("/api/ElectricVehicle")
public class ElectricVehicleController {
    private final ElectricVehicleService electricVehicleService;

    @PostMapping
    public ResponseEntity<VehicleDetailInfo> addElectricVehicle(@RequestBody VehicleCreateDTO vehicleCreateDTO) {
        System.out.println("✅ Controller reached: ");
        VehicleDetailInfo vehicleCreateDetailInfo = electricVehicleService.addElectricVehicle(vehicleCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleCreateDetailInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectricVehicleResponseDTO> getVehicleById(@PathVariable String id) {
        ElectricVehicleResponseDTO response = electricVehicleService.getVehicleById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElectricVehicleResponseDTO> updateVehicle(
            @PathVariable String id,
            @Valid @RequestBody ElectricVehicleUpdateRequestDTO request) {
        ElectricVehicleResponseDTO response = electricVehicleService.updateVehicle(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String id) {
        electricVehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
