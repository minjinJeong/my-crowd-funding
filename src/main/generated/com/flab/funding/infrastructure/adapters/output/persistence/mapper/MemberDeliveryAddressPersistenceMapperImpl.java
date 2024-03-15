package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-15T22:17:34+0900",
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

        memberDeliveryAddressEntity.id( deliveryAddress.getId() );
        memberDeliveryAddressEntity.deliveryAddressKey( deliveryAddress.getDeliveryAddressKey() );
        memberDeliveryAddressEntity.member( memberToMemberEntity( deliveryAddress.getMember() ) );
        memberDeliveryAddressEntity.isDefault( deliveryAddress.getIsDefault() );
        memberDeliveryAddressEntity.zipCode( deliveryAddress.getZipCode() );
        memberDeliveryAddressEntity.address( deliveryAddress.getAddress() );
        memberDeliveryAddressEntity.addressDetail( deliveryAddress.getAddressDetail() );
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

        deliveryAddress.id( deliveryAddressEntity.getId() );
        deliveryAddress.deliveryAddressKey( deliveryAddressEntity.getDeliveryAddressKey() );
        deliveryAddress.member( memberEntityToMember( deliveryAddressEntity.getMember() ) );
        deliveryAddress.isDefault( deliveryAddressEntity.getIsDefault() );
        deliveryAddress.zipCode( deliveryAddressEntity.getZipCode() );
        deliveryAddress.address( deliveryAddressEntity.getAddress() );
        deliveryAddress.addressDetail( deliveryAddressEntity.getAddressDetail() );
        deliveryAddress.recipientName( deliveryAddressEntity.getRecipientName() );
        deliveryAddress.recipientPhone( deliveryAddressEntity.getRecipientPhone() );
        deliveryAddress.createdAt( deliveryAddressEntity.getCreatedAt() );
        deliveryAddress.updatedAt( deliveryAddressEntity.getUpdatedAt() );

        return deliveryAddress.build();
    }

    protected MemberEntity memberToMemberEntity(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberEntity.MemberEntityBuilder memberEntity = MemberEntity.builder();

        memberEntity.id( member.getId() );
        memberEntity.userKey( member.getUserKey() );
        memberEntity.status( member.getStatus() );
        memberEntity.linkType( member.getLinkType() );
        memberEntity.email( member.getEmail() );
        memberEntity.userName( member.getUserName() );
        memberEntity.nickName( member.getNickName() );
        memberEntity.phoneNumber( member.getPhoneNumber() );
        memberEntity.gender( member.getGender() );
        memberEntity.birthday( member.getBirthday() );
        memberEntity.password( member.getPassword() );
        memberEntity.lastLoginAt( member.getLastLoginAt() );
        memberEntity.createdAt( member.getCreatedAt() );
        memberEntity.updatedAt( member.getUpdatedAt() );

        return memberEntity.build();
    }

    protected Member memberEntityToMember(MemberEntity memberEntity) {
        if ( memberEntity == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.id( memberEntity.getId() );
        member.userKey( memberEntity.getUserKey() );
        member.status( memberEntity.getStatus() );
        member.linkType( memberEntity.getLinkType() );
        member.email( memberEntity.getEmail() );
        member.userName( memberEntity.getUserName() );
        member.nickName( memberEntity.getNickName() );
        member.phoneNumber( memberEntity.getPhoneNumber() );
        member.gender( memberEntity.getGender() );
        member.birthday( memberEntity.getBirthday() );
        member.password( memberEntity.getPassword() );
        member.lastLoginAt( memberEntity.getLastLoginAt() );
        member.createdAt( memberEntity.getCreatedAt() );
        member.updatedAt( memberEntity.getUpdatedAt() );

        return member.build();
    }
}
