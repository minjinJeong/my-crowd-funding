package com.flab.funding.domain.model;

import lombok.Getter;

@Getter
public enum SupportDeliveryStatus {
    READY("00"),
    PROCESSING("10"),
    SHIPPED("20"),
    IN_DELIVERY("30"),
    COMPLETE("40"),
    REFUNDED("50");

    private final String statusCode;

    SupportDeliveryStatus(String statusCode) {
        this.statusCode = statusCode;
    }
}
