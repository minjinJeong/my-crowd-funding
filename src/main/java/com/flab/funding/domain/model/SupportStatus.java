package com.flab.funding.domain.model;

import lombok.Getter;

@Getter
public enum SupportStatus {
    RESERVATION("10"),
    SUCCESS("20"),
    FAILURE("30");

    private final String statusCode;

    SupportStatus(String statusCode) {
        this.statusCode = statusCode;
    }
}
