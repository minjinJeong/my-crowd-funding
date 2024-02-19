package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberPaymentMethodRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberPaymentMethodRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberPaymentMethodMapper {

    MemberPaymentMethodMapper INSTANCE = Mappers.getMapper(MemberPaymentMethodMapper.class);

    @Mapping(source = "isDefault", target = "isDefault")
    PaymentMethod toPaymentMethod(MemberPaymentMethodRegisterRequest paymentMethodRegisterRequest);

    @Mapping(source = "default", target = "isDefault")
    MemberPaymentMethodRegisterResponse toMemberPaymentMethodRegisterResponse(PaymentMethod paymentMethod);
}