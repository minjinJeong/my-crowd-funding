package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class DeliveryAddress {
    private Long id;

    private String userKey;

    private boolean defaultYN;

    private String zipCode;

    private String address;

    private String addressDetail;

    private String recipientName;

    private String recipientPhone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
