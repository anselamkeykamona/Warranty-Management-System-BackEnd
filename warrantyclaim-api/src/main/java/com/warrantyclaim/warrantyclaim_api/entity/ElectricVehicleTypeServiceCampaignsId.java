package com.warrantyclaim.warrantyclaim_api.entity;

import java.io.Serializable;
import java.util.Objects;

public class ElectricVehicleTypeServiceCampaignsId implements Serializable {

    private String vehicleTypeId;
    private String campaignId;

    public ElectricVehicleTypeServiceCampaignsId() {}

    public ElectricVehicleTypeServiceCampaignsId(String vehicleTypeId, String campaignId) {
        this.vehicleTypeId = vehicleTypeId;
        this.campaignId = campaignId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectricVehicleTypeServiceCampaignsId)) return false;
        ElectricVehicleTypeServiceCampaignsId that = (ElectricVehicleTypeServiceCampaignsId) o;
        return Objects.equals(vehicleTypeId, that.vehicleTypeId) &&
                Objects.equals(campaignId, that.campaignId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleTypeId, campaignId);
    }

    // Optional: getters & setters nếu bạn không dùng Lombok cho class này

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }
}
