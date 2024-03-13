package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FundingStatus {
    REGIST("00"),
    REVIEW_WAIT("10"),
    OPEN_WAIT("20"),
    PROGRESS("30"),
    CANCEL("40"),
    DENY("50"),
    END("50");

    private final String code;

    public static FundingStatus valueOfCode(String code) {
        for (FundingStatus fundingStatus : values()) {
            if (fundingStatus.getCode().equalsIgnoreCase(code))
                return fundingStatus;
        }
        throw new IllegalArgumentException();
    }
}
