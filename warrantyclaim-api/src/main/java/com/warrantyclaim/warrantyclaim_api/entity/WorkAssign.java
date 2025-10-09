package com.warrantyclaim.warrantyclaim_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Work_Assign")
public class WorkAssign {

    @Id
    @Column(name = "Work_Assign_ID")
    private String workAssignId;

    @Column(name = "Work_Assign_Date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime workAssignDate;

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