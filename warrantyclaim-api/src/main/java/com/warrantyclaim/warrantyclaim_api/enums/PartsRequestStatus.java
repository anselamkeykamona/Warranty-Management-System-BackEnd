package com.warrantyclaim.warrantyclaim_api.enums;

public enum PartsRequestStatus {
    PENDING("Pending"),           // SC created request
    APPROVED("Approved"),         // EVM approved (has stock)
    REJECTED("Rejected"),         // EVM rejected (no stock)
    ORDERED("Ordered"),           // EVM preparing shipment
    IN_TRANSIT("In Transit"),     // Shipped from EVM to SC
    DELIVERED("Delivered"),       // SC received parts
    COMPLETED("Completed"),       // Parts added to SC inventory
    CANCELLED("Cancelled");       // Request cancelled

    private final String displayName;

    PartsRequestStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
