package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class MemberPaymentMethod {

    private Long id;
    private String paymentMethodKey;
    private Member member;
    private boolean isDefault;
    private String paymentNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemberPaymentMethod member(Member member) {
        this.member = member;
        return this;
    }

    public MemberPaymentMethod register() {
        this.paymentMethodKey = makeKey();
        return this;
    }

    // TODO key 생성 로직 수정
    private String makeKey() {
        return UUID.randomUUID().toString();
    }
}
