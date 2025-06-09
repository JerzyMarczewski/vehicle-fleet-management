package com.fleetmanager.vehiclefleetmanagement.enums;

public enum FlashAttribute {
    SUCCESS_MESSAGE("successMessage"),
    ERROR_MESSAGE("errorMessage"),
    INFO_MESSAGE("infoMessage"),
    WARNING_MESSAGE("warningMessage");

    private final String attributeName;

    FlashAttribute(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public String toString() {
        return attributeName;
    }
}