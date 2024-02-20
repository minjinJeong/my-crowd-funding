package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingReward;

public interface FundingPort {
    Funding getFundingByFundingId(String fundingId);
    Funding saveFunding(Funding funding);
    FundingCreator saveFundingCreator(FundingCreator fundingCreator);
    FundingItem saveFundingItem(FundingItem fundingItem);
    FundingReward saveFundingReward(FundingReward fundingReward);
}

