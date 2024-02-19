package com.flab.funding.domain.model;

public enum SupportDeliveryStatus {
    STAND_BY("10"),
    SUCCESS("20"),
    FAILURE("30");

    private final String statusCode;

    SupportDeliveryStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
