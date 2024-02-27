package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@Getter
public class SupportPayment {
    private Long id;
    private Long supportId;
    private Long memberPaymentMethodId;
    private SupportPaymentStatus status;
    private BigInteger amount;
    private LocalDateTime paymentAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
