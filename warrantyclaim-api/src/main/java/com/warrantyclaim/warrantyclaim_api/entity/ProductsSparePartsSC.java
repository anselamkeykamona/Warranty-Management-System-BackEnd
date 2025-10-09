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
    @Column(name = "ID_Product_Serial_SC")
    private String idProductSerialSc;

    @Column(name = "Name_Product")
    private String nameProduct;

    @Column(name = "Year_of_Manufacture")
    private LocalDate yearOfManufacture;

    @Column(name = "Price")
    private Float price;

    @Column(name = "Warranty_Period")
    private Integer warrantyPeriod;

    @Column(name = "Description")
    private String description;

    @Column(name = "Brand")
    private String brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Products_Part_Type_SC")
    private ProductsSparePartsTypeSC productsSparePartsTypeSC;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClaimID")
    private WarrantyClaim warrantyClaim;
}