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

import static com.flab.funding.data.FundingTestData.getFunding;
import static com.flab.funding.data.FundingTestData.getFundingReward;
import static com.flab.funding.data.MemberTestData.*;
import static com.flab.funding.data.SupportTestData.*;
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
        Member savedMember = getMember().activate();

        return entityManager.persist(MemberEntity.from(savedMember));
    }

    private FundingEntity saveFunding() {
        Funding savedFunding = getFunding().with(member).register();

        return entityManager.persist(
                FundingEntity.from(savedFunding)
        );
    }

    private FundingRewardEntity saveReward() {
        FundingReward fundingReward = getFundingReward().with(funding).unmapping();

        return entityManager.persist(
                FundingRewardEntity.from(fundingReward)
        );
    }

    private MemberDeliveryAddressEntity saveDeliveryAddress() {
        MemberDeliveryAddress savedMemberDeliveryAddress =
                getDeliveryAddress().with(member).register();

        return entityManager.persist(
                MemberDeliveryAddressEntity.from(savedMemberDeliveryAddress)
        );
    }

    private MemberPaymentMethodEntity savePaymentMethod() {
        MemberPaymentMethod savedMemberPaymentMethod =
                getPaymentMethod().with(member).register();

        return entityManager.persist(
                MemberPaymentMethodEntity.from(savedMemberPaymentMethod)
        );
    }

    @Test
    @DisplayName("후원 등록")
    public void saveSupport() {
        //given
        Support support = getSupport()
                .with(member, funding, reward,
                        getSupportDelivery().with(memberDeliveryAddress),
                        getSupportPayment().with(memberPaymentMethod))
                .register();

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

    @Test
    @DisplayName("후원 조회")
    public void getSupportBySupportKey() {
        //given
        Support support = getSupport()
                .with(member, funding, reward,
                        getSupportDelivery().with(memberDeliveryAddress),
                        getSupportPayment().with(memberPaymentMethod))
                .register();

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
        Support support = getSupport()
                .with(member, funding, reward, null, null)
                .register();

        Support savedSupport = supportPort.saveSupport(support);

        SupportDelivery supportDelivery = getSupportDelivery().with(savedSupport, memberDeliveryAddress);

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
        Support support = getSupport()
                .with(member, funding, reward, null, null)
                .register();

        Support savedSupport = supportPort.saveSupport(support);

        SupportDelivery supportDelivery = getSupportDelivery().with(savedSupport, memberDeliveryAddress);
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
        Support support = getSupport()
                .with(member, funding, reward, null, null)
                .register();

        Support savedSupport = supportPort.saveSupport(support);

        SupportPayment supportPayment = getSupportPayment().with(savedSupport, memberPaymentMethod);

        //when
        SupportPayment savedSupportPayment = supportPort.saveSupportPayment(supportPayment);

        //then
        assertNotNull(savedSupportPayment.getId());
        assertEquals(savedSupport.getSupportKey(), savedSupportPayment.getSupport().getSupportKey());
    }

}