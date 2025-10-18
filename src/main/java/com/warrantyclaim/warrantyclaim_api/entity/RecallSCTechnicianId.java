package com.warrantyclaim.warrantyclaim_api.entity;

import java.io.Serializable;
import java.util.Objects;

public class RecallSCTechnicianId implements Serializable {

    private String recallId;
    private String technicianId;

    public RecallSCTechnicianId() {}

    public RecallSCTechnicianId(String recallId, String technicianId) {
        this.recallId = recallId;
        this.technicianId = technicianId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecallSCTechnicianId)) return false;
        RecallSCTechnicianId that = (RecallSCTechnicianId) o;
        return Objects.equals(recallId, that.recallId) &&
                Objects.equals(technicianId, that.technicianId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recallId, technicianId);
    }

    // Optional: getters & setters nếu bạn không dùng Lombok cho class này

    public String getRecallId() {
        return recallId;
    }

    public void setRecallId(String recallId) {
        this.recallId = recallId;
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }
}
