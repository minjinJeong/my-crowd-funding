package com.flab.funding.repository;

import com.flab.funding.application.ports.output.SupportPort;
import com.flab.funding.domain.model.*;
import com.flab.funding.infrastructure.adapters.output.persistence.SupportPersistenceAdapter;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.SupportDeliveryRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.SupportRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SupportPersistenceAdapterTest {

    private final SupportPort supportPort;

    @Autowired
    public SupportPersistenceAdapterTest(SupportRepository supportRepository,
                                         SupportDeliveryRepository supportDeliveryRepository) {

        this.supportPort = new SupportPersistenceAdapter(supportRepository, supportDeliveryRepository);
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
        assertEquals(support.getFunding(), savedSupport.getFunding());
        assertEquals(support.getReward(), savedSupport.getReward());
        assertEquals(SupportStatus.RESERVATION, savedSupport.getStatus());
        assertEquals(support.getSupportDelivery(), savedSupport.getSupportDelivery());
        assertEquals(support.getSupportPayment(), savedSupport.getSupportPayment());
    }
    private Support getSupport() {
        return Support.builder()
                .member(getMemberRequest())
                .funding(getFundingRequest())
                .reward(getRewardRequest())
                .supportDelivery(getSupportDelivery())
                .supportPayment(getSupportPayment())
                .build();
    }

    private Member getMemberRequest() {
        return Member.builder()
                .userKey("MM-0001")
                .build();
    }

    private Funding getFundingRequest() {
        return Funding.builder()
                .fundingKey("FF-0001")
                .build();
    }

    private FundingReward getRewardRequest() {
        return FundingReward.builder()
                .id(1L)
                .build();
    }

    private SupportPayment getSupportPayment() {
        return SupportPayment.builder()
                .paymentMethod(getPaymentMethodRequest())
                .build();
    }

    private PaymentMethod getPaymentMethodRequest() {
        return PaymentMethod.builder()
                .paymentMethodKey("PM-0001")
                .build();
    }

    private SupportDelivery getSupportDelivery() {
        return SupportDelivery.builder()
                .support(getSupportRequest())
                .deliveryAddress(getDeliveryAddressRequest())
                .build();
    }

    private Support getSupportRequest() {
        return Support.builder()
                .supportKey("SS-0001")
                .build();
    }

    private DeliveryAddress getDeliveryAddressRequest() {
        return DeliveryAddress.builder()
                .deliveryAddressKey("DA-0001")
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
        SupportDelivery supportDelivery = getSupportDelivery();

        //when
        SupportDelivery savedSupportDelivery = supportPort.saveSupportDelivery(supportDelivery);

        //then
        assertNotNull(savedSupportDelivery.getId());
    }

    @Test
    @DisplayName("후원 배송정보 조회")
    public void getSupportDeliveryBySupportKey() {
        //given
        SupportDelivery supportDelivery = getSupportDelivery();
        SupportDelivery savedSupportDelivery = supportPort.saveSupportDelivery(supportDelivery);

        //when
        String supportKey = savedSupportDelivery.getSupport().getSupportKey();
        SupportDelivery findSupportDelivery = supportPort.getSupportDeliveryBySupportKey(supportKey);

        //then
        assertNotNull(savedSupportDelivery.getId());
        assertEquals(savedSupportDelivery.getId(), findSupportDelivery.getId());
    }
}