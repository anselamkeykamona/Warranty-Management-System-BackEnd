package com.warrantyclaim.warrantyclaim_api.entity;

import java.io.Serializable;
import java.util.Objects;

public class ServiceCampaignsSCTechnicianId implements Serializable {

    private String campaignId;
    private String technicianId;

    public ServiceCampaignsSCTechnicianId() {}

    public ServiceCampaignsSCTechnicianId(String campaignId, String technicianId) {
        this.campaignId = campaignId;
        this.technicianId = technicianId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceCampaignsSCTechnicianId)) return false;
        ServiceCampaignsSCTechnicianId that = (ServiceCampaignsSCTechnicianId) o;
        return Objects.equals(campaignId, that.campaignId) &&
                Objects.equals(technicianId, that.technicianId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignId, technicianId);
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }

    // Optional: getters & setters nếu bạn không dùng Lombok cho class này
}
