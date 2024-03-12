package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class PaymentMethod {
    private Long id;

    private String paymentMethodKey;

    private Member member;

    private boolean isDefault;

    private String paymentNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public PaymentMethod register(Member member) {
        this.member = member;
        makeKey();
        return this;
    }

    // TODO key 생성 로직 수정
    private void makeKey() {
        this.paymentMethodKey = UUID.randomUUID().toString();
    }
}
