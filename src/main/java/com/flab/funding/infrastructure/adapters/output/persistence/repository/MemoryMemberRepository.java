package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository, MemberDeliveryAddressRepository, MemberPaymentMethodRepository {

    private static final Map<Long, MemberEntity> store = new HashMap<>();
    private static final Map<Long, MemberDeliveryAddressEntity> deliveryAddressMap = new HashMap<>();
    private static final Map<Long, MemberPaymentMethodEntity> paymentMethodMap = new HashMap<>();
    private static Long id = 1L;
    private static Long deliveryId = 1L;
    private static Long paymentId = 1L;

    @Override
    public MemberEntity save(MemberEntity member) {
        MemberEntity savedMember = MemberEntity.builder()
                .id(id)
                .userKey(id.toString())
                .status(member.getStatus())
                .linkType(member.getLinkType())
                .email(member.getEmail())
                .userName(member.getUserName())
                .nickName(member.getNickName())
                .phoneNum(member.getPhoneNum())
                .gender(member.getGender())
                .birthday(member.getBirthday())
                .password(member.getPassword())
                .lastLoginAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        store.put(id++, savedMember);
        return savedMember;
    }

    @Override
    public Optional<MemberEntity> findByUserKey(String userKey) {
        return Optional.ofNullable(store.get(Long.valueOf(userKey)));
    }

    @Override
    public MemberDeliveryAddressEntity save(MemberDeliveryAddressEntity deliveryAddressEntity) {
        MemberDeliveryAddressEntity deliveryAddress = MemberDeliveryAddressEntity.builder()
                .id(deliveryId)
                .member(deliveryAddressEntity.getMember())
                .isDefault(deliveryAddressEntity.isDefault())
                .zipCode(deliveryAddressEntity.getZipCode())
                .address(deliveryAddressEntity.getAddress())
                .recipientName(deliveryAddressEntity.getRecipientName())
                .recipientPhone(deliveryAddressEntity.getRecipientPhone())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        deliveryAddressMap.put(deliveryId++, deliveryAddress);
        return deliveryAddress;
    }

    @Override
    public MemberPaymentMethodEntity save(MemberPaymentMethodEntity paymentMethodEntity) {
        MemberPaymentMethodEntity paymentMethod = MemberPaymentMethodEntity.builder()
                .id(paymentId)
                .member(paymentMethodEntity.getMember())
                .isDefault(paymentMethodEntity.isDefault())
                .paymentNum(paymentMethodEntity.getPaymentNum())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        paymentMethodMap.put(paymentId++, paymentMethodEntity);
        return null;
    }
}
