package com.flab.funding.domain.model;

public enum MemberGender {
    MALE('0'),
    FEMALE('1');

    private final char gender;

    MemberGender(char gender) {
        this.gender = gender;
    }
}
