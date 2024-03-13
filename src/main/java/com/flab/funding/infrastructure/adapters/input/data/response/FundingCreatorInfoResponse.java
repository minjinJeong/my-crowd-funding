package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FundingCreatorInfoResponse {

    private String fundingKey;
    private boolean isValid;
    private String businessNumber;
    private String representative;
    private String introduce;

    public static FundingCreatorInfoResponse from(FundingCreator fundingCreator) {
        return FundingMapper.INSTANCE.toFundingCreatorInfoResponse(fundingCreator);
    }
}
