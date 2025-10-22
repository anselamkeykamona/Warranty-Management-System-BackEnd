package com.warrantyclaim.warrantyclaim_api.service.Implement;
import com.warrantyclaim.warrantyclaim_api.dto.*;
import com.warrantyclaim.warrantyclaim_api.repository.ScTechnicianRepository;
import com.warrantyclaim.warrantyclaim_api.entity.*;
import com.warrantyclaim.warrantyclaim_api.mapper.RecallMapper;
import com.warrantyclaim.warrantyclaim_api.repository.*;
import com.warrantyclaim.warrantyclaim_api.service.RecallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warrantyclaim.warrantyclaim_api.dto.TechnicianAssignDTO;
import com.warrantyclaim.warrantyclaim_api.dto.RecallStatusUpdateDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecallServiceImp implements RecallService {

    @Autowired
    private RecallRepository recallRepository;

    @Autowired
    private ElectricVehicleTypeRecallRepository typeRecallRepo;

    @Autowired
    private ElectricVehicleRepository vehicleRepo;

    @Autowired
    private RecallElectricVehicleRepository recallVehicleRepo;

    @Autowired
    private RecallSCTechnicianRepository technicianRepo;

    @Autowired
    private ScTechnicianRepository scTechnicianRepo;


    @Override
    public RecallResponseDTO createRecall(RecallCreateDTO dto) {
        Recall recall = RecallMapper.toEntity(dto);
        recallRepository.save(recall);

        for (String typeId : dto.getVehicleTypeIds()) {
            ElectricVehicleTypeRecall mapping = new ElectricVehicleTypeRecall(typeId, dto.getId(), null, null);
            typeRecallRepo.save(mapping);

            List<ElectricVehicle> vehicles = vehicleRepo.findByVehicleType_Id(typeId);
            for (ElectricVehicle v : vehicles) {
                RecallElectricVehicle rv = new RecallElectricVehicle(dto.getId(), v.getId(), recall, v);
                recallVehicleRepo.save(rv);
            }
        }

        return RecallMapper.toDTO(recall);
    }

    @Override
    public List<VehicleRecallDTO> getAffectedVehicles(String recallId) {
        List<RecallElectricVehicle> mappings = recallVehicleRepo.findByRecallId(recallId);
        return mappings.stream().map(m -> {
            ElectricVehicle v = m.getVehicle();
            return new VehicleRecallDTO(v.getId(), v.getName(), v.getOwner(), v.getPhoneNumber(), v.getEmail());
        }).collect(Collectors.toList());
    }

    @Override
    public void assignTechnician(TechnicianAssignDTO dto) {
        Recall recall = recallRepository.findById(dto.getRecallId()).orElseThrow();
        SCTechnician tech = scTechnicianRepo.findById(dto.getTechnicianId()).orElseThrow();
        RecallSCTechnician assignment = new RecallSCTechnician(dto.getRecallId(), dto.getTechnicianId(), LocalDateTime.now(), recall, tech);
        technicianRepo.save(assignment);
    }

    @Override
    public void updateStatus(RecallStatusUpdateDTO dto) {
        Recall recall = recallRepository.findById(dto.getRecallId()).orElseThrow();
        recall.setStatus(dto.getStatus());
        recallRepository.save(recall);
    }
}
