package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SupportStatus {
    RESERVATION("10"),
    SUCCESS("20"),
    FAILURE("30");

    private final String statusCode;
}
