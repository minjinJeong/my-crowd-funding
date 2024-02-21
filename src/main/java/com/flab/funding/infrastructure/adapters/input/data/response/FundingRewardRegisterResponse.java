package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FundingRewardRegisterResponse {

    public static FundingRewardRegisterResponse from(FundingReward fundingReward) {
        return FundingMapper.INSTANCE.toFundingRewardRegisterResponse(fundingReward);
    }
}
