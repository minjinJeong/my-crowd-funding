package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingItemOption;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.FundingItemOptionAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.FundingPersistenceMapper;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "funding_item")
public class FundingItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private FundingEntity funding;

    private String itemName;

    @Convert(converter = FundingItemOptionAttributeConverter.class)
    private FundingItemOption optionType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static FundingItemEntity toFundingItemEntity(FundingItem fundingItem) {
        return FundingPersistenceMapper.INSTANCE.toFundingItemEntity(fundingItem);
    }

    public FundingItem toFundingItem() {
        return FundingPersistenceMapper.INSTANCE.toFundingItem(this);
    }
}
