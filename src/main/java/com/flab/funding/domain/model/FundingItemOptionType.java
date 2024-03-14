package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FundingItemOptionType {

    NONE("00"),
    SHORT_ANSWER("10"),
    MULTIPLE_CHOICE("20");

    private final String code;

    public static FundingItemOptionType valueOfCode(String code) {
        for (FundingItemOptionType fundingItemOptionType : values()) {
            if (fundingItemOptionType.getCode().equalsIgnoreCase(code))
                return fundingItemOptionType;
        }
        throw new IllegalArgumentException();
    }
}
