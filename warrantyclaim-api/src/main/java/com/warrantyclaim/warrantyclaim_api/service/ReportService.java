package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.dto.CreateReportRequest;
import com.warrantyclaim.warrantyclaim_api.dto.ReportDTO;
import com.warrantyclaim.warrantyclaim_api.dto.UpdateReportRequest;
import java.util.List;

public interface ReportService {
    ReportDTO createReport(CreateReportRequest request);
    ReportDTO getReportById(Long id);
    List<ReportDTO> getAllReports();
    ReportDTO updateReport(Long id, UpdateReportRequest request);
    void deleteReport(Long id);
    
    ReportDTO assignServiceCampaign(Long reportId, Long serviceCampaignId);
    ReportDTO assignRecall(Long reportId, Long recallId);
    ReportDTO assignWarrantyClaim(Long reportId, Long warrantyClaimId);
}