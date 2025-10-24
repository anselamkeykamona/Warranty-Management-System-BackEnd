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
    @Column(name = "ID_Warranty_Policy", length = 50)
    private String id;

    @Column(length = 255)
    private String description;


    @OneToMany(mappedBy = "warrantyPolicy")
    private List<WarrantyPolicyElectricVehicleType> warrantyPolicy;

    @OneToMany(mappedBy = "warrantyPolicy")
    private List<WarrantyPolicyProductsSparePartsTypeSC> warrantyPolicyProductsSparePartsTypeSCs;

    @OneToMany(mappedBy = "warrantyPolicy")
    private List<ProductsSparePartsTypeEVMWarrantyPolicy> warrantyPoliciesEvmTypes;

}
