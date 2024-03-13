package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberGender {
    MALE("M"),
    FEMALE("F");

    private final String gender;

    public static MemberGender valueOfGender(String gender) {
        for (MemberGender memberGender : values()) {
            if (memberGender.getGender().equalsIgnoreCase(gender))
                return memberGender;
        }
        throw new IllegalArgumentException();
    }
}
