package com.warrantyclaim.warrantyclaim_api.entity;


import java.io.Serializable;
import java.util.Objects;

public class RecallElectricVehicleId implements Serializable {

    private String recallId;
    private String vehicleId;

    public RecallElectricVehicleId() {}

    public RecallElectricVehicleId(String recallId, String vehicleId) {
        this.recallId = recallId;
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecallElectricVehicleId)) return false;
        RecallElectricVehicleId that = (RecallElectricVehicleId) o;
        return Objects.equals(recallId, that.recallId) &&
                Objects.equals(vehicleId, that.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recallId, vehicleId);
    }

    public String getRecallId() {
        return recallId;
    }

    public void setRecallId(String recallId) {
        this.recallId = recallId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    // Optional: getters & setters nếu bạn không dùng Lombok cho class này
}
