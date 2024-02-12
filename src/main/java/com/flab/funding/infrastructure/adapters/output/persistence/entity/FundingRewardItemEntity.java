package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
}
