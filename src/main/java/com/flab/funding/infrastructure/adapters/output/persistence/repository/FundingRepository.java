package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingCreatorEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingItemEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardEntity;

import java.util.Optional;

public interface FundingRepository {

    FundingEntity save(FundingEntity fundingEntity);

    FundingCreatorEntity save(FundingCreatorEntity fundingCreatorEntity);

    FundingItemEntity save(FundingItemEntity fundingItemEntity);

    FundingRewardEntity save(FundingRewardEntity fundingRewardEntity);

    Optional<FundingEntity> findByFundingKey(String fundingKey);
}
