package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// TODO : JPA 연동 후 삭제
public class MemoryMemberDeliveryAddressRepository implements MemberDeliveryAddressRepository {

    private static final Map<Long, MemberDeliveryAddressEntity> deliveryAddressMap = new HashMap<>();
    private static Long id = 1L;

    @Override
    public MemberDeliveryAddressEntity save(MemberDeliveryAddressEntity deliveryAddressEntity) {
        MemberDeliveryAddressEntity deliveryAddress = MemberDeliveryAddressEntity.builder()
                .id(id)
                .deliveryAddressKey(id.toString())
                .member(deliveryAddressEntity.getMember())
                .isDefault(deliveryAddressEntity.getIsDefault())
                .zipCode(deliveryAddressEntity.getZipCode())
                .address(deliveryAddressEntity.getAddress())
                .addressDetail(deliveryAddressEntity.getAddressDetail())
                .recipientName(deliveryAddressEntity.getRecipientName())
                .recipientPhone(deliveryAddressEntity.getRecipientPhone())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        deliveryAddressMap.put(id++, deliveryAddress);
        return deliveryAddress;
    }
}
