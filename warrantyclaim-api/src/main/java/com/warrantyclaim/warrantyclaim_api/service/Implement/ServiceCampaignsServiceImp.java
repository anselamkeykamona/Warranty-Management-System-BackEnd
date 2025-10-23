package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicleType;
import com.warrantyclaim.warrantyclaim_api.entity.SCTechnician;
import com.warrantyclaim.warrantyclaim_api.entity.ServiceCampaigns;
import com.warrantyclaim.warrantyclaim_api.enums.ServiceCampaignsStatus;
import com.warrantyclaim.warrantyclaim_api.exception.ResourceNotFoundException;
import com.warrantyclaim.warrantyclaim_api.mapper.ServiceCampaignsMapper;
import com.warrantyclaim.warrantyclaim_api.repository.ElectricVehicleTypeRepository;
import com.warrantyclaim.warrantyclaim_api.repository.ScTechnicianRepository;
import com.warrantyclaim.warrantyclaim_api.repository.ServiceCampaignsRepository;
import com.warrantyclaim.warrantyclaim_api.service.ServiceCampaignsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceCampaignsServiceImp implements ServiceCampaignsService {

    private final ServiceCampaignsMapper mapper;
    private final ServiceCampaignsRepository repository;
    private final ElectricVehicleTypeRepository electricVehicleTypeRepository;
    private final ScTechnicianRepository scTechnicianRepository;
    // Need update status and notification send


    @Override
    @Transactional
    public ReportInfoListDTO getAllReport(String campaignId) {
        ServiceCampaigns serviceCampaigns = repository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Service campaign not found with ID: " + campaignId));

        return mapper.toListReportDTO(serviceCampaigns);
    }

    @Override
    @Transactional
    public ServiceCampaignsResponseDTO createServiceCampaigns(ServiceCampaignsRequestDTO requestDTO) {
        ServiceCampaigns serviceCampaigns = mapper.toEntityServiceCampaigns(requestDTO);

        serviceCampaigns.setId(generateClaimId());

        if (requestDTO.getVehicleTypeIds() != null && !requestDTO.getVehicleTypeIds().isEmpty()) {
            for (String vehicleTypeId : requestDTO.getVehicleTypeIds()) {
                ElectricVehicleType vehicleType = electricVehicleTypeRepository.findById(vehicleTypeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicle type not found with ID: " + vehicleTypeId));
                serviceCampaigns.addVehicleType(vehicleType);
            }
        }

        if (requestDTO.getTechnicianIds() != null && !requestDTO.getTechnicianIds().isEmpty()) {
            for (String technicianId : requestDTO.getTechnicianIds()) {
                SCTechnician technician = scTechnicianRepository.findById(technicianId)
                        .orElseThrow(() -> new ResourceNotFoundException("Technician not found with ID: " + technicianId));
                serviceCampaigns.addTechnician(technician);
            }
        }

        serviceCampaigns.setStatus(ServiceCampaignsStatus.PLANNED);

        repository.save(serviceCampaigns);
        return mapper.toResponseDTO(serviceCampaigns);
    }

    @Override
    public ServiceCampaignsResponseDTO updateDate(String campaignId,LocalDate startDate, LocalDate endDate) {
        ServiceCampaigns serviceCampaigns = repository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Service campaign not found with ID: " + campaignId));

        serviceCampaigns.setStartDate(startDate);
        serviceCampaigns.setEndDate(endDate);
        repository.save(serviceCampaigns);

        return mapper.toResponseDTO(serviceCampaigns);
    }



    @Override
    @Transactional
    public ServiceCampaignsResponseDTO getCampaignById(String id) {
        ServiceCampaigns campaign = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service campaign not found with ID: " + id));

        return mapper.toResponseDTO(campaign);
    }

    @Transactional
    public ServiceCampaignsResponseDTO updateCampaign(String id, ServiceCampaignsUpdateDTO request) {
        // 1. Find campaign
        ServiceCampaigns campaign = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service campaign not found with ID: " + id));

        // 2. Update basic fields
        mapper.updateEntity(campaign, request);

        // 3. Update vehicle types if provided
        if (request.getVehicleTypeIds() != null) {
            List<ElectricVehicleType> vehicleTypes = new ArrayList<>();
            for (String vehicleTypeId : request.getVehicleTypeIds()) {
                ElectricVehicleType vehicleType = electricVehicleTypeRepository.findById(vehicleTypeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicle type not found with ID: " + vehicleTypeId));
                vehicleTypes.add(vehicleType);
            }
            campaign.setElectricVehicleTypes(vehicleTypes);
        }

        // 4. Update technicians if provided
        if (request.getTechnicianIds() != null) {
            List<SCTechnician> technicians = new ArrayList<>();
            for (String technicianId : request.getTechnicianIds()) {
                SCTechnician technician = scTechnicianRepository.findById(technicianId)
                        .orElseThrow(() -> new ResourceNotFoundException("Technician not found with ID: " + technicianId));
                technicians.add(technician);
            }
            campaign.setScTechnicians(technicians);
        }

        // 5. Save
        ServiceCampaigns updatedCampaign = repository.save(campaign);

        return mapper.toResponseDTO(updatedCampaign);
    }

    @Transactional(readOnly = true)
    public Page<ServiceCampaignsListDTO> getAllCampaigns(Pageable pageable) {
        Page<ServiceCampaigns> campaigns = repository.findAll(pageable);
        return campaigns.map(mapper::toListDTO);
    }

    @Transactional
    public void deleteCampaign(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Service campaign not found with ID: " + id);
        }

        repository.deleteById(id);
    }

    @Transactional
    public ServiceCampaignsResponseDTO assignVehicleTypes(String campaignId, AssignVehicleTypesDTO request) {
        // 1. Find campaign
        ServiceCampaigns campaign = repository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Service campaign not found with ID: " + campaignId));

        // 2. Find and add vehicle types
        List<ElectricVehicleType> vehicleTypes = new ArrayList<>();
        for (String vehicleTypeId : request.getVehicleTypeIds()) {
            ElectricVehicleType vehicleType = electricVehicleTypeRepository.findById(vehicleTypeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Vehicle type not found with ID: " + vehicleTypeId));
            vehicleTypes.add(vehicleType);
        }

        // 3. Set vehicle types (replace existing)
        campaign.setElectricVehicleTypes(vehicleTypes);

        // 4. Save
        ServiceCampaigns updatedCampaign = repository.save(campaign);

        return mapper.toResponseDTO(updatedCampaign);
    }

    @Transactional
    public ServiceCampaignsResponseDTO addVehicleType(String campaignId, String vehicleTypeId) {
        // 1. Find campaign
        ServiceCampaigns campaign = repository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Service campaign not found with ID: " + campaignId));

        // 2. Find vehicle type
        ElectricVehicleType vehicleType = electricVehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle type not found with ID: " + vehicleTypeId));

        // 3. Check if already assigned
        boolean exists = campaign.getVehicleTypeCampaigns().stream()
                .anyMatch(vtc -> vtc.getVehicleType().getId().equals(vehicleTypeId));

        // 4. Add only if not already linked
        if (!exists) {
            campaign.addVehicleType(vehicleType);
        }

        // 5. Save campaign
        ServiceCampaigns updatedCampaign = repository.save(campaign);

        // 6. Convert to DTO
        return mapper.toResponseDTO(updatedCampaign);
    }

    @Transactional
    public ServiceCampaignsResponseDTO removeVehicleType(String campaignId, String vehicleTypeId) {
        ServiceCampaigns campaign = repository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Service campaign not found with ID: " + campaignId));

        boolean exists = campaign.getVehicleTypeCampaigns().stream()
                .anyMatch(tc -> tc.getVehicleType().getId().equals(vehicleTypeId));

        if (!exists) {
            campaign.removeTechnician(vehicleTypeId);
        }

        ServiceCampaigns updatedCampaign = repository.save(campaign);
        return mapper.toResponseDTO(updatedCampaign);
    }

    @Transactional
    public ServiceCampaignsResponseDTO assignTechnicians(String campaignId, AssignTechniciansDTO request) {
        // 1. Find campaign
        ServiceCampaigns campaign = repository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Service campaign not found with ID: " + campaignId));

        // 2. Find and add technicians
        List<SCTechnician> technicians = new ArrayList<>();
        for (String technicianId : request.getTechnicianIds()) {
            SCTechnician technician = scTechnicianRepository.findById(technicianId)
                    .orElseThrow(() -> new ResourceNotFoundException("Technician not found with ID: " + technicianId));
            technicians.add(technician);
        }

        // 3. Set technicians (replace existing)
        campaign.setScTechnicians(technicians);

        // 4. Save
        ServiceCampaigns updatedCampaign = repository.save(campaign);

        return mapper.toResponseDTO(updatedCampaign);
    }

    @Transactional
    public ServiceCampaignsResponseDTO addTechnician(String campaignId, String technicianId) {
        ServiceCampaigns campaign = repository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Service campaign not found with ID: " + campaignId));

        SCTechnician technician = scTechnicianRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found with ID: " + technicianId));

        boolean exists = campaign.getTechnicianCampaigns().stream()
                .anyMatch(tc -> tc.getTechnician().getId().equals(technicianId));

        if (!exists) {
            campaign.addTechnician(technician);
        }

        ServiceCampaigns updatedCampaign = repository.save(campaign);
        return mapper.toResponseDTO(updatedCampaign);
    }

    @Transactional
    public ServiceCampaignsResponseDTO removeTechnician(String campaignId, String technicianId) {
        ServiceCampaigns campaign = repository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Service campaign not found with ID: " + campaignId));

        boolean exists = campaign.getTechnicianCampaigns().stream()
                .anyMatch(tc -> tc.getTechnician().getId().equals(technicianId));

        if (!exists) {
            campaign.removeTechnician(technicianId);
        }

        ServiceCampaigns updatedCampaign = repository.save(campaign);
        return mapper.toResponseDTO(updatedCampaign);
    }



    private String generateClaimId() {
        return "SCA-" + LocalDate.now().getYear() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
