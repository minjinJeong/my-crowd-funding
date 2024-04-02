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
import com.flab.funding.infrastructure.adapters.output.persistence.repository.FundingCreatorRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.FundingItemRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.FundingRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.FundingRewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FundingPersistenceAdapter implements FundingPort {

    private final FundingRepository fundingRepository;
    private final FundingCreatorRepository fundingCreatorRepository;
    private final FundingItemRepository fundingItemRepository;
    private final FundingRewardRepository fundingRewardRepository;

    @Transactional
    @Override
    public Funding saveFunding(Funding funding) {

        FundingEntity fundingEntity = FundingEntity.from(funding);
        FundingEntity savedFunding = fundingRepository.save(fundingEntity);
        return savedFunding.toFunding();
    }

    @Override
    public Funding getFundingByFundingKey(String fundingKey) {

        FundingEntity fundingEntity = fundingRepository.findByFundingKey(fundingKey)
                .orElse(FundingEntity.builder().build());

        return fundingEntity.toFunding();
    }

    @Transactional
    @Override
    public FundingCreator saveFundingCreator(FundingCreator fundingCreator) {

        FundingCreatorEntity fundingCreatorEntity = FundingCreatorEntity.from(fundingCreator);
        FundingCreatorEntity savedFundingCreator = fundingCreatorRepository.save(fundingCreatorEntity);
        return savedFundingCreator.toFundingCreator();
    }

    @Transactional
    @Override
    public FundingItem saveFundingItem(FundingItem fundingItem) {

        FundingItemEntity fundingItemEntity = FundingItemEntity.toFundingItemEntity(fundingItem);
        FundingItemEntity savedFundingItem = fundingItemRepository.save(fundingItemEntity);
        return savedFundingItem.toFundingItem();
    }

    @Transactional
    @Override
    public FundingReward saveFundingReward(FundingReward fundingReward) {

        FundingRewardEntity fundingRewardEntity = FundingRewardEntity.toFundingRewardEntity(fundingReward);
        FundingRewardEntity savedFundingReward = fundingRewardRepository.save(fundingRewardEntity);
        return savedFundingReward.toFundingReward();
    }

    // TODO : get 테스트 및 수정 필요
    @Override
    public FundingCreator getFundingCreatorByFundingKey(String fundingKey) {

        FundingCreatorEntity fundingCreatorEntity = fundingCreatorRepository.findByFunding_FundingKey(fundingKey)
                .orElse(FundingCreatorEntity.builder().build());

        return fundingCreatorEntity.toFundingCreator();
    }

    @Override
    public FundingItem getFundingItemByFundingKey(String fundingKey) {

        FundingItemEntity fundingItemEntity = fundingItemRepository.findByFunding_FundingKey(fundingKey)
                .orElse(FundingItemEntity.builder().build());

        return fundingItemEntity.toFundingItem();
    }

    @Override
    public FundingReward getFundingRewardByFundingKey(String fundingKey) {

        FundingRewardEntity fundingRewardEntity = fundingRewardRepository.findByFunding_FundingKey(fundingKey)
                .orElse(FundingRewardEntity.builder().build());

        return fundingRewardEntity.toFundingReward();
    }
}
