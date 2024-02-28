package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingItemOptionType;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingItemOptionRequest;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class FundingItemInfoResponse {

    private String fundingKey;
    private String itemName;
    private FundingItemOptionType optionType;
    private List<FundingItemOptionRequest> fundingItemOptions;

    public static FundingItemInfoResponse from(FundingItem fundingItem) {
        return FundingMapper.INSTANCE.toFundingItemInfoResponse(fundingItem);
    }
}
