package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingCreatorEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingItemEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.FundingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FundingPersistenceAdapter implements FundingPort {

    private final FundingRepository fundingRepository;

    @Override
    public Funding getFundingByFundingKey(String fundingKey) {
        FundingEntity fundingEntity = fundingRepository.findByFundingKey(fundingKey).orElse(FundingEntity.builder().build());;
        return fundingEntity.toFunding();
    }

    @Override
    public Funding saveFunding(Funding funding) {
        FundingEntity fundingEntity = FundingEntity.from(funding);
        FundingEntity savedFunding = fundingRepository.save(fundingEntity);
        return savedFunding.toFunding();
    }

    @Override
    public FundingCreator saveFundingCreator(FundingCreator fundingCreator) {
        FundingCreatorEntity fundingCreatorEntity = FundingCreatorEntity.from(fundingCreator);
        FundingCreatorEntity savedFundingCreator = fundingRepository.save(fundingCreatorEntity);
        return savedFundingCreator.toFundingCreator();
    }

    @Override
    public FundingItem saveFundingItem(FundingItem fundingItem) {
        FundingItemEntity fundingItemEntity = FundingItemEntity.toFundingItemEntity(fundingItem);
        FundingItemEntity savedFundingItem = fundingRepository.save(fundingItemEntity);
        return savedFundingItem.toFundingItem();
    }

    @Override
    public FundingReward saveFundingReward(FundingReward fundingReward) {
        FundingRewardEntity fundingRewardEntity = FundingRewardEntity.toFundingRewardEntity(fundingReward);
        FundingRewardEntity savedFundingReward = fundingRepository.save(fundingRewardEntity);
        return savedFundingReward.toFundingReward();
    }

    @Override
    public FundingCreator getFundingCreatorByFundingKey(String fundingKey) {
        return null;
    }

    @Override
    public FundingItem getFundingItemByFundingKey(String fundingKey) {
        return null;
    }

    @Override
    public FundingReward getFundingRewardByFundingKey(String fundingKey) {
        return null;
    }
}
