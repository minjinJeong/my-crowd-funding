package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberPaymentMethodRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberPaymentMethodRegisterResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-03T21:52:04+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class MemberPaymentMethodMapperImpl implements MemberPaymentMethodMapper {

    @Override
    public PaymentMethod toPaymentMethod(MemberPaymentMethodRegisterRequest paymentMethodRegisterRequest) {
        if ( paymentMethodRegisterRequest == null ) {
            return null;
        }

        PaymentMethod.PaymentMethodBuilder paymentMethod = PaymentMethod.builder();

        paymentMethod.userKey( paymentMethodRegisterRequest.getUserKey() );
        paymentMethod.isDefault( paymentMethodRegisterRequest.getIsDefault() );
        paymentMethod.paymentNumber( paymentMethodRegisterRequest.getPaymentNumber() );

        return paymentMethod.build();
    }

    @Override
    public MemberPaymentMethodRegisterResponse toMemberPaymentMethodRegisterResponse(PaymentMethod paymentMethod) {
        if ( paymentMethod == null ) {
            return null;
        }

        MemberPaymentMethodRegisterResponse.MemberPaymentMethodRegisterResponseBuilder memberPaymentMethodRegisterResponse = MemberPaymentMethodRegisterResponse.builder();

        memberPaymentMethodRegisterResponse.paymentMethodKey( paymentMethod.getPaymentMethodKey() );
        memberPaymentMethodRegisterResponse.userKey( paymentMethod.getUserKey() );
        memberPaymentMethodRegisterResponse.isDefault( paymentMethod.getIsDefault() );
        memberPaymentMethodRegisterResponse.paymentNumber( paymentMethod.getPaymentNumber() );
        memberPaymentMethodRegisterResponse.createdAt( paymentMethod.getCreatedAt() );
        memberPaymentMethodRegisterResponse.updatedAt( paymentMethod.getUpdatedAt() );

        return memberPaymentMethodRegisterResponse.build();
    }
}
