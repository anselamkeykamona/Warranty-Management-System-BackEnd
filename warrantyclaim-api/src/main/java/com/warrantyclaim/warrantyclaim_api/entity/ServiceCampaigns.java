package com.warrantyclaim.warrantyclaim_api.entity;

import com.warrantyclaim.warrantyclaim_api.enums.ServiceCampaignsStatus;
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

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "RequiredParts",length = 45)
    private String requiredParts;

    @Column(name = "Description", length = 45)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", length = 50)
    private ServiceCampaignsStatus status;

    @Column(name = "NotificationSent")
    private Boolean notificationSent;
}
