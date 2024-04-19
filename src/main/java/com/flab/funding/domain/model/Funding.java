package com.flab.funding.domain.model;

import com.flab.funding.domain.utils.MakeDomainKeyUtils;
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

    public Funding with(Member member) {

        return Funding.builder()
                .id(this.id)
                .fundingKey(this.fundingKey)
                .member(member)
                .isAdult(this.isAdult)
                .pricePlan(this.pricePlan)
                .category(this.category)
                .expectAmount(this.expectAmount)
                .status(this.status)
                .title(this.title)
                .fundingDescription(this.fundingDescription)
                .fundingIntroduce(this.fundingIntroduce)
                .budgetDescription(this.budgetDescription)
                .scheduleDescription(this.scheduleDescription)
                .teamDescription(this.teamDescription)
                .rewardDescription(this.rewardDescription)
                .tags(this.tags)
                .startAt(this.startAt)
                .endAt(this.endAt)
                .build();
    }

    public Funding register() {
        this.status = FundingStatus.REGISTER;
        this.fundingKey = MakeDomainKeyUtils.newKey("FF");
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
