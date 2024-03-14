package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberLinkType {

    NONE("00"),
    GOOGLE("01"),
    NAVER("02");

    private final String code;

    public static MemberLinkType valueOfCode(String code) {
        for (MemberLinkType memberLinkType : values()) {
            if (memberLinkType.getCode().equalsIgnoreCase(code))
                return memberLinkType;
        }
        throw new IllegalArgumentException();
    }
}