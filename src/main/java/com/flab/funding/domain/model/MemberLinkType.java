package com.flab.funding.domain.model;

public enum MemberLinkType {
    NONE("00"),
    GOOGLE("01"),
    NAVER("02");

    private final String linkType;

    MemberLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getLinkType() {
        return linkType;
    }
}
