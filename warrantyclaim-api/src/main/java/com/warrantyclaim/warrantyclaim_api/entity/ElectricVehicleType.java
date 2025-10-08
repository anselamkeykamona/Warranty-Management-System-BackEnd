package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Electric_Vehicle_Type")
public class ElectricVehicleType {
    @Id
    @Column(name = "ID_Electric_Vehicle_Type")
    private String idElectricVehicleType;

    @Column(name = "Description")
    private String description;

    @Column(name = "Model_Name")
    private String modelName;

    @Column(name = "Year_Model_Year")
    private Integer yearModelYear;

    @Column(name = "Battery_Type")
    private String batteryType;

    @Column(name = "Price")
    private Float price;

    @Column(name = "Quantity")
    private Integer quantity;

    @OneToMany(mappedBy = "electricVehicleType")
    private List<ElectricVehicle> electricVehicles = new ArrayList<>();

    @ManyToMany(mappedBy = "electricVehicleTypes")
    private List<WarrantyPolicy> warrantyPolicies = new ArrayList<>();

    @ManyToMany(mappedBy = "electricVehicleTypes")
    private List<Recall> recalls = new ArrayList<>();

    @ManyToMany(mappedBy = "electricVehicleTypes")
    private List<ServiceCampaigns> serviceCampaigns = new ArrayList<>();

}
