package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.FundingPersistenceMapper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "funding_creator")
public class FundingCreatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_creator_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private FundingEntity funding;

    @ColumnDefault("false")
    private boolean isValid;

    private String businessNumber;

    private String representative;

    private String introduce;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static FundingCreatorEntity from(FundingCreator fundingCreator) {
        return FundingPersistenceMapper.INSTANCE.toFundingCreatorEntity(fundingCreator);
    }

    public FundingCreator toFundingCreator() {
        return FundingPersistenceMapper.INSTANCE.toFundingCreator(this);
    }
}
