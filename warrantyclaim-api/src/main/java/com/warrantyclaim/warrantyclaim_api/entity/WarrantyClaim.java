package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "ClaimID", length = 50)
    private String id;

    @Column(length = 100)
    private String customerName;

    @Column(length = 20)
    private String customerPhone;

    private LocalDate claimDate;

    @Column(length = 100)
    private String issueDescription;

    @Column(length = 50)
    private String status;

    @Column(length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "Vehicle_VIN_ID")
    private ElectricVehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "SC_StaffID")
    private SCStaff staff;

    @ManyToOne
    @JoinColumn(name = "SC_TechnicianID")
    private SCTechnician technician;
}
