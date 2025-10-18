package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.dto.ServiceCampaignsRequestDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ServiceCampaignsResponseDTO;
import com.warrantyclaim.warrantyclaim_api.entity.ServiceCampaigns;
import com.warrantyclaim.warrantyclaim_api.enums.ServiceCampaignsStatus;
import com.warrantyclaim.warrantyclaim_api.mapper.ServiceCampaignsMapper;
import com.warrantyclaim.warrantyclaim_api.repository.ServiceCampaignsRepository;
import com.warrantyclaim.warrantyclaim_api.service.ServiceCampaignsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceCampaignsServiceImp implements ServiceCampaignsService {

    private final ServiceCampaignsMapper mapper;
    private final ServiceCampaignsRepository repository;


    @Override
    public ServiceCampaignsResponseDTO createServiceCampaigns(ServiceCampaignsRequestDTO requestDTO) {
        ServiceCampaigns serviceCampaigns = mapper.toEntityServiceCampaigns(requestDTO);

        serviceCampaigns.setId(generateClaimId());
        serviceCampaigns.setStatus(ServiceCampaignsStatus.PLANNED);

        repository.save(serviceCampaigns);
        return mapper.toResponseDTO(serviceCampaigns);
    }

    @Override
    public ServiceCampaignsResponseDTO findById(String id) {
        return null;
    }

    private String generateClaimId() {
        return "SCA-" + LocalDate.now().getYear() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
