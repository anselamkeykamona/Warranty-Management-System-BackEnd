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
@Table(name = "Electric_Vehicle")
public class ElectricVehicle {
    @Id
    @Column(name = "Vehicle_ID")
    private String vehicleId;

    @Column(name = "Vehicle_Name")
    private String vehicleName;

    @Column(name = "Total_KM")
    private Float totalKm;

    @Column(name = "VIN")
    private String vin;

    @Column(name = "Picture")
    private String picture;

    @Column(name = "Production_Date")
    private LocalDate productionDate;

    @Column(name = "Owner")
    private String owner;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Email")
    private String email;

    @Column(name = "Status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Electric_Vehicle_Type")
    private ElectricVehicleType electricVehicleType;

    @OneToMany(mappedBy = "electricVehicle")
    private List<WarrantyClaim> warrantyClaims = new ArrayList<>();
}