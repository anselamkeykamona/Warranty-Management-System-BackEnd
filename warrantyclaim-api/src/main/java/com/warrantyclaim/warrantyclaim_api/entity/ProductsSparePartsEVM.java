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
@Table(name = "Products_Spare_Parts_Type_EVM")
public class ProductsSparePartsEVM {
    @Id
    @Column(name = "ID_Product_Serial_EVM")
    private String idProductSerialEvm;

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
    @JoinColumn(name = "id_product_serial_evm")
    private ProductsSparePartsTypeEVM productsSparePartsTypeEVM;
}
