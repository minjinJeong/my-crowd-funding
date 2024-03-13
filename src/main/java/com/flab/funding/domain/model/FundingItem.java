package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class FundingItem {
    private Long id;
    private Long fundingId;
    private String fundingKey;
    private String itemName;
    private FundingItemOptionType optionType;
    private List<FundingItemOption> fundingItemOptions;
    private List<FundingRewardItem> fundingRewardItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
