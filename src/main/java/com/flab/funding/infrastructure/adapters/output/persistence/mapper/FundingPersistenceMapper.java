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
    FundingRewardEntity toFundingRewardEntity(FundingReward fundingReward);

    @Mapping(source = "funding.id", target = "fundingId")
    FundingReward toFundingReward(FundingRewardEntity fundingRewardEntity);
}
