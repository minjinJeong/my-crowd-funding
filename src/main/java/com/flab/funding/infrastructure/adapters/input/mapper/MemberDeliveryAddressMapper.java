package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.MemberDeliveryAddress;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberDeliveryAddressRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberDeliveryAddressRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberDeliveryAddressMapper {

    MemberDeliveryAddressMapper INSTANCE = Mappers.getMapper(MemberDeliveryAddressMapper.class);

    @Mapping(source = "userKey", target = "member.userKey")
    MemberDeliveryAddress toDeliveryAddress(MemberDeliveryAddressRegisterRequest deliveryAddressRegisterRequest);

    @Mapping(source = "member.userKey", target = "userKey")
    MemberDeliveryAddressRegisterResponse toMemberDeliveryAddressRegisterResponse(MemberDeliveryAddress memberDeliveryAddress);
}
