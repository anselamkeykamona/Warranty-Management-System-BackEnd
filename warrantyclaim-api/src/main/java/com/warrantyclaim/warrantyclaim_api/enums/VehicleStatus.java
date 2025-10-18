package com.warrantyclaim.warrantyclaim_api.enums;

public enum VehicleStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    IN_WARRANTY("In Warranty"),
    RECALLED("Recalled"),
    RETIRED("Retired");

    private final String displayName;

    VehicleStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
