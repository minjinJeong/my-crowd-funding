package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-11T19:54:49+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class MemberDeliveryAddressPersistenceMapperImpl implements MemberDeliveryAddressPersistenceMapper {

    @Override
    public MemberDeliveryAddressEntity toMemberDeliveryAddressEntity(DeliveryAddress deliveryAddress) {
        if ( deliveryAddress == null ) {
            return null;
        }

        MemberDeliveryAddressEntity.MemberDeliveryAddressEntityBuilder memberDeliveryAddressEntity = MemberDeliveryAddressEntity.builder();

        memberDeliveryAddressEntity.member( toMemberEntity( deliveryAddress.getUserKey() ) );
        memberDeliveryAddressEntity.id( deliveryAddress.getId() );
        memberDeliveryAddressEntity.defaultYN( deliveryAddress.isDefaultYN() );
        memberDeliveryAddressEntity.zipCode( deliveryAddress.getZipCode() );
        memberDeliveryAddressEntity.address( deliveryAddress.getAddress() );
        memberDeliveryAddressEntity.recipientName( deliveryAddress.getRecipientName() );
        memberDeliveryAddressEntity.recipientPhone( deliveryAddress.getRecipientPhone() );
        memberDeliveryAddressEntity.createdAt( deliveryAddress.getCreatedAt() );
        memberDeliveryAddressEntity.updatedAt( deliveryAddress.getUpdatedAt() );

        return memberDeliveryAddressEntity.build();
    }

    @Override
    public DeliveryAddress toDeliveryAddress(MemberDeliveryAddressEntity deliveryAddressEntity) {
        if ( deliveryAddressEntity == null ) {
            return null;
        }

        DeliveryAddress.DeliveryAddressBuilder deliveryAddress = DeliveryAddress.builder();

        deliveryAddress.userKey( deliveryAddressEntityMemberUserKey( deliveryAddressEntity ) );
        deliveryAddress.id( deliveryAddressEntity.getId() );
        deliveryAddress.defaultYN( deliveryAddressEntity.isDefaultYN() );
        deliveryAddress.zipCode( deliveryAddressEntity.getZipCode() );
        deliveryAddress.address( deliveryAddressEntity.getAddress() );
        deliveryAddress.recipientName( deliveryAddressEntity.getRecipientName() );
        deliveryAddress.recipientPhone( deliveryAddressEntity.getRecipientPhone() );
        deliveryAddress.createdAt( deliveryAddressEntity.getCreatedAt() );
        deliveryAddress.updatedAt( deliveryAddressEntity.getUpdatedAt() );

        return deliveryAddress.build();
    }

    private String deliveryAddressEntityMemberUserKey(MemberDeliveryAddressEntity memberDeliveryAddressEntity) {
        if ( memberDeliveryAddressEntity == null ) {
            return null;
        }
        MemberEntity member = memberDeliveryAddressEntity.getMember();
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
