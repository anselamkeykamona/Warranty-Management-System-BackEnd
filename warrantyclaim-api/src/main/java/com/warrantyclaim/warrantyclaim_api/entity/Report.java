package com.warrantyclaim.warrantyclaim_api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Report")
public class Report {

    @Id
    @Column(name = "ID_Report")
    private String idReport;

    @Column(name = "Description")
    private String description;

    @Column(name = "Image")
    private String image;

    @Column(name = "Error")
    private String error;

    @Column(name = "Status")
    private String status;

    @Column(name = "ReportName")
    private String reportName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CampaignsID")
    private ServiceCampaigns serviceCampaigns;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Recall_ID")
    private Recall recall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SC_StaffID")
    private ScStaff scStaff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVM_Staff_ID")
    private EVMStaff evmStaff;
}