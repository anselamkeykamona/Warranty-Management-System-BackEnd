package com.warrantyclaim.warrantyclaim_api.entity;

import com.warrantyclaim.warrantyclaim_api.enums.VehicleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "Electric_Vehicle")
public class ElectricVehicle {
    @Id
    @Column(name = "Vehicle_VIN_ID", length = 50)
    private String id;

    @Column(name = "Vehicle_Name", length = 100)
    private String name;

    @Column(name = "Total_KM")
    private Float totalKm;

    @Column(length = 250)
    private String picture;

    @Column(name = "Production_Date")
    private LocalDate productionDate;

    @Column(length = 100)
    private String owner;

    @Column(name = "Phone_Number", length = 20)
    private String phoneNumber;

    @Column(length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private VehicleStatus status;

    @ManyToOne
    @JoinColumn(name = "ID_Electric_Vehicle_Type")
    private ElectricVehicleType vehicleType;
}
