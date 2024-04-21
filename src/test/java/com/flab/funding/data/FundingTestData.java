package com.flab.funding.data;

import com.flab.funding.domain.model.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.flab.funding.data.MemberTestData.getMemberRequest;

public class FundingTestData {

    public static Funding getFunding() {
        return Funding.builder()
                .member(getMemberRequest())
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

    private static List<FundingTag> getTags() {

        List<FundingTag> fundingTags = new ArrayList<>();

        fundingTags.add(createTag("검색 키워드1"));
        fundingTags.add(createTag("검색 키워드2"));
        fundingTags.add(createTag("검색 키워드3"));

        return fundingTags;
    }

    private static FundingTag createTag(String tag) {
        return FundingTag.builder()
                .tag(tag)
                .build();
    }

    static Funding getFundingRequest() {
        return Funding.builder()
                .fundingKey("FF-0001")
                .build();
    }


    public static Funding getRealFunding() {
        return Funding.builder()
                .id(1L)
                .fundingKey("FF-0001")
                .member(getMemberRequest())
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

    public static FundingCreator getFundingCreator() {
        return FundingCreator.builder()
                .funding(getFundingRequest())
                .isValid(true)
                .businessNumber("12345678")
                .representative("홍길동")
                .introduce("안녕하세요, 개인 사업자 홍길동입니다.")
                .build();
    }

    public static FundingItem getFundingItem() {
        return FundingItem.builder()
                .funding(getFundingRequest())
                .itemName("은 귀걸이")
                .optionType(FundingItemOptionType.NONE)
                .fundingItemOptions(getFundingItemOptions())
                .build();
    }

    private static List<FundingItemOption> getFundingItemOptions() {

        List<FundingItemOption> itemOptions = new ArrayList<>();

        itemOptions.add(createItemOption("3mm"));
        itemOptions.add(createItemOption("5mm"));
        itemOptions.add(createItemOption("7mm"));
        itemOptions.add(createItemOption("9mm"));

        return itemOptions;
    }

    private static FundingItemOption createItemOption(String option) {
        return FundingItemOption.builder()
                .optionName(option)
                .build();
    }

    public static FundingReward getFundingReward() {
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

    private static List<FundingRewardItem> getFundingRewardItems() {

        List<FundingRewardItem> rewardItems = new ArrayList<>();

        rewardItems.add(createRewardItem(1L));
        rewardItems.add(createRewardItem(2L));
        rewardItems.add(createRewardItem(3L));

        return rewardItems;
    }

    private static FundingRewardItem createRewardItem(Long fundingItemId) {
        return FundingRewardItem.builder()
                .fundingItem(FundingItem.builder().id(fundingItemId).build())
                .build();
    }

    static FundingReward getRewardRequest() {
        return FundingReward.builder()
                .id(1L)
                .build();
    }

    public static FundingRewardItem getFundingRewardItem(Funding savedFunding,
                                                         FundingReward savedFundingReward,
                                                         FundingItem savedFundingItem) {

        return FundingRewardItem.builder()
                .funding(savedFunding)
                .fundingReward(savedFundingReward)
                .fundingItem(savedFundingItem)
                .build();
    }
}
