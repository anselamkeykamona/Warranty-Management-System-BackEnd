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
@Table(name = "Products_Spare_Parts_SC")
public class ProductsSparePartsSC {
    @Id
    @Column(name = "ID_Products_Part_SC")
    private String idProductsPartSc;

    @Column(name = "Description")
    private String description;

    @Column(name = "Part_Name")
    private String partName;

    @Column(name = "Year_Model_Year")
    private Integer yearModelYear;

    @Column(name = "Total_Amount_of_Product")
    private Integer totalAmountOfProduct;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Manufacturer")
    private String manufacturer;

    @Column(name = "Condition")
    private String condition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Products_Part_Type_SC")
    private ProductsSparePartsTypeSC productsSparePartsTypeSC;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClaimID")
    private WarrantyClaim warrantyClaim;
}
