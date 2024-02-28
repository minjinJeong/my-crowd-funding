package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingStatus;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FundingRegisterResponse {

    private String fundingKey;
    private FundingStatus status;

    public static FundingRegisterResponse from(Funding funding) {
        return FundingMapper.INSTANCE.toFundingRegisterResponse(funding);
    }
}
