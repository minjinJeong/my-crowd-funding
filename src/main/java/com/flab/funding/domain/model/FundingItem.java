package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FundingItem {
    private Long id;
    private String funding_id;
    private String itemName;
    private FundingItemOption optionType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
