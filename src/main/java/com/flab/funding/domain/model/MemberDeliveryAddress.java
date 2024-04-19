package com.flab.funding.domain.model;

import com.flab.funding.domain.utils.MakeDomainKeyUtils;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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

    public MemberDeliveryAddress with(Member member) {

        return MemberDeliveryAddress.builder()
                .id(this.id)
                .deliveryAddressKey(this.deliveryAddressKey)
                .member(member)
                .isDefault(this.isDefault)
                .zipCode(this.zipCode)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .recipientName(this.recipientName)
                .recipientPhone(this.recipientPhone)
                .build();
    }

    public MemberDeliveryAddress register() {
        this.deliveryAddressKey = MakeDomainKeyUtils.newKey("DA");
        return this;
    }
}
