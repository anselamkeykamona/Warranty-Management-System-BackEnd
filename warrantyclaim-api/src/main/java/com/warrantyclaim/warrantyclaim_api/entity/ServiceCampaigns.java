package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Service_Campaigns")
public class ServiceCampaigns {
    @Id
    @Column(name = "CampaignsID", length = 50)
    private String id;

    @Column(name = "CampaignsTypeName", length = 100)
    private String typeName;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(length = 45)
    private String requiredParts;

    @Column(length = 45)
    private String description;

    @Column(length = 50)
    private String status;

    private Boolean notificationSent;
}
