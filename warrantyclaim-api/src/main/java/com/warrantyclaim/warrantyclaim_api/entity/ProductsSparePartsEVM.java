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
@Table(name = "Products_Spare_Parts_EVM")
public class ProductsSparePartsEVM {

    @Id
    @Column(name = "ID_Product_Serial_EVM", length = 50)
    private String id;

    @Column(name = "Name_Product", length = 100)
    private String name;

    @Column(name = "Year_of_Manufacture")
    private LocalDate yearOfManufacture;

    @Column(length = 100)
    private String brand;

    private Float price;

    @Column(length = 45)
    private String description;

    @ManyToOne
    @JoinColumn(name = "ID_Products_Part_Type_EVM")
    private ProductsSparePartsTypeEVM partType;
}
