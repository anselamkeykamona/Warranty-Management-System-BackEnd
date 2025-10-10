package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Products_Spare_Parts_EVM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsSparePartsEVM {

    @Id
    @Column(name = "ID_Product_Serial_EVM")
    private String id;

    @Column(name = "Name_Product")
    private String nameProduct;

    @Column(name = "Year_of_Manufacture")
    private LocalDate yearOfManufacture;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Warranty_Period")
    private Integer warrantyPeriod;

    @Column(name = "Description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Products_Part_Type_EVM")
    private ProductsSparePartsTypeEVM productsSparePartsTypeEVM;
}
