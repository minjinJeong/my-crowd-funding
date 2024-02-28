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
}
