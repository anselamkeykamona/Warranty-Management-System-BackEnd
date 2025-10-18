package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.dto.ServiceCampaignsRequestDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ServiceCampaignsResponseDTO;

public interface ServiceCampaignsService {
    public ServiceCampaignsResponseDTO createServiceCampaigns(ServiceCampaignsRequestDTO requestDTO);

    public ServiceCampaignsResponseDTO findById(String id);

}
