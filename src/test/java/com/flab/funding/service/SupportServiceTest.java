package com.flab.funding.service;

import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.application.ports.output.SupportPort;
import com.flab.funding.domain.model.*;
import com.flab.funding.domain.service.SupportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @Mock
    private MemberPort memberPort;

    @Mock
    private FundingPort fundingPort;
    
    @Test
    @DisplayName("후원 등록")
    public void registerSupport() {
        //given
        Support support = getSupport();

        given(memberPort.getMemberByUserKey(any()))
                .willReturn(getMemberRequest());

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(getFundingRequest());

        given(supportPort.saveSupport(any(Support.class)))
                .willReturn(support);

        given(supportPort.getSupportBySupportKey(any()))
                .willReturn(support);

        //when
        Support savedSupport = supportService.registerSupport(support);
        Support findSupport = supportService.getSupportBySupportKey(savedSupport.getSupportKey());

        //then
        assertNotNull(savedSupport.getSupportKey());
        assertEquals(savedSupport.getSupportKey(), findSupport.getSupportKey());
        assertEquals(SupportStatus.RESERVATION, savedSupport.getStatus());
        assertEquals(savedSupport.getStatus(), findSupport.getStatus());

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
                .memberPaymentMethod(getPaymentMethodRequest())
                .build();
    }

    private MemberPaymentMethod getPaymentMethodRequest() {
        return MemberPaymentMethod.builder()
                .paymentMethodKey("PM-0001")
                .build();
    }

    private SupportDelivery getSupportDelivery() {
        return SupportDelivery.builder()
                .support(getSupportRequest())
                .memberDeliveryAddress(getDeliveryAddressRequest())
                .build();
    }

    private Support getSupportRequest() {
        return Support.builder()
                .supportKey("SS-0001")
                .build();
    }

    private MemberDeliveryAddress getDeliveryAddressRequest() {
        return MemberDeliveryAddress.builder()
                .deliveryAddressKey("DA-0001")
                .build();
    }

    @Test
    @DisplayName("배송 시작")
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
        assertEquals(SupportDeliveryStatus.SHIPPED, savedSupportDelivery.getStatus());
    }

    @Test
    @DisplayName("배송 중")
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
        assertEquals(SupportDeliveryStatus.IN_DELIVERY, savedSupportDelivery.getStatus());
    }
    
    @Test
    @DisplayName("배송 완료")
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
        assertEquals(SupportDeliveryStatus.COMPLETE, savedSupportDelivery.getStatus());
    }

}