package com.warrantyclaim.warrantyclaim_api.service.Implement;
import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.enums.RecallStatus;
import com.warrantyclaim.warrantyclaim_api.exception.ResourceNotFoundException;
import com.warrantyclaim.warrantyclaim_api.repository.ScTechnicianRepository;
import com.warrantyclaim.warrantyclaim_api.entity.*;
import com.warrantyclaim.warrantyclaim_api.mapper.RecallMapper;
import com.warrantyclaim.warrantyclaim_api.repository.*;
import com.warrantyclaim.warrantyclaim_api.service.RecallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecallServiceImp implements RecallService {

    @Autowired
    private RecallRepository recallRepository;
    @Autowired
    private ElectricVehicleRepository vehicleRepo;

    @Autowired
    private ScTechnicianRepository scTechnicianRepo;

    @Autowired
    private RecallMapper mapper;

    @Autowired
    private ElectricVehicleTypeRepository electricVehicleTypeRepository;

    @Override
    @Transactional
    public RecallResponseDTO createRecall(RecallCreateDTO createDTO) {

        Recall recall = mapper.toEntityRecall(createDTO);
        recall.setId(generatedIdRecall());

        if (recall.getStatus() == null) {
            recall.setStatus(RecallStatus.INACTIVE);
        }
        if (recall.getNotificationSent() == null) {
            recall.setNotificationSent(false);
        }

        if(createDTO.getVehicleId() != null && !createDTO.getVehicleId().isEmpty()) {
            for(String vehicleId : createDTO.getVehicleId()) {
                ElectricVehicle electricVehicle = vehicleRepo.findById(vehicleId)
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicle not existed with this Id " + vehicleId));
                recall.addElectricVehicle(electricVehicle);
            }
        }

        if(createDTO.getVehicleTypeIds() != null && !createDTO.getVehicleTypeIds().isEmpty()) {
            for(String vehicleTypeId : createDTO.getVehicleTypeIds()) {
                ElectricVehicleType electricVehicleType = electricVehicleTypeRepository.findById(vehicleTypeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicle not existed with this Id " + vehicleTypeId));
                recall.addVehicleType(electricVehicleType);
            }
        }

        if(createDTO.getTechnicianIds() != null && !createDTO.getTechnicianIds().isEmpty()) {
            for(String technicianId : createDTO.getTechnicianIds()) {
                SCTechnician technician = scTechnicianRepo.findById(technicianId)
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicle not existed with this Id " + technicianId));
                recall.addTechnician(technician);
            }
        }

        recallRepository.save(recall);
        return mapper.toRecallResponseDTO(recall);
    }

    @Override
    @Transactional(readOnly = true)
    public RecallResponseDTO getRecallById(String id) {
        Recall recall = recallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + id));
        return mapper.toRecallResponseDTO(recall);
    }

    @Override
    @Transactional
    public RecallResponseDTO updateRecall(String id, UpdateRecallRequest updateDTO) {
        // Find recall
        Recall recall = recallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + id));

        // Update basic fields
        mapper.updateRecallRequest(recall, updateDTO);

        // Update individual vehicles if provided
        if (updateDTO.getVehicleVinIds() != null) {
            List<ElectricVehicle> vehicles = new ArrayList<>();
            for (String vehicleId : updateDTO.getVehicleVinIds()) {
                ElectricVehicle vehicle = vehicleRepo.findById(vehicleId)
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + vehicleId));
                vehicles.add(vehicle);
            }
            recall.setElectricVehicles(vehicles);
        }

        // Update vehicle types if provided
        if (updateDTO.getVehicleTypeIds() != null) {
            List<ElectricVehicleType> vehicleTypes = new ArrayList<>();
            for (String vehicleTypeId : updateDTO.getVehicleTypeIds()) {
                ElectricVehicleType vehicleType = electricVehicleTypeRepository.findById(vehicleTypeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicle type not found with ID: " + vehicleTypeId));
                vehicleTypes.add(vehicleType);
            }
            recall.setElectricVehicleTypes(vehicleTypes);
        }

        // Update technicians if provided
        if (updateDTO.getTechnicianIds() != null) {
            List<SCTechnician> technicians = new ArrayList<>();
            for (String technicianId : updateDTO.getTechnicianIds()) {
                SCTechnician technician = scTechnicianRepo.findById(technicianId)
                        .orElseThrow(() -> new ResourceNotFoundException("Technician not found with ID: " + technicianId));
                technicians.add(technician);
            }
            recall.setScTechnicians(technicians);
        }

        // Save and return
        Recall updatedRecall = recallRepository.save(recall);
        return mapper.toRecallResponseDTO(updatedRecall);
    }

    @Override
    @Transactional
    public void deleteRecall(String id) {
        if (!recallRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recall not found with ID: " + id);
        }
        recallRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RecallsListDTO> getAllRecalls(Pageable pageable) {
        Page<Recall> recalls = recallRepository.findAll(pageable);
        return recalls.map(mapper::toListDTO);
    }

    @Override
    @Transactional
    public RecallResponseDTO updateRecallStatus(String id, RecallStatus statusDTO) {
        Recall recall = recallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + id));

        recall.setStatus(statusDTO);
        recall = recallRepository.save(recall);

        return mapper.toRecallResponseDTO(recall);
    }

    @Override
    @Transactional
    public RecallResponseDTO updateNotificationSent(String id, Boolean notificationDTO) {
        Recall recall = recallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + id));

        recall.setNotificationSent(notificationDTO);
        recall = recallRepository.save(recall);

        return mapper.toRecallResponseDTO(recall);
    }

    @Override
    @Transactional
    public RecallResponseDTO assignVehicles(String recallId, List<String> vehicleIds) {
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + recallId));

        // Find and add vehicles
        List<ElectricVehicle> vehicles = new ArrayList<>();
        for (String vehicleId : vehicleIds) {
            ElectricVehicle vehicle = vehicleRepo.findById(vehicleId)
                    .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + vehicleId));
            vehicles.add(vehicle);
        }

        // Set vehicles (replace existing)
        recall.setElectricVehicles(vehicles);

        // Save and return
        Recall updatedRecall = recallRepository.save(recall);
        return mapper.toRecallResponseDTO(updatedRecall);
    }

    @Override
    @Transactional
    public RecallResponseDTO addVehicle(String recallId, String vehicleId) {
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + recallId));

        ElectricVehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + vehicleId));

        // Check if already assigned
        boolean exists = recall.getRecallElectricVehicles().stream()
                .anyMatch(rv -> rv.getVehicle().getId().equals(vehicleId));

        // Add only if not already linked
        if (!exists) {
            recall.addElectricVehicle(vehicle);
        }

        // Save and return
        Recall updatedRecall = recallRepository.save(recall);
        return mapper.toRecallResponseDTO(updatedRecall);
    }

    @Override
    @Transactional
    public RecallResponseDTO removeVehicle(String recallId, String vehicleId) {
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + recallId));

        recall.removeElectricVehicle(vehicleId);

        Recall updatedRecall = recallRepository.save(recall);
        return mapper.toRecallResponseDTO(updatedRecall);
    }

    @Override
    @Transactional
    public RecallResponseDTO assignVehicleTypes(String recallId, List<String> vehicleTypeIds) {
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + recallId));

        // Find and add vehicle types
        List<ElectricVehicleType> vehicleTypes = new ArrayList<>();
        for (String vehicleTypeId : vehicleTypeIds) {
            ElectricVehicleType vehicleType = electricVehicleTypeRepository.findById(vehicleTypeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Vehicle type not found with ID: " + vehicleTypeId));
            vehicleTypes.add(vehicleType);
        }

        // Set vehicle types (replace existing)
        recall.setElectricVehicleTypes(vehicleTypes);

        // Save and return
        Recall updatedRecall = recallRepository.save(recall);
        return mapper.toRecallResponseDTO(updatedRecall);
    }

    @Override
    @Transactional
    public RecallResponseDTO addVehicleType(String recallId, String vehicleTypeId) {
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + recallId));

        ElectricVehicleType vehicleType = electricVehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle type not found with ID: " + vehicleTypeId));

        // Check if already assigned
        boolean exists = recall.getVehicleTypeRecalls().stream()
                .anyMatch(rvt -> rvt.getVehicleType().getId().equals(vehicleTypeId));

        // Add only if not already linked
        if (!exists) {
            recall.addVehicleType(vehicleType);
        }

        // Save and return
        Recall updatedRecall = recallRepository.save(recall);
        return mapper.toRecallResponseDTO(updatedRecall);
    }

    @Override
    @Transactional
    public RecallResponseDTO removeVehicleType(String recallId, String vehicleTypeId) {
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + recallId));

        recall.removeVehicleType(vehicleTypeId);

        Recall updatedRecall = recallRepository.save(recall);
        return mapper.toRecallResponseDTO(updatedRecall);
    }

    @Override
    @Transactional
    public RecallResponseDTO assignTechnicians(String recallId, List<String> technicianIds) {
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + recallId));

        // Find and add technicians
        List<SCTechnician> technicians = new ArrayList<>();
        for (String technicianId : technicianIds) {
            SCTechnician technician = scTechnicianRepo.findById(technicianId)
                    .orElseThrow(() -> new ResourceNotFoundException("Technician not found with ID: " + technicianId));
            technicians.add(technician);
        }

        // Set technicians (replace existing)
        recall.setScTechnicians(technicians);

        // Save and return
        Recall updatedRecall = recallRepository.save(recall);
        return mapper.toRecallResponseDTO(updatedRecall);
    }

    @Override
    @Transactional
    public RecallResponseDTO addTechnician(String recallId, String technicianId) {
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + recallId));

        SCTechnician technician = scTechnicianRepo.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found with ID: " + technicianId));

        // Check if already assigned
        boolean exists = recall.getTechniciansRecall().stream()
                .anyMatch(rt -> rt.getTechnician().getId().equals(technicianId));

        // Add only if not already linked
        if (!exists) {
            recall.addTechnician(technician);
        }

        // Save and return
        Recall updatedRecall = recallRepository.save(recall);
        return mapper.toRecallResponseDTO(updatedRecall);
    }

    @Override
    @Transactional
    public RecallResponseDTO removeTechnician(String recallId, String technicianId) {
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + recallId));

        recall.removeTechnician(technicianId);

        Recall updatedRecall = recallRepository.save(recall);
        return mapper.toRecallResponseDTO(updatedRecall);
    }

    @Override
    @Transactional(readOnly = true)
    public ReportInfoListDTO getAllReports(String recallId) {
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with ID: " + recallId));

        return mapper.toListReportResponseDTO(recall);
    }

    public String generatedIdRecall() {
        return "RE-" + LocalDate.now().getYear() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
