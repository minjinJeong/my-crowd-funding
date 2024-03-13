package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCategory;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FundingRegisterRequest {

    private String userKey;
    private boolean isAdult;
    private String pricePlan;
    private FundingCategory category;
    private BigInteger expectAmount;
    private String title;
    private String fundingDescription;
    private String fundingIntroduce;
    private String budgetDescription;
    private String scheduleDescription;
    private String teamDescription;
    private String rewardDescription;
    private List<FundingTagRegisterRequest> tags;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public Funding toFunding() {
        return FundingMapper.INSTANCE.toFunding(this);
    }
}
