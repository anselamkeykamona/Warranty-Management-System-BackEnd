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
    @Column(name = "Recall_ID", length = 50)
    private String id;

    @Column(name = "RecallName", length = 100)
    private String name;

    @Column(length = 255)
    private String issueDescription;

    private LocalDate startDate;

    @Column(length = 255)
    private String requiredAction;

    @Column(length = 255)
    private String partsRequired;

    @Column(length = 50)
    private String status;

    private Boolean notificationSent;

    @Column(length = 50)
    private String evmApprovalStatus;
}
