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

    public Support with(Member member,
                        Funding funding,
                        SupportDelivery supportDelivery,
                        SupportPayment supportPayment) {

        return Support.builder()
                .id(this.id)
                .member(member)
                .funding(funding)
                .reward(this.reward)
                .supportKey(this.supportKey)
                .status(this.status)
                .supportDelivery(supportDelivery)
                .supportPayment(supportPayment)
                .build();
    }

    public Support register() {
        this.status = SupportStatus.RESERVATION;
        this.supportKey = MakeDomainKeyUtils.newKey("SS");
        return this;
    }
}
