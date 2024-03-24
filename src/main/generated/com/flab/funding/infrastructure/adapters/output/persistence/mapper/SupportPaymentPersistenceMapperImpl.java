package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.SupportPayment;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportPaymentEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-24T20:52:32+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class SupportPaymentPersistenceMapperImpl implements SupportPaymentPersistenceMapper {

    @Override
    public SupportPaymentEntity toSupportPaymentEntity(SupportPayment supportPayment) {
        if ( supportPayment == null ) {
            return null;
        }

        SupportPaymentEntity.SupportPaymentEntityBuilder supportPaymentEntity = SupportPaymentEntity.builder();

        supportPaymentEntity.support( supportPaymentToSupportEntity( supportPayment ) );
        supportPaymentEntity.memberPaymentMethod( supportPaymentToMemberPaymentMethodEntity( supportPayment ) );
        supportPaymentEntity.id( supportPayment.getId() );
        supportPaymentEntity.status( supportPayment.getStatus() );
        supportPaymentEntity.amount( supportPayment.getAmount() );
        supportPaymentEntity.paymentAt( supportPayment.getPaymentAt() );
        supportPaymentEntity.createdAt( supportPayment.getCreatedAt() );
        supportPaymentEntity.updatedAt( supportPayment.getUpdatedAt() );

        return supportPaymentEntity.build();
    }

    @Override
    public SupportPayment toSupportPayment(SupportPaymentEntity supportPaymentEntity) {
        if ( supportPaymentEntity == null ) {
            return null;
        }

        SupportPayment.SupportPaymentBuilder supportPayment = SupportPayment.builder();

        supportPayment.supportId( supportPaymentEntitySupportId( supportPaymentEntity ) );
        supportPayment.memberPaymentMethodId( supportPaymentEntityMemberPaymentMethodId( supportPaymentEntity ) );
        supportPayment.memberPaymentMethodKey( supportPaymentEntityMemberPaymentMethodPaymentMethodKey( supportPaymentEntity ) );
        supportPayment.id( supportPaymentEntity.getId() );
        supportPayment.status( supportPaymentEntity.getStatus() );
        supportPayment.amount( supportPaymentEntity.getAmount() );
        supportPayment.paymentAt( supportPaymentEntity.getPaymentAt() );
        supportPayment.createdAt( supportPaymentEntity.getCreatedAt() );
        supportPayment.updatedAt( supportPaymentEntity.getUpdatedAt() );

        return supportPayment.build();
    }

    protected SupportEntity supportPaymentToSupportEntity(SupportPayment supportPayment) {
        if ( supportPayment == null ) {
            return null;
        }

        SupportEntity.SupportEntityBuilder supportEntity = SupportEntity.builder();

        supportEntity.id( supportPayment.getSupportId() );

        return supportEntity.build();
    }

    protected MemberPaymentMethodEntity supportPaymentToMemberPaymentMethodEntity(SupportPayment supportPayment) {
        if ( supportPayment == null ) {
            return null;
        }

        MemberPaymentMethodEntity.MemberPaymentMethodEntityBuilder<?, ?> memberPaymentMethodEntity = MemberPaymentMethodEntity.builder();

        memberPaymentMethodEntity.id( supportPayment.getMemberPaymentMethodId() );
        memberPaymentMethodEntity.paymentMethodKey( supportPayment.getMemberPaymentMethodKey() );

        return memberPaymentMethodEntity.build();
    }

    private Long supportPaymentEntitySupportId(SupportPaymentEntity supportPaymentEntity) {
        if ( supportPaymentEntity == null ) {
            return null;
        }
        SupportEntity support = supportPaymentEntity.getSupport();
        if ( support == null ) {
            return null;
        }
        Long id = support.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long supportPaymentEntityMemberPaymentMethodId(SupportPaymentEntity supportPaymentEntity) {
        if ( supportPaymentEntity == null ) {
            return null;
        }
        MemberPaymentMethodEntity memberPaymentMethod = supportPaymentEntity.getMemberPaymentMethod();
        if ( memberPaymentMethod == null ) {
            return null;
        }
        Long id = memberPaymentMethod.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String supportPaymentEntityMemberPaymentMethodPaymentMethodKey(SupportPaymentEntity supportPaymentEntity) {
        if ( supportPaymentEntity == null ) {
            return null;
        }
        MemberPaymentMethodEntity memberPaymentMethod = supportPaymentEntity.getMemberPaymentMethod();
        if ( memberPaymentMethod == null ) {
            return null;
        }
        String paymentMethodKey = memberPaymentMethod.getPaymentMethodKey();
        if ( paymentMethodKey == null ) {
            return null;
        }
        return paymentMethodKey;
    }
}
