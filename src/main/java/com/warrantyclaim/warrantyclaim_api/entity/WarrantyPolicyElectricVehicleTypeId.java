package com.warrantyclaim.warrantyclaim_api.entity;

import java.io.Serializable;
import java.util.Objects;

public class WarrantyPolicyElectricVehicleTypeId implements Serializable {

    private String warrantyPolicyId;
    private String vehicleTypeId;

    public WarrantyPolicyElectricVehicleTypeId() {}

    public WarrantyPolicyElectricVehicleTypeId(String warrantyPolicyId, String vehicleTypeId) {
        this.warrantyPolicyId = warrantyPolicyId;
        this.vehicleTypeId = vehicleTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarrantyPolicyElectricVehicleTypeId)) return false;
        WarrantyPolicyElectricVehicleTypeId that = (WarrantyPolicyElectricVehicleTypeId) o;
        return Objects.equals(warrantyPolicyId, that.warrantyPolicyId) &&
                Objects.equals(vehicleTypeId, that.vehicleTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warrantyPolicyId, vehicleTypeId);
    }

    // Optional: getters & setters nếu bạn không dùng Lombok cho class này

    public String getWarrantyPolicyId() {
        return warrantyPolicyId;
    }

    public void setWarrantyPolicyId(String warrantyPolicyId) {
        this.warrantyPolicyId = warrantyPolicyId;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}
