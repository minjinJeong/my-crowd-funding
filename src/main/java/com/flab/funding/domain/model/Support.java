package com.flab.funding.domain.model;

import com.flab.funding.domain.utils.MakeDomainKeyUtils;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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
        this.supportKey = MakeDomainKeyUtils.newKey("SS");
        return this;
    }
}
