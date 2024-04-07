package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.FundingPersistenceMapper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "funding_reward")
public class FundingRewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_reward_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private FundingEntity funding;

    @ColumnDefault("false")
    private boolean isDelivery;

    private String rewardTitle;

    private BigInteger amount;

    @OneToMany(mappedBy = "fundingReward", cascade = CascadeType.ALL)
    private List<FundingRewardItemEntity> fundingRewardItems;

    private int countLimit;

    private int personalLimit;

    private LocalDate expectDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static FundingRewardEntity from(FundingReward fundingReward) {
        return FundingPersistenceMapper.INSTANCE.toFundingRewardEntity(fundingReward);
    }

    public FundingReward toFundingReward() {
        return FundingPersistenceMapper.INSTANCE.toFundingReward(this);
    }
}
