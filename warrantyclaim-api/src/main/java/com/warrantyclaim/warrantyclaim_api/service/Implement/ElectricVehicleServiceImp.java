package com.warrantyclaim.warrantyclaim_api.service.Implement;

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
import com.warrantyclaim.warrantyclaim_api.repository.ScStaffRepository;
import com.warrantyclaim.warrantyclaim_api.service.ElectricVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ElectricVehicleServiceImp implements ElectricVehicleService {
    private final ElectricVehicleRepository electricVehicleRepository;
    private final ElectricVehicleMapper mapper;
    private final ScStaffRepository scStaffRepository;
    private final ElectricVehicleTypeRepository electricVehicleTypeRepository;

    @Override
    public VehicleDetailInfo addElectricVehicle(VehicleCreateDTO vehicleCreateDTO) {
        ElectricVehicleType electricVehicleType = electricVehicleTypeRepository.findById(vehicleCreateDTO.getElectricVehicleTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("No vehicle type with this Id"));

        System.out.println("---------------" + vehicleCreateDTO.getTotalKm() + "-----------" +  vehicleCreateDTO.getProductionDate());
        ElectricVehicle electricVehicle = mapper.toEntityElectricVehicle(vehicleCreateDTO);
        electricVehicle.setVehicleType(electricVehicleType);
        electricVehicle.setStatus(VehicleStatus.ACTIVE);
        electricVehicleRepository.save(electricVehicle);
        return mapper.toVehicleDetailInfo(electricVehicle);
    }
}
