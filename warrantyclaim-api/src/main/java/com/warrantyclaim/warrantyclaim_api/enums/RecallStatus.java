package com.warrantyclaim.warrantyclaim_api.enums;

public enum RecallStatus {
    INACTIVE("Inactive"),
    ACTIVE("Active"),
    COMPLETE("Complete all electric vehicle");

    private final String displayName;

    RecallStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
