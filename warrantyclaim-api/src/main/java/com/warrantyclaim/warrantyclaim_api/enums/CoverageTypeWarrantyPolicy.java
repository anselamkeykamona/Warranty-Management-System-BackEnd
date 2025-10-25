package com.warrantyclaim.warrantyclaim_api.enums;

public enum CoverageTypeWarrantyPolicy {
    FULL("Full"),
    LIMITED("Limited"),
    BATTERY("Only cover for battery"),
    PARTS_ONLY("Only cover for parts"),
    NONE("No coverage");

    private final String displayName;

    CoverageTypeWarrantyPolicy(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
