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
@Table(name = "Recall")
public class Recall {

    @Id
    @Column(name = "Recall_ID")
    private String recallId;

    @Column(name = "RecallName")
    private String recallName;

    @Column(name = "IssueDescription")
    private String issueDescription;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "RequiredAction")
    private String requiredAction;

    @Column(name = "PartsRequired")
    private String partsRequired;

    @Column(name = "Status")
    private String status;

    @Column(name = "NotificationSent")
    private Boolean notificationSent;

    @Column(name = "EVMApprovalStatus")
    private String evmApprovalStatus;

    @OneToMany(mappedBy = "recall")
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "recall")
    private List<WorkAssign> workAssigns = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Electric_Vehicle_Type_Recall",
            joinColumns = @JoinColumn(name = "Recall_ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_Electric_Vehicle_Type")
    )
    private List<ElectricVehicleType> electricVehicleTypes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Recall_Electric_Vehicle",
            joinColumns = @JoinColumn(name = "Recall_ID"),
            inverseJoinColumns = @JoinColumn(name = "Vehicle_ID")
    )
    private List<ElectricVehicle> electricVehicles = new ArrayList<>();
}