package com.warrantyclaim.warrantyclaim_api.enums;

public enum PartsRequestStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    ORDERED("Ordered"),
    IN_TRANSIT("In Transit"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String displayName;

    PartsRequestStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
