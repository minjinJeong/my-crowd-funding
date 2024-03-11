package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-11T22:11:58+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class MemberPaymentMethodPersistenceMapperImpl implements MemberPaymentMethodPersistenceMapper {

    @Override
    public MemberPaymentMethodEntity toMemberPaymentMethodEntity(PaymentMethod paymentMethod) {
        if ( paymentMethod == null ) {
            return null;
        }

        MemberPaymentMethodEntity.MemberPaymentMethodEntityBuilder<?, ?> memberPaymentMethodEntity = MemberPaymentMethodEntity.builder();

        memberPaymentMethodEntity.member( toMemberEntity( paymentMethod.getUserKey() ) );
        memberPaymentMethodEntity.id( paymentMethod.getId() );
        memberPaymentMethodEntity.paymentMethodKey( paymentMethod.getPaymentMethodKey() );
        memberPaymentMethodEntity.isDefault( paymentMethod.getIsDefault() );
        memberPaymentMethodEntity.paymentNumber( paymentMethod.getPaymentNumber() );
        memberPaymentMethodEntity.createdAt( paymentMethod.getCreatedAt() );
        memberPaymentMethodEntity.updatedAt( paymentMethod.getUpdatedAt() );

        return memberPaymentMethodEntity.build();
    }

    @Override
    public PaymentMethod toPaymentMethod(MemberPaymentMethodEntity paymentMethodEntity) {
        if ( paymentMethodEntity == null ) {
            return null;
        }

        PaymentMethod.PaymentMethodBuilder paymentMethod = PaymentMethod.builder();

        paymentMethod.userKey( paymentMethodEntityMemberUserKey( paymentMethodEntity ) );
        paymentMethod.id( paymentMethodEntity.getId() );
        paymentMethod.paymentMethodKey( paymentMethodEntity.getPaymentMethodKey() );
        paymentMethod.isDefault( paymentMethodEntity.getIsDefault() );
        paymentMethod.paymentNumber( paymentMethodEntity.getPaymentNumber() );
        paymentMethod.createdAt( paymentMethodEntity.getCreatedAt() );
        paymentMethod.updatedAt( paymentMethodEntity.getUpdatedAt() );

        return paymentMethod.build();
    }

    private String paymentMethodEntityMemberUserKey(MemberPaymentMethodEntity memberPaymentMethodEntity) {
        if ( memberPaymentMethodEntity == null ) {
            return null;
        }
        MemberEntity member = memberPaymentMethodEntity.getMember();
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
