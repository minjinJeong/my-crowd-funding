package com.flab.funding.domain.model;

public enum SupportPaymentStatus {
    STAND_BY("10"),
    SUCCESS("20"),
    FAILURE("30");

    private final String statusCode;

    SupportPaymentStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
