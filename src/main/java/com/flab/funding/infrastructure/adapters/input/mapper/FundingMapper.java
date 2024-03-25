package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingCreatorRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingItemRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRewardRegisterRequest;
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

    FundingItem toFundingItem(FundingItemRegisterRequest fundingItemRegisterRequest);

    @Mapping(source = "funding.fundingKey", target = "fundingKey")
    FundingCreatorInfoResponse toFundingCreatorInfoResponse(FundingCreator fundingCreator);

    @Mapping(source = "funding.fundingKey", target = "fundingKey")
    FundingItemInfoResponse toFundingItemInfoResponse(FundingItem fundingItem);

    @Mapping(source = "funding.fundingKey", target = "fundingKey")
    FundingRewardInfoResponse toFundingRewardInfoResponse(FundingReward fundingReward);
}
