package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BankCode {
    KOREA("001"),
    KOOKMIN("004"),
    WOORI("020"),
    HANA("081");

    private final String code;

    public static BankCode valueOfLabel(String code) {
        for (BankCode bankCode : values()) {
            if (bankCode.getCode().equalsIgnoreCase(code))
                return bankCode;
        }
        throw new IllegalArgumentException();
    }
}
