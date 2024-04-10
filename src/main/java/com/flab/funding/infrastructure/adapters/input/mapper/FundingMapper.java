package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.*;
import com.flab.funding.infrastructure.adapters.input.data.request.*;
import com.flab.funding.infrastructure.adapters.input.data.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FundingMapper extends DefaultMethodToMapper {

    FundingMapper INSTANCE = Mappers.getMapper(FundingMapper.class);

    Funding toFunding(FundingRegisterRequest fundingRegisterRequest);

    FundingRegisterResponse toFundingRegisterResponse(Funding funding);

    FundingInfoResponse toFundingInfoResponse(Funding funding);

    FundingCreator toFundingCreator(FundingCreatorRegisterRequest fundingCreatorRegisterRequest);

    FundingReward toFundingReward(FundingRewardRegisterRequest fundingRewardRegisterRequest);

    @Mapping(source = "fundingItemId", target = "fundingItem.id")
    FundingRewardItem toFundingRewardItem(FundingRewardItemRequest fundingRewardItemRequest);

    FundingItem toFundingItem(FundingItemRegisterRequest fundingItemRegisterRequest);

    @Mapping(source = "funding.fundingKey", target = "fundingKey")
    FundingCreatorInfoResponse toFundingCreatorInfoResponse(FundingCreator fundingCreator);

    @Mapping(source = "funding.fundingKey", target = "fundingKey")
    FundingItemInfoResponse toFundingItemInfoResponse(FundingItem fundingItem);

    @Mapping(source = "funding.fundingKey", target = "fundingKey")
    FundingRewardInfoResponse toFundingRewardInfoResponse(FundingReward fundingReward);

    @Mapping(source = "fundingItem.id", target = "fundingItemId")
    FundingRewardItemResponse toFundingRewardItemResponse (FundingRewardItem fundingRewardItem);

    FundingItemRegisterResponse toFundingItemRegisterResponse(FundingItem fundingItem);
}
