package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCategory;
import com.flab.funding.domain.model.FundingStatus;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.FundingCategoryAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.FundingStatusAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.FundingPersistenceMapper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "funding")
public class FundingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MemberEntity member;

    @ColumnDefault("false")
    @Column(name = "is_adult", columnDefinition = "TINYINT(1)")
    private boolean adult;

    private String price_plan;

    @Convert(converter = FundingCategoryAttributeConverter.class)
    private FundingCategory categoryCode;

    private BigInteger expectAmount;

    @Convert(converter = FundingStatusAttributeConverter.class)
    private FundingStatus status;

    private String title;

    private String fundingDesc;

    private String fundingIntroduce;

    private String budgetDesc;

    private String scheduleDesc;

    private String teamDesc;

    private String rewardDesc;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    public static FundingEntity from(Funding funding) {
        return FundingPersistenceMapper.INSTANCE.toFundingEntity(funding);
    }

    public Funding toFunding() {
        return FundingPersistenceMapper.INSTANCE.toFunding(this);
    }
}
