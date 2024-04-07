package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportStatus;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.SupportStatusAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.SupportPersistenceMapper;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "support")
public class SupportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private FundingEntity funding;

    @OneToOne
    @JoinColumn(name = "funding_reward_id")
    private FundingRewardEntity reward;

    private String supportKey;

    @Convert(converter = SupportStatusAttributeConverter.class)
    @Column(name = "status_code")
    private SupportStatus status;

    @OneToOne(mappedBy = "support", cascade = CascadeType.ALL)
    private SupportDeliveryEntity supportDelivery;

    @OneToOne(mappedBy = "support", cascade = CascadeType.ALL)
    private SupportPaymentEntity supportPayment;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    public static SupportEntity from(Support support) {
        return SupportPersistenceMapper.INSTANCE.toSupportEntity(support);
    }

    public Support toSupport() {
        return SupportPersistenceMapper.INSTANCE.toSupport(this);
    }
}