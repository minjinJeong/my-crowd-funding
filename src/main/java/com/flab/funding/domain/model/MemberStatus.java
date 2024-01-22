package com.flab.funding.domain.model;

public enum MemberStatus {
    ACTIVATE("10"),
    SUSPENDED("20"),
    DORMANT("30"),
    WITHDRAW("90");

    private final String status;

    MemberStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
