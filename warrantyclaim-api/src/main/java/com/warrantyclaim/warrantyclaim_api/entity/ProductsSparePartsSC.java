package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Products_Spare_Parts_SC")
public class ProductsSparePartsSC {
    @Id
    @Column(name = "ID_Product_Serial_SC", length = 50)
    private String id;

    @Column(name = "Name_Product", length = 100)
    private String name;

    @Column(name = "Model_Year")
    private LocalDate yearOfManufacture;

    @Column(name = "Price")
    private float price;

    @Column(name = "Warranty_Period")
    private Integer warrantyPeriod;

    @Column(name = "Description", length = 45)
    private String description;

    @Column(name = "Brand", length = 100)
    private String brand;

    @ManyToOne
    @JoinColumn(name = "ID_Products_Part_Type_SC")
    private ProductsSparePartsTypeSC partType;

    @ManyToOne
    @JoinColumn(name = "ClaimID")
    private WarrantyClaim claim;

    @ManyToOne
    @JoinColumn(name = "Vehicle_VIN_ID")
    private ElectricVehicle electricVehicle;
}
