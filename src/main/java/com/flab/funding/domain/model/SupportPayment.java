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

    public SupportPayment support(Support support) {
        this.support = support;
        return this;
    }

    public SupportPayment memberPaymentMethod(MemberPaymentMethod memberPaymentMethod) {
        this.memberPaymentMethod = memberPaymentMethod;
        return this;
    }

}
