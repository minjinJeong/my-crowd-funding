package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T18:47:53+0900",
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

        memberPaymentMethodEntity.id( paymentMethod.getId() );
        memberPaymentMethodEntity.paymentMethodKey( paymentMethod.getPaymentMethodKey() );
        memberPaymentMethodEntity.member( memberToMemberEntity( paymentMethod.getMember() ) );
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

        paymentMethod.id( paymentMethodEntity.getId() );
        paymentMethod.paymentMethodKey( paymentMethodEntity.getPaymentMethodKey() );
        paymentMethod.member( memberEntityToMember( paymentMethodEntity.getMember() ) );
        paymentMethod.isDefault( paymentMethodEntity.getIsDefault() );
        paymentMethod.paymentNumber( paymentMethodEntity.getPaymentNumber() );
        paymentMethod.createdAt( paymentMethodEntity.getCreatedAt() );
        paymentMethod.updatedAt( paymentMethodEntity.getUpdatedAt() );

        return paymentMethod.build();
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
