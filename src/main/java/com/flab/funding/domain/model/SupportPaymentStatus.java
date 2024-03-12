package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SupportPaymentStatus {
    READY("00"),
    PROCESSING("10"),
    SUCCESS("20"),
    FAILURE("30"),
    REFUNDED("50");

    private final String code;

    public static SupportPaymentStatus valueOfLabel(String code) {
        for (SupportPaymentStatus supportPaymentStatus : values()) {
            if (supportPaymentStatus.getCode().equalsIgnoreCase(code))
                return supportPaymentStatus;
        }
        throw new IllegalArgumentException();
    }
}
