package com.warrantyclaim.warrantyclaim_api.enums;

public enum EvmApprovalStatus {
    Disapproved("Disapproved"),
    Approved("Approved"),
    WAITING("Waiting for approval");

    private final String displayName;

    EvmApprovalStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
