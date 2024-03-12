package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberStatus {
    ACTIVATE("10"),
    SUSPENDED("20"),
    DORMANT("30"),
    WITHDRAW("90");

    private final String code;

    public static MemberStatus valueOfLabel(String code) {
        for (MemberStatus memberStatus : values()) {
            if (memberStatus.getCode().equalsIgnoreCase(code))
                return memberStatus;
        }
        throw new IllegalArgumentException();
    }
}
