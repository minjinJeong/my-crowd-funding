package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class DeliveryAddress {
    private Long id;

    private String deliveryAddressKey;

    private Member member;

    private boolean isDefault;

    private String zipCode;

    private String address;

    private String addressDetail;

    private String recipientName;

    private String recipientPhone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DeliveryAddress register(Member member) {
        this.member = member;
        makeKey();
        return this;
    }

    // TODO key 생성 로직 수정
    private void makeKey() {
        this.deliveryAddressKey = UUID.randomUUID().toString();
    }
}
