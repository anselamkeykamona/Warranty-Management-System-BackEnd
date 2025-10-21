package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.dto.PartsRequestCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.PartsRequestResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.PartsRequestUpdateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.PartsResponseListDTO;
import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicle;
import com.warrantyclaim.warrantyclaim_api.entity.PartsRequest;
import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsTypeSC;
import com.warrantyclaim.warrantyclaim_api.exception.ResourceNotFoundException;
import com.warrantyclaim.warrantyclaim_api.mapper.PartsRequestMapper;
import com.warrantyclaim.warrantyclaim_api.repository.ElectricVehicleRepository;
import com.warrantyclaim.warrantyclaim_api.repository.PartsRequestRepository;
import com.warrantyclaim.warrantyclaim_api.repository.ProductsSparePartsTypeSCRepository;
import com.warrantyclaim.warrantyclaim_api.service.PartsRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class PartsRequestServiceImp implements PartsRequestService {

    private final PartsRequestRepository partsRequestRepository;
    private final ProductsSparePartsTypeSCRepository partTypeRepository;
    private final PartsRequestMapper partsRequestMapper;
    private final ElectricVehicleRepository electricVehicleRepository;

    @Transactional
    public PartsRequestResponseDTO createPartsRequest(PartsRequestCreateDTO request) {
        // 1. Validate part type exists
        ProductsSparePartsTypeSC partType = partTypeRepository.findById(request.getPartTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Part type not found with ID: " + request.getPartTypeId()));

        //Create entity
        PartsRequest partsRequest = partsRequestMapper.toEntity(request);

        if(request.getVehicleId() != null) {
            ElectricVehicle electricVehicle = electricVehicleRepository.findById(request.getVehicleId())
                    .orElseThrow(() -> new ResourceNotFoundException("This electric vehicle not existed with this ID" + request.getVehicleId()));
            partsRequest.setElectricVehicle(electricVehicle);
        }




//        // 2. Validate staff exists
//        SCStaff staff = scStaffRepository.findById(request.getStaffId())
//                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + request.getStaffId()));

        // 3. Update entity

        partsRequest.setId(generateRequestId());
        partsRequest.setPartType(partType);
//        partsRequest.setStaff(staff);

        // 4. Save
        PartsRequest savedRequest = partsRequestRepository.save(partsRequest);

        return partsRequestMapper.toResponseDTO(savedRequest);
    }

    /**
     * Get parts request by ID
     */
    @Transactional(readOnly = true)
    public PartsRequestResponseDTO getPartsRequestById(String id) {
        PartsRequest partsRequest = partsRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parts request not found with ID: " + id));

        return partsRequestMapper.toResponseDTO(partsRequest);
    }

    /**
     * Get all parts requests (with pagination)
     */
    @Transactional(readOnly = true)
    public Page<PartsResponseListDTO> getAllPartsRequests(Pageable pageable) {
        Page<PartsRequest> requests = partsRequestRepository.findAll(pageable);
        return requests.map(partsRequestMapper::toListDTO);
    }

    /**
     * Get all parts requests (without pagination)
     */
    @Transactional(readOnly = true)
    public List<PartsResponseListDTO> getAllPartsRequests() {
        List<PartsRequest> requests = partsRequestRepository.findAll();
        return requests.stream()
                .map(partsRequestMapper::toListDTO)
                .collect(Collectors.toList());
    }

    /**
     * Update parts request
     */
    @Transactional
    public PartsRequestResponseDTO updatePartsRequest(String id, PartsRequestUpdateDTO request) {
        // 1. Find parts request
        PartsRequest partsRequest = partsRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parts request not found with ID: " + id));


        // 2. Update part type if changed
        if (request.getPartTypeId() != null) {
            ProductsSparePartsTypeSC partType = partTypeRepository.findById(request.getPartTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Part type not found with ID: " + request.getPartTypeId()));
            partsRequest.setPartType(partType);
        }

        if(request.getVehicleId() != null) {
            ElectricVehicle electricVehicle = electricVehicleRepository.findById(request.getVehicleId())
                    .orElseThrow(() -> new ResourceNotFoundException("This electric vehicle not existed with this ID" + request.getVehicleId()));
            partsRequest.setElectricVehicle(electricVehicle);
        }

        // 3. Update staff if changed
//        if (request.getStaffId() != null) {
//            SCStaff staff = scStaffRepository.findById(request.getStaffId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + request.getStaffId()));
//            partsRequest.setStaff(staff);
//        }

        partsRequestMapper.updateEntity(partsRequest, request);
        PartsRequest updatedRequest = partsRequestRepository.save(partsRequest);

        return partsRequestMapper.toResponseDTO(updatedRequest);
    }

    /**
     * Delete parts request
     */
    @Transactional
    public void deletePartsRequest(String id) {
        if (!partsRequestRepository.existsById(id)) {
            throw new ResourceNotFoundException("Parts request not found with ID: " + id);
        }

        partsRequestRepository.deleteById(id);
    }


    /**
     * Generate unique request ID
     */
    private String generateRequestId() {
        return "PR-" + LocalDate.now().getYear() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
