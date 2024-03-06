package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberDeliveryAddressRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberDeliveryAddressRegisterResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-06T20:19:47+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class MemberDeliveryAddressMapperImpl implements MemberDeliveryAddressMapper {

    @Override
    public DeliveryAddress toDeliveryAddress(MemberDeliveryAddressRegisterRequest deliveryAddressRegisterRequest) {
        if ( deliveryAddressRegisterRequest == null ) {
            return null;
        }

        DeliveryAddress.DeliveryAddressBuilder deliveryAddress = DeliveryAddress.builder();

        deliveryAddress.userKey( deliveryAddressRegisterRequest.getUserKey() );
        deliveryAddress.isDefault( deliveryAddressRegisterRequest.getIsDefault() );
        deliveryAddress.zipCode( deliveryAddressRegisterRequest.getZipCode() );
        deliveryAddress.address( deliveryAddressRegisterRequest.getAddress() );
        deliveryAddress.addressDetail( deliveryAddressRegisterRequest.getAddressDetail() );
        deliveryAddress.recipientName( deliveryAddressRegisterRequest.getRecipientName() );
        deliveryAddress.recipientPhone( deliveryAddressRegisterRequest.getRecipientPhone() );

        return deliveryAddress.build();
    }

    @Override
    public MemberDeliveryAddressRegisterResponse toMemberDeliveryAddressRegisterResponse(DeliveryAddress deliveryAddress) {
        if ( deliveryAddress == null ) {
            return null;
        }

        MemberDeliveryAddressRegisterResponse.MemberDeliveryAddressRegisterResponseBuilder memberDeliveryAddressRegisterResponse = MemberDeliveryAddressRegisterResponse.builder();

        memberDeliveryAddressRegisterResponse.deliveryAddressKey( deliveryAddress.getDeliveryAddressKey() );
        memberDeliveryAddressRegisterResponse.userKey( deliveryAddress.getUserKey() );
        memberDeliveryAddressRegisterResponse.isDefault( deliveryAddress.getIsDefault() );
        memberDeliveryAddressRegisterResponse.zipCode( deliveryAddress.getZipCode() );
        memberDeliveryAddressRegisterResponse.address( deliveryAddress.getAddress() );
        memberDeliveryAddressRegisterResponse.addressDetail( deliveryAddress.getAddressDetail() );
        memberDeliveryAddressRegisterResponse.recipientName( deliveryAddress.getRecipientName() );
        memberDeliveryAddressRegisterResponse.recipientPhone( deliveryAddress.getRecipientPhone() );
        memberDeliveryAddressRegisterResponse.createdAt( deliveryAddress.getCreatedAt() );
        memberDeliveryAddressRegisterResponse.updatedAt( deliveryAddress.getUpdatedAt() );

        return memberDeliveryAddressRegisterResponse.build();
    }
}
