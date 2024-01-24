package com.flab.funding.domain.model;

public enum MemberGender {
    MALE("M"),
    FEMALE("F");

    private final String gender;

    MemberGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
