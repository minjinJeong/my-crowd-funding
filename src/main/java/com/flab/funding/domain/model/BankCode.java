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
}
