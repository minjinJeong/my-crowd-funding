package com.flab.funding.domain.model;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingTagEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class Funding {
    private Long id;
    private String fundingKey;
    private MemberEntity member;
    private boolean isAdult;
    private String pricePlan;
    private FundingCategory category;
    private BigInteger expectAmount;
    private FundingStatus status;
    private String title;
    private String fundingDescription;
    private String fundingIntroduce;
    private String budgetDescription;
    private String scheduleDescription;
    private String teamDescription;
    private String rewardDescription;
    private List<FundingTagEntity> tags;
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

    public Funding waitReview() {
        this.status = FundingStatus.REVIEW_WAIT;
        return this;
    }

    public Funding completeReview() {
        this.status = FundingStatus.OPEN_WAIT;
        return this;
    }

    public Funding cancel() {
        this.status = FundingStatus.CANCEL;
        return this;
    }
}
