package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingReward;

public interface FindFundingUseCase {

    Funding getFundingByFundingKey(String fundingKey);

    FundingCreator getFundingCreatorByFundingKey(String fundingKey);

    FundingItem getFundingItemByFundingKey(String fundingKey);

    FundingReward getFundingRewardByFundingKey(String fundingKey);
}
