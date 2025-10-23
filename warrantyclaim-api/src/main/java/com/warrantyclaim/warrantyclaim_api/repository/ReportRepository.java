package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, String> {
    List<Report> findByStaffId(String staffId);
    List<Report> findByEvmId(String evmId);
    List<Report> findByCampaignId(String campaignId);
    List<Report> findByRecallId(String recallId);
    List<Report> findByWarrantyClaimId(String warrantyClaimId);
}