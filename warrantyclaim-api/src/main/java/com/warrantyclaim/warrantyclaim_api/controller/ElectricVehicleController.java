package com.warrantyclaim.warrantyclaim_api.controller;


import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.service.ElectricVehicleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Configure as needed
@RequestMapping("/api/ElectricVehicle")
//@SecurityRequirement(name = "bearerAuth")
public class ElectricVehicleController {
    private final ElectricVehicleService electricVehicleService;

    @PostMapping
    public ResponseEntity<VehicleDetailInfo> addElectricVehicle(@RequestBody VehicleCreateDTO vehicleCreateDTO) {
        System.out.println("✅ Controller reached: ");
        VehicleDetailInfo vehicleCreateDetailInfo = electricVehicleService.addElectricVehicle(vehicleCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleCreateDetailInfo);
    }

    @GetMapping
    public ResponseEntity<Page<ElectricVehicleListResponseDTO>> getAllVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ElectricVehicleListResponseDTO> response = electricVehicleService.getAllVehicles(pageable);
        return ResponseEntity.ok(response);
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
