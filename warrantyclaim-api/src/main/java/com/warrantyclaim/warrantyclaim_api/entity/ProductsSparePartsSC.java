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

    private LocalDate yearOfManufacture;
    private Float price;
    private Integer warrantyPeriod;

    @Column(length = 45)
    private String description;

    @Column(length = 100)
    private String brand;

    @ManyToOne
    @JoinColumn(name = "ID_Products_Part_Type_SC")
    private ProductsSparePartsTypeSC partType;

    @ManyToOne
    @JoinColumn(name = "ClaimID")
    private WarrantyClaim claim;
}
