package com.warrantyclaim.warrantyclaim_api.mapper;

import com.warrantyclaim.warrantyclaim_api.dto.VehicleBasicInfoDTO;
import com.warrantyclaim.warrantyclaim_api.dto.VehicleDetailInfo;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import org.springframework.stereotype.Component;

@Component
public class ElectricVehicleMapper {

    public VehicleBasicInfoDTO toVehicleBasicInfoDTO(ElectricVehicle vehicle) {
        if (vehicle == null) return null;

        VehicleBasicInfoDTO info = new VehicleBasicInfoDTO();
        info.setVehicleId(vehicle.getId());
        info.setVehicleName(vehicle.getName());
        info.setVin(vehicle.getVin());
        info.setOwner(vehicle.getOwner());
        info.setEmail(vehicle.getEmail());
        info.setPhoneNumber(vehicle.getPhoneNumber());
        return info;
    }

    private VehicleDetailInfo toVehicleDetailInfo(ElectricVehicle vehicle) {
        if (vehicle == null) return null;

        VehicleDetailInfo info = new VehicleDetailInfo();
        info.setVehicleId(vehicle.getId());
        info.setVehicleName(vehicle.getName());
        info.setVin(vehicle.getVin());
        info.setTotalKm(vehicle.getTotalKm());
        info.setOwner(vehicle.getOwner());
        info.setPhoneNumber(vehicle.getPhoneNumber());
        info.setEmail(vehicle.getEmail());
        info.setStatus(vehicle.getStatus());

        if (vehicle.getVehicleType() != null) {
            info.setModelName(vehicle.getVehicleType().getModelName());
        }

        return info;
    }

}
