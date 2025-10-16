package com.warrantyclaim.warrantyclaim_api.mapper;

import com.warrantyclaim.warrantyclaim_api.dto.VehicleBasicInfoDTO;
import com.warrantyclaim.warrantyclaim_api.dto.VehicleCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.VehicleDetailInfo;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import com.warrantyclaim.warrantyclaim_api.enums.VehicleStatus;
import org.springframework.stereotype.Component;

@Component
public class ElectricVehicleMapper {

    public ElectricVehicle toEntityElectricVehicle(VehicleCreateDTO vehicleCreateDTO) {
        if(vehicleCreateDTO == null) {
            return null;
        }

        ElectricVehicle electricVehicle = new ElectricVehicle();
        electricVehicle.setPicture(vehicleCreateDTO.getPicture());
        electricVehicle.setId(vehicleCreateDTO.getVehicleId());
        electricVehicle.setName(vehicleCreateDTO.getVehicleName());
        electricVehicle.setEmail(vehicleCreateDTO.getEmail());
        electricVehicle.setProductionDate(vehicleCreateDTO.getProductionDate());
        electricVehicle.setOwner(vehicleCreateDTO.getOwner());
        electricVehicle.setPhoneNumber(vehicleCreateDTO.getPhoneNumber());
        electricVehicle.setTotalKm(vehicleCreateDTO.getTotalKm());
        electricVehicle.setStatus(vehicleCreateDTO.getStatus());

        return electricVehicle;
     }

    public VehicleBasicInfoDTO toVehicleBasicInfoDTO(ElectricVehicle vehicle) {
        if (vehicle == null) return null;

        VehicleBasicInfoDTO info = new VehicleBasicInfoDTO();
        info.setVehicleId(vehicle.getId());
        info.setVehicleName(vehicle.getName());
        info.setOwner(vehicle.getOwner());
        info.setEmail(vehicle.getEmail());
        info.setPhoneNumber(vehicle.getPhoneNumber());
        return info;
    }

    public VehicleDetailInfo toVehicleDetailInfo(ElectricVehicle vehicle) {
        if (vehicle == null) return null;

        VehicleDetailInfo info = new VehicleDetailInfo();
        info.setVehicleId(vehicle.getId());
        info.setVehicleName(vehicle.getName());
        info.setTotalKm(vehicle.getTotalKm());
        info.setOwner(vehicle.getOwner());
        info.setPhoneNumber(vehicle.getPhoneNumber());
        info.setEmail(vehicle.getEmail());
        info.setStatus(vehicle.getStatus());


        if (vehicle.getVehicleType() != null) {
            info.setModelName(vehicle.getVehicleType().getModelName());
            info.setElectricVehicleType(vehicle.getVehicleType());
            info.setVehicleTypeName(vehicle.getVehicleType().getModelName());
        }

        return info;
    }

}
