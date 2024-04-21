package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@Getter
public class SupportPayment {

    private Long id;

    private Support support;

    private MemberPaymentMethod memberPaymentMethod;

    private SupportPaymentStatus status;

    private BigInteger amount;

    private LocalDateTime paymentAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public SupportPayment with(Support support) {

        return SupportPayment.builder()
                .id(this.id)
                .support(support)
                .memberPaymentMethod(this.memberPaymentMethod)
                .status(this.status)
                .amount(this.amount)
                .paymentAt(this.paymentAt)
                .build();
    }

    public SupportPayment with(MemberPaymentMethod memberPaymentMethod) {

        return SupportPayment.builder()
                .id(this.id)
                .support(this.support)
                .memberPaymentMethod(memberPaymentMethod)
                .status(this.status)
                .amount(this.amount)
                .paymentAt(this.paymentAt)
                .build();
    }

    public SupportPayment with(Support support,
                               MemberPaymentMethod memberPaymentMethod) {

        return SupportPayment.builder()
                .id(this.id)
                .support(support)
                .memberPaymentMethod(memberPaymentMethod)
                .status(this.status)
                .amount(this.amount)
                .paymentAt(this.paymentAt)
                .build();
    }

}
