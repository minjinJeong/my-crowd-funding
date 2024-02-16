package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FundingMapper {

    FundingMapper INSTANCE = Mappers.getMapper(FundingMapper.class);

    @Mapping(source = "isAdult", target = "isAdult")
    Funding toFunding(FundingRegisterRequest fundingRegisterRequest);

    FundingRegisterResponse toFundingRegisterResponse(Funding funding);

    FundingInfoResponse toFundingInfoResponse(Funding funding);
}
