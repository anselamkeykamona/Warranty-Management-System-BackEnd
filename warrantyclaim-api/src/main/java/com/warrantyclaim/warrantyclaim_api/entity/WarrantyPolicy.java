package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Warranty_Policy")
public class WarrantyPolicy {
    @Id
    @Column(name = "ID_Warranty_Policy")
    private String idWarrantyPolicy;

    @Column(name = "Description")
    private String description;

    @ManyToMany
    @JoinTable (
        name = "Warranty_Policy_Products_Spare_Parts_Type_SC",
        joinColumns = @JoinColumn(name = "id_warranty_policy"),
        inverseJoinColumns = @JoinColumn(name = "id_products_part_type_sc")
    )
    private List<ProductsSparePartsTypeSC> productsSparePartsTypeSCs = new ArrayList<>();

    @ManyToMany
    @JoinTable (
            name = "Products_SparePartsTypeEVM_WarrantyPolicy",
            joinColumns = @JoinColumn(name = "id_warranty_policy"),
            inverseJoinColumns = @JoinColumn(name = "id_products_part_type_evm")
    )
    private List<ProductsSparePartsTypeEVM> productsSparePartsTypeEVMS = new ArrayList<>();

    @ManyToMany
    @JoinTable (
            name = "Warranty_Policy_Electric_Vehicle_Type",
            joinColumns = @JoinColumn(name = "id_warranty_policy"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private List<ElectricVehicleType> electricVehicleTypes = new ArrayList<>();






}
