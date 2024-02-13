package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FundingMapper {

    FundingMapper INSTANCE = Mappers.getMapper(FundingMapper.class);

    Funding toFunding(FundingRegisterRequest fundingRegisterRequest);

    FundingRegisterResponse toFundingRegisterResponse(Funding funding);

    FundingInfoResponse toFundingInfoResponse(Funding funding);
}
