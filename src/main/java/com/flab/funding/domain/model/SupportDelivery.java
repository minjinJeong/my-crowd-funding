package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class SupportDelivery {

    private Long id;

    private Support support;

    private MemberDeliveryAddress memberDeliveryAddress;

    private SupportDeliveryStatus status;

    private String shipmentName;

    private LocalDateTime shipmentAt;

    private String trackingName;

    private LocalDateTime trackingAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public SupportDelivery with(Support support,
                                MemberDeliveryAddress memberDeliveryAddress) {

        return SupportDelivery.builder()
                .id(this.id)
                .support(support)
                .memberDeliveryAddress(memberDeliveryAddress)
                .status(this.status)
                .shipmentName(this.shipmentName)
                .shipmentAt(this.shipmentAt)
                .trackingName(this.trackingName)
                .trackingAt(this.trackingAt)
                .build();
    }

    public SupportDelivery shippedOut() {
        this.status = SupportDeliveryStatus.SHIPPED;
        return this;
    }

    public SupportDelivery inDelivery() {
        this.status = SupportDeliveryStatus.IN_DELIVERY;
        return this;
    }

    public SupportDelivery complete() {
        this.status = SupportDeliveryStatus.COMPLETE;
        return this;
    }
}
