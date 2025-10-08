package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Work_Assign")
public class WorkAssign {
    @Id
    @Column(name = "WorkAssign_ID")
    private String workAssignId;

    @Column(name = "Assign_Date")
    private LocalDate assignDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClaimID")
    private WarrantyClaim warrantyClaim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SC_TechnicianID")
    private ScTechnician scTechnician;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Vehicle_ID")
    private ElectricVehicle electricVehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Recall_ID")
    private Recall recall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CampaignsID")
    private ServiceCampaigns serviceCampaigns;
}
