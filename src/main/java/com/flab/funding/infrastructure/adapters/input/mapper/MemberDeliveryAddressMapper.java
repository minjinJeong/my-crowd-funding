package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberDeliveryAddressRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberDeliveryAddressRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberDeliveryAddressMapper {
    MemberDeliveryAddressMapper INSTANCE = Mappers.getMapper(MemberDeliveryAddressMapper.class);

    DeliveryAddress toDeliveryAddress(MemberDeliveryAddressRegisterRequest deliveryAddressRegisterRequest);

    MemberDeliveryAddressRegisterResponse toMemberDeliveryAddressRegisterResponse(DeliveryAddress deliveryAddress);
}
