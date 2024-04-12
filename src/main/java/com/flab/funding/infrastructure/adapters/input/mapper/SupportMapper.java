package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.SupportDeliveryInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.SupportRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupportMapper {

    SupportMapper INSTANCE = Mappers.getMapper(SupportMapper.class);

    @Mapping(source = "userKey", target = "member.userKey")
    @Mapping(source = "fundingKey", target = "funding.fundingKey")
    @Mapping(source = "rewardId", target = "reward.id")
    Support toSupport(SupportRegisterRequest supportRegisterRequest);

    SupportRegisterResponse toSupportRegisterResponse(Support support);

    @Mapping(source = "support.supportKey", target = "supportKey")
    SupportDeliveryInfoResponse toSupportDeliveryInfoResponse(SupportDelivery supportDelivery);
}
