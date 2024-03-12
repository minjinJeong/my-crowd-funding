package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberDeliveryAddressRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberDeliveryAddressRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberDeliveryAddressMapper {
    MemberDeliveryAddressMapper INSTANCE = Mappers.getMapper(MemberDeliveryAddressMapper.class);

    @Mapping(source = "userKey", target = "member", qualifiedByName = "toMember")
    DeliveryAddress toDeliveryAddress(MemberDeliveryAddressRegisterRequest deliveryAddressRegisterRequest);

    @Mapping(source = "member.userKey", target = "userKey")
    MemberDeliveryAddressRegisterResponse toMemberDeliveryAddressRegisterResponse(DeliveryAddress deliveryAddress);

    @Named("toMember")
    default Member toMember(String value) {
        if (value == null) {
            return null;
        }

        return Member.builder().userKey(value).build();
    }
}
