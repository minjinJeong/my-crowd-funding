package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberPaymentMethodRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberPaymentMethodRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberPaymentMethodMapper {

    MemberPaymentMethodMapper INSTANCE = Mappers.getMapper(MemberPaymentMethodMapper.class);

    @Mapping(source = "userKey", target = "member", qualifiedByName = "toMember")
    PaymentMethod toPaymentMethod(MemberPaymentMethodRegisterRequest paymentMethodRegisterRequest);

    @Mapping(source = "member.userKey", target = "userKey")
    MemberPaymentMethodRegisterResponse toMemberPaymentMethodRegisterResponse(PaymentMethod paymentMethod);

    @Named("toMember")
    default Member toMember(String value) {
        if (value == null) {
            return null;
        }

        return Member.builder().userKey(value).build();
    }
}