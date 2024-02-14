package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCategory;
import com.flab.funding.domain.model.FundingStatus;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FundingRegisterRequest {

    private String userKey;
    private boolean adult;
    private String pricePlan;
    private FundingCategory categoryCode;
    private BigInteger expectAmount;
    private String title;
    private String fundingDesc;
    private String fundingIntroduce;
    private String budgetDesc;
    private String scheduleDesc;
    private String teamDesc;
    private String rewardDesc;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public Funding toFunding() {
        return FundingMapper.INSTANCE.toFunding(this);
    }
}
