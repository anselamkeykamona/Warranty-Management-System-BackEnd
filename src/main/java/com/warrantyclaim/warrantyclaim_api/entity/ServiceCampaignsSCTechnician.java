package com.warrantyclaim.warrantyclaim_api.entity;
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

@Table(name = "Service_Campaigns_SC_Technician")
@IdClass(ServiceCampaignsSCTechnicianId.class)
public class ServiceCampaignsSCTechnician {

    @Id
    @Column(name = "CampaignsID", length = 50)
    private String campaignId;

    @Id
    @Column(name = "SC_TechnicianID", length = 50)
    private String technicianId;

    @Column(name = "AssignedDate")
    private LocalDateTime assignedDate;

    @ManyToOne
    @JoinColumn(name = "CampaignsID", insertable = false, updatable = false)
    private ServiceCampaigns campaign;

    @ManyToOne
    @JoinColumn(name = "SC_TechnicianID", insertable = false, updatable = false)
    private SCTechnician technician;
}

