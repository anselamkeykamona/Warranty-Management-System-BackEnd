package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Products_Spare_Parts_Type_EVM")
public class ProductsSparePartsTypeEVM {

    @Id
    @Column(name = "ID_Products_Part_Type_EVM")
    private String idProductsPartTypeEvm;

    @Column(name = "Description")
    private String description;

    @Column(name = "Part_Name")
    private String partName;

    @Column(name = "Year_Model_Year")
    private Integer yearModelYear;

    @Column(name = "Total_Amount_Of_Product")
    private Integer totalAmountOfProduct;

    @Column(name = "Price")
    private Float price;

    @Column(name = "Manufacturer")
    private String manufacturer;

    @Column(name = "Condition")
    private String condition;

    @OneToMany(mappedBy = "productsSparePartsTypeEVM")
    private List<ProductsSparePartsEVM> productsSparePartsEVMList = new ArrayList<>();

    @ManyToMany(mappedBy = "productsSparePartsTypeEVMs")
    private List<WarrantyPolicy> warrantyPolicies = new ArrayList<>();
}