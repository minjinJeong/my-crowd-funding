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

    public FundingItem with(Funding funding) {

        return FundingItem.builder()
                .id(this.id)
                .funding(funding)
                .itemName(this.itemName)
                .optionType(this.optionType)
                .fundingItemOptions(this.fundingItemOptions)
                .fundingRewardItems(this.fundingRewardItems)
                .build();
    }
}
