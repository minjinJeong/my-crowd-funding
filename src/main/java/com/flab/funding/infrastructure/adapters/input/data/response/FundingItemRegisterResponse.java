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
public class FundingItemRegisterResponse {

    // TODO 펀딩 아이템 생성 후 ID 반환 필요
    private String fundingKey;
    private String itemName;
    private FundingItemOptionType optionType;
    private List<FundingItemOptionRequest> fundingItemOptions;

    public static FundingItemRegisterResponse from(FundingItem fundingItem) {

        return FundingMapper.INSTANCE.toFundingItemRegisterResponse(fundingItem);
    }
}
