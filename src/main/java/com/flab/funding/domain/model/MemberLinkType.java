package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberLinkType {
    NONE("00"),
    GOOGLE("01"),
    NAVER("02");

    private final String type;
}