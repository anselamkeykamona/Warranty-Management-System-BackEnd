package com.warrantyclaim.warrantyclaim_api.mapper;


import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.entity.*;

public class WarrantyClaimMapper {

    // Mapper for Warranty Claim Create Request
    public WarrantyClaim toEntityWarrantyClaim(WarrantyClaimCreateRequestDTO requestDTO) {
        if(requestDTO == null) {
            return null;
        }

        WarrantyClaim warrantyClaim = new WarrantyClaim();
        warrantyClaim.setClaimDate(requestDTO.getClaimDate());
        warrantyClaim.setEmail(requestDTO.getEmail());
        warrantyClaim.getElectricVehicle().setPicture(requestDTO.getPicture());
        warrantyClaim.setStatus("Pending"); // default status

        //  Vehicle and Staff relationships should be set in the service layer

        return warrantyClaim;
    }

    public WarrantyClaimResponseDTO toResponseWarrantyClaim(WarrantyClaim warrantyClaim) {
        if(warrantyClaim == null) {
            return null;
        }

        WarrantyClaimResponseDTO response = new WarrantyClaimResponseDTO();
        response.setClaimId(warrantyClaim.getClaimId());
        response.setCustomerName(warrantyClaim.getCustomerName());
        response.setCustomerPhone(warrantyClaim.getCustomerPhone());
        response.setClaimDate(warrantyClaim.getClaimDate());
        response.setIssueDescription(warrantyClaim.getIssueDescription());
        response.setStatus(warrantyClaim.getStatus());
        response.setEmail(warrantyClaim.getEmail());

        // Map vehicle
        if (warrantyClaim.getElectricVehicle() != null) {
            response.setVehicle(toVehicleBasicInfoDTO(warrantyClaim.getElectricVehicle()));
        }

        // Map staff
        if (warrantyClaim.getScStaff() != null) {
            response.setAssignedStaff(toStaffBasicInfoDTO(warrantyClaim.getScStaff()));
        }

        return response;
    }
    //------------------------------------------------------------------------------------------
    //Helper for info nested object
    private VehicleBasicInfoDTO toVehicleBasicInfoDTO(ElectricVehicle vehicle) {
        if (vehicle == null) return null;

        VehicleBasicInfoDTO info = new VehicleBasicInfoDTO();
        info.setVehicleId(vehicle.getVehicleId());
        info.setVehicleName(vehicle.getVehicleName());
        info.setVin(vehicle.getVin());
        info.setOwner(vehicle.getOwner());
        return info;
    }

    private StaffBasicInfoDTO toStaffBasicInfoDTO(ScStaff staff) {
        if (staff == null) return null;

        StaffBasicInfoDTO info = new StaffBasicInfoDTO();
        info.setStaffId(staff.getScStaffId());
        info.setAccountName(staff.getAccountName());
        info.setEmail(staff.getEmail());
        info.setPhoneNumber(staff.getPhoneNumber());
        return info;
    }

    private SparePartInfoDTO toSparePartInfo(ProductsSparePartsSC part) {
        if (part == null) return null;

        SparePartInfoDTO info = new SparePartInfoDTO();
        info.setPartId(part.getIdProductSerialSc());
        info.setNameProduct(part.getNameProduct());
        info.setBrand(part.getBrand());
        info.setPrice(part.getPrice());
        info.setWarrantyPeriod(part.getWarrantyPeriod());
        return info;
    }

    private WorkAssignedInfoDTO toWorkAssignmentInfo(WorkAssign workAssign) {
        if (workAssign == null) return null;

        WorkAssignedInfoDTO info = new WorkAssignedInfoDTO();
        info.setWorkAssignId(workAssign.getWorkAssignId());
        info.setWorkAssignDate(workAssign.getWorkAssignDate());

        if (workAssign.getScTechnician() != null) {
            info.setTechnicianId(workAssign.getScTechnician().getScTechnicianId());
            info.setTechnicianName(workAssign.getScTechnician().getName());
        }

        return info;
    }


}
