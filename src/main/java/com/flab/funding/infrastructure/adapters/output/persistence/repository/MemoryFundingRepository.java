package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingCreatorEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingItemEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// TODO : JPA 연동 후 삭제
@Repository
public class MemoryFundingRepository implements FundingRepository {

    private static final Map<Long, FundingEntity> store = new HashMap<>();
    private static final Map<Long, FundingCreatorEntity> creators = new HashMap<>();
    private static final Map<Long, FundingItemEntity> items = new HashMap<>();
    private static final Map<Long, FundingRewardEntity> rewards = new HashMap<>();

    private static Long fundingId = 1L;
    private static Long creatorId = 1L;
    private static Long itemId = 1L;
    private static Long rewardId = 1L;

    // TODO : JPA 연동 후 createAt 시간 갱신되는지 확인할 것
    @Override
    public FundingEntity save(FundingEntity funding) {

        Long id = funding.getFundingKey() != null
                ? Long.parseLong(funding.getFundingKey())
                : fundingId++;

        FundingEntity savedFunding = FundingEntity.builder()
                .id(id)
                .fundingKey(id.toString())
                .member(funding.getMember())
                .isAdult(funding.isAdult())
                .pricePlan(funding.getPricePlan())
                .categoryCode(funding.getCategoryCode())
                .expectAmount(funding.getExpectAmount())
                .status(funding.getStatus())
                .title(funding.getTitle())
                .fundingDesc(funding.getFundingDesc())
                .fundingIntroduce(funding.getFundingIntroduce())
                .budgetDesc(funding.getBudgetDesc())
                .scheduleDesc(funding.getScheduleDesc())
                .teamDesc(funding.getTeamDesc())
                .rewardDesc(funding.getRewardDesc())
                .startAt(funding.getStartAt())
                .endAt(funding.getEndAt())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        store.put(id, savedFunding);
        return savedFunding;
    }

    @Override
    public FundingCreatorEntity save(FundingCreatorEntity fundingCreatorEntity) {

        Long id = creatorId++;

        FundingCreatorEntity savedFundingCreator = FundingCreatorEntity.builder()
                .id(id)
                .funding(fundingCreatorEntity.getFunding())
                .isValid(fundingCreatorEntity.isValid())
                .businessNum(fundingCreatorEntity.getBusinessNum())
                .representative(fundingCreatorEntity.getRepresentative())
                .introduce(fundingCreatorEntity.getIntroduce())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        creators.put(id, savedFundingCreator);
        return savedFundingCreator;
    }

    @Override
    public FundingItemEntity save(FundingItemEntity fundingItemEntity) {

        Long id = itemId++;

        FundingItemEntity savedFundingItem = FundingItemEntity.builder()
                .id(id)
                .funding(fundingItemEntity.getFunding())
                .itemName(fundingItemEntity.getItemName())
                .optionType(fundingItemEntity.getOptionType())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        items.put(id, savedFundingItem);
        return savedFundingItem;
    }

    @Override
    public FundingRewardEntity save(FundingRewardEntity fundingRewardEntity) {
        Long id = rewardId++;

        FundingRewardEntity savedFundingReward = FundingRewardEntity.builder()
                .id(id)
                .funding(fundingRewardEntity.getFunding())
                .isDelivery(fundingRewardEntity.isDelivery())
                .rewardTitle(fundingRewardEntity.getRewardTitle())
                .amount(fundingRewardEntity.getAmount())
                .countLimit(fundingRewardEntity.getCountLimit())
                .personalLimit(fundingRewardEntity.getPersonalLimit())
                .expectDate(fundingRewardEntity.getExpectDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        rewards.put(id, savedFundingReward);
        return savedFundingReward;
    }

    @Override
    public Optional<FundingEntity> findByFundingId(String fundingId) {
        return Optional.ofNullable(store.get(Long.valueOf(fundingId)));
    }
}
