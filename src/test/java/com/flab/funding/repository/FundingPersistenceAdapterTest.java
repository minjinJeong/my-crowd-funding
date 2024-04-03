package com.flab.funding.repository;

import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.domain.model.*;
import com.flab.funding.infrastructure.adapters.output.persistence.FundingPersistenceAdapter;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FundingPersistenceAdapterTest {

    private final FundingPort fundingPort;

    private Member member;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    public FundingPersistenceAdapterTest(FundingRepository fundingRepository,
                                         FundingCreatorRepository fundingCreatorRepository,
                                         FundingItemRepository fundingItemRepository,
                                         FundingRewardRepository fundingRewardRepository,
                                         FundingRewardItemRepository fundingRewardItemRepository) {

        this.fundingPort = new FundingPersistenceAdapter(
                fundingRepository,
                fundingCreatorRepository,
                fundingItemRepository,
                fundingRewardRepository,
                fundingRewardItemRepository
        );
    }

    @BeforeEach
    public void setUp() {

        Member savedMember = Member.builder()
                .linkType(MemberLinkType.NONE)
                .email("Test@gmail.com")
                .userName("홍길순")
                .nickName("테스터")
                .phoneNumber("010-1111-2222")
                .gender(MemberGender.FEMALE)
                .birthday(LocalDate.of(1998, 1, 30))
                .password("")
                .build();

        MemberEntity memberEntity = entityManager.persist(MemberEntity.from(savedMember.activate()));
        member = memberEntity.toMember();
    }

    @Test
    @DisplayName("펀딩 등록")
    public void saveFunding() {
        //given
        Funding funding = getFunding().member(member).register();

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
        assertEquals(funding.getTags().size(), savedFunding.getTags().size());
        assertEquals(funding.getStartAt(), savedFunding.getStartAt());
        assertEquals(funding.getEndAt(), savedFunding.getEndAt());
    }

    private Funding getFunding() {
        return Funding.builder()
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
        Funding funding = getFunding().member(member).register();
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
        Funding funding = getFunding().member(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingCreator fundingCreator = getFundingCreator().funding(savedFunding);

        //when
        FundingCreator savedFundingCreator = fundingPort.saveFundingCreator(fundingCreator);

        //then
        assertNotNull(savedFundingCreator.getId());
        assertEquals(fundingCreator.getFunding().getFundingKey(), savedFundingCreator.getFunding().getFundingKey());
        assertEquals(fundingCreator.getIsValid(), savedFundingCreator.getIsValid());
        assertEquals(fundingCreator.getBusinessNumber(), savedFundingCreator.getBusinessNumber());
        assertEquals(fundingCreator.getRepresentative(), savedFundingCreator.getRepresentative());
        assertEquals(fundingCreator.getIntroduce(), savedFundingCreator.getIntroduce());
    }

    private FundingCreator getFundingCreator() {
        return FundingCreator.builder()
                .isValid(true)
                .businessNumber("12345678")
                .representative("홍길동")
                .introduce("안녕하세요, 개인 사업자 홍길동입니다.")
                .build();
    }

    @Test
    @DisplayName("펀딩 아이템 등록")
    public void saveFundingItem() {
        //given
        Funding funding = getFunding().member(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingItem fundingItem = getFundingItem().funding(savedFunding);

        //when
        FundingItem savedFundingItem = fundingPort.saveFundingItem(fundingItem);

        //then
        assertNotNull(savedFundingItem.getId());
        assertEquals(fundingItem.getFunding().getId(), savedFundingItem.getFunding().getId());
        assertEquals(fundingItem.getItemName(), savedFundingItem.getItemName());
        assertEquals(fundingItem.getOptionType(), savedFundingItem.getOptionType());
        assertEquals(fundingItem.getFundingItemOptions().size(), savedFundingItem.getFundingItemOptions().size());
    }

    private FundingItem getFundingItem() {
        return FundingItem.builder()
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
        Funding funding = getFunding().member(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingReward fundingReward = getFundingReward().funding(savedFunding);
        
        //when
        FundingReward savedFundingReward = fundingPort.saveFundingReward(fundingReward);

        //then
        assertNotNull(savedFundingReward.getId());
        assertEquals(fundingReward.getFunding().getId(), savedFundingReward.getFunding().getId());
        assertEquals(fundingReward.getIsDelivery(), savedFundingReward.getIsDelivery());
        assertEquals(fundingReward.getRewardTitle(), savedFundingReward.getRewardTitle());
        assertEquals(fundingReward.getAmount(), savedFundingReward.getAmount());
        assertEquals(fundingReward.getCountLimit(), savedFundingReward.getCountLimit());
        assertEquals(fundingReward.getPersonalLimit(), savedFundingReward.getPersonalLimit());
        assertEquals(fundingReward.getExpectDate(), savedFundingReward.getExpectDate());
    }

    private FundingReward getFundingReward() {
        return FundingReward.builder()
                .isDelivery(true)
                .rewardTitle("귀걸이 세트")
                .amount(BigInteger.valueOf(15000))
                .countLimit(10)
                .personalLimit(5)
                .expectDate(LocalDate.of(2024, 3, 31))
                .build();
    }

    @Test
    @DisplayName("펀딩 리워드-아이템 매핑")
    public void saveFundingRewardItems() {
        //given
        Funding funding = getFunding().member(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingItem fundingItem = getFundingItem().funding(savedFunding);
        FundingItem savedFundingItem1 = fundingPort.saveFundingItem(fundingItem);
        FundingItem savedFundingItem2 = fundingPort.saveFundingItem(fundingItem);
        FundingItem savedFundingItem3 = fundingPort.saveFundingItem(fundingItem);

        FundingReward fundingReward = getFundingReward().funding(savedFunding);
        FundingReward savedFundingReward = fundingPort.saveFundingReward(fundingReward);

        List<FundingRewardItem> fundingRewardItems = new ArrayList<>();
        fundingRewardItems.add(getFundingRewardItem(savedFunding, savedFundingReward, savedFundingItem1));
        fundingRewardItems.add(getFundingRewardItem(savedFunding, savedFundingReward, savedFundingItem2));
        fundingRewardItems.add(getFundingRewardItem(savedFunding, savedFundingReward, savedFundingItem3));

        //when
        List<FundingRewardItem> savedFundingRewardItems =
                fundingPort.saveFundingRewardItems(fundingRewardItems);

        //then
        assertEquals(fundingRewardItems.size(), savedFundingRewardItems.size());
    }

    private FundingRewardItem getFundingRewardItem(Funding savedFunding, FundingReward savedFundingReward, FundingItem savedFundingItem1) {
        return FundingRewardItem.builder()
                .funding(savedFunding)
                .fundingReward(savedFundingReward)
                .fundingItem(savedFundingItem1)
                .build();
    }

    @Test
    @DisplayName("펀딩 창작자 조회")
    public void getFundingCreatorByFundingKey() {
        //given
        Funding funding = getFunding().member(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingCreator fundingCreator = getFundingCreator().funding(savedFunding);
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
        Funding funding = getFunding().member(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingItem fundingItem = getFundingItem().funding(savedFunding);
        FundingItem savedFundingItem = fundingPort.saveFundingItem(fundingItem);

        //when
        String fundingKey = savedFundingItem.getFunding().getFundingKey();
        FundingItem findFundingItem = fundingPort.getFundingItemByFundingKey(fundingKey);

        //then
        assertNotNull(fundingKey);
        assertNotNull(findFundingItem.getId());
        assertEquals(savedFundingItem.getItemName(), findFundingItem.getItemName());
    }

    @Test
    @DisplayName("펀딩 리워드 조회")
    public void getFundingRewardByFundingKey() {
        //given
        Funding funding = getFunding().member(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingReward fundingReward = getFundingReward().funding(savedFunding);
        FundingReward savedFundingReward = fundingPort.saveFundingReward(fundingReward);

        //when
        String fundingKey = savedFundingReward.getFunding().getFundingKey();
        FundingReward findFundingReward = fundingPort.getFundingRewardByFundingKey(fundingKey);

        //then
        assertNotNull(savedFundingReward.getId());
        assertEquals(savedFundingReward.getRewardTitle(), findFundingReward.getRewardTitle());
        assertEquals(savedFundingReward.getAmount(), findFundingReward.getAmount());
    }
}