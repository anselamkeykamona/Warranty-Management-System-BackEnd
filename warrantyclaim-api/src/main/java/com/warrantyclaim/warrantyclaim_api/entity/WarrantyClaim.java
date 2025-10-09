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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Vehicle_ID")
    private ElectricVehicle electricVehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SC_StaffID")
    private ScStaff scStaff;

    @OneToMany(mappedBy = "warrantyClaim")
    private List<ProductsSparePartsSC> productsSparePartsSCList = new ArrayList<>();

    @OneToMany(mappedBy = "warrantyClaim")
    private List<WorkAssign> workAssigns = new ArrayList<>();
}