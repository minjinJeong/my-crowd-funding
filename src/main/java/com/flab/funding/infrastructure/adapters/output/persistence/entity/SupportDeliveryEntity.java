package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportDeliveryStatus;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.SupportDeliveryStatusAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.SupportDeliveryPersistenceMapper;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "support_delivery")
public class SupportDeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "support_id")
    private SupportEntity support;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_delivery_address_id")
    private MemberDeliveryAddressEntity memberDeliveryAddress;

    @Convert(converter = SupportDeliveryStatusAttributeConverter.class)
    @Column(name = "status_code")
    private SupportDeliveryStatus status;

    private String shipmentName;

    private LocalDateTime shipmentAt;

    private String trackingName;

    private LocalDateTime trackingAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static SupportDeliveryEntity from(SupportDelivery supportDelivery) {
        return SupportDeliveryPersistenceMapper.INSTANCE.SupportDeliveryEntity(supportDelivery);
    }

    public SupportDelivery toSupportDelivery() {
        return SupportDeliveryPersistenceMapper.INSTANCE.toSupportDelivery(this);
    }
}
