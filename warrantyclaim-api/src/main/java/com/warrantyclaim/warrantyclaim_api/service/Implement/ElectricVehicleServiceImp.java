package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.dto.ElectricVehicleResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ElectricVehicleUpdateRequestDTO;
import com.warrantyclaim.warrantyclaim_api.dto.VehicleCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.VehicleDetailInfo;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicleType;
import com.warrantyclaim.warrantyclaim_api.entity.SCStaff;
import com.warrantyclaim.warrantyclaim_api.enums.VehicleStatus;
import com.warrantyclaim.warrantyclaim_api.exception.ResourceNotFoundException;
import com.warrantyclaim.warrantyclaim_api.mapper.ElectricVehicleMapper;
import com.warrantyclaim.warrantyclaim_api.repository.ElectricVehicleRepository;
import com.warrantyclaim.warrantyclaim_api.repository.ElectricVehicleTypeRepository;
import com.warrantyclaim.warrantyclaim_api.repository.SCStaffRepository;
import com.warrantyclaim.warrantyclaim_api.service.ElectricVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ElectricVehicleServiceImp implements ElectricVehicleService {
    private final ElectricVehicleRepository electricVehicleRepository;
    private final ElectricVehicleMapper mapper;
    private final SCStaffRepository scStaffRepository;
    private final ElectricVehicleTypeRepository electricVehicleTypeRepository;

    @Override
    @Transactional
    public VehicleDetailInfo addElectricVehicle(VehicleCreateDTO vehicleCreateDTO) {
        ElectricVehicleType electricVehicleType = electricVehicleTypeRepository.findById(vehicleCreateDTO.getElectricVehicleTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("No vehicle type with this Id"));
        ElectricVehicle electricVehicle = mapper.toEntityElectricVehicle(vehicleCreateDTO);
        electricVehicle.setVehicleType(electricVehicleType);
        electricVehicle.setStatus(VehicleStatus.ACTIVE);
        electricVehicleRepository.save(electricVehicle);
        return mapper.toVehicleDetailInfo(electricVehicle);
    }

    @Transactional(readOnly = true)
    public ElectricVehicleResponseDTO getVehicleById(String id) {
        ElectricVehicle vehicle = electricVehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Electric Vehicle not found with ID: " + id));

        return mapper.toResponseDTO(vehicle);
    }

    /**
     * Update vehicle
     */
    @Transactional
    public ElectricVehicleResponseDTO updateVehicle(String id, ElectricVehicleUpdateRequestDTO request) {
        // 1. Find vehicle
        ElectricVehicle vehicle = electricVehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Electric Vehicle not found with ID: " + id));

        // 2. Validate and fetch vehicle type if changed
        if (request.getVehicleTypeId() != null) {
            ElectricVehicleType vehicleType = electricVehicleTypeRepository.findById(request.getVehicleTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vehicle Type not found with ID: " + request.getVehicleTypeId()));
            vehicle.setVehicleType(vehicleType);
        }

        // 3. Update fields
        mapper.updateEntityElectricVehicle(request, vehicle);
        // 4. Save
        ElectricVehicle updatedVehicle = electricVehicleRepository.save(vehicle);

        return mapper.toResponseDTO(updatedVehicle);
    }

    @Transactional
    public void deleteVehicle(String id) {
        // 1. Check if vehicle exists
        if (!electricVehicleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Electric Vehicle not found with ID: " + id);
        }

        // 2. Delete
        electricVehicleRepository.deleteById(id);
    }


}