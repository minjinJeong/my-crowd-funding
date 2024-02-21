package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FundingCreatorRegisterResponse {

    public static FundingCreatorRegisterResponse from(FundingCreator fundingCreator) {
        return FundingMapper.INSTANCE.toFundingCreatorRegisterResponse(fundingCreator);
    }
}
