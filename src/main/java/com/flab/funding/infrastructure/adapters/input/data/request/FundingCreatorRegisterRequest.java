package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FundingCreatorRegisterRequest {

    public FundingCreator toFundingCreator() {
        return FundingMapper.INSTANCE.toFundingCreator(this);
    }
}
