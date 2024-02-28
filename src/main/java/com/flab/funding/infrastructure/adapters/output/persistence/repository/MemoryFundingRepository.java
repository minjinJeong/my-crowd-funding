package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

// TODO : JPA 연동 후 삭제
@Repository
public class MemoryFundingRepository implements FundingRepository {

    private static final Map<Long, FundingEntity> store = new HashMap<>();
    private static final Map<Long, FundingTagEntity> tags = new HashMap<>();
    private static final Map<Long, FundingCreatorEntity> creators = new HashMap<>();
    private static final Map<Long, FundingItemEntity> items = new HashMap<>();
    private static final Map<Long, FundingItemOptionEntity> itemOptions = new HashMap<>();
    private static final Map<Long, FundingRewardEntity> rewards = new HashMap<>();
    private static final Map<Long, FundingRewardItemEntity> rewardItems = new HashMap<>();

    private static Long fundingId = 1L;
    private static Long tagId = 1L;
    private static Long creatorId = 1L;
    private static Long itemId = 1L;
    private static Long itemOptionId = 1L;
    private static Long rewardId = 1L;
    private static Long rewardItemId = 1L;

    // TODO : JPA 연동 후 createAt 시간 갱신되는지 확인할 것
    @Override
    public FundingEntity save(FundingEntity fundingEntity) {

        Long id = fundingEntity.getFundingKey() != null
                ? Long.parseLong(fundingEntity.getFundingKey())
                : fundingId++;

        List<FundingTagEntity> savedFundingTags = saveFundingTags(id, fundingEntity.getTags());

        FundingEntity savedFunding = FundingEntity.builder()
                .id(id)
                .fundingKey(id.toString())
                .member(fundingEntity.getMember())
                .isAdult(fundingEntity.getIsAdult())
                .pricePlan(fundingEntity.getPricePlan())
                .categoryCode(fundingEntity.getCategoryCode())
                .expectAmount(fundingEntity.getExpectAmount())
                .status(fundingEntity.getStatus())
                .title(fundingEntity.getTitle())
                .fundingDesc(fundingEntity.getFundingDesc())
                .fundingIntroduce(fundingEntity.getFundingIntroduce())
                .budgetDesc(fundingEntity.getBudgetDesc())
                .scheduleDesc(fundingEntity.getScheduleDesc())
                .teamDesc(fundingEntity.getTeamDesc())
                .rewardDesc(fundingEntity.getRewardDesc())
                .tags(savedFundingTags)
                .startAt(fundingEntity.getStartAt())
                .endAt(fundingEntity.getEndAt())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        store.put(id, savedFunding);
        return savedFunding;
    }

    private List<FundingTagEntity> saveFundingTags(Long fundingId, List<FundingTagEntity> fundingTags) {

        Long id = tagId++;
        List<FundingTagEntity> savedTags = new ArrayList<>();

        if (fundingTags == null) {
            return savedTags;
        }

        for (FundingTagEntity tag : fundingTags) {

            FundingTagEntity savedTagEntity = FundingTagEntity.builder()
                    .id(id)
                    .funding(FundingEntity.builder().id(fundingId).build())
                    .tag(tag.getTag())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            savedTags.add(savedTagEntity);
            tags.put(id, savedTagEntity);
        }

        return savedTags;
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
        for (FundingItemOptionEntity itemOption : fundingItemOptions) {
            Long id = itemOptionId++;

            FundingItemOptionEntity savedItemOptionEntity = FundingItemOptionEntity.builder()
                    .id(id)
                    .fundingItem(FundingItemEntity.builder().id(fundingItemId).build())
                    .option(itemOption.getOption())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            savedItemOptions.add(savedItemOptionEntity);
            itemOptions.put(id, savedItemOptionEntity);
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

    private List<FundingRewardItemEntity> savedRewardItems(String fundingKey, Long fundingRewardId, List<FundingRewardItemEntity> fundingRewardItems) {

        List<FundingRewardItemEntity> savedRewardItems = new ArrayList<>();

        FundingEntity fundingEntity = store.get(Long.valueOf(fundingKey));

        for (FundingRewardItemEntity rewardItem : fundingRewardItems) {
            Long id = rewardItemId++;

            FundingRewardItemEntity savedRewardItemEntity = FundingRewardItemEntity.builder()
                    .id(id)
                    .funding(fundingEntity)
                    .fundingReward(FundingRewardEntity.builder().id(fundingRewardId).build())
                    .fundingItem(rewardItem.getFundingItem())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            savedRewardItems.add(savedRewardItemEntity);
            rewardItems.put(id, savedRewardItemEntity);
        }

        return savedRewardItems;
    }

    @Override
    public Optional<FundingEntity> findByFundingKey(String fundingKey) {
        return Optional.ofNullable(store.get(Long.valueOf(fundingKey)));
    }
}
