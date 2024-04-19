package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.MemberPaymentMethod;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberPaymentMethodRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberPaymentMethodRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberPaymentMethodMapper {

    MemberPaymentMethodMapper INSTANCE = Mappers.getMapper(MemberPaymentMethodMapper.class);

    @Mapping(source = "userKey", target = "member.userKey")
    MemberPaymentMethod toPaymentMethod(MemberPaymentMethodRegisterRequest paymentMethodRegisterRequest);

    @Mapping(source = "member.userKey", target = "userKey")
    MemberPaymentMethodRegisterResponse toMemberPaymentMethodRegisterResponse(MemberPaymentMethod memberPaymentMethod);
}