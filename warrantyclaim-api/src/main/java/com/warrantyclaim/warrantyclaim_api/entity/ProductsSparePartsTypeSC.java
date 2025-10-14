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

@Table(name = "Products_Spare_Parts_Type_SC")
public class ProductsSparePartsTypeSC {
    @Id
    @Column(name = "ID_Products_Part_Type_SC", length = 50)
    private String id;

    @Column(length = 45)
    private String description;

    @Column(name = "Part_Name", length = 100)
    private String partName;

    private Integer yearModelYear;
    private Integer totalAmountOfProduct;
    private Float price;

    @Column(length = 100)
    private String manufacturer;

    @Column(name = "Condition", length = 50)
    private String condition;
}
