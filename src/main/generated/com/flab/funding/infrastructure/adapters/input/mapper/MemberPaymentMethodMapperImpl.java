package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberPaymentMethodRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberPaymentMethodRegisterResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T18:47:53+0900",
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

        paymentMethod.member( toMember( paymentMethodRegisterRequest.getUserKey() ) );
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

        memberPaymentMethodRegisterResponse.userKey( paymentMethodMemberUserKey( paymentMethod ) );
        memberPaymentMethodRegisterResponse.paymentMethodKey( paymentMethod.getPaymentMethodKey() );
        memberPaymentMethodRegisterResponse.isDefault( paymentMethod.getIsDefault() );
        memberPaymentMethodRegisterResponse.paymentNumber( paymentMethod.getPaymentNumber() );
        memberPaymentMethodRegisterResponse.createdAt( paymentMethod.getCreatedAt() );
        memberPaymentMethodRegisterResponse.updatedAt( paymentMethod.getUpdatedAt() );

        return memberPaymentMethodRegisterResponse.build();
    }

    private String paymentMethodMemberUserKey(PaymentMethod paymentMethod) {
        if ( paymentMethod == null ) {
            return null;
        }
        Member member = paymentMethod.getMember();
        if ( member == null ) {
            return null;
        }
        String userKey = member.getUserKey();
        if ( userKey == null ) {
            return null;
        }
        return userKey;
    }
}
