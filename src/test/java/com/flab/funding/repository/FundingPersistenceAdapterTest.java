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

import java.util.ArrayList;
import java.util.List;

import static com.flab.funding.data.FundingTestData.*;
import static com.flab.funding.data.MemberTestData.getMember;
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

        Member savedMember = getMember();

        MemberEntity memberEntity = entityManager.persist(MemberEntity.from(savedMember.activate()));
        member = memberEntity.toMember();
    }

    @Test
    @DisplayName("펀딩 등록")
    public void saveFunding() {
        //given
        Funding funding = getFunding().with(member).register();

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

    @Test
    @DisplayName("펀딩 조회")
    public void getFundingByFundingKey() {
        //given
        Funding funding = getFunding().with(member).register();
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
        Funding funding = getFunding().with(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingCreator fundingCreator = getFundingCreator().with(savedFunding);

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

    @Test
    @DisplayName("펀딩 아이템 등록")
    public void saveFundingItem() {
        //given
        Funding funding = getFunding().with(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingItem fundingItem = getFundingItem().with(savedFunding);

        //when
        FundingItem savedFundingItem = fundingPort.saveFundingItem(fundingItem);

        //then
        assertNotNull(savedFundingItem.getId());
        assertEquals(fundingItem.getFunding().getId(), savedFundingItem.getFunding().getId());
        assertEquals(fundingItem.getItemName(), savedFundingItem.getItemName());
        assertEquals(fundingItem.getOptionType(), savedFundingItem.getOptionType());
        assertEquals(fundingItem.getFundingItemOptions().size(), savedFundingItem.getFundingItemOptions().size());
    }

    @Test
    @DisplayName("펀딩 리워드 등록")
    public void saveFundingReward() {
        //given
        Funding funding = getFunding().with(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingReward fundingReward = getFundingReward().with(savedFunding);
        
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

    @Test
    @DisplayName("펀딩 리워드-아이템 매핑")
    public void saveFundingRewardItems() {
        //given
        Funding funding = getFunding().with(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingItem fundingItem = getFundingItem().with(savedFunding);
        FundingItem savedFundingItem1 = fundingPort.saveFundingItem(fundingItem);
        FundingItem savedFundingItem2 = fundingPort.saveFundingItem(fundingItem);
        FundingItem savedFundingItem3 = fundingPort.saveFundingItem(fundingItem);

        FundingReward fundingReward = getFundingReward().with(savedFunding);
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

    @Test
    @DisplayName("펀딩 창작자 조회")
    public void getFundingCreatorByFundingKey() {
        //given
        Funding funding = getFunding().with(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingCreator fundingCreator = getFundingCreator().with(savedFunding);
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
        Funding funding = getFunding().with(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingItem fundingItem = getFundingItem().with(savedFunding);
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
        Funding funding = getFunding().with(member).register();
        Funding savedFunding = fundingPort.saveFunding(funding);

        FundingReward fundingReward = getFundingReward().with(savedFunding);
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