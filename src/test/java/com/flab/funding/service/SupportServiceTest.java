package com.flab.funding.service;

import com.flab.funding.application.ports.output.SupportPort;
import com.flab.funding.domain.model.*;
import com.flab.funding.domain.service.SupportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Named;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class SupportServiceTest {

    @InjectMocks
    private SupportService supportService;

    @Mock
    private SupportPort supportPort;
    
    @Test
    @Named("후원 등록")
    public void registerSupport() {
        //given
        Support support = getSupport();

        given(supportPort.saveSupport(any(Support.class)))
                .willReturn(support);

        given(supportPort.getSupportBySupportKey(any()))
                .willReturn(support);

        //when
        Support savedSupport = supportService.registerSupport(support);
        Support findSupport = supportService.getSupportBySupportKey(savedSupport.getSupportKey());

        //then
        assertNotNull(savedSupport.getSupportKey());
        assertEquals(savedSupport.getId(), findSupport.getId());
        assertEquals(savedSupport.getUserKey(), findSupport.getUserKey());
        assertEquals(savedSupport.getFundingKey(), findSupport.getFundingKey());
        assertEquals(savedSupport.getRewardId(), findSupport.getRewardId());
        assertEquals(savedSupport.getSupportKey(), findSupport.getSupportKey());
        assertEquals(savedSupport.getStatus(), SupportStatus.RESERVATION);
        assertEquals(savedSupport.getStatus(), findSupport.getStatus());
        assertEquals(savedSupport.getSupportDelivery(), findSupport.getSupportDelivery());
        assertEquals(savedSupport.getSupportPayment(), findSupport.getSupportPayment());

    }

    private Support getSupport() {
        return Support.builder()
                .userKey("MM-0001")
                .fundingKey("FF-0001")
                .rewardId(1L)
                .supportDelivery(getSupportDelivery())
                .supportPayment(getSupportPayment())
                .build();
    }

    private SupportPayment getSupportPayment() {
        return SupportPayment.builder()
                .memberPaymentMethodKey("PM-0001")
                .build();
    }

    private SupportDelivery getSupportDelivery() {
        return SupportDelivery.builder()
                .supportKey("SS-0001")
                .memberDeliveryAddressKey("DA-0001")
                .build();
    }

    @Test
    @Named("배송 시작")
    public void shippedOut() {
        //given
        Support support = getSupport();
        SupportDelivery supportDelivery = getSupportDelivery();

        given(supportPort.getSupportDeliveryBySupportKey(any()))
                .willReturn(supportDelivery);

        given(supportPort.saveSupportDelivery(any(SupportDelivery.class)))
                .willReturn(supportDelivery);
        
        //when
        SupportDelivery savedSupportDelivery = supportService.shippedOut(support.getSupportKey());

        //then
        assertEquals(savedSupportDelivery.getStatus(), SupportDeliveryStatus.SHIPPED);
    }

    @Test
    @Named("배송 중")
    public void outForDelivery() {
        //given
        Support support = getSupport();
        SupportDelivery supportDelivery = getSupportDelivery();

        given(supportPort.getSupportDeliveryBySupportKey(any()))
                .willReturn(supportDelivery);

        given(supportPort.saveSupportDelivery(any(SupportDelivery.class)))
                .willReturn(supportDelivery);

        //when
        SupportDelivery savedSupportDelivery = supportService.outForDelivery(support.getSupportKey());

        //then
        assertEquals(savedSupportDelivery.getStatus(), SupportDeliveryStatus.IN_DELIVERY);
    }
    
    @Test
    @Named("배송 완료")
    public void deliveryComplete() {
        //given
        Support support = getSupport();
        SupportDelivery supportDelivery = getSupportDelivery();

        given(supportPort.getSupportDeliveryBySupportKey(any()))
                .willReturn(supportDelivery);

        given(supportPort.saveSupportDelivery(any(SupportDelivery.class)))
                .willReturn(supportDelivery);

        //when
        SupportDelivery savedSupportDelivery = supportService.deliveryComplete(support.getSupportKey());

        //then
        assertEquals(savedSupportDelivery.getStatus(), SupportDeliveryStatus.COMPLETE);
    }

}