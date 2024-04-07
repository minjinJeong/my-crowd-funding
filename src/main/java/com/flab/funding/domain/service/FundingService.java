package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.CancelFundingUseCase;
import com.flab.funding.application.ports.input.FindFundingUseCase;
import com.flab.funding.application.ports.input.RegisterFundingUseCase;
import com.flab.funding.application.ports.input.ReviewFundingUseCase;
import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.*;
import com.flab.funding.infrastructure.config.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FundingService implements RegisterFundingUseCase, ReviewFundingUseCase, CancelFundingUseCase, FindFundingUseCase {

    private final MemberPort memberPort;

    private final FundingPort fundingPort;

    @Override
    public Funding registerFunding(Funding funding) {

        Member member =
                memberPort.getMemberByUserKey(funding.getMember().getUserKey());

        return fundingPort.saveFunding(funding.member(member).register());
    }

    @Override
    public FundingCreator registerFundingCreator(FundingCreator fundingCreator) {

        Funding funding =
                fundingPort.getFundingByFundingKey(fundingCreator.getFunding().getFundingKey());

        return fundingPort.saveFundingCreator(fundingCreator.funding(funding));
    }

    @Override
    public FundingItem makeFundingItem(FundingItem fundingItem) {

        Funding funding =
                fundingPort.getFundingByFundingKey(fundingItem.getFunding().getFundingKey());

        return fundingPort.saveFundingItem(fundingItem.funding(funding));
    }

    @Override
    public FundingReward makeFundingReward(FundingReward fundingReward) {

        Funding funding =
                fundingPort.getFundingByFundingKey(fundingReward.getFunding().getFundingKey());

        FundingReward savedFundingReward = fundingPort.saveFundingReward(fundingReward.funding(funding));

        mappingFundingRewardItem(funding, savedFundingReward);

        return savedFundingReward;
    }

    private void mappingFundingRewardItem(Funding funding, FundingReward fundingReward) {
        List<FundingRewardItem> fundingRewardItems = fundingReward.getFundingRewardItems();
        for (FundingRewardItem rewardItem : fundingRewardItems) {
            FundingRewardItem.builder()
                    .funding(funding)
                    .fundingReward(fundingReward)
                    .fundingItem(rewardItem.getFundingItem());
        }

        fundingPort.saveFundingRewardItems(fundingRewardItems);
    }

    @Override
    public Funding waitForFundingReview(String fundingKey) {
        Funding funding = fundingPort.getFundingByFundingKey(fundingKey);
        return fundingPort.saveFunding(funding.waitReview());
    }

    @Override
    public Funding completeFundingReview(String fundingKey) {
        Funding funding = fundingPort.getFundingByFundingKey(fundingKey);
        return fundingPort.saveFunding(funding.completeReview());
    }

    @Override
    public Funding cancelFunding(String fundingKey) {
        Funding funding = fundingPort.getFundingByFundingKey(fundingKey);
        return fundingPort.saveFunding(funding.cancel());
    }

    @Override
    public Funding getFundingByFundingKey(String fundingKey) {
        return fundingPort.getFundingByFundingKey(fundingKey);
    }

    @Override
    public FundingCreator getFundingCreatorByFundingKey(String fundingKey) {
        return fundingPort.getFundingCreatorByFundingKey(fundingKey);
    }

    @Override
    public FundingItem getFundingItemByFundingKey(String fundingKey) {
        return fundingPort.getFundingItemByFundingKey(fundingKey);
    }

    @Override
    public FundingReward getFundingRewardByFundingKey(String fundingKey) {
        return fundingPort.getFundingRewardByFundingKey(fundingKey);
    }
}
