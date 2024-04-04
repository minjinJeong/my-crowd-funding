package com.flab.funding.service;

import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.*;
import com.flab.funding.domain.service.FundingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class FundingServiceTest {

    @InjectMocks
    private FundingService fundingService;

    @Mock
    private FundingPort fundingPort;

    @Mock
    private MemberPort memberPort;

    @Test
    @DisplayName("펀딩 등록")
    public void registerFunding() {
        //given
        Funding funding = getFunding();

        given(memberPort.getMemberByUserKey(eq(funding.getMember().getUserKey())))
                .willReturn(getMemberRequest());

        given(fundingPort.saveFunding(any(Funding.class)))
                .willReturn(funding);

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(funding);

        //when
        Funding savedFunding = fundingService.registerFunding(funding);
        Funding findFunding = fundingService.getFundingByFundingKey(savedFunding.getFundingKey());

        //then
        assertNotNull(savedFunding.getFundingKey());
        assertEquals(savedFunding.getId(), findFunding.getId());
        assertNotNull(savedFunding.getFundingKey());
        assertEquals(savedFunding.getFundingKey(), findFunding.getFundingKey());
        assertEquals(savedFunding.getMember().getUserKey(), findFunding.getMember().getUserKey());
        assertEquals(savedFunding.getIsAdult(), findFunding.getIsAdult());
        assertEquals(savedFunding.getPricePlan(), findFunding.getPricePlan());
        assertEquals(savedFunding.getCategory(), findFunding.getCategory());
        assertEquals(savedFunding.getExpectAmount(), findFunding.getExpectAmount());
        assertEquals(savedFunding.getStatus(), FundingStatus.REGISTER);
        assertEquals(savedFunding.getStatus(), findFunding.getStatus());
        assertEquals(savedFunding.getTitle(), findFunding.getTitle());
        assertEquals(savedFunding.getFundingDescription(), findFunding.getFundingDescription());
        assertEquals(savedFunding.getFundingIntroduce(), findFunding.getFundingIntroduce());
        assertEquals(savedFunding.getBudgetDescription(), findFunding.getBudgetDescription());
        assertEquals(savedFunding.getScheduleDescription(), findFunding.getScheduleDescription());
        assertEquals(savedFunding.getTeamDescription(), findFunding.getTeamDescription());
        assertEquals(savedFunding.getRewardDescription(), findFunding.getRewardDescription());
        assertIterableEquals(savedFunding.getTags(), findFunding.getTags());
        assertEquals(savedFunding.getStartAt(), findFunding.getStartAt());
        assertEquals(savedFunding.getEndAt(), findFunding.getEndAt());

    }

    private Funding getFunding() {
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

    private Member getMemberRequest() {
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
    @DisplayName("펀딩 창장자 등록")
    public void registerFundingCreator() {
        //given
        FundingCreator fundingCreator = getFundingCreator();

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(getFunding());

        given(fundingPort.saveFundingCreator(any(FundingCreator.class)))
                .willReturn(fundingCreator);

        given(fundingPort.getFundingCreatorByFundingKey(any()))
                .willReturn(fundingCreator);

        //when
        FundingCreator savedFundingCreator = fundingService.registerFundingCreator(fundingCreator);
        FundingCreator findFundingCreator = fundingService.getFundingCreatorByFundingKey(savedFundingCreator.getFunding().getFundingKey());

        //then
        assertEquals(savedFundingCreator.getId(), findFundingCreator.getId());
        assertEquals(savedFundingCreator.getFunding(), findFundingCreator.getFunding());
        assertEquals(savedFundingCreator.getIsValid(), findFundingCreator.getIsValid());
        assertEquals(savedFundingCreator.getBusinessNumber(), findFundingCreator.getBusinessNumber());
        assertEquals(savedFundingCreator.getRepresentative(), findFundingCreator.getRepresentative());
        assertEquals(savedFundingCreator.getIntroduce(), findFundingCreator.getIntroduce());
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
    @DisplayName("펀딩 아이템 생성")
    public void makeFundingItem() {
        //given
        FundingItem fundingItem = getFundingItem();

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(getFunding());

        given(fundingPort.saveFundingItem(any(FundingItem.class)))
                .willReturn(fundingItem);

        given(fundingPort.getFundingItemByFundingKey(any()))
                .willReturn(fundingItem);

        //when
        FundingItem savedFundingItem = fundingService.makeFundingItem(fundingItem);
        FundingItem findFundingItem = fundingService.getFundingItemByFundingKey(savedFundingItem.getFunding().getFundingKey());

        //then
        assertEquals(savedFundingItem.getId(), findFundingItem.getId());
        assertEquals(savedFundingItem.getFunding(), findFundingItem.getFunding());
        assertEquals(savedFundingItem.getItemName(), findFundingItem.getItemName());
        assertEquals(savedFundingItem.getOptionType(), findFundingItem.getOptionType());
        assertIterableEquals(savedFundingItem.getFundingItemOptions(), findFundingItem.getFundingItemOptions());
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
    @DisplayName("펀딩 리워드 생성")
    public void makeFundingReward() {
        //given
        FundingReward fundingReward = getFundingReward();

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(getFunding());

        given(fundingPort.saveFundingReward(any(FundingReward.class)))
                .willReturn(fundingReward);

        given(fundingPort.saveFundingRewardItems(any()))
                .willReturn(getFundingRewardItems());

        given(fundingPort.getFundingRewardByFundingKey(any()))
                .willReturn(fundingReward);

        //when
        FundingReward savedFundingReward = fundingService.makeFundingReward(fundingReward);
        FundingReward findFundingReward = fundingService.getFundingRewardByFundingKey(savedFundingReward.getFunding().getFundingKey());

        //then
        assertEquals(savedFundingReward.getId(), findFundingReward.getId());
        assertEquals(savedFundingReward.getFunding(), findFundingReward.getFunding());
        assertEquals(savedFundingReward.getIsDelivery(), findFundingReward.getIsDelivery());
        assertEquals(savedFundingReward.getRewardTitle(), findFundingReward.getRewardTitle());
        assertEquals(savedFundingReward.getAmount(), findFundingReward.getAmount());
        assertIterableEquals(savedFundingReward.getFundingRewardItems(), findFundingReward.getFundingRewardItems());
        assertEquals(savedFundingReward.getCountLimit(), findFundingReward.getCountLimit());
        assertEquals(savedFundingReward.getPersonalLimit(), findFundingReward.getPersonalLimit());
        assertEquals(savedFundingReward.getExpectDate(), findFundingReward.getExpectDate());

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
    @DisplayName("펀딩 심사대기")
    public void waitForReview() {
        //given
        Funding funding = getFunding().register();

        given(fundingPort.saveFunding(any(Funding.class)))
                .willReturn(funding);

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(funding);
        
        //when
        Funding savedFunding = fundingService.waitForFundingReview(funding.getFundingKey());

        //then
        assertEquals(savedFunding.getStatus(), FundingStatus.REVIEW_WAIT);
    }
    
    @Test
    @DisplayName("펀딩 심사완료")
    public void completeReview() {
        //given
        Funding funding = getFunding().register();

        given(fundingPort.saveFunding(any(Funding.class)))
                .willReturn(funding);

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(funding);

        //when
        Funding savedFunding = fundingService.completeFundingReview(funding.getFundingKey());

        //then
        assertEquals(savedFunding.getStatus(), FundingStatus.OPEN_WAIT);
    }
    
    @Test
    @DisplayName("펀딩 취소")
    public void cancel() {
        //given
        Funding funding = getFunding().register();

        given(fundingPort.saveFunding(any(Funding.class)))
                .willReturn(funding);

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(funding);

        //when
        Funding savedFunding = fundingService.cancelFunding(funding.getFundingKey());

        //then
        assertEquals(savedFunding.getStatus(), FundingStatus.CANCEL);
    }
}