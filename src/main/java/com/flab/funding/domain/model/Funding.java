package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class Funding {

    private Long id;
    private String fundingKey;
    private Member member;
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
    private List<FundingTag> tags;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public Funding member(Member member) {
        this.member = member;
        return this;
    }

    public Funding register() {
        this.status = FundingStatus.REGISTER;
        this.fundingKey = makeKey();
        return this;
    }

    // TODO key 생성 로직 수정
    private String makeKey() {
        return UUID.randomUUID().toString();
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
