package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingReward;

public interface RegisterFundingUseCase {

    Funding registerFunding(Funding funding);

    FundingCreator registerFundingCreator(FundingCreator fundingCreator);

    FundingItem makeFundingItem(FundingItem fundingItem);

    FundingReward makeFundingReward(FundingReward fundingReward);
}
