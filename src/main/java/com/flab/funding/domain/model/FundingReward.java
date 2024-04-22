package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class FundingReward {

    private Long id;

    private Funding funding;

    private boolean isDelivery;

    private String rewardTitle;

    private BigInteger amount;

    private List<FundingRewardItem> fundingRewardItems;

    private int countLimit;

    private int personalLimit;

    private LocalDate expectDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public FundingReward with(Funding funding) {

        return FundingReward.builder()
                .id(this.id)
                .funding(funding)
                .isDelivery(this.isDelivery)
                .rewardTitle(this.rewardTitle)
                .amount(this.amount)
                .fundingRewardItems(this.fundingRewardItems)
                .countLimit(this.countLimit)
                .personalLimit(this.personalLimit)
                .expectDate(this.expectDate)
                .build();
    }

    public FundingReward unmapping() {
        this.fundingRewardItems = null;
        return this;
    }
}
