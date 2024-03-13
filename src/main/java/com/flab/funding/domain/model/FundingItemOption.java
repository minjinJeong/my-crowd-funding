package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FundingItemOption {
    private Long id;
    private Long fundingItemId;
    private String option;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
