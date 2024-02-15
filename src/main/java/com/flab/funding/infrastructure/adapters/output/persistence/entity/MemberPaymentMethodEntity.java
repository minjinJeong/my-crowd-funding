package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.MemberPaymentMethodPersistenceMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "paymentType")
@Getter
@Table(name = "user_payment_method")
public class MemberPaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_payment_method_id")
    private Long id;

    private String paymentMethodKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MemberEntity member;

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isDefault;

    private String paymentNum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static MemberPaymentMethodEntity from(PaymentMethod paymentMethod) {
        return MemberPaymentMethodPersistenceMapper.INSTANCE.toMemberPaymentMethodEntity(paymentMethod);
    }

    public PaymentMethod toPaymentMethod() {
        return MemberPaymentMethodPersistenceMapper.INSTANCE.toPaymentMethod(this);
    }

}
