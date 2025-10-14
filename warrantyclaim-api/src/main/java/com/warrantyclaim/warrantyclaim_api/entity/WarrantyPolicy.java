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
@Table(name = "Warranty_Policy")
public class WarrantyPolicy {

    @Id
    @Column(name = "ID_Warranty_Policy")
    private String idWarrantyPolicy;

    @Column(name = "Description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "Products_SparePartsTypeEVM_WarrantyPolicy",
            joinColumns = @JoinColumn(name = "ID_Warranty_Policy"),
            inverseJoinColumns = @JoinColumn(name = "ID_Products_Part_Type_EVM")
    )
    private List<ProductsSparePartsTypeEVM> productsSparePartsTypeEVMs = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Warranty_Policy_Products_Spare_Parts_Type_SC",
            joinColumns = @JoinColumn(name = "ID_Warranty_Policy"),
            inverseJoinColumns = @JoinColumn(name = "ID_Products_Part_Type_SC")
    )
    private List<ProductsSparePartsTypeSC> productsSparePartsTypeSCs = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Warranty_Policy_Electric_Vehicle_Type",
            joinColumns = @JoinColumn(name = "ID_Warranty_Policy"),
            inverseJoinColumns = @JoinColumn(name = "ID_Electric_Vehicle_Type")
    )
    private List<ElectricVehicleType> electricVehicleTypes = new ArrayList<>();
}