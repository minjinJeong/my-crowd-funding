package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.*;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    FundingRewardItemEntity toFundingRewardItemEntity(FundingRewardItem fundingRewardItem);

    FundingRewardItem toFundingRewardItem(FundingRewardItemEntity fundingRewardItemEntity);

//    @IterableMapping
    List<FundingRewardItemEntity> toFundingRewardItemEntities(List<FundingRewardItem> fundingRewardItems);
}
