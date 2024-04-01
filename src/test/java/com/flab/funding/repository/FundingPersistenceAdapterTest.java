package com.flab.funding.repository;

import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.domain.model.*;
import com.flab.funding.infrastructure.adapters.output.persistence.FundingPersistenceAdapter;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.FundingCreatorRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.FundingItemRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.FundingRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.FundingRewardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FundingPersistenceAdapterTest {

    private final FundingPort fundingPort;

    public FundingPersistenceAdapterTest(FundingRepository fundingRepository,
                                         FundingCreatorRepository fundingCreatorRepository,
                                         FundingItemRepository fundingItemRepository,
                                         FundingRewardRepository fundingRewardRepository) {

        this.fundingPort = new FundingPersistenceAdapter(
                fundingRepository,
                fundingCreatorRepository,
                fundingItemRepository,
                fundingRewardRepository
        );
    }

    @Test
    @DisplayName("펀딩 등록")
    public void saveFunding() {
        //given
        Funding funding = getFunding();

        //when
        Funding savedFunding = fundingPort.saveFunding(funding);

        //then
        assertNotNull(savedFunding.getId());
        assertNotNull(savedFunding.getFundingKey());
        assertEquals(funding.getMember().getUserKey(), savedFunding.getMember().getUserKey());
        assertEquals(funding.getIsAdult(), savedFunding.getIsAdult());
        assertEquals(funding.getPricePlan(), savedFunding.getPricePlan());
        assertEquals(funding.getCategory(), savedFunding.getCategory());
        assertEquals(funding.getExpectAmount(), savedFunding.getExpectAmount());
        assertEquals(FundingStatus.REGISTER, savedFunding.getStatus());
        assertEquals(funding.getTitle(), savedFunding.getTitle());
        assertEquals(funding.getFundingDescription(), savedFunding.getFundingDescription());
        assertEquals(funding.getFundingIntroduce(), savedFunding.getFundingIntroduce());
        assertEquals(funding.getBudgetDescription(), savedFunding.getBudgetDescription());
        assertEquals(funding.getScheduleDescription(), savedFunding.getScheduleDescription());
        assertEquals(funding.getTeamDescription(), savedFunding.getTeamDescription());
        assertEquals(funding.getRewardDescription(), savedFunding.getRewardDescription());
        assertIterableEquals(funding.getTags(), savedFunding.getTags());
        assertEquals(funding.getStartAt(), savedFunding.getStartAt());
        assertEquals(funding.getEndAt(), savedFunding.getEndAt());
    }

    private Funding getFunding() {
        return Funding.builder()
                .member(getMember())
                .isAdult(false)
                .pricePlan("00")
                .category(FundingCategory.FOOD)
                .expectAmount(BigInteger.valueOf(100000))
                .title("제목")
                .fundingDescription("펀딩 상세")
                .fundingIntroduce("펀딩 소개글")
                .budgetDescription("예산 계획")
                .scheduleDescription("펀딩 계획")
                .teamDescription("팀 소개")
                .rewardDescription("리워드 소개")
                .tags(getTags())
                .startAt(LocalDateTime.of(2024, 2, 1, 12, 0))
                .endAt(LocalDateTime.of(2024, 2, 28, 12, 0))
                .build();
    }

    private Member getMember() {
        return Member.builder()
                .userKey("MM-0001")
                .build();
    }

    private List<FundingTag> getTags() {
        List<FundingTag> fundingTags = new ArrayList<>();
        fundingTags.add(createTag("검색 키워드1"));
        fundingTags.add(createTag("검색 키워드2"));
        fundingTags.add(createTag("검색 키워드3"));
        return fundingTags;
    }

    private FundingTag createTag(String tag) {
        return FundingTag.builder()
                .tag(tag)
                .build();
    }

    @Test
    @DisplayName("펀딩 조회")
    public void getFundingByFundingKey() {
        //given
        Funding funding = getFunding();
        Funding savedFunding = fundingPort.saveFunding(funding);

        //when
        Funding findFunding = fundingPort.getFundingByFundingKey(savedFunding.getFundingKey());

        //then
        assertNotNull(savedFunding.getFundingKey());
        assertEquals(savedFunding.getFundingKey(), findFunding.getFundingKey());
    }

    @Test
    @DisplayName("펀딩 창작자 등록")
    public void saveFundingCreator() {
        //given
        FundingCreator fundingCreator = getFundingCreator();

        //when
        FundingCreator savedFundingCreator = fundingPort.saveFundingCreator(fundingCreator);

        //then
        assertNotNull(savedFundingCreator.getId());
        assertEquals(fundingCreator.getFunding(), savedFundingCreator.getFunding());
        assertEquals(fundingCreator.getIsValid(), savedFundingCreator.getIsValid());
        assertEquals(fundingCreator.getBusinessNumber(), savedFundingCreator.getBusinessNumber());
        assertEquals(fundingCreator.getRepresentative(), savedFundingCreator.getRepresentative());
        assertEquals(fundingCreator.getIntroduce(), savedFundingCreator.getIntroduce());
    }

    private FundingCreator getFundingCreator() {
        return FundingCreator.builder()
                .funding(getFundingRequest())
                .isValid(true)
                .businessNumber("12345678")
                .representative("홍길동")
                .introduce("안녕하세요, 개인 사업자 홍길동입니다.")
                .build();
    }

    private Funding getFundingRequest() {
        return Funding.builder().fundingKey("FF-0001").build();
    }

    @Test
    @DisplayName("펀딩 아이템 등록")
    public void saveFundingItem() {
        //given
        FundingItem fundingItem = getFundingItem();

        //when
        FundingItem savedFundingItem = fundingPort.saveFundingItem(fundingItem);

        //then
        assertNull(savedFundingItem.getId());
        assertEquals(fundingItem.getFunding(), savedFundingItem.getFunding());
        assertEquals(fundingItem.getItemName(), savedFundingItem.getItemName());
        assertEquals(fundingItem.getOptionType(), savedFundingItem.getOptionType());
        assertIterableEquals(fundingItem.getFundingItemOptions(), savedFundingItem.getFundingItemOptions());
    }

    private FundingItem getFundingItem() {
        return FundingItem.builder()
                .funding(getFundingRequest())
                .itemName("은 귀걸이")
                .optionType(FundingItemOptionType.NONE)
                .fundingItemOptions(getFundingItemOptions())
                .build();
    }

    private List<FundingItemOption> getFundingItemOptions() {
        List<FundingItemOption> itemOptions = new  ArrayList<>();
        itemOptions.add(createItemOption("3mm"));
        itemOptions.add(createItemOption("5mm"));
        itemOptions.add(createItemOption("7mm"));
        itemOptions.add(createItemOption("9mm"));
        return itemOptions;
    }

    private FundingItemOption createItemOption(String option) {
        return FundingItemOption.builder().option(option).build();
    }

    @Test
    @DisplayName("펀딩 리워드 등록")
    public void saveFundingReward() {
        //given
        FundingReward fundingReward = getFundingReward();
        
        //when
        FundingReward savedFundingReward = fundingPort.saveFundingReward(fundingReward);

        //then
        assertNull(savedFundingReward.getId());
        assertEquals(fundingReward.getFunding(), savedFundingReward.getFunding());
        assertEquals(fundingReward.getIsDelivery(), savedFundingReward.getIsDelivery());
        assertEquals(fundingReward.getRewardTitle(), savedFundingReward.getRewardTitle());
        assertEquals(fundingReward.getAmount(), savedFundingReward.getAmount());
        assertIterableEquals(fundingReward.getFundingRewardItems(), savedFundingReward.getFundingRewardItems());
        assertEquals(fundingReward.getCountLimit(), savedFundingReward.getCountLimit());
        assertEquals(fundingReward.getPersonalLimit(), savedFundingReward.getPersonalLimit());
        assertEquals(fundingReward.getExpectDate(), savedFundingReward.getExpectDate());
    }

    private FundingReward getFundingReward() {
        return FundingReward.builder()
                .funding(getFundingRequest())
                .isDelivery(true)
                .rewardTitle("귀걸이 세트")
                .amount(BigInteger.valueOf(15000))
                .fundingRewardItems(getFundingRewardItems())
                .countLimit(10)
                .personalLimit(5)
                .expectDate(LocalDate.of(2024, 3, 31))
                .build();
    }

    private List<FundingRewardItem> getFundingRewardItems() {
        List<FundingRewardItem> rewardItems = new ArrayList<>();
        rewardItems.add(createRewardItem(1L));
        rewardItems.add(createRewardItem(2L));
        rewardItems.add(createRewardItem(3L));
        return rewardItems;
    }

    private FundingRewardItem createRewardItem(Long fundingItemId) {
        return FundingRewardItem.builder()
                .fundingItem(FundingItem.builder().id(fundingItemId).build())
                .build();
    }

    @Test
    @DisplayName("펀딩 창작자 조회")
    public void getFundingCreatorByFundingKey() {
        //given
        FundingCreator fundingCreator = getFundingCreator();
        FundingCreator savedFundingCreator = fundingPort.saveFundingCreator(fundingCreator);

        //when
        String fundingKey = savedFundingCreator.getFunding().getFundingKey();
        FundingCreator findFundingCreator = fundingPort.getFundingCreatorByFundingKey(fundingKey);

        //then
        assertNotNull(fundingKey);
        assertNotNull(findFundingCreator.getId());
        assertEquals(savedFundingCreator.getBusinessNumber(), findFundingCreator.getBusinessNumber());
    }

    @Test
    @DisplayName("펀딩 아이템 조회")
    public void getFundingItemByFundingKey() {
        //given
        FundingItem fundingItem = getFundingItem();
        FundingItem savedFundingItem = fundingPort.saveFundingItem(fundingItem);

        //when
        String fundingKey = savedFundingItem.getFunding().getFundingKey();
        FundingItem findFundingItem = fundingPort.getFundingItemByFundingKey(fundingKey);

        //then
        assertNull(fundingKey);
        assertNull(findFundingItem.getId());
        assertEquals(savedFundingItem.getItemName(), findFundingItem.getItemName());
    }

    @Test
    @DisplayName("펀딩 리워드 조회")
    public void getFundingRewardByFundingKey() {
        //given
        FundingReward fundingReward = getFundingReward();
        FundingReward savedFundingReward = fundingPort.saveFundingReward(fundingReward);

        //when
        String fundingKey = savedFundingReward.getFunding().getFundingKey();
        FundingReward findFundingReward = fundingPort.getFundingRewardByFundingKey(fundingKey);

        //then
        assertNull(savedFundingReward.getId());
        assertEquals(savedFundingReward.getRewardTitle(), findFundingReward.getRewardTitle());
        assertEquals(savedFundingReward.getAmount(), findFundingReward.getAmount());
    }
}