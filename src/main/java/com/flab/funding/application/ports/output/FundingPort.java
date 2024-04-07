package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.*;

import java.util.List;

public interface FundingPort {

    Funding getFundingByFundingKey(String fundingKey);

    FundingCreator getFundingCreatorByFundingKey(String fundingKey);

    FundingItem getFundingItemByFundingKey(String fundingKey);

    FundingReward getFundingRewardByFundingKey(String fundingKey);

    Funding saveFunding(Funding funding);

    FundingCreator saveFundingCreator(FundingCreator fundingCreator);

    FundingItem saveFundingItem(FundingItem fundingItem);

    FundingReward saveFundingReward(FundingReward fundingReward);

    List<FundingRewardItem> saveFundingRewardItems(List<FundingRewardItem> fundingRewardItems);
}

