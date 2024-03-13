package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberDeliveryAddressRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberDeliveryAddressRegisterResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T18:47:53+0900",
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

        deliveryAddress.member( toMember( deliveryAddressRegisterRequest.getUserKey() ) );
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

        memberDeliveryAddressRegisterResponse.userKey( deliveryAddressMemberUserKey( deliveryAddress ) );
        memberDeliveryAddressRegisterResponse.deliveryAddressKey( deliveryAddress.getDeliveryAddressKey() );
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

    private String deliveryAddressMemberUserKey(DeliveryAddress deliveryAddress) {
        if ( deliveryAddress == null ) {
            return null;
        }
        Member member = deliveryAddress.getMember();
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
