package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingItemOption;
import com.flab.funding.domain.model.FundingRewardItem;
import com.flab.funding.infrastructure.adapters.input.mapper.FundingMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FundingItemRegisterRequest {

    private String fundingKey;
    private String itemName;
    private FundingItemOption optionType;
    private List<FundingItemOption> fundingItemOptions;

    public FundingItem toFundingItem() {
        return FundingMapper.INSTANCE.toFundingItem(this);
    }
}
