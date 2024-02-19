package com.flab.funding.domain.model;

public enum SupportStatus {
    RESERVATION("10"),
    SUCCESS("20"),
    FAILURE("30");

    private final String statusCode;

    SupportStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
