package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FundingRewardItem {

    private Long id;

    private Funding funding;

    private FundingReward fundingReward;

    private FundingItem fundingItem;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
