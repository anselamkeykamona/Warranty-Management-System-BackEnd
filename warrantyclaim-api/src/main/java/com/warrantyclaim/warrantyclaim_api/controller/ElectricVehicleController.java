package com.warrantyclaim.warrantyclaim_api.controller;


import com.warrantyclaim.warrantyclaim_api.dto.VehicleCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.VehicleDetailInfo;
import com.warrantyclaim.warrantyclaim_api.dto.WarrantyClaimDetailResponseDTO;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import com.warrantyclaim.warrantyclaim_api.service.ElectricVehicleService;
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
}
