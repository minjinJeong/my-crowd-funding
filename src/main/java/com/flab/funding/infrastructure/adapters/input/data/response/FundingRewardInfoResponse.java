package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRewardItemRequest;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class FundingRewardInfoResponse {

    private String fundingKey;
    private boolean isDelivery;
    private String rewardTitle;
    private BigInteger amount;
    private List<FundingRewardItemRequest> fundingRewardItems;
    private int countLimit;
    private int personalLimit;
    private LocalDate expectDate;

    public static FundingRewardInfoResponse from(FundingReward fundingReward) {
        return FundingMapper.INSTANCE.toFundingRewardInfoResponse(fundingReward);
    }
}
