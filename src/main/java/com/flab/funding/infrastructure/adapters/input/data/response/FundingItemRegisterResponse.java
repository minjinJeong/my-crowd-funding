package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FundingItemRegisterResponse {

    private String fundingKey;

    public static FundingItemRegisterResponse from(FundingItem fundingItem) {
        return FundingMapper.INSTANCE.toFundingItemRegisterResponse(fundingItem);
    }
}
