package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.dto.CreateReportRequest;
import com.warrantyclaim.warrantyclaim_api.dto.ReportDTO;
import com.warrantyclaim.warrantyclaim_api.dto.UpdateReportRequest;
import com.warrantyclaim.warrantyclaim_api.entity.Report;
import com.warrantyclaim.warrantyclaim_api.entity.Recall;
import com.warrantyclaim.warrantyclaim_api.entity.ServiceCampaign;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyClaim;
import com.warrantyclaim.warrantyclaim_api.exception.ResourceNotFoundException;
import com.warrantyclaim.warrantyclaim_api.mapper.ReportMapper;
import com.warrantyclaim.warrantyclaim_api.repository.ReportRepository;
import com.warrantyclaim.warrantyclaim_api.repository.RecallRepository;
import com.warrantyclaim.warrantyclaim_api.repository.ServiceCampaignRepository;
import com.warrantyclaim.warrantyclaim_api.repository.WarrantyClaimRepository;
import com.warrantyclaim.warrantyclaim_api.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ServiceCampaignRepository serviceCampaignRepository;
    private final RecallRepository recallRepository;
    private final WarrantyClaimRepository warrantyClaimRepository;
    private final ReportMapper reportMapper;

    @Override
    @Transactional
    public ReportDTO createReport(CreateReportRequest request) {
        Report report = new Report();
        report.setTitle(request.getTitle());
        report.setDescription(request.getDescription());
        return reportMapper.toDTO(reportRepository.save(report));
    }

    @Override
    public ReportDTO getReportById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));
        return reportMapper.toDTO(report);
    }

    @Override
    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll().stream()
                .map(reportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReportDTO updateReport(Long id, UpdateReportRequest request) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));
        
        if (request.getTitle() != null) {
            report.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            report.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            report.setStatus(request.getStatus());
        }
        
        return reportMapper.toDTO(reportRepository.save(report));
    }

    @Override
    @Transactional
    public void deleteReport(Long id) {
        if (!reportRepository.existsById(id)) {
            throw new ResourceNotFoundException("Report not found with id: " + id);
        }
        reportRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ReportDTO assignServiceCampaign(Long reportId, Long serviceCampaignId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + reportId));
        
        ServiceCampaign serviceCampaign = serviceCampaignRepository.findById(serviceCampaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Service Campaign not found with id: " + serviceCampaignId));
        
        report.setServiceCampaign(serviceCampaign);
        return reportMapper.toDTO(reportRepository.save(report));
    }

    @Override
    @Transactional
    public ReportDTO assignRecall(Long reportId, Long recallId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + reportId));
        
        Recall recall = recallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with id: " + recallId));
        
        report.setRecall(recall);
        return reportMapper.toDTO(reportRepository.save(report));
    }

    @Override
    @Transactional
    public ReportDTO assignWarrantyClaim(Long reportId, Long warrantyClaimId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + reportId));
        
        WarrantyClaim warrantyClaim = warrantyClaimRepository.findById(warrantyClaimId)
                .orElseThrow(() -> new ResourceNotFoundException("Warranty Claim not found with id: " + warrantyClaimId));
        
        report.setWarrantyClaim(warrantyClaim);
        return reportMapper.toDTO(reportRepository.save(report));
    }
}