package com.flab.funding.domain.model;

import lombok.Getter;

@Getter
public enum SupportPaymentStatus {
    READY("00"),
    PROCESSING("10"),
    SUCCESS("20"),
    FAILURE("30"),
    REFUNDED("50");

    private final String statusCode;

    SupportPaymentStatus(String statusCode) {
        this.statusCode = statusCode;
    }
}
