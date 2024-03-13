package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Support {
    private Long id;
    private Long userId;
    private Long fundingId;
    private Long rewardId;
    private String supportKey;
    private SupportStatus status;
    private SupportDelivery supportDelivery;
    private SupportPayment supportPayment;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
