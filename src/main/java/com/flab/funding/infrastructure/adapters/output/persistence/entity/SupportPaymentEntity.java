package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.SupportPayment;
import com.flab.funding.domain.model.SupportPaymentStatus;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.SupportPaymentStatusAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.SupportPaymentPersistenceMapper;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "support_payment")
public class SupportPaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_payment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "support_id")
    private SupportEntity support;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_payment_method_id")
    private MemberPaymentMethodEntity memberPaymentMethod;

    @Convert(converter = SupportPaymentStatusAttributeConverter.class)
    @Column(name = "status_code")
    private SupportPaymentStatus status;

    private BigInteger amount;

    private LocalDateTime paymentAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static SupportPaymentEntity from(SupportPayment supportPayment) {
        return SupportPaymentPersistenceMapper.INSTANCE.toSupportPaymentEntity(supportPayment);
    }

    public SupportPayment toSupportPayment() {
        return SupportPaymentPersistenceMapper.INSTANCE.toSupportPayment(this);
    }
}