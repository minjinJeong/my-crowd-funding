package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberGender {
    MALE("M"),
    FEMALE("F");

    private final String gender;
}
