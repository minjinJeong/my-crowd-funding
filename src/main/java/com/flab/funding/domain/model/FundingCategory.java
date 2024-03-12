package com.flab.funding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FundingCategory {
    BOARD_GAME("01"),
    DIGITAL_GAME("02"),
    WEBTOON("03"),
    CHARACTER("06"),
    HOME_LIVING("07"),
    TECH("08"),
    PET("09"),
    FOOD("10"),
    CLOTHES("12"),
    STUFF("13"),
    JEWELRY("14"),
    PUBLISHING("15"),
    DESIGN("16"),
    MUSIC("18"),
    VIDEO("19");

    private final String code;

    public static FundingCategory valueOfLabel(String code) {
        for (FundingCategory fundingCategory : values()) {
            if (fundingCategory.getCode().equalsIgnoreCase(code))
                return fundingCategory;
        }
        throw new IllegalArgumentException();
    }
}
