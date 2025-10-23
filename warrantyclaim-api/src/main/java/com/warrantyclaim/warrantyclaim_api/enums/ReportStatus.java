package com.warrantyclaim.warrantyclaim_api.enums;

public enum ReportStatus {
    NEW("New"),
    IN_PROGRESS("In Progress"),
    RESOLVED("Resolved"),
    CLOSED("Closed"),
    PENDING("Pending"),
    CANCELLED("Cancelled");

    private final String displayName;

    ReportStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}