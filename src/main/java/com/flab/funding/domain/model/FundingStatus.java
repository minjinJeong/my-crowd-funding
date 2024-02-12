package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FundingStatus {
    REGIST("00"),
    REVIEW_WAIT("10"),
    REVIEW_END("11"),
    OPEN_WAIT("20"),
    PROGRESS("30"),
    END("40");

    private final String statusCode;
}
