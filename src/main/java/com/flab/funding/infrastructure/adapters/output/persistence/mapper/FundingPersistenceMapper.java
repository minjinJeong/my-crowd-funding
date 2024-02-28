package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.*;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FundingPersistenceMapper {

    FundingPersistenceMapper INSTANCE = Mappers.getMapper(FundingPersistenceMapper.class);

    FundingEntity toFundingEntity(Funding funding);

    Funding toFunding(FundingEntity fundingEntity);

    @Mapping(source = "fundingKey", target = "funding.fundingKey")
    FundingCreatorEntity toFundingCreatorEntity(FundingCreator fundingCreator);

    @Mapping(source = "funding.id", target = "fundingId")
    @Mapping(source = "funding.fundingKey", target = "fundingKey")
    FundingCreator toFundingCreator(FundingCreatorEntity fundingCreatorEntity);

    @Mapping(source = "fundingId", target = "funding.id")
    @Mapping(source = "fundingKey", target = "funding.fundingKey")
    FundingItemEntity toFundingItemEntity(FundingItem fundingItem);

    @Mapping(source = "funding.id", target = "fundingId")
    @Mapping(source = "funding.fundingKey", target = "fundingKey")
    FundingItem toFundingItem(FundingItemEntity fundingItemEntity);

    @Mapping(source = "fundingId", target = "funding.id")
    @Mapping(source = "fundingKey", target = "funding.fundingKey")
    FundingRewardEntity toFundingRewardEntity(FundingReward fundingReward);

    @Mapping(source = "funding.id", target = "fundingId")
    @Mapping(source = "funding.fundingKey", target = "fundingKey")
    FundingReward toFundingReward(FundingRewardEntity fundingRewardEntity);

    @Mapping(source = "fundingId", target = "funding.id")
    @Mapping(source = "fundingRewardId", target = "fundingReward.id")
    @Mapping(source = "fundingItemId", target = "fundingItem.id")
    FundingRewardItemEntity toFundingRewardItemEntity(FundingRewardItem fundingRewardItem);

    @Mapping(source = "funding.id", target = "fundingId")
    @Mapping(source = "fundingReward.id", target = "fundingRewardId")
    @Mapping(source = "fundingItem.id", target = "fundingItemId")
    FundingRewardItem toFundingRewardItem(FundingRewardItemEntity fundingRewardItemEntity);
}
