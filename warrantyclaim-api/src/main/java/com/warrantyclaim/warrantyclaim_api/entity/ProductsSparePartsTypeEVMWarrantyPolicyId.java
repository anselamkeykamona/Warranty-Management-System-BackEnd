package com.warrantyclaim.warrantyclaim_api.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Getter
@Setter
public class ProductsSparePartsTypeEVMWarrantyPolicyId implements Serializable {

    private String partTypeId;
    private String warrantyPolicyId;

    // bắt buộc phải có constructor không tham số
    public ProductsSparePartsTypeEVMWarrantyPolicyId() {}

    // constructor đầy đủ
    public ProductsSparePartsTypeEVMWarrantyPolicyId(String partTypeId, String warrantyPolicyId) {
        this.partTypeId = partTypeId;
        this.warrantyPolicyId = warrantyPolicyId;
    }

    // equals() và hashCode() là bắt buộc
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductsSparePartsTypeEVMWarrantyPolicyId)) return false;
        ProductsSparePartsTypeEVMWarrantyPolicyId that = (ProductsSparePartsTypeEVMWarrantyPolicyId) o;
        return Objects.equals(partTypeId, that.partTypeId)
                && Objects.equals(warrantyPolicyId, that.warrantyPolicyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partTypeId, warrantyPolicyId);
    }


}
