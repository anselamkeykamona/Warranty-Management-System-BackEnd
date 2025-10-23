package com.warrantyclaim.warrantyclaim_api.enums;

public enum ServiceCampaignsStatus {
    PLANNED("Planned"),
    ACTIVE("Active"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private final String displayName;

    ServiceCampaignsStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
