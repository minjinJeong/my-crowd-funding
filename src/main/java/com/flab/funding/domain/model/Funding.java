package com.flab.funding.domain.model;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@Getter
public class Funding {
    private Long id;
    private String fundingKey;
    private MemberEntity member;
    private boolean isAdult;
    private String pricePlan;
    private FundingCategory categoryCode;
    private BigInteger expectAmount;
    private FundingStatus status;
    private String title;
    private String fundingDesc;
    private String fundingIntroduce;
    private String budgetDesc;
    private String scheduleDesc;
    private String teamDesc;
    private String rewardDesc;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public Funding regist() {
        this.status = FundingStatus.REGIST;
        return this;
    }
}
