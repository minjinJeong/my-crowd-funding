package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FundingCreator {

    private Long id;
    private Long fundingId;
    private String fundingKey;
    private boolean isValid;
    private String businessNumber;
    private String representative;
    private String introduce;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
