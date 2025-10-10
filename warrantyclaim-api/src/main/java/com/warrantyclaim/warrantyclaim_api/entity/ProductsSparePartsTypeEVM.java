package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Products_Spare_Parts_Type_EVM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsSparePartsTypeEVM {

    @Id
    @Column(name = "ID_Products_Part_Type_EVM")
    private String id;

    @Column(name = "Description")
    private String description;

    @Column(name = "Part_Name")
    private String partName;

    @Column(name = "Year_Model_Year")
    private Integer yearModelYear;

    @Column(name = "Total_Amount_Of_Product")
    private Integer totalAmountOfProduct;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Manufacturer")
    private String manufacturer;

    @Column(name = "Condition")
    private String condition;

    @OneToMany(mappedBy = "productsSparePartsTypeEVM", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductsSparePartsEVM> productsSparePartsEVM;
}
