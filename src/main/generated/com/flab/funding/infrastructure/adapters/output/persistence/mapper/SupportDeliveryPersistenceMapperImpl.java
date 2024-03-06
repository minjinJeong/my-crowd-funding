package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-06T20:19:48+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class SupportDeliveryPersistenceMapperImpl implements SupportDeliveryPersistenceMapper {

    @Override
    public SupportDeliveryEntity SupportDeliveryEntity(SupportDelivery supportDelivery) {
        if ( supportDelivery == null ) {
            return null;
        }

        SupportDeliveryEntity.SupportDeliveryEntityBuilder supportDeliveryEntity = SupportDeliveryEntity.builder();

        supportDeliveryEntity.support( supportDeliveryToSupportEntity( supportDelivery ) );
        supportDeliveryEntity.memberDeliveryAddress( supportDeliveryToMemberDeliveryAddressEntity( supportDelivery ) );
        supportDeliveryEntity.id( supportDelivery.getId() );
        supportDeliveryEntity.status( supportDelivery.getStatus() );
        supportDeliveryEntity.shipmentName( supportDelivery.getShipmentName() );
        supportDeliveryEntity.shipmentAt( supportDelivery.getShipmentAt() );
        supportDeliveryEntity.trackingName( supportDelivery.getTrackingName() );
        supportDeliveryEntity.trackingAt( supportDelivery.getTrackingAt() );
        supportDeliveryEntity.createdAt( supportDelivery.getCreatedAt() );
        supportDeliveryEntity.updatedAt( supportDelivery.getUpdatedAt() );

        return supportDeliveryEntity.build();
    }

    @Override
    public SupportDelivery toSupportDelivery(SupportDeliveryEntity supportDeliveryEntity) {
        if ( supportDeliveryEntity == null ) {
            return null;
        }

        SupportDelivery.SupportDeliveryBuilder supportDelivery = SupportDelivery.builder();

        supportDelivery.supportId( supportDeliveryEntitySupportId( supportDeliveryEntity ) );
        supportDelivery.supportKey( supportDeliveryEntitySupportSupportKey( supportDeliveryEntity ) );
        supportDelivery.memberDeliveryAddressId( supportDeliveryEntityMemberDeliveryAddressId( supportDeliveryEntity ) );
        supportDelivery.id( supportDeliveryEntity.getId() );
        supportDelivery.status( supportDeliveryEntity.getStatus() );
        supportDelivery.shipmentName( supportDeliveryEntity.getShipmentName() );
        supportDelivery.shipmentAt( supportDeliveryEntity.getShipmentAt() );
        supportDelivery.trackingName( supportDeliveryEntity.getTrackingName() );
        supportDelivery.trackingAt( supportDeliveryEntity.getTrackingAt() );
        supportDelivery.createdAt( supportDeliveryEntity.getCreatedAt() );
        supportDelivery.updatedAt( supportDeliveryEntity.getUpdatedAt() );

        return supportDelivery.build();
    }

    protected SupportEntity supportDeliveryToSupportEntity(SupportDelivery supportDelivery) {
        if ( supportDelivery == null ) {
            return null;
        }

        SupportEntity.SupportEntityBuilder supportEntity = SupportEntity.builder();

        supportEntity.id( supportDelivery.getSupportId() );

        return supportEntity.build();
    }

    protected MemberDeliveryAddressEntity supportDeliveryToMemberDeliveryAddressEntity(SupportDelivery supportDelivery) {
        if ( supportDelivery == null ) {
            return null;
        }

        MemberDeliveryAddressEntity.MemberDeliveryAddressEntityBuilder memberDeliveryAddressEntity = MemberDeliveryAddressEntity.builder();

        memberDeliveryAddressEntity.id( supportDelivery.getMemberDeliveryAddressId() );

        return memberDeliveryAddressEntity.build();
    }

    private Long supportDeliveryEntitySupportId(SupportDeliveryEntity supportDeliveryEntity) {
        if ( supportDeliveryEntity == null ) {
            return null;
        }
        SupportEntity support = supportDeliveryEntity.getSupport();
        if ( support == null ) {
            return null;
        }
        Long id = support.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String supportDeliveryEntitySupportSupportKey(SupportDeliveryEntity supportDeliveryEntity) {
        if ( supportDeliveryEntity == null ) {
            return null;
        }
        SupportEntity support = supportDeliveryEntity.getSupport();
        if ( support == null ) {
            return null;
        }
        String supportKey = support.getSupportKey();
        if ( supportKey == null ) {
            return null;
        }
        return supportKey;
    }

    private Long supportDeliveryEntityMemberDeliveryAddressId(SupportDeliveryEntity supportDeliveryEntity) {
        if ( supportDeliveryEntity == null ) {
            return null;
        }
        MemberDeliveryAddressEntity memberDeliveryAddress = supportDeliveryEntity.getMemberDeliveryAddress();
        if ( memberDeliveryAddress == null ) {
            return null;
        }
        Long id = memberDeliveryAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
