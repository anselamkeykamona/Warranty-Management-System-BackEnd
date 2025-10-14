package com.warrantyclaim.warrantyclaim_api.entity;

import java.io.Serializable;
import java.util.Objects;

public class WarrantyPolicyProductsSparePartsTypeSCId implements Serializable {

    private String warrantyPolicyId;
    private String partTypeId;

    public WarrantyPolicyProductsSparePartsTypeSCId() {}

    public WarrantyPolicyProductsSparePartsTypeSCId(String warrantyPolicyId, String partTypeId) {
        this.warrantyPolicyId = warrantyPolicyId;
        this.partTypeId = partTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarrantyPolicyProductsSparePartsTypeSCId)) return false;
        WarrantyPolicyProductsSparePartsTypeSCId that = (WarrantyPolicyProductsSparePartsTypeSCId) o;
        return Objects.equals(warrantyPolicyId, that.warrantyPolicyId) &&
                Objects.equals(partTypeId, that.partTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warrantyPolicyId, partTypeId);
    }

    // Optional: getters & setters (nếu bạn không dùng Lombok cho class này)

    public String getPartTypeId() {
        return partTypeId;
    }

    public void setPartTypeId(String partTypeId) {
        this.partTypeId = partTypeId;
    }

    public String getWarrantyPolicyId() {
        return warrantyPolicyId;
    }

    public void setWarrantyPolicyId(String warrantyPolicyId) {
        this.warrantyPolicyId = warrantyPolicyId;
    }
}
