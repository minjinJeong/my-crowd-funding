package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FundingRewardItem {
    private Long id;
    private Long fundingId;
    private Long fundingRewardId;
    private Long fundingItemId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
