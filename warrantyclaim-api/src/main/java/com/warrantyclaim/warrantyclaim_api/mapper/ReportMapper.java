package com.warrantyclaim.warrantyclaim_api.mapper;

import com.warrantyclaim.warrantyclaim_api.dto.ReportCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ReportResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ReportUpdateDTO;
import com.warrantyclaim.warrantyclaim_api.entity.EVMStaff;
import com.warrantyclaim.warrantyclaim_api.entity.Recall;
import com.warrantyclaim.warrantyclaim_api.entity.Report;
import com.warrantyclaim.warrantyclaim_api.entity.SCStaff;
import com.warrantyclaim.warrantyclaim_api.entity.ServiceCampaigns;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyClaim;
import com.warrantyclaim.warrantyclaim_api.enums.ReportStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ReportMapper {

    public Report toEntity(ReportCreateDTO dto) {
        Report report = new Report();
        report.setId("RPT-" + UUID.randomUUID().toString().substring(0, 8));
        report.setReportName(dto.getReportName());
        report.setDescription(dto.getDescription());
        report.setError(dto.getError());
        report.setImage(dto.getImage());
        report.setStatus(dto.getStatus() != null ? dto.getStatus() : ReportStatus.NEW);
        report.setCreatedDate(LocalDate.now());
        report.setUpdatedDate(LocalDate.now());
        return report;
    }

    public void updateEntityFromDTO(Report report, ReportUpdateDTO dto) {
        if (dto.getReportName() != null) {
            report.setReportName(dto.getReportName());
        }
        if (dto.getDescription() != null) {
            report.setDescription(dto.getDescription());
        }
        if (dto.getError() != null) {
            report.setError(dto.getError());
        }
        if (dto.getImage() != null) {
            report.setImage(dto.getImage());
        }
        if (dto.getStatus() != null) {
            report.setStatus(dto.getStatus());
        }
        report.setUpdatedDate(LocalDate.now());
    }

    public ReportResponseDTO toDTO(Report report) {
        ReportResponseDTO dto = new ReportResponseDTO();
        dto.setId(report.getId());
        dto.setReportName(report.getReportName());
        dto.setDescription(report.getDescription());
        dto.setError(report.getError());
        dto.setImage(report.getImage());
        dto.setStatus(report.getStatus());
        dto.setCreatedDate(report.getCreatedDate());
        dto.setUpdatedDate(report.getUpdatedDate());

        // Set campaign data if available
        ServiceCampaigns campaign = report.getCampaign();
        if (campaign != null) {
            dto.setCampaignId(campaign.getId());
            dto.setCampaignName(campaign.getTypeName()); // Changed from getName to getTypeName
        }

        // Set recall data if available
        Recall recall = report.getRecall();
        if (recall != null) {
            dto.setRecallId(recall.getId());
            dto.setRecallTitle(recall.getName()); // Changed from getTitle to getName
        }

        // Set warranty claim data if available
        WarrantyClaim warrantyClaim = report.getWarrantyClaim();
        if (warrantyClaim != null) {
            dto.setWarrantyClaimId(warrantyClaim.getId());
        }

        // Set staff data if available
        SCStaff staff = report.getStaff();
        if (staff != null) {
            dto.setStaffId(staff.getId());
            dto.setStaffName(staff.getAccountName()); // Changed from getFullName to getAccountName
        }

        // Set EVM staff data if available
        EVMStaff evmStaff = report.getEvm();
        if (evmStaff != null) {
            dto.setEvmStaffId(evmStaff.getId());
            dto.setEvmStaffName(evmStaff.getName()); // Changed from getFullName to getName
        }

        return dto;
    }

    public List<ReportResponseDTO> toDTOList(List<Report> reports) {
        return reports.stream().map(this::toDTO).collect(Collectors.toList());
    }
}