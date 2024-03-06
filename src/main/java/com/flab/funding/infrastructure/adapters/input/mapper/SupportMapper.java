package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.SupportDeliveryInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.SupportRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupportMapper {

    SupportMapper INSTANCE = Mappers.getMapper(SupportMapper.class);

    Support toSupport(SupportRegisterRequest supportRegisterRequest);

    SupportRegisterResponse toSupportRegisterResponse(Support support);

    SupportDeliveryInfoResponse toSupportDeliveryInfoResponse(SupportDelivery supportDelivery);
}
