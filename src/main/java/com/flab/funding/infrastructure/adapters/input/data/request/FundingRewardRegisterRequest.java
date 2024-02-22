package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.domain.model.FundingRewardItem;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FundingRewardRegisterRequest {

    private String fundingKey;
    private boolean isDelivery;
    private String rewardTitle;
    private BigInteger amount;
    private List<FundingRewardItem> fundingRewardItems;
    private int countLimit;
    private int personalLimit;
    private LocalDate expectDate;

    public FundingReward toFundingReward() {
        return FundingMapper.INSTANCE.toFundingReward(this);
    }
}
