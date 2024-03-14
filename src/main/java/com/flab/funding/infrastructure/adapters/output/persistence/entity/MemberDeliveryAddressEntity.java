package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.MemberDeliveryAddressPersistenceMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Table(name = "user_delivery_address")
public class MemberDeliveryAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_delivery_address_id")
    private Long id;

    private String deliveryAddressKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ColumnDefault("false")
    private boolean isDefault;

    private String zipCode;

    private String address;

    private String addressDetail;

    private String recipientName;

    private String recipientPhone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static MemberDeliveryAddressEntity from(DeliveryAddress deliveryAddress) {
        return MemberDeliveryAddressPersistenceMapper.INSTANCE.toMemberDeliveryAddressEntity(deliveryAddress);
    }

    public DeliveryAddress toDeliveryAddress() {
        return MemberDeliveryAddressPersistenceMapper.INSTANCE.toDeliveryAddress(this);
    }
}
