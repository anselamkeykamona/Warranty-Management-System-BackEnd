package com.warrantyclaim.warrantyclaim_api.controller;

import com.warrantyclaim.warrantyclaim_api.dto.PartsRequestCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.PartsRequestResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.PartsRequestUpdateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.PartsResponseListDTO;
import com.warrantyclaim.warrantyclaim_api.service.PartsRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parts-requests")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PartsRequestController {

    private final PartsRequestService partsRequestService;

    @PostMapping
    public ResponseEntity<PartsRequestResponseDTO> createPartsRequest(
            @Valid @RequestBody PartsRequestCreateDTO request) {
        PartsRequestResponseDTO response = partsRequestService.createPartsRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get parts request by ID
     * GET /api/parts-requests/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<PartsRequestResponseDTO> getPartsRequestById(@PathVariable String id) {
        PartsRequestResponseDTO response = partsRequestService.getPartsRequestById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all parts requests (paginated)
     * GET /api/parts-requests?page=0&size=10
     */
    @GetMapping
    public ResponseEntity<Page<PartsResponseListDTO>> getAllPartsRequests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "requestDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<PartsResponseListDTO> response = partsRequestService.getAllPartsRequests(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartsRequestResponseDTO> updatePartsRequest(
            @PathVariable String id,
            @Valid @RequestBody PartsRequestUpdateDTO request) {
        PartsRequestResponseDTO response = partsRequestService.updatePartsRequest(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete parts request
     * DELETE /api/parts-requests/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartsRequest(@PathVariable String id) {
        partsRequestService.deletePartsRequest(id);
        return ResponseEntity.noContent().build();
    }
}
