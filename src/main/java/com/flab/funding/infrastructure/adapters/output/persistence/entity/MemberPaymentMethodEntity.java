package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.MemberPaymentMethod;
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
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ColumnDefault("false")
    private boolean isDefault;

    private String paymentNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static MemberPaymentMethodEntity from(MemberPaymentMethod memberPaymentMethod) {
        return MemberPaymentMethodPersistenceMapper.INSTANCE.toMemberPaymentMethodEntity(memberPaymentMethod);
    }

    public MemberPaymentMethod toPaymentMethod() {
        return MemberPaymentMethodPersistenceMapper.INSTANCE.toPaymentMethod(this);
    }

}
