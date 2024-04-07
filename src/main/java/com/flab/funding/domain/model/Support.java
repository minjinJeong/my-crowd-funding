package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class Support {

    private Long id;
    private Member member;
    private Funding funding;
    private FundingReward reward;
    private String supportKey;
    private SupportStatus status;
    private SupportDelivery supportDelivery;
    private SupportPayment supportPayment;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public Support member(Member member) {
        this.member = member;
        return this;
    }

    public Support funding(Funding funding) {
        this.funding = funding;
        return this;
    }

    public Support reward(FundingReward fundingReward) {
        this.reward = fundingReward;
        return this;
    }

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
