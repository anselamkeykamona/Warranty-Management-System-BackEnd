package com.warrantyclaim.warrantyclaim_api.mapper;

import com.warrantyclaim.warrantyclaim_api.dto.ServiceCampaignsRequestDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ServiceCampaignsResponseDTO;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import com.warrantyclaim.warrantyclaim_api.entity.ServiceCampaigns;
import org.springframework.stereotype.Component;

@Component
public class ServiceCampaignsMapper {

    public ServiceCampaigns toEntityServiceCampaigns(ServiceCampaignsRequestDTO requestDTO) {
        if(requestDTO == null) {
            return null;
        }

        ServiceCampaigns serviceCampaigns = new ServiceCampaigns();
        serviceCampaigns.setStartDate(requestDTO.getStartDate());
        serviceCampaigns.setEndDate(requestDTO.getEndDate());
        serviceCampaigns.setTypeName(requestDTO.getTypeName());

        serviceCampaigns.setStatus(requestDTO.getStatus());

        if(requestDTO.getDescription() != null) {
            serviceCampaigns.setDescription(requestDTO.getDescription());
        }
        return serviceCampaigns;
    }

    public ServiceCampaignsResponseDTO toResponseDTO(ServiceCampaigns serviceCampaigns) {
        if(serviceCampaigns == null) {
            return null;
        }
        ServiceCampaignsResponseDTO responseDTO = new ServiceCampaignsResponseDTO();

        responseDTO.setId(serviceCampaigns.getId());
        responseDTO.setStartDate(serviceCampaigns.getStartDate());
        responseDTO.setEndDate(serviceCampaigns.getEndDate());
        responseDTO.setTypeName(serviceCampaigns.getTypeName());
        responseDTO.setRequiredParts(serviceCampaigns.getRequiredParts());
        responseDTO.setStatus(serviceCampaigns.getStatus());

        if(responseDTO.getDescription() != null) {
            serviceCampaigns.setDescription(responseDTO.getDescription());
        }
        return responseDTO;
    }


}
