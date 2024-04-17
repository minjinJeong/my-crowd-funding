package com.flab.funding.repository;

import com.flab.funding.application.ports.output.SupportPort;
import com.flab.funding.domain.model.*;
import com.flab.funding.infrastructure.adapters.output.persistence.SupportPersistenceAdapter;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.*;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.SupportDeliveryRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.SupportPaymentRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.SupportRepository;
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
public class SupportPersistenceAdapterTest {

    private final SupportPort supportPort;

    private Member member;

    private Funding funding;

    private FundingReward reward;

    private MemberDeliveryAddress memberDeliveryAddress;

    private MemberPaymentMethod memberPaymentMethod;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    public SupportPersistenceAdapterTest(SupportRepository supportRepository,
                                         SupportDeliveryRepository supportDeliveryRepository,
                                         SupportPaymentRepository supportPaymentRepository) {

        this.supportPort = new SupportPersistenceAdapter(supportRepository, supportDeliveryRepository, supportPaymentRepository);
    }

    @BeforeEach
    public void setUp() {

        member = saveMember().toMember();

        funding = saveFunding().toFunding();

        reward = saveReward().toFundingReward();

        memberDeliveryAddress = saveDeliveryAddress().toDeliveryAddress();

        memberPaymentMethod = savePaymentMethod().toPaymentMethod();

    }

    private MemberEntity saveMember() {
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

        return entityManager.persist(MemberEntity.from(
                savedMember.activate())
        );
    }

    private FundingEntity saveFunding() {
        Funding savedFunding = Funding.builder()
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

        return entityManager.persist(
                FundingEntity.from(savedFunding.with(member).register())
        );
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

    private FundingRewardEntity saveReward() {
        FundingReward fundingReward = FundingReward.builder()
                .funding(funding)
                .isDelivery(true)
                .rewardTitle("귀걸이 세트")
                .amount(BigInteger.valueOf(15000))
                .countLimit(10)
                .personalLimit(5)
                .expectDate(LocalDate.of(2024, 3, 31))
                .build();

        return entityManager.persist(
                FundingRewardEntity.from(fundingReward)
        );
    }

    private MemberDeliveryAddressEntity saveDeliveryAddress() {
        MemberDeliveryAddress savedMemberDeliveryAddress = MemberDeliveryAddress.builder()
                .member(member)
                .isDefault(true)
                .zipCode("01234")
                .address("서울특별시 강서구")
                .addressDetail("OO 아파트 xxx동 xxxx호")
                .recipientName("홍길동")
                .recipientPhone("010-1111-2222")
                .build();

        return entityManager.persist(
                MemberDeliveryAddressEntity.from(savedMemberDeliveryAddress.register())
        );
    }

    private MemberPaymentMethodEntity savePaymentMethod() {
        MemberPaymentMethod savedMemberPaymentMethod = MemberPaymentMethod.builder()
                .isDefault(true)
                .paymentNumber("3565 43")
                .build();

        return entityManager.persist(
                MemberPaymentMethodEntity.from(savedMemberPaymentMethod.with(member).register())
        );
    }

    @Test
    @DisplayName("후원 등록")
    public void saveSupport() {
        //given
        Support support = getSupport().register();

        //when
        Support savedSupport = supportPort.saveSupport(support);

        //then
        assertNotNull(savedSupport.getId());
        assertNotNull(savedSupport.getSupportKey());
        assertEquals(support.getMember().getId(), savedSupport.getMember().getId());
        assertEquals(support.getFunding().getId(), savedSupport.getFunding().getId());
        assertEquals(support.getReward().getId(), savedSupport.getReward().getId());
        assertEquals(SupportStatus.RESERVATION, savedSupport.getStatus());
        assertEquals(support.getSupportDelivery().getMemberDeliveryAddress().getId(), savedSupport.getSupportDelivery().getMemberDeliveryAddress().getId());
        assertEquals(SupportDeliveryStatus.READY, savedSupport.getSupportDelivery().getStatus());
        assertEquals(support.getSupportPayment().getMemberPaymentMethod().getId(), savedSupport.getSupportPayment().getMemberPaymentMethod().getId());
        assertEquals(SupportPaymentStatus.READY, savedSupport.getSupportPayment().getStatus());
    }
    private Support getSupport() {
        return Support.builder()
                .member(member)
                .funding(funding)
                .reward(reward)
                .supportDelivery(getSupportDelivery())
                .supportPayment(getSupportPayment())
                .build();
    }

    private SupportDelivery getSupportDelivery() {
        return SupportDelivery.builder()
                .memberDeliveryAddress(memberDeliveryAddress)
                .status(SupportDeliveryStatus.READY)
                .build();
    }

    private SupportPayment getSupportPayment() {
        return SupportPayment.builder()
                .memberPaymentMethod(memberPaymentMethod)
                .status(SupportPaymentStatus.READY)
                .build();
    }

    @Test
    @DisplayName("후원 조회")
    public void getSupportBySupportKey() {
        //given
        Support support = getSupport().register();
        Support savedSupport = supportPort.saveSupport(support);

        //when
        String supportKey = savedSupport.getSupportKey();
        Support findSupport = supportPort.getSupportBySupportKey(supportKey);

        //then
        assertNotNull(savedSupport.getId());
        assertEquals(savedSupport.getSupportKey(), findSupport.getSupportKey());
    }

    @Test
    @DisplayName("후원 배송정보 등록")
    public void saveSupportDelivery() {
        //given
        Support support = Support.builder()
                .member(member)
                .funding(funding)
                .reward(reward)
                .build()
                .register();

        Support savedSupport = supportPort.saveSupport(support);

        SupportDelivery supportDelivery = getSupportDelivery().with(savedSupport);

        //when
        SupportDelivery savedSupportDelivery = supportPort.saveSupportDelivery(supportDelivery);

        //then
        assertNotNull(savedSupportDelivery.getId());
        assertEquals(savedSupport.getSupportKey(), savedSupportDelivery.getSupport().getSupportKey());
    }

    @Test
    @DisplayName("후원 배송정보 조회")
    public void getSupportDeliveryBySupportKey() {
        //given
        Support support = Support.builder()
                .member(member)
                .funding(funding)
                .reward(reward)
                .build()
                .register();

        Support savedSupport = supportPort.saveSupport(support);

        SupportDelivery supportDelivery = getSupportDelivery().with(savedSupport);
        SupportDelivery savedSupportDelivery = supportPort.saveSupportDelivery(supportDelivery);

        //when
        String supportKey = savedSupport.getSupportKey();
        SupportDelivery findSupportDelivery = supportPort.getSupportDeliveryBySupportKey(supportKey);

        //then
        assertNotNull(savedSupportDelivery.getId());
        assertEquals(savedSupportDelivery.getId(), findSupportDelivery.getId());
    }

    @Test
    @DisplayName("후원 결제수단 등록")
    public void saveSupportPayment() {
        //given
        Support support = Support.builder()
                .member(member)
                .funding(funding)
                .reward(reward)
                .build()
                .register();

        Support savedSupport = supportPort.saveSupport(support);

        SupportPayment supportPayment = getSupportPayment().with(savedSupport);

        //when
        SupportPayment savedSupportPayment = supportPort.saveSupportPayment(supportPayment);

        //then
        assertNotNull(savedSupportPayment.getId());
        assertEquals(savedSupport.getSupportKey(), savedSupportPayment.getSupport().getSupportKey());
    }

}