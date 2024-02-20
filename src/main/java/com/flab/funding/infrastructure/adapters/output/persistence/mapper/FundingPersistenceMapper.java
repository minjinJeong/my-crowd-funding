package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingCreatorEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingItemEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FundingPersistenceMapper {

    FundingPersistenceMapper INSTANCE = Mappers.getMapper(FundingPersistenceMapper.class);

    FundingEntity toFundingEntity(Funding funding);

    Funding toFunding(FundingEntity fundingEntity);

    FundingCreatorEntity toFundingCreatorEntity(FundingCreator fundingCreator);

    FundingCreator toFundingCreator(FundingCreatorEntity fundingCreatorEntity);

    FundingItemEntity toFundingItemEntity(FundingItem fundingItem);

    FundingItem toFundingItem(FundingItemEntity fundingItemEntity);

    FundingRewardEntity toFundingRewardEntity(FundingReward fundingReward);

    FundingReward toFundingReward(FundingRewardEntity fundingRewardEntity);
}
