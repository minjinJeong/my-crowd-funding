package com.flab.funding.domain.model;

public enum BankCode {
    KOREA("001"),
    KOOKMIN("004"),
    WOORI("020"),
    HANA("081");

    private final String code;

    BankCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
