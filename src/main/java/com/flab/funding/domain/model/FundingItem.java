package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class FundingItem {

    private Long id;
    private Funding funding;
    private String itemName;
    private FundingItemOptionType optionType;
    private List<FundingItemOption> fundingItemOptions;
    private List<FundingRewardItem> fundingRewardItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FundingItem funding(Funding funding) {
        this.funding = funding;
        return this;
    }
}
