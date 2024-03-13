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
    private Long fundingId;
    private String fundingKey;
    private boolean isDelivery;
    private String rewardTitle;
    private BigInteger amount;
    private List<FundingRewardItem> fundingRewardItems;
    private int countLimit;
    private int personalLimit;
    private LocalDate expectDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
