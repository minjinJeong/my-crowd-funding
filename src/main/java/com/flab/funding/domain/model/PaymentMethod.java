package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PaymentMethod {
    private Long id;

    private String userKey;

    private boolean defaultYN;

    private String paymentNum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
