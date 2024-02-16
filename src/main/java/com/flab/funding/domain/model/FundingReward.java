package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class FundingReward {
    private Long id;
    private String funding_id;
    private String rewardTitle;
    private BigInteger amount;
    private int countLimit;
    private int personalLimit;
    private LocalDate expectDate;
    private boolean isDelivery;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
