package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.FundingRewardItem;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.FundingPersistenceMapper;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "funding_reward_item")
public class FundingRewardItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_reward_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private FundingEntity funding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_reward_id")
    private FundingRewardEntity fundingReward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_item_id")
    private FundingItemEntity fundingItem;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static List<FundingRewardItemEntity> from(List<FundingRewardItem> fundingRewardItems) {
        return FundingPersistenceMapper.INSTANCE.toFundingRewardItemEntities(fundingRewardItems);
    }

    public FundingRewardItem toFundingRewardItem() {
        return FundingPersistenceMapper.INSTANCE.toFundingRewardItem(this);
    }
}
