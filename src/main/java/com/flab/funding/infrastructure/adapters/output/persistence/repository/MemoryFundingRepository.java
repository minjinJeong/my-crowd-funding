package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

// TODO : JPA 연동 후 삭제
@Repository
public class MemoryFundingRepository implements FundingRepository {

    private static final Map<Long, FundingEntity> store = new HashMap<>();
    private static final Map<Long, FundingCreatorEntity> creators = new HashMap<>();
    private static final Map<Long, FundingItemEntity> items = new HashMap<>();
    private static final Map<Long, FundingItemOptionEntity> itemOptions = new HashMap<>();
    private static final Map<Long, FundingRewardEntity> rewards = new HashMap<>();
    private static final Map<Long, FundingRewardItemEntity> rewardItems = new HashMap<>();

    private static Long fundingId = 1L;
    private static Long creatorId = 1L;
    private static Long itemId = 1L;
    private static Long itemOptionId = 1L;
    private static Long rewardId = 1L;
    private static Long rewardItemId = 1L;

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
                .isAdult(funding.getIsAdult())
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

        String fundingKey = fundingCreatorEntity.getFunding().getFundingKey();
        FundingEntity fundingEntity = store.get(Long.valueOf(fundingKey));

        FundingCreatorEntity savedFundingCreator = FundingCreatorEntity.builder()
                .id(id)
                .funding(fundingEntity)
                .isValid(fundingCreatorEntity.getIsValid())
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

        String fundingKey = fundingItemEntity.getFunding().getFundingKey();
        FundingEntity fundingEntity = store.get(Long.valueOf(fundingKey));

        List<FundingItemOptionEntity> fundingItemOptionEntities
                = this.saveItemOptions(id, fundingItemEntity.getFundingItemOptions());

        FundingItemEntity savedFundingItem = FundingItemEntity.builder()
                .id(id)
                .funding(fundingEntity)
                .itemName(fundingItemEntity.getItemName())
                .optionType(fundingItemEntity.getOptionType())
                .fundingItemOptions(fundingItemOptionEntities)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        items.put(id, savedFundingItem);
        return savedFundingItem;
    }

    private List<FundingItemOptionEntity> saveItemOptions(Long fundingItemId, List<FundingItemOptionEntity> fundingItemOptions) {

        List<FundingItemOptionEntity> savedItemOptions = new ArrayList<>();
        for (FundingItemOptionEntity option : fundingItemOptions) {
            Long id = itemOptionId++;

            FundingItemOptionEntity itemOption = FundingItemOptionEntity.builder()
                    .id(id)
                    .fundingItem(FundingItemEntity.builder().id(fundingItemId).build())
                    .option(option.getOption())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            savedItemOptions.add(itemOption);
            itemOptions.put(id, itemOption);
        }

        return savedItemOptions;
    }

    @Override
    public FundingRewardEntity save(FundingRewardEntity fundingRewardEntity) {

        Long id = rewardId++;

        String fundingKey = fundingRewardEntity.getFunding().getFundingKey();
        FundingEntity fundingEntity = store.get(Long.valueOf(fundingKey));

        List<FundingRewardItemEntity> fundingRewardItemEntities
                = this.savedRewardItems(fundingKey, id, fundingRewardEntity.getFundingRewardItems());

        FundingRewardEntity savedFundingReward = FundingRewardEntity.builder()
                .id(id)
                .funding(fundingEntity)
                .isDelivery(fundingRewardEntity.getIsDelivery())
                .rewardTitle(fundingRewardEntity.getRewardTitle())
                .amount(fundingRewardEntity.getAmount())
                .fundingRewardItems(fundingRewardItemEntities)
                .countLimit(fundingRewardEntity.getCountLimit())
                .personalLimit(fundingRewardEntity.getPersonalLimit())
                .expectDate(fundingRewardEntity.getExpectDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        rewards.put(id, savedFundingReward);
        return savedFundingReward;
    }

    private List<FundingRewardItemEntity> savedRewardItems(String fundingKey, Long fundingRewardId, List<FundingRewardItemEntity> fundingRewardItemEntities) {

        List<FundingRewardItemEntity> savedRewardItems = new ArrayList<>();

        FundingEntity fundingEntity = store.get(Long.valueOf(fundingKey));

        for (FundingRewardItemEntity item : fundingRewardItemEntities) {
            Long id = rewardItemId++;

            FundingRewardItemEntity rewardItem = FundingRewardItemEntity.builder()
                    .id(id)
                    .funding(fundingEntity)
                    .fundingReward(FundingRewardEntity.builder().id(fundingRewardId).build())
                    .fundingItem(item.getFundingItem())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            savedRewardItems.add(rewardItem);
            rewardItems.put(id, rewardItem);
        }

        return savedRewardItems;
    }

    @Override
    public Optional<FundingEntity> findByFundingId(String fundingId) {
        return Optional.ofNullable(store.get(Long.valueOf(fundingId)));
    }
}
