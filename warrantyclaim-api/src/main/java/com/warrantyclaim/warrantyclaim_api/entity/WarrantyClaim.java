package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Warranty_Claim")
public class WarrantyClaim {
    @Id
    @Column(name = "ClaimID")
    private String claimId;

    @Column(name = "CustomerName")
    private String customerName;

    @Column(name = "CustomerPhone")
    private String customerPhone;

    @Column(name = "ClaimDate")
    private LocalDate claimDate;

    @Column(name = "IssueDescription")
    private String issueDescription;

    @Column(name = "Status")
    private String status;

    @Column(name = "Email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "Vehicle_ID")
    private ElectricVehicle electricVehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SC_StaffID", nullable = false)
    private ScStaff scStaff;
}
