package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Service_Campaigns")
public class ServiceCampaigns {
    @Id
    @Column(name = "CampaignsID")
    private String campaignsId;

    @Column(name = "CampaignsTypeName")
    private String campaignsTypeName;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "RequiredParts")
    private String requiredParts;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status")
    private String status;

    @Column(name = "NotificationSent")
    private Boolean notificationSent;

    @OneToMany(mappedBy = "serviceCampaigns")
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "serviceCampaigns")
    private List<WorkAssign> workAssigns = new ArrayList<>();

    @ManyToMany
    @JoinTable (
            name = "Electric_Vehicle_Type_Service_Campaigns",
            joinColumns = @JoinColumn(name = "CampaignsID"),
            inverseJoinColumns = @JoinColumn(name = "ID_Electric_Vehicle_Type")
    )
    private List<ElectricVehicleType> electricVehicleTypes = new ArrayList<>();
}
