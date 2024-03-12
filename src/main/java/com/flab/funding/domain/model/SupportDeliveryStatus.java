package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SupportDeliveryStatus {
    READY("00"),
    PROCESSING("10"),
    SHIPPED("20"),
    IN_DELIVERY("30"),
    COMPLETE("40"),
    REFUNDED("50");

    private final String code;

    public static SupportDeliveryStatus valueOfLabel(String code) {
        for (SupportDeliveryStatus supportDeliveryStatus : values()) {
            if (supportDeliveryStatus.getCode().equalsIgnoreCase(code))
                return supportDeliveryStatus;
        }
        throw new IllegalArgumentException();
    }
}
