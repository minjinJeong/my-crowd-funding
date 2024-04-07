package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class MemberDeliveryAddress {

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

    public MemberDeliveryAddress member(Member member) {
        this.member = member;
        return this;
    }

    public MemberDeliveryAddress register() {
        this.deliveryAddressKey = makeKey();
        return this;
    }

    // TODO key 생성 로직 수정
    private String makeKey() {
        return UUID.randomUUID().toString();
    }
}
