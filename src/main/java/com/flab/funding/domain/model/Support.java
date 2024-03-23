package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class Support {

    private Long id;
    private Long userId;
    private String userKey;
    private Long fundingId;
    private String fundingKey;
    private Long rewardId;
    private String supportKey;
    private SupportStatus status;
    private SupportDelivery supportDelivery;
    private SupportPayment supportPayment;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public Support register() {
        this.status = SupportStatus.RESERVATION;
        this.supportKey = makeKey();
        return this;
    }

    // TODO key 생성 로직 수정
    private String makeKey() {
        return UUID.randomUUID().toString();
    }
}
