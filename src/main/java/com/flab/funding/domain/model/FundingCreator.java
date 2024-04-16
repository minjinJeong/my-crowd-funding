package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FundingCreator {

    private Long id;

    private Funding funding;

    private boolean isValid;

    private String businessNumber;

    private String representative;

    private String introduce;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public FundingCreator with(Funding funding) {

        return FundingCreator.builder()
                .id(this.id)
                .funding(funding)
                .isValid(this.isValid)
                .businessNumber(this.businessNumber)
                .representative(this.representative)
                .introduce(this.introduce)
                .build();
    }
}
