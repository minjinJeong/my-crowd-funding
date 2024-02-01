package com.flab.funding.domain.model;

public enum MemberStatus {
    ACTIVATE("10"),
    SUSPENDED("20"),
    DORMANT("30"),
    WITHDRAW("90");

    private final String statusCode;

    MemberStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
